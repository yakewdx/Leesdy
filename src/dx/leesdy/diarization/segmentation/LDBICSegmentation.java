/**
 * Leesdy
 * dx.leesdy.diarization.segmentation
 * Author: DxWang LDBICSegmentation.java
 * Created at:2017年4月26日
 */
package dx.leesdy.diarization.segmentation;

import java.util.ArrayList;

import JSci.maths.vectors.AbstractDoubleVector;
import JSci.maths.vectors.DoubleVector;
import dx.leesdy.diarization.LDDiarizationResultReaderT;
import dx.leesdy.diarization.datastructure.LDClusterSet;
import dx.leesdy.diarization.datastructure.LDMatrix;
import dx.leesdy.diarization.datastructure.LDVector;
import dx.leesdy.diarization.model.*;
import dx.leesdy.model.LDStatusCenter;

/**
 * 
 */
public class LDBICSegmentation {
	
	private LDStatusCenter statusCenter;
	
	private LDClusterSet result;
	
	private double[] bicvalue;
	private ArrayList<Integer> changePoints;
	
	private LDDiarizationResultReaderT reader;
	
	private int window_size = 200;
	private int window_growth = 200;
	private int base_w = 100;
	
	public LDBICSegmentation(LDStatusCenter statusCenter){
		this.result = new LDClusterSet();
		this.changePoints = new ArrayList<Integer>();
		this.statusCenter = statusCenter;
		registerInfo();
	}
	
	private void registerInfo() {
		reader = statusCenter.getReader_T();
		reader.setBicWindowSize(window_size);
	}
	
	public void segmentation(ArrayList<LDVector> frames) {
		
		if (frames.size() < window_size + 1) return;
		
		this.bicvalue = new double[frames.size() - 2 * window_size];
		//double[] k = new double[this.bicvalue.length];
		reader.setBicValue(bicvalue);
		reader.setChangePoints(changePoints);
		
		
		for (int i = window_size; i < frames.size() - window_size; i++) {
			ArrayList<LDVector> l = new ArrayList<LDVector> ();
			ArrayList<LDVector> r = new ArrayList<LDVector> ();
			for (int j = i - window_size; j < i; j++) {
				l.add(frames.get(j));
			}
			for (int j = i; j < i + window_size; j++) {
				r.add(frames.get(j));
			}
			
			Gaussian g1 = Gaussian.approximate(l);
			Gaussian g2 = Gaussian.approximate(r);
			Gaussian g0 = Gaussian.Merge(g1, g2);
			int dim = l.get(0).dimension();
			bicvalue[i-window_size] = calcBICValue(g0, g1, g2, l.size(), r.size(), dim);
			
			if (i > window_size + 4 && i < frames.size() - window_size - 4) {
				int j = i - 2 - window_size;
				if (bicvalue[j]- bicvalue[j-2] >0 &&
						bicvalue[j]- bicvalue[j-1] >0 &&
						bicvalue[j]- bicvalue[j+1] >0 &&
						bicvalue[j]- bicvalue[j+2] >0)
					this.changePoints.add(i);
			}
					
		}
		
		
//		int size = 20000;
//		for (int i = window_size; i < size - window_size; i++) {
//			
//		}
//		ArrayList<LDVector> l = new ArrayList<LDVector> ();
//		ArrayList<LDVector> r = new ArrayList<LDVector> ();
//		for (int j = 0; j < window_size; j++) {
//			l.add(frames.get(j));
//		}
//		for (int j = window_size; j < size; j++) {
//			r.add(frames.get(j));
//		}
//		
//		int dim = l.get(0).dimension();
//		int cc = 30;
//		for (int i = window_size; i < size - window_size; i += cc) {
//			
//			Gaussian g1 = Gaussian.approximate(l);
//			Gaussian g2 = Gaussian.approximate(r);
//			Gaussian g0 = Gaussian.Merge(g1, g2);
//			bicvalue[i-window_size] = calcBICValue(g0, g1, g2, l.size(), r.size(), dim);
//			for (int j = 0; j < cc; j++){
//				l.add(frames.get(j+i));
//				r.remove(0);
//			}
//		}
		
		
		// winGrow:
		
		// initial set:
//		int beg = base_w, end = window_size;
//		int changePoint = -1;
//		while (end < frames.size()) {
//			changePoint = calcBIC(beg, end, frames);
//			if (changePoint == -1 && end - beg < 5 * window_growth) {
//				end += window_growth;
//			} else {
//				if (changePoint == -1){
//					this.changePoints.add(changePoint);
//					beg = end - base_w;
//					end = beg + window_size;
//				}
//				else {
//					beg = changePoint + 1;
//					end = beg + window_size;
//				}
//			}
//		}
		
	}
	
	private int calcBIC(int begin, int end, ArrayList<LDVector> frames) {
		
		ArrayList<LDVector> l = new ArrayList<LDVector> ();
		ArrayList<LDVector> r = new ArrayList<LDVector> ();
		for (int i = begin - base_w; i < begin; i++) l.add(frames.get(i));
		for (int i = begin; i < end + base_w; i++) r.add(frames.get(i));

		double max = -1;
		int maxi = 0;
		double bic;
		// change point
		for (int i = begin ; i < end ; i++) {
			Gaussian g1 = Gaussian.approximate(l);
			Gaussian g2 = Gaussian.approximate(r);
			Gaussian g0 = Gaussian.Merge(g1, g2);
			bic = calcBICValue(g0, g1, g2, l.size(), r.size(), l.get(0).dimension());
			if (max < bic) {
				max = bic;
				maxi = i;
			}
			bicvalue[i - window_size] = bic;
			l.add(frames.get(i));
			r.remove(0);
		}
		if (max > 0) return maxi;
		else return -1;
		
	}
	
	
	// g0:H0 g1,g2:H1 N1,N2 are sizes, d is dimension
	// H0 : l and r are from 1 speaker
	// H1 : l and r are from 2 speakers
	private double calcBICValue(Gaussian g0, Gaussian g1, Gaussian g2, int N1, int N2, double d) {

		double N = N1 + N2;
		double R = N*LDMatrix.logDet(g0.getCovariance()) - N1 * LDMatrix.logDet(g1.getCovariance()) - N2 * LDMatrix.logDet(g2.getCovariance());
		double lambda = 1.0;
		double p = 0.5 * (d + 0.5 * d * (d+1)) * Math.log(N);
		double bic = R / 2 - lambda * p;
		return bic;
	}
	

	/**
	 * @return the changePoints
	 */
	public ArrayList<Integer> getChangePoints() {
		return changePoints;
	}


}
