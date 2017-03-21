package dx.leesdy.model.paintcomponents;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;
import dx.leesdy.model.*;
import dx.leesdy.player.LDWavPlayer;

public class PDrawVerticalLine extends PDrawPlaybackState {

	public PDrawVerticalLine(int priority) {
		super(priority);
		// TODO Auto-generated constructor stub
	}
	
	public PDrawVerticalLine(int priority, WavWrapper wav, LDWavPlayer player) {
		super(priority, wav, player);
		
	}

	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		
		MouseState ms = DeviceState.getInstance().getMouseState();
		MediaPlayer pl = player.getPlayer();
		Duration mediaLength = pl.getTotalDuration();
		Double totalTime = mediaLength.toSeconds();
		double canvasWidth = canvas.getWidth();
		
		if (ms.isMouseInCanvas()) {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setStroke(Color.WHITE);
			gc.setFill(Color.WHITE);
			gc.setLineWidth(0.5);
			gc.strokeLine(ms.getX(), 0, ms.getX(), canvas.getHeight());
			String mousePos = String.format("%.1f/%.1f", ms.getX() / canvasWidth * totalTime , totalTime);
			gc.fillText(mousePos, ms.getX() + 1.5, 15);
			//System.out.println(mousePos);
		}
		
	}
	
	@Override
	public void updateState() {
		MouseState ms = DeviceState.getInstance().getMouseState();
		if (ms.isMouseInCanvas()) {
			this.setNeedToUpdate();
		}
	}

}
