/**
 * Leesdy
 * dx.leesdy.model.paintcomponents
 * Author: DxWang PDrawBicValue.java
 * Created at:2017年4月26日
 */
package dx.leesdy.model.paintcomponents;

import dx.leesdy.model.LDStatusCenter;
import dx.leesdy.utils.LDDebug;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * 
 */
public class PDrawBicValue extends PainterComponent {

	int counter;

	/**
	 * @param priority
	 * @param statusCenter
	 */
	public PDrawBicValue(int priority, LDStatusCenter statusCenter) {
		super(priority, statusCenter);
		// TODO Auto-generated constructor stub
		counter = 0;
		this.name = "PDrawBicValue";
	}

	/* (non-Javadoc)
	 * @see dx.leesdy.model.paintcomponents.PainterComponent#updateState()
	 */
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

	/* (non-Javadoc)
	 * @see dx.leesdy.model.paintcomponents.PainterComponent#paint(javafx.scene.canvas.Canvas)
	 */
	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		//LDDebug.print("PDrawBicValue : updating");
		double [] bic = statusCenter.getReader_T().getBicValue();
		int window_size = statusCenter.getReader_T().getBicWindowSize();
		double step = statusCenter.getReader_T().getSegStep();
		double length = statusCenter.getReader_T().getSegLen();
		
		if (bic != null) {
			// find min and max
			double min = Double.MAX_VALUE;
			double max = -Double.MAX_VALUE;
			for (int i = 0; i < bic.length; i++) {
				if (min > bic[i]) min = bic[i];
				if (max < bic[i] && !Double.isInfinite(bic[i])) max = bic[i];
			}
			
			GraphicsContext gc = canvas.getGraphicsContext2D();
			
			MediaPlayer pl = statusCenter.getMediaPlayer();

			Duration mediaLength = pl.getTotalDuration();

			Double totalTime = mediaLength.toSeconds();
			double canvasWidth = canvas.getWidth();
			double canvasHeight = canvas.getHeight();
			
			double prevX = 0;
			double prevY = canvasHeight;
			gc.setStroke(Color.WHITE);
			for (int i = 0; i < bic.length; i++) {
				double time = window_size * step + i * step+ length / 2;
				double xpos = time / totalTime * canvasWidth;
				double ypos = (canvasHeight - 30) - (bic[i] - min) / (max - min) * (canvasHeight - 60);
				//LDDebug.print("energy ratio: " + (min));
				gc.strokeLine(prevX, prevY, xpos, ypos);
				prevX = xpos;
				prevY = ypos;
			}
			
		}
	}

}
