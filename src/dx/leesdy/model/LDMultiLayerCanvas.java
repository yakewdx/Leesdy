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

	int width;
	int height;
	
	// Load to UI layout.
	private Pane pane;
	
	ArrayList<LDCanvas> layers;
	
	private void init() {
		
		this.layers = new ArrayList<LDCanvas>();
		
	}
	public LDMultiLayerCanvas() {
		
		// fix width
		this.width = 400;
		this.height = 200;
		init();
	}
	
	public LDMultiLayerCanvas(int width, int height) {
		this.width = width;
		this.height = height;
		init();
	}
	
	public LDCanvas createNewLayer() {
		LDCanvas canvas = LDCanvasFactory.getFactory().createCanvasWithSize(width, height);
		layers.add(canvas);
		if (pane != null) pane.getChildren().add(canvas);
		return canvas;
	}
	
	public ArrayList<LDCanvas> getLayers() {
		return layers;
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
		for (LDCanvas layer : layers) {
			this.pane.getChildren().add(layer);
		}
	}
}
