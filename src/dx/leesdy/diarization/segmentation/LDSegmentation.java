package dx.leesdy.diarization.segmentation;

import java.util.ArrayList;

import dx.leesdy.diarization.LDDiarizationResultReaderT;
import dx.leesdy.diarization.parameter.LDParameter;
import dx.leesdy.diarization.parameter.SegmentationParameter;
import dx.leesdy.model.LDStatusCenter;
import dx.leesdy.model.WavWrapper;
import edu.cmu.sphinx.util.Complex;

import java.lang.Math;

public class LDSegmentation {

	private ArrayList<double[]> segments;
	
	private WavWrapper wav;
	private SegmentationParameter parameter;
	
	private LDDiarizationResultReaderT reader;
	
	public LDSegmentation (LDStatusCenter statusCenter, LDParameter parameter, LDDiarizationResultReaderT reader) {
		this.wav = statusCenter.getSource();
		this.parameter = parameter.getSegmentationParameter();
		this.segments = new ArrayList<double[]>();
		this.reader = reader;
	}
	
	public ArrayList<double[]> getSegments() {
		return segments;
	}
	
	public void initialize() {
		// int step
		int [] data_s = wav.getData()[0];
		
		double mu = 0.97;
		
		
		// pre-emphasize
		double [] data = new double[data_s.length];
		
		for (int i = 0; i < data.length; i++) {
			data[i] = data_s[i];
			data[i] = 1 - mu * (1 / data[i]);
		}
		
		// set step and length of each segment
		long samplingRate = wav.getSamplingRate();
		
		// parameter.step is in seconds.
		// 0.010 * 44100 = 441 samples
		int step = (int)(parameter.getStep() * samplingRate);
		reader.setSegStep(parameter.getStep());
		
		
		// parameter.length is in seconds.
		// 0.025 * 44100 = 1102.5 samples
		int length = (int)(parameter.getLength() * samplingRate);
		reader.setSegLen(parameter.getLength());
		
		// initilize hamming window
		double a = 0.46;
		double [] W = new double[length];
		for (int i = 0; i < length; i++) {
			W[i] = (1 - a) - a * Math.cos((2 * Math.PI * i)/(length - 1)); 
		}
		
		// apply window
		for (int i = 0; i < data.length; i += step) {
			double [] seg = new double[length];
			for (int j = i; j < i + length && j < data.length; j++) {
				seg[j-i] = data[j] * W[j-i];
			}
			segments.add(seg);
		}
		
	}
	
	
}
