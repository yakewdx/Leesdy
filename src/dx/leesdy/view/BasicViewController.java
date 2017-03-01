package dx.leesdy.view;

import javafx.scene.paint.*;
import javafx.scene.shape.*;
import dx.leesdy.player.LDWavPlayer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class BasicViewController {

	@FXML
	private Canvas canvas;
	
	@FXML
	private Button playButton;
	
	@FXML
	private Button pauseButton;
	
	@FXML
	private Button StopButton;
	
	private LDWavPlayer player;
	/**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public BasicViewController() {
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
    	// test drawing
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	
    	gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        
        this.pauseButton.setVisible(false);
    }
    
    @FXML
    private void handlePlay() {
    	// todo: Play wav
    	System.out.println("Play clicked.");
    	player.play();
    	playButton.setVisible(false);
    	pauseButton.setVisible(true);
    }
    
    @FXML
    private void handlePause() {
    	System.out.println("Pause clicked");
    	this.player.pause();
    	//this.playButton.setText("Play");
    	playButton.setVisible(true);
    	pauseButton.setVisible(false);
    	
    }
    
    
    @FXML
    private void handleStop() {
    	// todo: Stop music
    	System.out.println("Stop clicked.");
    	this.player.stop();
    }
    
    public void setPlayer(LDWavPlayer _player) {
    	this.player = _player;
    }
    
    public Canvas getCanvas() {
    	return this.canvas;
    }
    
}
