package dx.leesdy.view;

import java.util.ArrayList;

import dx.leesdy.model.paintcomponents.PainterComponent;
import javafx.scene.canvas.Canvas;

public class Painter {

	private ArrayList<PainterComponent> list;
	
	private Canvas canvas;
	
	public Painter(Canvas canvas){
		this.canvas = canvas;
		list = new ArrayList<PainterComponent>();
	};
	
	public Painter addComponent(PainterComponent pc) {
		list.add(pc);
		return this;
	}
	
	public void paint() {
		list.forEach(component -> {
			component.paint(canvas);
		});
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
}
