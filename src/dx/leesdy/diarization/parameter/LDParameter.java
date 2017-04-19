package dx.leesdy.diarization.parameter;

public class LDParameter {
	
	public LDParameter() {
		
		this.segmentationParameter = new SegmentationParameter(10, 25);
	}
	
	private SegmentationParameter segmentationParameter;

	public SegmentationParameter getSegmentationParameter() {
		return segmentationParameter;
	}

	public void setSegmentationParameter(SegmentationParameter segmentationParameter) {
		this.segmentationParameter = segmentationParameter;
	}
	
	
}
