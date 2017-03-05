package dx.leesdy.model.paintcomponents;

import javafx.scene.canvas.Canvas;

public abstract class PainterComponent implements Comparable<PainterComponent> {

	// id
	private int id;
	// Priority
	private int priority;
	
	public PainterComponent(int priority) {
		this.priority = priority;
	}
	
	@Override
	public int compareTo(PainterComponent o) {
		// TODO Auto-generated method stub
		return this.priority < o.priority ? 1:-1;
	}
	
	public abstract void paint(Canvas canvas);
	
}
