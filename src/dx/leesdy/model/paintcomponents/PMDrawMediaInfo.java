package dx.leesdy.model.paintcomponents;

import dx.leesdy.model.LDStatusCenter;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class PMDrawMediaInfo extends PainterComponent {

	public PMDrawMediaInfo(int priority, LDStatusCenter statusCenter) {
		super(priority, statusCenter);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		this.setNeedToUpdate(false);
	}

	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		GraphicsContext gc = canvas.getGraphicsContext2D();
		MediaPlayer mediaPlayer = this.statusCenter.getMediaPlayer();
		Media media = mediaPlayer.getMedia();
		
		int time = (int)mediaPlayer.getCurrentTime().toSeconds();
		
		
		int duration = (int)media.durationProperty().getValue().toSeconds();
		int total_minutes = duration / 60;
		int total_seconds = duration % 60;
		
		
		
	}

}
