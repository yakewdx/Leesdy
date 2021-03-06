package dx.leesdy.model.paintcomponents;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import dx.leesdy.model.*;

public class PDrawWav extends PainterComponent {

	protected WavWrapper wav;
	protected int [][] data;
	
	public PDrawWav(int priority, LDStatusCenter statusCenter) {
		super(priority, statusCenter);
		// TODO Auto-generated constructor stub
		wav = statusCenter.getSource();
		init();
	}
	
	public void setWav(WavWrapper wrapper) {
		wav = wrapper;
		init();
	}
	
	private void init() {
		data = wav.getData();
		this.name = "PDrawWav";
	}
	
	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		if (data != null) {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setStroke(Color.AQUA);
			gc.setLineWidth(0.5);
			double canvasWidth = canvas.getWidth();
			double canvasHeight = canvas.getHeight();
			int length = data[0].length;
			double k = canvasHeight / 2.0 / 32768.0;
			double x = 0, y = 0, prevX = 0, prevY = 0;
//			for (int i = 0; i < canvasWidth; ++i) {
//				x = i;
//				y = (int) (canvasHeight - (int)(data[0][i*step]*k + canvasHeight/2));
//				
//				if (i != 0) {
//					gc.strokeLine(x, y, prevX, prevY);
//				}
//				
//				prevX = x;
//				prevY = y;
//			}
			for (int i = 0; i < length; i++) {
				x = (double) i / (double)length * canvasWidth;
				y = canvasHeight - (data[0][i] * k + canvasHeight / 2);
				
				if (i != 0) {
					gc.strokeLine(x, y, prevX, prevY);
				}
				
				prevX = x;
				prevY = y;
			}
			
		} else {
			System.out.println("Raw data is null.");
		}
	}

	/* (non-Javadoc)
	 * @see dx.leesdy.model.paintcomponents.PainterComponent#updateState()
	 */
	@Override
	public void updateState() {
		// TODO Auto-generated method stub
		
		this.setNeedToUpdate(false);
		if (this.getDrawingCount() == 0) {
			this.setNeedToUpdate();
		}
	}

}
