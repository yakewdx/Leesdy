package dx.leesdy.model.paintcomponents;

import javafx.scene.canvas.Canvas;

public abstract class PainterComponent implements Comparable<PainterComponent> {

	static private int idCount = 0;
	// id
	private int id;
	// Priority
	private int priority;
	
	public PainterComponent(int priority) {
		this.priority = priority;
		this.id = idCount++;
	}
	
	@Override
	public int compareTo(PainterComponent o) {
		// TODO Auto-generated method stub
		return this.priority < o.priority ? 1:-1;
	}
	
	public abstract void paint(Canvas canvas);
	
	public int getId() {
		return this.id;
	}
	
	public int getPriority() {
		return this.priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}
}
