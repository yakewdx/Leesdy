/**
 * Leesdy
 * dx.leesdy.utils
 * Author: DxWang LDInterval.java
 * Created at:2017年3月1日
 */
package dx.leesdy.utils;

/**
 * 
 */
public class LDInterval<typename> {
	
	public enum Gender {
		Male,
		Female,
		Unknown,
		NotSet
	}
	
	public enum Band {
		Telephone,
		Studio,
		NotSet
	}
	
	public enum Environment {
		Music,
		SpeechOnly,
		NotSet
	}
	
	/*
	 * start position
	 */
	protected typename startPos;
	
	/*
	 * end position
	 */
	protected typename endPos;
	
	/*
	 * class index
	 */
	protected String showName;
	protected int channelNumber;
	protected Gender gender; 
	protected Band band;
	protected Environment environment;
	protected String speaker;
	
	public String getShowName() {
		return showName;
	}
	
	public void setShowName(String name) {
		showName = name;
	}
	
	
	
	public typename getStartPos() {
		return startPos;
	}
	
	public void setStartPos(typename startpos) {
		startPos = startpos;
	}
	
	public typename getEndPos() {
		return endPos;
	}
	
	public void setEndPos(typename endpos) {
		endPos = endpos;
	}

	
	public LDInterval(typename start, typename end) {
		startPos = start;
		endPos = end;
	}

	/**
	 * @return the channelNumber
	 */
	public int getChannelNumber() {
		return channelNumber;
	}

	/**
	 * @param channelNumber the channelNumber to set
	 */
	public void setChannelNumber(int channelNumber) {
		this.channelNumber = channelNumber;
	}

	/**
	 * @return the gender
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}

	/**
	 * @return the band
	 */
	public Band getBand() {
		return band;
	}

	/**
	 * @param band the band to set
	 */
	public void setBand(Band band) {
		this.band = band;
	}

	/**
	 * @return the environment
	 */
	public Environment getEnvironment() {
		return environment;
	}

	/**
	 * @param environment the environment to set
	 */
	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	/**
	 * @return the speaker
	 */
	public String getSpeaker() {
		return speaker;
	}

	/**
	 * @param speaker the speaker to set
	 */
	public void setSpeaker(String speaker) {
		this.speaker = speaker;
	}
	
}
