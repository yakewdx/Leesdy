/**
 * Leesdy
 * dx.leesdy.utils
 * Author: DxWang LDDiarizationResultReader.java
 * Created at:2017年3月5日
 */
package dx.leesdy.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import dx.leesdy.utils.LDSegment;
/**
 * 
 */
public class LDDiarizationResultReader {

	private String segmentFile;

	private ArrayList<LDSegment> list;
	
	public LDDiarizationResultReader(String filename) {
		this.segmentFile = filename;
		this.list = new ArrayList<LDSegment>();
		this.readSegmentFromFile();
	}
	
	
	/**
	 * @return result of the read process
	 * 
	 * 1: error in reading
	 * 0: succeeded
	 * 
	 */
	public int readSegmentFromFile() {
		
		int lineCount = 1;
		try {
			for (String line : Files.readAllLines(Paths.get(segmentFile))) {
				if (lineCount++ == 1) {
					continue;
				}
				String [] components = line.split("\\s");
				
				String showName = components[0];
				int channelNumber = Integer.valueOf(components[1]);
				Double startPos = Double.valueOf(components[2]) / 100;
				Double length = Double.valueOf(components[3]) / 100;
				
				LDSegment.Gender gender = LDSegment.Gender.NotSet;
				switch (components[4]) {
				case "F":
					gender = LDSegment.Gender.Female;
					break;
				case "M":
					gender = LDSegment.Gender.Male;
					break;
				case "U":
					gender = LDSegment.Gender.Unknown;
					break;
				default:
					break;
				}
				
				LDSegment.Band band = LDSegment.Band.NotSet;
				switch (components[5]) {
				case "T":
					band = LDSegment.Band.Telephone;
					break;
				case "S":
					band = LDSegment.Band.Studio;
					break;
				default:
					break;
				}
				
				// Unknown property yet.
				LDSegment.Environment environment = LDSegment.Environment.SpeechOnly;
				
				String speakerLabel = components[7];
				
				this.list.add(new LDSegment(showName,
							  			    channelNumber, 
							  			    startPos,
							  			    startPos + length,
							  			    gender,
							  			    band,
							  			    environment,
							  			    speakerLabel));
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * @return the segmentFile
	 */
	public String getSegmentFile() {
		return segmentFile;
	}

	/**
	 * @param segmentFile the segmentFile to set
	 */
	public void setSegmentFile(String segmentFile) {
		this.segmentFile = segmentFile;
	}
	/**
	 * @return the list
	 */
	public ArrayList<LDSegment> getList() {
		return list;
	}
	/**
	 * @param list the list to set
	 */
	public void setList(ArrayList<LDSegment> list) {
		this.list = list;
	}
}
