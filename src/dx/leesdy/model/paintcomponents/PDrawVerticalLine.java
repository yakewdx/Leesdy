package dx.leesdy.model.paintcomponents;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import dx.leesdy.diarization.LDDiarizationResultReaderT;
import dx.leesdy.model.*;
import dx.leesdy.utils.LDSegment;

public class PDrawVerticalLine extends PDrawPlaybackState {
	
	private boolean showDiarizationResult;
	private boolean showBicValue;
	
	private void init() {
		this.showBicValue = false;
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
	
	public void setShowBicValue(boolean show) {
		this.showBicValue = show;
	}
	
	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		
		MouseState ms = this.getCanvas().getMouseState();
		int addonCount = 0;
		
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
				addonCount++;
				// get Label from the list
				
				LDSegment target = this.statusCenter.getReader().searchIntervalByTime(mouseTime);
				if (target.contains(mouseTime))
				gc.fillText("Speaker: " + target.getSpeaker(), ms.getX() + 1.5, 15 + addonCount * 14);
			}
			
			if (this.showBicValue == true) {
				addonCount++;
				LDDiarizationResultReaderT reader = this.statusCenter.getReader_T();
				if (reader != null) {
					double length = reader.getSegLen();
					double step = reader.getSegStep();
					int window_size = reader.getBicWindowSize();
					double[] bicValue = this.statusCenter.getReader_T().getBicValue();
					
					int n = bicValue.length + 2 * window_size;
					int t = (int) ((double)n * mouseTime / totalTime);
					if (t < bicValue.length + window_size && t > window_size) {
						double target = bicValue[t-window_size];
						gc.fillText(String.format("Bic: %f", target), ms.getX() + 1.5, 15 + addonCount * 14);
					}
					
				}
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
