/**
 * Leesdy
 * dx.leesdy.utils
 * Author: DxWang LDSegment.java
 * Created at:2017年3月5日
 */
package dx.leesdy.utils;

/**
 * 
 */
public class LDSegment extends LDInterval<Double> {

	/**
	 * @param start
	 * @param end
	 * @param classindex
	 */
	public LDSegment(Double start, Double end) {
		super(start, end);
		// TODO Auto-generated constructor stub
	}

	public LDSegment(String showName,
					 int channelNumber, 
					 Double startpos,
					 Double endpos,
					 Gender gender,
					 Band band,
					 Environment environment,
					 String speakerLabel) {
		super(startpos, endpos);
		this.showName = showName;
		this.channelNumber = channelNumber;
		this.gender = gender;
		this.band = band;
		this.environment = environment;
		this.speaker = speakerLabel;
	}
	
	
}
