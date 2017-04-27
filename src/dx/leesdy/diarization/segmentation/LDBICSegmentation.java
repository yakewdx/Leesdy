/**
 * Leesdy
 * dx.leesdy.diarization.segmentation
 * Author: DxWang LDBICSegmentation.java
 * Created at:2017年4月26日
 */
package dx.leesdy.diarization.segmentation;

import java.util.ArrayList;

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
	
	private LDDiarizationResultReaderT reader;
	
	private int window_size = 300;
	
	public LDBICSegmentation(LDStatusCenter statusCenter){
		this.result = new LDClusterSet();
		this.statusCenter = statusCenter;
		registerInfo();
	}
	
	private void registerInfo() {
		reader = statusCenter.getReader_T();
		reader.setBicWindowSize(window_size);
	}
	
	public void segmentation(ArrayList<LDVector> frames) {
		
		this.bicvalue = new double[frames.size() - 2 * window_size];
		reader.setBicValue(bicvalue);
		for (int i = window_size; i < frames.size() - window_size; i++) {
			ArrayList<LDVector> l = new ArrayList<LDVector> ();
			ArrayList<LDVector> r = new ArrayList<LDVector> ();
			for (int j = i - window_size; j < i; j++) {
				l.add(frames.get(j));
			}
			for (int j = i; j < i + window_size; j++) {
				r.add(frames.get(j));
			}
			bicvalue[i-window_size] = calcBICValue(l,r);
		}
		
		
	}
	
	private double calcBICValue(ArrayList<LDVector> l, ArrayList<LDVector> r) {
		
		// H0 : l and r are from 1 speaker:
		ArrayList<LDVector>h = new ArrayList<LDVector>();
		h.addAll(l);
		h.addAll(r);
		Gaussian g0 = Gaussian.approximate(h);
		
		// H1 : l and r are from 2 speakers:
		Gaussian g1 = Gaussian.approximate(l);
		Gaussian g2 = Gaussian.approximate(l);
		
		double N,N1,N2;
		N = h.size();
		N1 = l.size();
		N2 = r.size();
		
		double R = N*LDMatrix.logDet(g0.getCovariance()) - N1 * LDMatrix.logDet(g1.getCovariance()) - N2 * LDMatrix.logDet(g2.getCovariance());
		double d = h.get(0).dimension();
		double lambda = 2.5;
		double p = 0.5 * (d + 0.5 * d * (d+1)) * Math.log(N);
		double bic = R - lambda * p;
		return bic;
	}
	
}
