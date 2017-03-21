/**
 * Leesdy
 * dx.leesdy.view
 * Author: DxWang CanvasViewController.java
 * Created at:2017年3月7日
 */
package dx.leesdy.view;

import dx.leesdy.model.LDMultiLayerCanvas;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;

/**
 * 
 */
public class CanvasViewController {
	
	@FXML
	private AnchorPane anchorPane;
	
	@FXML
	private SplitPane splitPane;
	
	@FXML
	private ScrollPane scrollPane;
	
	@FXML
	private Pane pane;
	
	@FXML
	private ListView<Pane> listView;

	private LDMultiLayerCanvas canvas;
	
	@FXML
    private void initialize() {
		canvas.setPane(pane);
    	//setPlayButtonState(true);
    }
	
	public LDMultiLayerCanvas getCanvas() {
    	return this.canvas;
    }
	
	public CanvasViewController() {
		canvas = new LDMultiLayerCanvas(1000,200);
		
	}
}
