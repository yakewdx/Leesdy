package dx.leesdy.model.paintcomponents;

import com.sun.javafx.binding.StringFormatter;

import dx.leesdy.model.WavWrapper;
import dx.leesdy.player.LDWavPlayer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class PDrawPlaybackState extends PDrawWav {

	private LDWavPlayer player;
	
	public PDrawPlaybackState(int priority) {
		super(priority);
		// TODO Auto-generated constructor stub
	}
	
	public void setPlayer(LDWavPlayer player) {
		this.player = player;
	}
	
	public PDrawPlaybackState(int priority, WavWrapper wav, LDWavPlayer player) {
		super(priority, wav);
		// TODO Auto-generated constructor stub
		this.player = player;
	}

	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		if (this.wav != null && this.player != null) {
			Status status = this.player.getPlayer().getStatus();
			if (status == Status.PLAYING || status == Status.PAUSED) {
				Media media = player.getPlayer().getMedia();
				MediaPlayer pl = player.getPlayer();
				Duration curTime = pl.getCurrentTime();
				Duration mediaLength = pl.getTotalDuration();
				Double time = curTime.toSeconds();
				Double totalTime = mediaLength.toSeconds();
				double canvasWidth = canvas.getWidth();
				double canvasHeight = canvas.getHeight();
				int pos = (int)(time / totalTime * canvasWidth);
				int length = data[0].length;
				int step = (int) (length / canvasWidth);
				GraphicsContext gc = canvas.getGraphicsContext2D();
				
				String playbackStatus = String.format("%.2f / %.2f", time, totalTime);
				
				gc.setStroke(Color.WHITE);
				gc.setFill(Color.WHITE);
				gc.fillText(playbackStatus, 3, 10);
				gc.setLineWidth(0.5);
				gc.strokeLine(pos, 0, pos, canvas.getHeight());
				
			}
			
		}
		
	}

}
