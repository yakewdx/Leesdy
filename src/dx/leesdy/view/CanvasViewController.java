/**
 * Leesdy
 * dx.leesdy.view
 * Author: DxWang CanvasViewController.java
 * Created at:2017年3月7日
 */
package dx.leesdy.view;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
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
	private Pane pane;
	
	@FXML
	private ListView<Pane> listView;

	private Canvas canvas;
	
	@FXML
    private void initialize() {
    	
		pane.getChildren().add(canvas);
		
    	// test drawing
    	GraphicsContext gc = canvas.getGraphicsContext2D();
    	
    	gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLUE);
        gc.setLineWidth(5);
        gc.strokeLine(40, 10, 10, 40);
        gc.fillOval(10, 60, 30, 30);
        gc.strokeOval(60, 60, 30, 30);
        gc.fillRoundRect(110, 60, 30, 30, 10, 10);
        gc.strokeRoundRect(160, 60, 30, 30, 10, 10);
        gc.fillArc(10, 110, 30, 30, 45, 240, ArcType.OPEN);
        
    	//setPlayButtonState(true);
    }
	
	public Canvas getCanvas() {
    	return this.canvas;
    }
	
	public CanvasViewController() {
		canvas = new Canvas(400,200);
	}
}
