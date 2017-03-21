package dx.leesdy.model;

import dx.leesdy.utils.LDDiarizationResultReader;
import javafx.scene.media.MediaPlayer;

public class LDStatusCenter {

	/**
	 *  Store source file information
	 */
	private WavWrapper source;
	
	/**
	 *  Store media player information
	 */
	private MediaPlayer mediaPlayer;

	/**
	 *  Store diarization result
	 */
	private LDDiarizationResultReader reader;
	
	
	/**
	 *  Store device state
	 */
	private DeviceState deviceState;
	
	
	public String getSouceFile() {
		return this.getSource().getFilename();
	}
	
	public LDStatusCenter(String filename) {
		this.source = new WavWrapper(filename);
		this.deviceState = DeviceState.getInstance();
	}
	
	public MediaPlayer getMediaPlayer() {
		return mediaPlayer;
	}

	public void setMediaPlayer(MediaPlayer mediaPlayer) {
		this.mediaPlayer = mediaPlayer;
	}

	public LDDiarizationResultReader getReader() {
		return reader;
	}

	public void setReader(LDDiarizationResultReader reader) {
		this.reader = reader;
	}

	public DeviceState getDeviceState() {
		return deviceState;
	}

	public void setDeviceState(DeviceState deviceState) {
		this.deviceState = deviceState;
	}

	public WavWrapper getSource() {
		return source;
	}

	public void setSource(WavWrapper source) {
		this.source = source;
	}
	
	
}