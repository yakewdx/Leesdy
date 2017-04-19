package dx.leesdy.model.paintcomponents;

import dx.leesdy.model.LDStatusCenter;
import dx.leesdy.model.layout.LDCanvas;
import javafx.scene.canvas.Canvas;

public abstract class PainterComponent implements Comparable<PainterComponent> {

	private LDCanvas canvas;
	
	private long drawingCount;
	
	static private int idCount = 0;
	// id
	private int id;
	// Priority
	private int priority;
	
	private boolean needToUpdate;
	
	private boolean needToPrintDebugMsg;
	
	protected LDStatusCenter statusCenter;
	
	protected String name;
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PainterComponent(int priority, LDStatusCenter statusCenter) {
		this.setStatusCenter(statusCenter);
		needToUpdate = false;
		this.priority = priority;
		this.id = idCount++;
		this.setDrawingCount(0);
		this.name = "";
		this.needToPrintDebugMsg = true;
	}
	
	@Override
	public int compareTo(PainterComponent o) {
		// TODO Auto-generated method stub
		return this.priority < o.priority ? 1:-1;
	}
	
	public void clearCanvas() {
		canvas.getGraphicsContext2D().clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
	}
	
	public abstract void updateState();
	
	public void update() {
		canvas.getGraphicsContext2D().save();
		paint(canvas);
		needToUpdate = false;
		this.drawingCount++;
		canvas.getGraphicsContext2D().restore();
	}
	
	public abstract void paint(Canvas canvas);
	
	public int getId() {
		return this.id;
	}
	
	public void setNeedToUpdate(boolean needtoupdate) {
		this.needToUpdate = needtoupdate;
	}
	
	public void setNeedToUpdate() {
		setNeedToUpdate(true);
	}
	
	public boolean needToUpdate() {
		return needToUpdate;
	}
	
	public int getPriority() {
		return this.priority;
	}
	
	public void setPriority(int priority) {
		this.priority = priority;
	}

	/**
	 * @return the canvas
	 */
	public LDCanvas getCanvas() {
		return canvas;
	}

	/**
	 * @param canvas the canvas to set
	 * And set the update to true
	 */
	public void setCanvas(LDCanvas canvas) {
		this.canvas = canvas;
		this.needToUpdate = true;
	}

	public LDStatusCenter getStatusCenter() {
		return statusCenter;
	}

	public void setStatusCenter(LDStatusCenter statusCenter) {
		this.statusCenter = statusCenter;
	}

	public long getDrawingCount() {
		return drawingCount;
	}

	public void setDrawingCount(long drawingCount) {
		this.drawingCount = drawingCount;
	}

	public boolean isNeedToPrintDebugMsg() {
		return needToPrintDebugMsg;
	}

	public void setNeedToPrintDebugMsg(boolean needToPrintDebugMsg) {
		this.needToPrintDebugMsg = needToPrintDebugMsg;
	}
}
