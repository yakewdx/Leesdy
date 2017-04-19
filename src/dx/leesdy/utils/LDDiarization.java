/**
 * Leesdy
 * dx.leesdy.utils
 * Author: DxWang LDDiarization.java
 * Created at:2017年3月4日
 */
package dx.leesdy.utils;

import dx.leesdy.diarization.*;
/**
 * 
 */
public class LDDiarization{

	private String inputFile;
	
	private String outputFile;
	
	public LDDiarization(String input, String output) {
		inputFile = input;
		outputFile = output;
	}
	
	public void diarization() {
		String [] args = new String[4];
		args[0] = "--fInputMask=" + inputFile;
		args[1] = "--sOutputMask=" + outputFile;
		args[2] = "--doCEClustering";
		args[3] = "showName";
		LDDiarizationBase.main(args);
	}



	/**
	 * @return the inputFile
	 */
	public String getInputFile() {
		return inputFile;
	}



	/**
	 * @param inputFile the inputFile to set
	 */
	public void setInputFile(String inputFile) {
		this.inputFile = inputFile;
	}



	/**
	 * @return the outputFile
	 */
	public String getOutputFile() {
		return outputFile;
	}



	/**
	 * @param outputFile the outputFile to set
	 */
	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}
}
