package dx.leesdy.model.paintcomponents;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

import java.text.SimpleDateFormat;
import java.util.Date;

import dx.leesdy.model.*;

public class PDrawVerticalLine extends PainterComponent {

	public PDrawVerticalLine(int priority) {
		super(priority);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void paint(Canvas canvas) {
		// TODO Auto-generated method stub
		
		MouseState ms = DeviceState.getInstance().getMouseState();
		
		if (ms.isMouseInCanvas()) {
			GraphicsContext gc = canvas.getGraphicsContext2D();
			gc.setStroke(Color.WHITE);
			gc.setLineWidth(0.5);
			gc.strokeLine(ms.getX(), 0, ms.getX(), canvas.getHeight());
		}
		
	}

}
