package dx.leesdy.model.paintcomponents;

import java.util.*;

import dx.leesdy.model.LDStatusCenter;

import java.text.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PTimer extends PainterComponent {

	
	public PTimer(int priority, LDStatusCenter statusCenter) {
		super(priority, statusCenter);
		// TODO Auto-generated constructor stub
		this.name = "PTimer";
	}
	
	
	// Only for test
	// No Use
	@Override
	public void paint(Canvas canvas) {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss"); 
		String s = df.format(new Date());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.BLUE);
		gc.fillText(s, 10, 10);                    
	}
	
	@Override
	public void updateState() {
		this.setNeedToUpdate();
	}
}
