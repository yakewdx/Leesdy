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
import java.util.Comparator;

import dx.leesdy.utils.LDSegment;
/**
 * 
 */
public class LDDiarizationResultReader {

	private String segmentFile;

	private ArrayList<LDSegment> list;
	
	private boolean sorted;
	
	public LDDiarizationResultReader(String filename) {
		this.segmentFile = filename;
		this.list = new ArrayList<LDSegment>();
		this.readSegmentFromFile();
		this.sorted = false;
	}
	
	public void sortList() {
		if (!this.sorted) {
			list.sort(new Comparator<LDSegment> () {
	
				@Override
				public int compare(LDSegment o1, LDSegment o2) {
					// TODO Auto-generated method stub
					return o1.getStartPos() < o2.getStartPos() ? -1 : 1;
				}
				
			});
		}
	}
	
	public boolean isSorted() {
		return this.sorted;
	}
	
	
	/**
	 * @return result of the read process
	 * 
	 * 1: error in reading
	 * 0: succeeded
	 * 
	 */
	public int readSegmentFromFile() {
		
		int lineCount = 0;
		try {
			for (String line : Files.readAllLines(Paths.get(segmentFile))) {
				lineCount++;
				CharSequence sequence = line.subSequence(0, 2);
				
				if (sequence.toString().equals(";;")) {
					// comments
				} else {
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
			}
			
			this.sortList();
				
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public LDSegment searchIntervalByTime(double time) {
		
		// Sort the list first
		// if it has already been sorted,
		// the procedure will be skipped in the function.
		this.sortList();
		
		// BiSearch 
		int begin = 0, end = this.list.size();
		int mid = 0;
		while (begin < end) {
			mid = (begin + end) / 2;
			if (this.list.get(mid).getStartPos() < time) {
				begin = mid + 1;
			} else {
				end = mid;
			}
			
		}
		return this.list.get(begin-1);
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
