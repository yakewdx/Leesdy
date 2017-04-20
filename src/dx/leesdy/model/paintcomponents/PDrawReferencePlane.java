package dx.leesdy.model.paintcomponents;

import dx.leesdy.model.LDStatusCenter;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class PDrawReferencePlane extends PainterComponent {

	private boolean durationRetrieved;
	
	public PDrawReferencePlane(int priority, LDStatusCenter statusCenter) {
		super(priority, statusCenter);
		// TODO Auto-generated constructor stub
		this.durationRetrieved = false;
		this.name = "PDrawReferencePlane";
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
		Double total_width = canvas.getWidth();

		if (!duration.isUnknown()) {
			this.durationRetrieved = true;
		}
		
		Double total_height = canvas.getHeight();
		gc.setLineWidth(0.5);
		gc.setStroke(Color.rgb(122,213,244));
		
		int horizontalLineCount = 4;
		Double height_l = total_height / horizontalLineCount;
		for (int i = 0; i < horizontalLineCount; i++) {
			gc.strokeLine(0, height_l * i, total_width, height_l * i);
		}
		
		int verticalLineCount = 20;
		Double width_l = total_width / verticalLineCount;
		for (int i = 0; i < verticalLineCount; i++) {
			gc.strokeLine(width_l * i, 0, width_l * i, total_height);
		}
		
		
	}

}
