package dx.leesdy.diarization.audiofeature;

import java.util.ArrayList;

import dx.leesdy.diarization.LDDiarizationResultReaderT;
import dx.leesdy.diarization.datastructure.LDComplex;
import dx.leesdy.diarization.segmentation.LDTransform;
import dx.leesdy.model.LDStatusCenter;
import dx.leesdy.utils.LDDebug;

public class LDMFCCFeature {
	
	// mfcc + delta and delta-delta.
	private ArrayList<double[]> feature;
	
	// the initial mfcc
	private ArrayList<double[]> mfcc;
	private double[] energy;
	
	private long samplingRate;
	
	private double eps = 1e-6;
	
	private double lowerFreq;
	private double upperFreq;
	
	// default : 14
	private int mfccCount;
	
	private int filterBankCount;
	
	private LDDiarizationResultReaderT reader;
	
	public LDMFCCFeature(ArrayList<double[]> segments, LDStatusCenter statusCenter, LDDiarizationResultReaderT reader) {
		
		this.samplingRate = statusCenter.getSource().getSamplingRate();
		this.lowerFreq = 300;
		this.upperFreq = samplingRate / 2;
		this.filterBankCount = 26;
		this.mfccCount = 14;
		this.mfcc = new ArrayList<double[]> ();
		this.reader = reader;
		energy = new double[segments.size() + 1];
		reader.setMfccEnergy(energy);
		calc(segments);
	}
	
	
	
	private void calc(ArrayList<double[]> segments) {
		int cc = 1;
		for (double[] seg : segments) {
			double N = (double) seg.length;
			if (cc == 199) {
				LDDebug.print(".199");
			}
			LDComplex[] Xa = new LDComplex[(int) N];
			for (int i = 0 ; i< N; i++) {
				Xa[i] = new LDComplex(seg[i], 0);
			}
			LDComplex[] X = LDTransform.half_fft(Xa);
			double [] powSpec = LDComplex.powerSpectrum(X);
			
			// for test
			
			double _energy = 0;
			for (int i = 0; i < powSpec.length; i++) {
				_energy += powSpec[i];
			}
			this.energy[cc] = _energy;
			//LDDebug.print("E: " + _energy + " LogE: " + Math.log(_energy));
			
			double [] s = applyMelFilterBank(powSpec);
			double [] C = LDTransform.dct(s, mfccCount);
			//LDDebug.print("LDMFCCFeature : Calculating MFCC " + cc+ " / " + segments.size() + ".");
			mfcc.add(C);
			cc++;
		}
		
		LDDebug.print("errmsg");
		// delta of mfcc
		ArrayList<double[]> delta = calcDelta(mfcc);
		ArrayList<double[]> delta_delta = calcDelta(delta);
		
		this.feature = this.concat(mfcc, delta, delta_delta);
//		for (int i = 0; i < this.feature.size(); i++) {
//			double[] j = this.feature.get(i);
//			for (int k = 0; k < j.length; k++) {
//				System.out.printf("%f ", j[k]);
//			}
//			System.out.println();
//		}
		
	}
	
	private double[] applyMelFilterBank(double[] powerSpectrum) {
		// generate mel filter bank
		double lowerMel = this.freq2mel(lowerFreq);
		double upperMel = this.freq2mel(upperFreq);
		double f[] = new double[this.filterBankCount+2];
		int c = this.filterBankCount + 1;
		for (int i = 0; i <= c; i++) {
			f[i] = Math.floor(((powerSpectrum.length-1)*2-1)  * 
					this.mel2freq(lowerMel + (upperMel - lowerMel) / c * i) / 
					samplingRate);
		}
		double res[] = new double[this.filterBankCount];
		
		for (int m = 1; m < this.filterBankCount + 1; m++) {
			for (int k = 0; k < powerSpectrum.length; k++) {
				if (k >= f[m-1] && k <= f[m])  {
					double t = (k - f[m-1])/(f[m] - f[m-1]);
					res[m-1] += powerSpectrum[k] * t;
				}
				else
				if (k >= f[m] && k <= f[m+1]) {
					double t = (f[m+1]-k) / (f[m+1] - f[m]);
					res[m-1] += powerSpectrum[k] * t;
				}
			}
			res[m-1] = Math.log(res[m-1] + eps);
		}
		
		return res;
	}
	
	private ArrayList<double[]> concat(ArrayList<double[]> a, ArrayList<double[]> b, ArrayList<double[]> c) {
		ArrayList<double[]> res= new ArrayList<double[]>();
		for (int i = 0; i < a.size(); i++) {
			double[] _a, _b, _c;
			_a = a.get(i);
			_b = b.get(i);
			_c = c.get(i);
			
			int al = _a.length;
			int bl = _b.length;
			int cl = _c.length;
			
			double[] d = new double[al+bl+cl];
			System.arraycopy(_a, 0, d, 0, al);
			System.arraycopy(_b, 0, d, al, bl);
			System.arraycopy(_c, 0, d, al+bl, cl);
			res.add(d);
		}
		return res;
	}
	
	private double[] add(double[] a, double [] b) {
		// assume a and b have same size.
		double[] res = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			res[i] = a[i] + b[i];
		}
		return res;
	}
	
	private double[] sub(double[] a, double [] b) {
		// assume a and b have same size.
		double[] res = new double[a.length];
		for (int i = 0; i < a.length; i++) {
			res[i] = a[i] - b[i];
		}
		return res;
	}
	
	private double[] divide(double[] vec, int scalar) {
		double[] res = new double[vec.length];
		for (int i = 0; i < vec.length; i++) {
			res[i] = vec[i] / scalar;
		}
		return res;
	}
	
	private ArrayList<double[]> calcDelta(ArrayList<double []> a) {
		int length = a.size();
		ArrayList<double[]> res = new ArrayList<double[]>();
		for (int i = 0; i < length; i++) {
			int index1 = i - 1;
			if (index1 < 0) index1 = 0;
			
			int index2 = i + 1;
			if (index2 >= length) index2 = length - 1;
			double [] delta = divide(sub(a.get(index2), a.get(index1)), 2);
			res.add(delta);
		}
		return res;
	}
	
	private double freq2mel(double freq) {
		return 1125 * Math.log(1 + freq / 700);
	}
	
	private double mel2freq(double mel) {
		return 700 * (Math.exp(mel / 1125) - 1);
	}
	
}
