package dx.leesdy.model.paintcomponents;


import dx.leesdy.model.LDStatusCenter;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaPlayer.Status;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class PDrawPlaybackState extends PDrawWav {

	protected MediaPlayer player;
	
	public PDrawPlaybackState(int priority, LDStatusCenter statusCenter) {
		super(priority, statusCenter);
		// TODO Auto-generated constructor stub
		this.player = this.statusCenter.getMediaPlayer();
		this.name = "PDrawPlaybackState";
	}
	
	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		if (this.wav != null && this.player != null) {


				MediaPlayer pl = player;
				Duration curTime = pl.getCurrentTime();
				Duration mediaLength = pl.getTotalDuration();
				Double time = curTime.toSeconds();
				Double totalTime = mediaLength.toSeconds();
				double canvasWidth = canvas.getWidth();
				int pos = (int)(time / totalTime * canvasWidth);
				GraphicsContext gc = canvas.getGraphicsContext2D();
				
				gc.setStroke(Color.WHITE);
				gc.setFill(Color.WHITE);

				gc.setLineWidth(0.5);
				gc.strokeLine(pos, 0, pos, canvas.getHeight());
				
			}
	}
	
	@Override
	public void updateState() {
		if (this.wav != null && this.player != null) {
			Status status = this.player.getStatus();
			if (status == Status.PLAYING || 
					status == Status.PAUSED || 
					status == Status.STOPPED ||
					status == Status.READY) {
				this.setNeedToUpdate(true);
			}
		}
	}

}
