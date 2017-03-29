/**
 * Leesdy
 * dx.leesdy.model.layout
 * Author: DxWang LDCanvas.java
 * Created at:2017年3月20日
 */
package dx.leesdy.model.layout;

import dx.leesdy.model.MouseState;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.input.MouseEvent;

/**
 * 
 */
public class LDCanvas extends Canvas {

	private String description;
	
	private MouseState mouseState;
	
	public LDCanvas(String _description) {
		super();
		this.description = _description;
	}
	
	public LDCanvas() {
		super();
		this.description = "";
	}
	
	public LDCanvas(double width, double height) {
		super(width,height);
		this.description = "";
	}
	
	
	public void init(MouseState ms) {
		
		this.setMouseState(ms);
		
		this.setOnMouseMoved(new EventHandler<MouseEvent> () {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				//System.out.println("Mouse Moved");
				ms.setMouseInCanvas(true);
				ms.setX(event.getX());
				ms.setY(event.getY());
			}
			
		});
		
		this.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				ms.setMouseInCanvas(false);
				ms.setX(event.getX());
				ms.setY(event.getY());
			}
			
		});
	}
	public String getDescription() {
		return this.description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public MouseState getMouseState() {
		return mouseState;
	}

	public void setMouseState(MouseState mouseState) {
		this.mouseState = mouseState;
	}
	
}
