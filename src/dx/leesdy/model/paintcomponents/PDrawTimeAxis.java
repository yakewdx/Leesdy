package dx.leesdy.model.paintcomponents;

import dx.leesdy.model.LDStatusCenter;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class PDrawTimeAxis extends PainterComponent {

	private boolean durationRetrieved;
	
	public PDrawTimeAxis(int priority, LDStatusCenter statusCenter) {
		super(priority, statusCenter);
		// TODO Auto-generated constructor stub
		this.durationRetrieved = false;
		this.name = "PDrawTimeAxis";
	}

	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		if (this.getDrawingCount() == 0 || !this.durationRetrieved) {
			this.setNeedToUpdate();
		} else {
			this.setNeedToUpdate(false);
		}
	}

	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		GraphicsContext gc = canvas.getGraphicsContext2D();
		MediaPlayer player = this.statusCenter.getMediaPlayer();
		Duration duration = player.getTotalDuration();
		Double total_seconds = duration.toSeconds();
		Double total_width = canvas.getWidth();
		Double k = total_width / total_seconds;
		Duration dr = duration.divide(20);
		Duration pv = Duration.ZERO;
		
		if (!duration.isUnknown()) {
			this.durationRetrieved = true;
		}
		
		Double height = canvas.getHeight();
		Double shifting = 10.0;
		// Draw the axis
		gc.setLineWidth(1.0);
		gc.setStroke(Color.YELLOW);
		gc.strokeLine(0, height - shifting, total_width, height - shifting);
		//LDDebug.print(duration.toString());
		while (pv.lessThan(duration)) {
			
			Double seconds = pv.toSeconds();
			int cur_sec = (int)(seconds % 60);
			int cur_min = (int)(seconds / 60);
			Double pos = seconds * k;
			gc.setFill(Color.YELLOW);
			gc.fillOval(pos, height - shifting - 4, 2, 4);
			gc.fillText(String.format("%02d:%02d", cur_min, cur_sec), pos + 2, height - shifting - 4);
			//LDDebug.print(pos.toString());
			pv = pv.add(dr);
		}
		
	}

}
