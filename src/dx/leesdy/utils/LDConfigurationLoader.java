package dx.leesdy.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class LDConfigurationLoader {
	
	private String configFilename;

	private String audioFileName;
	private int defaultCanvasHeight;
	private int defaultCanvasWidth;
	
	private boolean loaded;
	
	public LDConfigurationLoader(String filename) {
		this.loaded = false;
		this.configFilename = filename;
	}
	
	public boolean loadConfig() {
		try {
			for (String line : Files.readAllLines(Paths.get(configFilename))) {
				
				if (line.trim().isEmpty() || line.subSequence(0, 1).toString().equals("#")) {
					// comments or empty
				} else {
					String [] components = line.split("\\s");
					if (components.length != 2) return false;
					switch (components[0]) {
					case "audio_filename":
						this.audioFileName = components[1];
						break;
					case "canvas_height":
						this.defaultCanvasHeight = Integer.valueOf(components[1]);
						break;
					case "canvas_width":
						this.defaultCanvasWidth = Integer.valueOf(components[1]);
						break;
					default:
						break;
					}
					
				}
			}	
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.loaded = true;
		return true;
	}
	
	public boolean isLoaded() {
		return loaded;
	}
	
	public String getConfigFilename() {
		return configFilename;
	}

	public void setConfigFilename(String configFilename) {
		this.configFilename = configFilename;
	}

	public String getAudioFileName() {
		return audioFileName;
	}

	public void setAudioFileName(String audioFileName) {
		this.audioFileName = audioFileName;
	}

	public int getDefaultCanvasHeight() {
		return defaultCanvasHeight;
	}

	public void setDefaultCanvasHeight(int defaultCanvasHeight) {
		this.defaultCanvasHeight = defaultCanvasHeight;
	}

	public int getDefaultCanvasWidth() {
		return defaultCanvasWidth;
	}

	public void setDefaultCanvasWidth(int defaultCanvasWidth) {
		this.defaultCanvasWidth = defaultCanvasWidth;
	}
	
	
}
