package dx.leesdy.view;

import dx.leesdy.controller.Main;
import dx.leesdy.player.LDWavPlayer;
import dx.leesdy.utils.LDInformationCenter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;

public class BasicViewController {

	private Node root;
	
	/**
     * The constructor.
     * The constructor is called before the initialize() method.
     */
    public BasicViewController() {
    }
    
    /**
     * Initializes the controller class. This method is automatically called
     * after the fxml file has been loaded.
     */
    @FXML
    private void initialize() {
    	
    }

    public void init() {
    	// todo: initialize other views
    	
    	try {
    		BorderPane mainBorderPane =(BorderPane)(((AnchorPane)root).getChildren().get(0));
    		BorderPane mainPane = (BorderPane)(mainBorderPane.getChildren().get(0));
    		
    		// load canvas view
    		FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/CanvasView.fxml"));
            AnchorPane canvasView = (AnchorPane) loader.load();
            mainPane.setCenter(canvasView);
            CanvasViewController canvasViewController = loader.getController();
            
            
            // load toolbox view
            loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/ToolboxView.fxml"));
            FlowPane toolboxView = (FlowPane) loader.load();
            mainPane.setTop(toolboxView);
            ToolboxViewController toolboxViewController = loader.getController();
            LDWavPlayer player = toolboxViewController.getPlayer();
            if (player.isInitializationSuceeded()) {
            	player.initGraphics(canvasViewController.getCanvas());
            }
            
            
            // load information view
            loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/InformationView.fxml"));
            AnchorPane informationView = (AnchorPane) loader.load();
            mainPane.setBottom(informationView);
            mainPane.setRight(null);
            InformationViewController informationViewController = loader.getController();
            LDInformationCenter.getInstance().addObserver(informationViewController);
            
            
            // init Media Player
            //LDWavPlayer player = new LDWavPlayer("resources/8k16bitpcm.wav", root);
            //controller.setPlayer(player);
            //player.setViewController(controller);
            
            //player.initGraphics(controller.getCanvas());
            
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    }

	/**
	 * @return the root
	 */
	public Node getRoot() {
		return root;
	}

	/**
	 * @param root the root to set
	 */
	public void setRoot(Node root) {
		this.root = root;
	}
    
    
}
