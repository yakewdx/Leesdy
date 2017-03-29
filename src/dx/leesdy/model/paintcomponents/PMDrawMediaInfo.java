package dx.leesdy.model.paintcomponents;

import dx.leesdy.model.LDStatusCenter;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;

public class PMDrawMediaInfo extends PainterComponent {

	private int total_minutes;
	private int total_seconds;
	
	public PMDrawMediaInfo(int priority, LDStatusCenter statusCenter) {
		super(priority, statusCenter);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		this.setNeedToUpdate();
	}

	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		GraphicsContext gc = canvas.getGraphicsContext2D();
		MediaPlayer mediaPlayer = this.statusCenter.getMediaPlayer();
		
		int time = (int)mediaPlayer.getCurrentTime().toSeconds();
		int cur_minutes = time / 60;
		int cur_seconds = time % 60;
	
		int duration = (int)mediaPlayer.getTotalDuration().toSeconds();
		total_minutes = duration / 60;
		total_seconds = duration % 60;
		
		String playbackTime = String.format("%02d:%02d / %02d:%02d", cur_minutes, cur_seconds, total_minutes, total_seconds);
		
		//if(this.isNeedToPrintDebugMsg())LDDebug.print("PMDrawMediaInfo : " + playbackTime);
		
		gc.setFill(Color.BLUEVIOLET);
		
		//LDDebug.print(playbackTime);
		gc.fillText(playbackTime, 10, 20);
		
	}

}
