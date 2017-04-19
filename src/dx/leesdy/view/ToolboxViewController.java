/**
 * Leesdy
 * dx.leesdy.view
 * Author: DxWang ToolboxViewController.java
 * Created at:2017年3月7日
 */
package dx.leesdy.view;

import dx.leesdy.controller.LDControlCenter;
import dx.leesdy.controller.LDWorkspaceManager;
import dx.leesdy.model.layout.LDButton;
import dx.leesdy.model.layout.LDToggleButton;
import dx.leesdy.player.LDWavPlayer;
import dx.leesdy.utils.LDDebug;
import dx.leesdy.utils.LDInitilizableComponent;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.media.MediaPlayer.Status;


public class ToolboxViewController implements LDInitilizableComponent{

	private boolean inited;
	
	private LDWorkspaceManager manager;
	
	private LDControlCenter currentControlCenter;
	
	private IntegerProperty selectedControlCenterID;
	
	@FXML
	private LDToggleButton playButton;
	
	@FXML
	private LDButton stopButton;
	
	@FXML
	private LDToggleButton addInfoButton;
	
	@FXML
	private LDButton diarizationButton;

	@FXML
	private void initialize() {
		
		this.inited = false;
		
		this.selectedControlCenterID = new SimpleIntegerProperty();
		
		this.loadButtonStates();
		
//		//player = new LDWavPlayer("resources/output.wav");
//		this.addInfoButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//				// TODO Auto-generated method stub
//				if (newValue == true) {
//					LDDebug.print("Add component button : selected");
//					currentControlCenter.handleAddComponent();
//				} else {
//					LDDebug.print("Add component button : unselected");
//					currentControlCenter.handleRemoveComponent("PDrawDiarizationResult");
//				}
//			}
//			
//		});
//		
//		this.playButton.selectedProperty().addListener(new ChangeListener<Boolean>() {
//
//			@Override
//			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
//				// TODO Auto-generated method stub
//				if (newValue == true) {
//					LDDebug.print("Play button : selected");
//					currentControlCenter.handlePlay();
//				} else {
//					LDDebug.print("Play button : unselected");
////					Status status = player.getStatusCenter().getMediaPlayer().getStatus();
////					LDDebug.print(status.name());
//					currentControlCenter.handlePause();
//				}
//			}
//		});
		
		this.selectedControlCenterID.addListener(new ChangeListener<Number> () {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				OnTrackSelected(newValue.intValue());
			}
			
		});
	}
	
	
	
//	@FXML
//    private void handlePause() {
//    	//System.out.println("Pause clicked");
//    	this.currentControlCenter.handlePause();
//    	
//    	//this.playButton.setText("Play");
//    	//setPlayButtonState(true);
//
//    }
//    
//	@FXML
//    private void handlePlay() {
//    	// todo: Play wav
//    	// System.out.println("Play clicked.");
//    	this.currentControlCenter.handlePlay();
//    	//setPlayButtonState(false);
//
//    }
	
    // Only for test
//    @FXML
//    private void handleAdd() {
//    	this.currentControlCenter.handleAddComponent();
//    }
    
    @FXML
    private void handleStop() {
    	// todo: Stop music
    	// System.out.println("Stop clicked.");
    	this.currentControlCenter.handleStop();
    	this.playButton.setSelected(false);
    	//setPlayButtonState(true);
    }
    
    @FXML
    private void handleDiarization() {
    	// todo: diarization
    	System.out.println("Start Diarization");
    	//this.player.diarization();
    	this.currentControlCenter.diarization();
    }
    
//    public void setPlayButtonState(boolean toShowPlay) {
//    	playButton.setVisible(toShowPlay);
//    	pauseButton.setVisible(!toShowPlay);
//    }
    
    
    private void OnTrackSelected(int id) {
    	if (this.inited)
    	this.playButton.selectedProperty().unbindBidirectional(this.currentControlCenter.getPlayButtonSelectedProperty());
    	if (this.inited)
    	this.addInfoButton.selectedProperty().unbindBidirectional(this.currentControlCenter.getAddButtonSelectedProperty());
    	LDDebug.print("ToolboxViewController : Track selected.");
    	this.currentControlCenter = this.manager.getControlCenterById(id);
		this.playButton.selectedProperty().bindBidirectional(this.currentControlCenter.getPlayButtonSelectedProperty());
    	this.addInfoButton.selectedProperty().bindBidirectional(this.currentControlCenter.getAddButtonSelectedProperty());
		this.loadButtonStates();
    }
    
    private void loadButtonStates() {
    	if (this.currentControlCenter == null) {
    		this.playButton.setDisable(true);
    		this.stopButton.setDisable(true);
    		this.addInfoButton.setDisable(true);
    		this.diarizationButton.setDisable(true);
    	} else {
    		Status playbackStatus = this.currentControlCenter.getPlayer().getPlayer().getStatus();
    		this.playButton.setDisable(false);
//    		if (playbackStatus == Status.PLAYING) {
//    			this.playButton.setSelected(true);
//    		} else {
//    			this.playButton.setSelected(false);
//    		}
    		this.stopButton.setDisable(false);
    		this.addInfoButton.setDisable(false);
//    		if (this.currentControlCenter.getPainter().getComponentByName("PDrawDiarizationResult") != null) {
//    			//this.addInfoButton.selectedProperty().set(true);
//    			this.addInfoButton.setSelected(true);
//    		} else {
//    			this.addInfoButton.setSelected(false);
//    			//this.addInfoButton.selectedProperty().set(false);
//    		}
    		
    		this.diarizationButton.setDisable(false);
    	}
    }


	public LDControlCenter getCurrentControlCenter() {
		return currentControlCenter;
	}



	public void setCurrentControlCenter(LDControlCenter controlCenter) {
		this.currentControlCenter = controlCenter;
	}



	@Override
	public void init() {
		// TODO Auto-generated method stub
		// this.currentControlCenter = manager.getTop();
		//this.selectedControlCenterID.bindBidirectional(this.manager.getCurrentlySelectedIDProperty());
		if (!this.inited) {
			LDDebug.print("ToolboxViewController : not inited , ID " + manager.getTopID());
			this.selectedControlCenterID.bindBidirectional(manager.getCurrentlySelectedIDProperty());
			OnTrackSelected(manager.getTopID());
			this.inited = true;
		} else {
			LDDebug.print("ToolboxViewController : inited , ID " + manager.getTopID());
			this.selectedControlCenterID.set(manager.getTopID());
		}
		//
		//this.player = this.currentControlCenter.getPlayer();
		//this.playButton.selectedProperty().bindBidirectional(currentControlCenter.getPlayButtonSelectedProperty());
	}

	public void setManager(LDWorkspaceManager manager) {
		this.manager = manager;
	}
    
}
