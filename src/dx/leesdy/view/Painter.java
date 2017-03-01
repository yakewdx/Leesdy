package dx.leesdy.view;

import java.util.ArrayList;

import dx.leesdy.model.paintcomponents.PainterComponent;
import javafx.scene.canvas.Canvas;

public class Painter implements Runnable {

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
	
	@Override
	public void run() {
		//while (!Thread.currentThread().isInterrupted()) {
			paint();
//			try {
//				Thread.sleep(100);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		//}
	}
	
	public void paint() {
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
		list.forEach(component -> {
			component.paint(canvas);
		});
		
	}
}
