/**
 * Leesdy
 * dx.leesdy.view
 * Author: DxWang ToolboxViewController.java
 * Created at:2017年3月7日
 */
package dx.leesdy.view;

import dx.leesdy.model.layout.LDButton;
import dx.leesdy.player.LDWavPlayer;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * 
 */
public class ToolboxViewController {

	
	private LDWavPlayer player;
	
	@FXML
	private LDButton playButton;
	
	@FXML
	private LDButton pauseButton;
	
	@FXML
	private LDButton stopButton;
	
	@FXML
	private LDButton addInfoButton;
	
	
	@FXML
	private void initialize() {
		player = new LDWavPlayer("resources/8k16bitpcm.wav");
	}
	
	
	
	@FXML
    private void handlePause() {
    	System.out.println("Pause clicked");
    	this.player.pause();
    	//this.playButton.setText("Play");
    	setPlayButtonState(true);

    }
    
	@FXML
    private void handlePlay() {
    	// todo: Play wav
    	System.out.println("Play clicked.");
    	player.play();
    	setPlayButtonState(false);

    }
	
    // Only for test
    @FXML
    private void handleAdd() {
    	this.player.addPainterComponents();
    }
    
    @FXML
    private void handleStop() {
    	// todo: Stop music
    	System.out.println("Stop clicked.");
    	this.player.stop();
    	setPlayButtonState(true);
    }
    
    @FXML
    private void handleDiarization() {
    	// todo: diarization
    	System.out.println("Start Diarization");
    	this.player.diarization();
    }
    
    public void setPlayButtonState(boolean toShowPlay) {
    	playButton.setVisible(toShowPlay);
    	pauseButton.setVisible(!toShowPlay);
    }
    
    public LDWavPlayer getPlayer() {
    	return player;
    }
    
}
