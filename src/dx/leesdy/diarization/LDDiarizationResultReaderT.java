package dx.leesdy.diarization;

public class LDDiarizationResultReaderT {
	
	private boolean needUpdate;
	private double segStep;
	private double segLen;
	
	private double[] mfccEnergy; 
	
	public LDDiarizationResultReaderT() {}

	public double[] getMfccEnergy() {
		return mfccEnergy;
	}

	public void setMfccEnergy(double[] mfccEnergy) {
		this.mfccEnergy = mfccEnergy;
	}

	public double getSegLen() {
		return segLen;
	}

	public void setSegLen(double segLen) {
		this.segLen = segLen;
	}

	public double getSegStep() {
		return segStep;
	}

	public void setSegStep(double segStep) {
		this.segStep = segStep;
	}

	public boolean isNeedUpdate() {
		return needUpdate;
	}

	public void setNeedUpdate(boolean needUpdate) {
		this.needUpdate = needUpdate;
	}
	
	
}
