package dx.leesdy.model.paintcomponents;

import dx.leesdy.model.LDStatusCenter;
import dx.leesdy.utils.LDDebug;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class PDrawFrameEnergy extends PainterComponent {

	int counter;
	
	public PDrawFrameEnergy(int priority, LDStatusCenter statusCenter) {
		super(priority, statusCenter);
		// TODO Auto-generated constructor stub
		counter = 0;
		this.name = "PDrawFrameEnergy";
	}

	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		if (statusCenter.getReader_T() != null) {
			if (statusCenter.getReader_T().isNeedUpdate()) this.setNeedToUpdate();
			else this.setNeedToUpdate(false);
		} else {
			this.setNeedToUpdate(false);
		}
		
	}

	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		
		//LDDebug.print("PDrawFrameEnergy : updating");
		double [] energy = statusCenter.getReader_T().getMfccEnergy();
		
		double step = statusCenter.getReader_T().getSegStep();
		double length = statusCenter.getReader_T().getSegLen();
		
		if (energy != null) {
			// find min and max
			double min = Double.MAX_VALUE;
			double max = -Double.MAX_VALUE;
			for (int i = 1; i < energy.length - 51; i++) {
				if (min > energy[i]) min = energy[i];
				if (max < energy[i] && !Double.isInfinite(energy[i])) max = energy[i];
			}
			
			GraphicsContext gc = canvas.getGraphicsContext2D();
			
			MediaPlayer pl = statusCenter.getMediaPlayer();

			Duration mediaLength = pl.getTotalDuration();

			Double totalTime = mediaLength.toSeconds();
			double canvasWidth = canvas.getWidth();
			double canvasHeight = canvas.getHeight();
			
			double prevX = 0;
			double prevY = canvasHeight;
			gc.setStroke(Color.GRAY);
			for (int i = 1; i < energy.length; i++) {
				double time = (i-1) * step + length / 2;
				double xpos = time / totalTime * canvasWidth;
				double ypos = (canvasHeight - 30) - (energy[i] - min) / (max - min) * (canvasHeight - 60);
				//LDDebug.print("energy ratio: " + (min));
				gc.strokeLine(prevX, prevY, xpos, ypos);
				prevX = xpos;
				prevY = ypos;
			}
			
		}
	}

}
