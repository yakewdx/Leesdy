/**
 * Leesdy
 * dx.leesdy.view
 * Author: DxWang CanvasViewController.java
 * Created at:2017年3月7日
 */
package dx.leesdy.view;

import java.io.IOException;

import dx.leesdy.controller.LDControlCenter;
import dx.leesdy.controller.LDWorkspaceManager;
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
	
	//private LDControlCenter controlCenter;
	private LDWorkspaceManager manager;
	
	public void setManager(LDWorkspaceManager manager) {
		this.manager = manager;
	}

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
	private ListView<Pane> mainListView;
	
	private MediaViewController mediaViewController;
	
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

//	public LDControlCenter getControlCenter() {
//		return controlCenter;
//	}
//
//	public void setControlCenter(LDControlCenter controlCenter) {
//		this.controlCenter = controlCenter;
//	}

	@Override
	public void init() {
		// TODO Auto-generated method stub
		// this.controlCenter.getMainCanvas().setPane(pane);

	}
	
	public void addNew() {
		LDControlCenter controlCenter = this.manager.getTop();
		
		// load media view from fxml
				try {
					
					FXMLLoader loader = new FXMLLoader();
			        loader.setLocation(Main.class.getResource("../view/MediaView.fxml"));
			        AnchorPane mediaView;
					mediaView = (AnchorPane) loader.load();
					this.listView.getItems().add(mediaView);
					Pane pane = new Pane();
					controlCenter.getMainCanvas().setPane(pane);
					this.mainListView.getItems().add(pane);
					
			        //mainPane.setCenter(canvasView);
			        mediaViewController = loader.getController();
			        mediaViewController.setControlCenter(controlCenter);
			        mediaViewController.setStatusCenter(controlCenter.getStatusCenter());
			        mediaViewController.init();
			        
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	}
	
	public void remove(int id) {
		this.listView.getItems().remove(id);
		this.pane.getChildren().remove(id);
		this.manager.getControlCenterById(id).getMainCanvas().setPane(null);
		//this.controlCenter.getMainCanvas().setPane(null);
		this.mediaViewController.destroy();
		this.mediaViewController = null;
	}
	
	public void removeSelectedController() {
		this.remove(this.manager.getCurrentlySelectedID());
	}
	
}
