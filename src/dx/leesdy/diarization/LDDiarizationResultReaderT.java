package dx.leesdy.diarization;

public class LDDiarizationResultReaderT {
	
	private boolean needUpdate;
	private double segStep;
	private double segLen;
	
	private double[] mfccEnergy; 
	
	private int bicWindowSize;
	private double[] bicValue;
	
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

	/**
	 * @return the bicValue
	 */
	public double[] getBicValue() {
		return bicValue;
	}

	/**
	 * @param bicValue the bicValue to set
	 */
	public void setBicValue(double[] bicValue) {
		this.bicValue = bicValue;
	}

	/**
	 * @return the bicWindowSize
	 */
	public int getBicWindowSize() {
		return bicWindowSize;
	}

	/**
	 * @param bicWindowSize the bicWindowSize to set
	 */
	public void setBicWindowSize(int bicWindowSize) {
		this.bicWindowSize = bicWindowSize;
	}
	
	
}
