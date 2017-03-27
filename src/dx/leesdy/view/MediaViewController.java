package dx.leesdy.view;

import dx.leesdy.controller.LDControlCenter;
import dx.leesdy.model.LDMultiLayerCanvas;
import dx.leesdy.model.LDStatusCenter;
import dx.leesdy.model.Painter;
import dx.leesdy.model.layout.*;
import dx.leesdy.model.paintcomponents.PDrawWav;
import dx.leesdy.model.paintcomponents.PMDrawMediaInfo;
import dx.leesdy.model.paintcomponents.PTestDrawWhiteBackground;
import dx.leesdy.model.paintcomponents.PTimer;
import dx.leesdy.utils.LDInitilizableComponent;
import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class MediaViewController implements LDInitilizableComponent{

	private LDStatusCenter statusCenter;
	
	private LDControlCenter controlCenter;
	
	@FXML
	private Pane canvasPane;
	
	@FXML
	private LDButton unusedButton;
	
	@FXML
	private LDToggleButton muteButton;
	
	@FXML
	private LDSlider slider;
	
	@FXML
	private LDProgressBar progressBar;
	

	public LDStatusCenter getStatusCenter() {
		return statusCenter;
	}

	public void setStatusCenter(LDStatusCenter statusCenter) {
		this.statusCenter = statusCenter;
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
		
		// TODO:
		// set callbacks to buttons
		this.unusedButton.setOnAction(new EventHandler<ActionEvent> () {

			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				controlCenter.handleUndefinedBehavior();
			}
			
		});
		
		this.muteButton.selectedProperty().addListener(new ChangeListener<Boolean> () {

			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				// TODO Auto-generated method stub
				if (newValue == true) {
					controlCenter.handleMute(true);
					slider.setDisable(true);
					progressBar.setDisable(true);
				} else {
					controlCenter.handleMute(false);
					slider.setDisable(false);
					progressBar.setDisable(false);
				}
			}
			
		});
		
		// create multi layer canvas and painter
		double width = this.canvasPane.getPrefWidth();
		double height = this.canvasPane.getPrefHeight();
		
		LDMultiLayerCanvas canvas = new LDMultiLayerCanvas(width - 6, height - 6);
		Painter painter = new Painter(canvas);
		canvas.setPane(canvasPane);
		canvas.setLayoutY(3);
		canvas.setLayoutX(3);
		
		//painter.addComponent(new PTimer(6, this.statusCenter));
		painter.addComponent(new PTestDrawWhiteBackground(1, this.statusCenter));
		painter.addComponent(new PMDrawMediaInfo(4, this.statusCenter));
		
		// run the painter.
		//painter.start();
		//new Thread(painter).start();
		new AnimationTimer() {

			@Override
			public void handle(long now) {
				// TODO Auto-generated method stub
				painter.paint();
			}
			
		}.start();
		
		
		// set the behaviors of slider and progress bar
		this.slider.valueProperty().addListener(new ChangeListener<Number> () {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// TODO Auto-generated method stub
				progressBar.setProgress(newValue.doubleValue() / 100);
				controlCenter.getPlayer().getPlayer().setVolume(newValue.doubleValue() / 100);
			}
			
		});
	}
	
	
}
