package dx.leesdy.model.paintcomponents;

import java.util.*;

import dx.leesdy.model.LDStatusCenter;

import java.text.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;

public class PTimer extends PainterComponent {

	
	public PTimer(int priority, LDStatusCenter statusCenter) {
		super(priority, statusCenter);
		// TODO Auto-generated constructor stub
	}
	
	
	// Only for test
	// No Use
	@Override
	public void paint(Canvas canvas) {
		SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss"); 
		String s = df.format(new Date());
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.fillText(s, 10, 10);                    
	}
	
	@Override
	public void updateState() {
		this.setNeedToUpdate();
	}
}
