package dx.leesdy.model;

import dx.leesdy.utils.*;
//import javafx.scene.media.Media;

// Wrap wav IOs
public class WavWrapper {
	
	private String filename;
	//private Media _media;
	private LDWavFileReader fileReader;
	public WavWrapper(String filename) {
		//_media = media;
		
		// substring(6) : remove "file:"
		// or getSource will return file:C:/******
		// fileReader = new LDWavFileReader(_media.getSource().substring(5));
		fileReader = new LDWavFileReader(filename);
		this.filename = filename;
	}
	
	public boolean isSucceeded(){
		return fileReader.isSuccess();
	}
	
//	public void setMedia(Media media) {
//		_media = media;
//	}
//	
//	public Media getMedia() {
//		return _media;
//	}
	
	public LDWavFileReader getFileReader() {
		return fileReader;
	}
	
	public int[][] getData() {
		if (fileReader.isSuccess())
			return fileReader.getData();
		else return null;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public long getSamplingRate() {
		return this.fileReader.getSampleRate();
	}
	
	public long getNumChannels() {
		return this.fileReader.getNumChannels();
	}

}
