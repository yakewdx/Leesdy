package dx.leesdy.controller;

import dx.leesdy.model.LDMultiLayerCanvas;
import dx.leesdy.model.Painter;
import dx.leesdy.player.LDWavPlayer;
import dx.leesdy.utils.LDConfigurationLoader;

public class LDControlCenter {
	
	/**
	 *  Main media player
	 */
	private LDWavPlayer player;
	
	/**
	 *  Main canvas
	 */
	private LDMultiLayerCanvas canvas;
	
	/**
	 *  Main painter
	 */
	private Painter painter;

	
	/**
	 *  Configuration loader
	 *  If set, it will load configs from the config file
	 *  
	 */
	private LDConfigurationLoader configurationLoader;
	
	
	public LDControlCenter() {
		this.configurationLoader = new LDConfigurationLoader("resources/settings.conf");
	}

	public void init() {
		this.configurationLoader.loadConfig();
		if (this.configurationLoader.isLoaded()) {
			this.player = new LDWavPlayer(this.configurationLoader.getAudioFileName());
			this.canvas = new LDMultiLayerCanvas(this.configurationLoader.getDefaultCanvasWidth(),
												 this.configurationLoader.getDefaultCanvasHeight());
			this.painter = new Painter(canvas);
			this.player.setPainter(painter);
			
			if (this.player.isInitializationSuceeded()) {
				this.player.initGraphics(canvas);
			}
		}
	}

	
	
	public LDWavPlayer getPlayer() {
		return player;
	}


	public void setPlayer(LDWavPlayer player) {
		this.player = player;
	}


	public LDMultiLayerCanvas getCanvas() {
		return canvas;
	}


	public void setCanvas(LDMultiLayerCanvas canvas) {
		this.canvas = canvas;
	}


	public Painter getPainter() {
		return painter;
	}


	public void setPainter(Painter painter) {
		this.painter = painter;
	}

	public LDConfigurationLoader getConfigurationLoader() {
		return configurationLoader;
	}

	public void setConfigurationLoader(LDConfigurationLoader configurationLoader) {
		this.configurationLoader = configurationLoader;
	}
	
	
}
