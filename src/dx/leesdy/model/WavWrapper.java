package dx.leesdy.model;

import java.io.*;
import java.util.ArrayList;

import dx.leesdy.utils.*;
import javafx.scene.media.Media;

// Wrap wav IOs
public class WavWrapper {
	
	private Media _media;
	private LDWavFileReader fileReader;
	public WavWrapper(Media media) {
		_media = media;
		
		// substring(6) : remove "file:"
		// or getSource will return file:C:/******
		fileReader = new LDWavFileReader(_media.getSource().substring(6));
	}
	
	public void setMedia(Media media) {
		_media = media;
	}
	
	public Media getMedia() {
		return _media;
	}
	
	public LDWavFileReader getFileReader() {
		return fileReader;
	}
	
	public int[][] getData() {
		if (fileReader.isSuccess())
			return fileReader.getData();
		else return null;
	}

}
