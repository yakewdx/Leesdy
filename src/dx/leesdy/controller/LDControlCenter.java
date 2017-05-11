package dx.leesdy.controller;

import dx.leesdy.model.LDMultiLayerCanvas;
import dx.leesdy.model.LDStatusCenter;
import dx.leesdy.model.MouseState;
import dx.leesdy.model.Painter;
import dx.leesdy.model.paintcomponents.PDrawBicValue;
import dx.leesdy.model.paintcomponents.PDrawFrameEnergy;
import dx.leesdy.model.paintcomponents.PDrawVerticalLine;
import dx.leesdy.player.LDWavPlayer;
import dx.leesdy.utils.LDConfigurationLoader;
import dx.leesdy.utils.LDDebug;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer.Status;
import javafx.util.Duration;

public class LDControlCenter implements EventHandler<KeyEvent>{
	
	private int id;

	private LDWorkspaceManager manager;
	
	/**
	 *  Main media player
	 */
	private LDWavPlayer player;
	
	/**
	 *  Main canvas
	 */
	private LDMultiLayerCanvas mainCanvas;
	
	
	/**
	 * Media canvas
	 */
	private LDMultiLayerCanvas mediaCanvas;
	
	/**
	 *  Main painter
	 */
	private Painter painter;

	/**
	 *  Status center for this part
	 */
	private LDStatusCenter statusCenter;
	
	
	public LDStatusCenter getStatusCenter() {
		return statusCenter;
	}

	public void setStatusCenter(LDStatusCenter statusCenter) {
		this.statusCenter = statusCenter;
	}

	private BooleanProperty playButtonSelectedProperty;
	
	private BooleanProperty addButtonSelectedProperty;
	
	/**
	 *  Configuration loader
	 *  If set, it will load configs from the config file
	 *  
	 */
	private LDConfigurationLoader configurationLoader;
	
	
	public LDControlCenter() {
		this.configurationLoader = new LDConfigurationLoader("resources/settings.conf");
		this.init("");
	}
	
	public LDControlCenter(String filename) {
		this.configurationLoader = new LDConfigurationLoader("resources/settings.conf");
		this.init(filename);
	}

	public void setFocus(boolean focus) {
		if (focus) {
			this.manager.setCurrentlySelectedID(this.id);
			statusCenter.setSelected(true);
		} else {
			statusCenter.setSelected(false);
		}
	}
	
	private void init(String name) {
		
		this.configurationLoader.loadConfig();
		if (this.configurationLoader.isLoaded()) {
			String filename = name;
			if (name == "") {
				filename = this.configurationLoader.getAudioFileName();
			}
			this.statusCenter = new LDStatusCenter(filename);
			this.player = new LDWavPlayer(filename, statusCenter);
			this.player.initPlayer();
			this.mainCanvas = new LDMultiLayerCanvas(this.configurationLoader.getDefaultCanvasWidth(),
												 this.configurationLoader.getDefaultCanvasHeight());
			this.mainCanvas.setControlCenter(this);
			this.mediaCanvas = new LDMultiLayerCanvas(132, 99);
			this.mediaCanvas.setControlCenter(this);
			this.playButtonSelectedProperty = new SimpleBooleanProperty();
			this.addButtonSelectedProperty = new SimpleBooleanProperty();
			this.mainCanvas.setOnMouseClicked(new EventHandler<MouseEvent> () {
			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				setFocus(true);
				handleSeek();
			}
			
		});
			this.painter = new Painter(mainCanvas);
			this.player.setPainter(painter);
			
			if (this.player.isInitializationSuceeded()) {
				this.player.initGraphics(mainCanvas);
			}
		}
		
		this.playButtonSelectedProperty.addListener((obs, ov, nv) -> {
			if (nv == true) {
				LDDebug.print("ControlCenter : Play button selected.");
				this.handlePlay();
			} else {
				LDDebug.print("ControlCenter : Play button unslected.");
				this.handlePause();
			}
		});
		
