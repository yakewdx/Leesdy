/**
 * Leesdy
 * dx.leesdy.model
 * Author: DxWang LDMultiLayerCanvas.java
 * Created at:2017年3月20日
 */
package dx.leesdy.model;

import java.util.ArrayList;

import dx.leesdy.model.layout.LDCanvas;
import dx.leesdy.utils.LDCanvasFactory;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
/**
 * 
 */
public class LDMultiLayerCanvas {

	double width;
	double height;
	
	MouseState mouseState;
	
	// Load to UI layout.
	private Pane pane;
	
	private Pane container;
	
	ArrayList<LDCanvas> layers;
	
	private void init() {
		
		this.container = new Pane();
		this.mouseState = new MouseState();
		this.layers = new ArrayList<LDCanvas>();
		
	}
	public LDMultiLayerCanvas() {
		
		// fix width
		this.width = 400;
		this.height = 200;
		init();
	}
	
	public LDMultiLayerCanvas(double width2, double height2) {
		this.width = width2;
		this.height = height2;
		init();
	}
	
	public LDCanvas createNewLayer() {
		LDCanvas canvas = LDCanvasFactory.getFactory().createCanvasWithSize(width, height);
		canvas.init(this.mouseState);
		layers.add(canvas);
		container.getChildren().add(canvas);
		//if (pane != null) pane.getChildren().add(canvas);
		return canvas;
	}
	
	public void removeLayer(LDCanvas canvas) {
		//if (pane != null) pane.getChildren().remove(canvas);
		container.getChildren().remove(canvas);
		this.layers.remove(canvas);
	}
	
	public ArrayList<LDCanvas> getLayers() {
		return layers;
	}
	
	public void setLayoutX(double x) {
		this.container.setLayoutX(x);
	}
	
	public void setLayoutY(double y) {
		this.container.setLayoutY(y);
	}
	
	public LDCanvas getLayerAtIndex(int index) {
		return layers.get(index);
	}
	/**
	 * @return the pane
	 */
	public Pane getPane() {
		return pane;
	}
	/**
	 * @param pane the pane to set
	 */
	public void setPane(Pane pane) {
		this.pane = pane;
//		for (LDCanvas layer : layers) {
//			this.pane.getChildren().add(layer);
//		}
		this.pane.getChildren().add(container);
	}
	public Pane getContainer() {
		return container;
	}
	public void setContainer(Pane container) {
		this.container = container;
	}
}
