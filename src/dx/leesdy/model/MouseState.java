package dx.leesdy.model;

public class MouseState {

	public MouseState(){
		mouseInCanvas = false;
		X = 0.0;
		Y = 0.0;
	}
	
	private boolean mouseInCanvas;
	private Double X;
	private Double Y;
	
	public Double getX() {
		return X;
	}
	public void setX(Double x) {
		X = x;
	}
	
	public Double getY() {
		return Y;
	}
	public void setY(Double y) {
		Y = y;
	}
	
	public boolean isMouseInCanvas() {
		return mouseInCanvas;
	}
	public void setMouseInCanvas(boolean state) {
		mouseInCanvas = state;
	}
	
}