		this.addButtonSelectedProperty.addListener((obs, ov, nv) -> {
			if (nv == true) {
				LDDebug.print("ControlCenter : Add component button selected.");
				this.handleAddComponent();
			} else {
				LDDebug.print("ControlCenter : Add component button unselected.");
				this.handleRemoveComponent("PDrawDiarizationResult");
			}
		});
//		this.playButtonSelectedProperty.addListener((changeEvent, oldValue, newValue) -> {
//			if (newValue.booleanValue() == true) {
//				LDDebug.print("ControlCenter : Play button selected.");
//				this.handlePlay();
//			} else {
//				LDDebug.print("ControlCenter : Play button unselected.");
//				this.handlePause();
//			}
//		});
	}

	public void diarization() {
		if (painter.getComponentByName("PDrawFrameEnergy") == null)
			painter.addComponent(new PDrawFrameEnergy(15,this.statusCenter));
		if (painter.getComponentByName("PDrawBicValue") == null) {
			painter.addComponent(new PDrawBicValue(16,this.statusCenter));
			PDrawVerticalLine pc = (PDrawVerticalLine)painter.getComponentByName("PDrawVerticalLine");
			pc.setShowBicValue(true);
		}
		this.player.diarization();
		
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
		MouseState ms = this.mainCanvas.getMouseState();
		double x = ms.getX();
		Duration seekTime = this.player.getPlayer().getTotalDuration().multiply(x / this.mainCanvas.getContainer().getWidth());
		this.player.getPlayer().seek(seekTime);
	}
	
	
	/**
	 * Event Handler: add component
	 */
	public void handleAddComponent() {
		this.player.addPainterComponents();
		LDDebug.print("Control center: add Component");
	}
	
	
	/**
	 * 
	 * @param name
	 */
	public void handleVolumeChange(double volume) {
		
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


	public Painter getPainter() {
		return painter;
	}


	public void setPainter(Painter painter) {
		this.painter = painter;
	}

	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	
	public LDConfigurationLoader getConfigurationLoader() {
		return configurationLoader;
	}

	public void setConfigurationLoader(LDConfigurationLoader configurationLoader) {
		this.configurationLoader = configurationLoader;
	}

	@Override
	public void handle(KeyEvent event) {
		// TODO Auto-generated method stub
		LDDebug.print("LDMultiLayerCanvas : Key pressed.");
		switch (event.getCode()) {
		case SPACE:
			if (this.player.getPlayer().getStatus() == Status.STOPPED ||
					this.player.getPlayer().getStatus() == Status.READY || 
					this.player.getPlayer().getStatus() == Status.PAUSED) {
				this.handlePlay();
				this.playButtonSelectedProperty.set(true);
			} else if (this.player.getPlayer().getStatus() == Status.PLAYING) {
				this.playButtonSelectedProperty.set(false);
			}
			break;
		default:
			LDDebug.print("LDControlCenter : default keypress.");
			break;
		
		}
	}

	public BooleanProperty getPlayButtonSelectedProperty() {
		return playButtonSelectedProperty;
	}

	public void setPlayButtonSelectedProperty(BooleanProperty playButtonSelectedProperty) {
		this.playButtonSelectedProperty = playButtonSelectedProperty;
	}

	public LDMultiLayerCanvas getMainCanvas() {
		return mainCanvas;
	}

	public void setMainCanvas(LDMultiLayerCanvas mainCanvas) {
		this.mainCanvas = mainCanvas;
	}

	public LDMultiLayerCanvas getMediaCanvas() {
		return mediaCanvas;
	}

	public void setMediaCanvas(LDMultiLayerCanvas mediaCanvas) {
		this.mediaCanvas = mediaCanvas;
	}

	public LDWorkspaceManager getManager() {
		return manager;
	}

	public void setManager(LDWorkspaceManager manager) {
		this.manager = manager;
	}

	public BooleanProperty getAddButtonSelectedProperty() {
		return addButtonSelectedProperty;
	}

	public void setAddButtonSelectedProperty(BooleanProperty addButtonSelectedProperty) {
		this.addButtonSelectedProperty = addButtonSelectedProperty;
	}
	
	
}
