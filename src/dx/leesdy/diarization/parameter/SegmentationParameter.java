package dx.leesdy.diarization.parameter;

public class SegmentationParameter {
	
	// in seconds
	private double step;
	
	// in seconds
	private double length;
	
	public SegmentationParameter(double _step, double _length) {
		
		// _step in milliseconds
		this.step = _step / 1000;
		
		// _length in milliseconds
		this.length = _length / 1000;
	}
	
	public double getStep() {
		return step;
	}
	public void setStep(double step) {
		this.step = step;
	}
	public double getLength() {
		return length;
	}
	public void setLength(double length) {
		this.length = length;
	}
}
