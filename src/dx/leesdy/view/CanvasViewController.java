/**
 * Leesdy
 * dx.leesdy.view
 * Author: DxWang CanvasViewController.java
 * Created at:2017年3月7日
 */
package dx.leesdy.view;

import java.io.IOException;

import dx.leesdy.controller.LDControlCenter;
import dx.leesdy.controller.Main;
import dx.leesdy.model.LDMultiLayerCanvas;
import dx.leesdy.utils.LDInitilizableComponent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
public class CanvasViewController implements LDInitilizableComponent{
	
	private LDControlCenter controlCenter;
	
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
	
	@FXML
    private void initialize() {
    	//setPlayButtonState(true);
    }
	
//	public LDMultiLayerCanvas getCanvas() {
//    	return this.canvas;
//    }
	
	public CanvasViewController() {

		//canvas = new LDMultiLayerCanvas(2000,200);
		
	}

	public LDControlCenter getControlCenter() {
		return controlCenter;
	}

	public void setControlCenter(LDControlCenter controlCenter) {
		this.controlCenter = controlCenter;
	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		this.controlCenter.getCanvas().setPane(pane);
		
		// load media view from fxml
		try {
			
			FXMLLoader loader = new FXMLLoader();
	        loader.setLocation(Main.class.getResource("../view/MediaView.fxml"));
	        AnchorPane mediaView;
			mediaView = (AnchorPane) loader.load();
			this.listView.getItems().add(mediaView);
	        //mainPane.setCenter(canvasView);
	        MediaViewController mediaViewController = loader.getController();
	        mediaViewController.setControlCenter(controlCenter);
	        mediaViewController.setStatusCenter(this.controlCenter.getPlayer().getStatusCenter());
	        mediaViewController.init();
	        
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        

	}
	
}
