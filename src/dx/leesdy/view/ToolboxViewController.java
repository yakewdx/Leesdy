/**
 * Leesdy
 * dx.leesdy.view
 * Author: DxWang ToolboxViewController.java
 * Created at:2017年3月7日
 */
package dx.leesdy.view;

import dx.leesdy.controller.LDControlCenter;
import dx.leesdy.model.layout.LDButton;
import dx.leesdy.model.layout.LDToggleButton;
import dx.leesdy.player.LDWavPlayer;
import dx.leesdy.utils.LDDebug;
import dx.leesdy.utils.LDInitilizableComponent;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.media.MediaPlayer.Status;


public class ToolboxViewController implements LDInitilizableComponent{

	private LDControlCenter controlCenter;
	
	private LDWavPlayer player;
	
	@FXML
	private LDToggleButton playButton;
	
	@FXML
	private LDButton stopButton;
	
	@FXML
	private LDToggleButton addInfoButton;
	
	
	@FXML
	private void initialize() {
		//player = new LDWavPlayer("resources/output.wav");
		this.addInfoButton.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if (newValue == true) {
					LDDebug.print("Add component button : selected");
					controlCenter.handleAddComponent();
				} else {
					LDDebug.print("Add component button : unselected");
					controlCenter.handleRemoveComponent("PDrawDiarizationResult");
				}
			}
			
		});
		
		this.playButton.selectedProperty().addListener(new ChangeListener<Boolean>() {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if (newValue == true) {
					LDDebug.print("Play button : selected");
					controlCenter.handlePlay();
				} else {
					LDDebug.print("Play button : unselected");
//					Status status = player.getStatusCenter().getMediaPlayer().getStatus();
//					LDDebug.print(status.name());
					controlCenter.handlePause();
				}
			}
			
		});
	}
	
	
	
	@FXML
    private void handlePause() {
    	//System.out.println("Pause clicked");
    	this.controlCenter.handlePause();
    	
    	//this.playButton.setText("Play");
    	//setPlayButtonState(true);

    }
    
	@FXML
    private void handlePlay() {
    	// todo: Play wav
    	// System.out.println("Play clicked.");
    	this.controlCenter.handlePlay();
    	//setPlayButtonState(false);

    }
	
    // Only for test
    @FXML
    private void handleAdd() {
    	this.controlCenter.handleAddComponent();
    }
    
    @FXML
    private void handleStop() {
    	// todo: Stop music
    	// System.out.println("Stop clicked.");
    	this.controlCenter.handleStop();
    	this.playButton.setSelected(false);
    	//setPlayButtonState(true);
    }
    
    @FXML
    private void handleDiarization() {
    	// todo: diarization
    	System.out.println("Start Diarization");
    	this.player.diarization();
    }
    
//    public void setPlayButtonState(boolean toShowPlay) {
//    	playButton.setVisible(toShowPlay);
//    	pauseButton.setVisible(!toShowPlay);
//    }
    
    public LDWavPlayer getPlayer() {
    	return player;
    }



	public LDControlCenter getControlCenter() {
		return controlCenter;
	}



	public void setControlCenter(LDControlCenter controlCenter) {
		this.controlCenter = controlCenter;
	}



	@Override
	public void init() {
		// TODO Auto-generated method stub
		this.player = this.controlCenter.getPlayer();
	}
    
}
