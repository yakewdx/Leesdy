package dx.leesdy.diarization;

import java.util.ArrayList;

public class LDDiarizationResultReaderT {
	
	private boolean needUpdate;
	private double segStep;
	private double segLen;
	
	private double[] mfccEnergy; 
	
	private int bicWindowSize;
	private double[] bicValue;
	private ArrayList<Integer> changePoints;
	
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

	/**
	 * @return the changePoints
	 */
	public ArrayList<Integer> getChangePoints() {
		return changePoints;
	}

	/**
	 * @param changePoints the changePoints to set
	 */
	public void setChangePoints(ArrayList<Integer> changePoints) {
		this.changePoints = changePoints;
	}
	
	
}
