package dx.leesdy.model.paintcomponents;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.util.ArrayList;

import dx.leesdy.model.*;
import dx.leesdy.player.LDWavPlayer;
import dx.leesdy.utils.LDDiarizationResultReader;
import dx.leesdy.utils.LDSegment;

public class PDrawVerticalLine extends PDrawPlaybackState {
	
	boolean showDiarizationResult;
	
	private void init() {
		this.showDiarizationResult = false;
		this.name = "PDrawVerticalLine";
	}
	
	public PDrawVerticalLine(int priority,LDStatusCenter statusCenter) {
		super(priority, statusCenter);
		// TODO Auto-generated constructor stub
		init();
	}
	
	public void setShowDiarizationResult(boolean show) {
		this.showDiarizationResult = show;
	}
	
	
	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		
		MouseState ms = statusCenter.getDeviceState().getMouseState();
		
		if (ms.isMouseInCanvas()) {
			MediaPlayer pl = player;
			Duration mediaLength = pl.getTotalDuration();
			Double totalTime = mediaLength.toSeconds();
			double canvasWidth = canvas.getWidth();
			double mouseTime = 0.0;
			
			
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setStroke(Color.WHITE);
			gc.setFill(Color.WHITE);
			gc.setLineWidth(0.5);
			gc.strokeLine(ms.getX(), 0, ms.getX(), canvas.getHeight());
			mouseTime = ms.getX() / canvasWidth * totalTime;
			String mousePos = String.format("%.1f/%.1f", mouseTime, totalTime);
			gc.fillText(mousePos, ms.getX() + 1.5, 15);
			//System.out.println(mousePos);
			
			if (this.showDiarizationResult == true) {
				
				// get Label from the list
				
				LDSegment target = this.statusCenter.getReader().searchIntervalByTime(mouseTime);
				gc.fillText(target.getSpeaker(), ms.getX() + 1.5, 29);
			}
		}
		
	}
	
	@Override
	public void updateState() {
		//MouseState ms = statusCenter.getDeviceState().getMouseState();
		//if (ms.isMouseInCanvas()) {
			this.setNeedToUpdate();
		//}
	}

}
