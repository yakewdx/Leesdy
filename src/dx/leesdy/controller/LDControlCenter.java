package dx.leesdy.controller;

import dx.leesdy.model.LDMultiLayerCanvas;
import dx.leesdy.model.MouseState;
import dx.leesdy.model.Painter;
import dx.leesdy.player.LDWavPlayer;
import dx.leesdy.utils.LDConfigurationLoader;
import dx.leesdy.utils.LDDebug;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

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
			
			this.canvas.setOnMouseClicked(new EventHandler<MouseEvent> () {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				handleSeek();
			}
			
		});
			this.painter = new Painter(canvas);
			this.player.setPainter(painter);
			
			if (this.player.isInitializationSuceeded()) {
				this.player.initGraphics(canvas);
			}
		}
	}

	/**
	 * Event Handler: Play
	 */
	public void handlePlay() {
		this.player.play();
		LDDebug.print("Control center: play");
	}
	
	/**
	 * Event Handler: Pause
	 */
	public void handlePause() {
		this.player.pause();
		LDDebug.print("Control center: pause");
	}
	
	/**
	 * Event Handler: Stop
	 */
	public void handleStop() {
		this.player.stop();
		LDDebug.print("Control center: stop");
	}
	
	/**
	 * Event Handler: Unused
	 */
	public void handleUndefinedBehavior () {
		LDDebug.print("Control center: undefined behavior");
	}
	
	/**
	 * Event Handler: Mute
	 */
	public void handleMute(boolean state) {
		this.player.getPlayer().setMute(state);
	}
	
	/**
	 * EventHandler: Seek
	 */
	public void handleSeek() {
		MouseState ms = this.canvas.getMouseState();
		double x = ms.getX();
		Duration seekTime = this.player.getPlayer().getTotalDuration().multiply(x / this.canvas.getContainer().getWidth());
		this.player.getPlayer().seek(seekTime);
	}
	
	
	/**
	 * Event Handler: add component
	 */
	public void handleAddComponent() {
		this.player.addPainterComponents();
		LDDebug.print("Control center: add Component");
	}
	
	public void handleRemoveComponent(String name) {
		this.player.removePainterComponent(name);
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
