package dx.leesdy.model.paintcomponents;

import javafx.collections.ObservableList;
import javafx.collections.ObservableMap;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.media.Media;
import javafx.scene.media.Track;
import javafx.scene.paint.Color;

import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import dx.leesdy.model.*;

public class PDrawWav extends PainterComponent {

	protected WavWrapper wav;
	protected int [][] data;
	
	public PDrawWav(int priority) {
		super(priority);
		// TODO Auto-generated constructor stub
	}
	
	public PDrawWav(int priority, WavWrapper wrapper) {
		super(priority);
		wav = wrapper;
		init();
	}
	
	public void setWav(WavWrapper wrapper) {
		wav = wrapper;
		init();
	}
	
	private void init() {
		data = wav.getData();
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
			int step = (int) (length / canvasWidth);
			double k = canvasHeight / 2.0 / 32768.0;
			int x = 0, y = 0, prevX = 0, prevY = 0;
			for (int i = 0; i < canvasWidth; ++i) {
				x = i;
				y = (int) (canvasHeight - (int)(data[0][i*step]*k + canvasHeight/2));
				
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

}
