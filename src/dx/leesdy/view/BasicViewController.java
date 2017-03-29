package dx.leesdy.view;

import java.io.File;

import dx.leesdy.controller.LDControlCenter;
import dx.leesdy.controller.LDWorkspaceManager;
import dx.leesdy.controller.Main;
import dx.leesdy.utils.LDDebug;
import dx.leesdy.utils.LDInformationCenter;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.FlowPane;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class BasicViewController {

	//private LDControlCenter controlCenter;
	private LDWorkspaceManager manager;
	
	private Node root;
	
	private Stage mainStage;
	
	private CanvasViewController canvasViewController;
	
	private ToolboxViewController toolboxViewController;
	
	private InformationViewController informationViewController;
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
    	this.manager = new LDWorkspaceManager();
    }
    
    /**
     * Button : Handle Open file
     */
    @FXML
    private void handleOpen() {

    	 FileChooser fileChooser = new FileChooser();
    	 fileChooser.setTitle("Open Resource File");
    	 fileChooser.getExtensionFilters().add(new ExtensionFilter("Audio Files", "*.wav", ".sph"));
    	 File selectedFile = fileChooser.showOpenDialog(mainStage);
    	 if (selectedFile != null) {
    	    //mainStage.display(selectedFile);
    		 LDDebug.print(selectedFile.getName());
    		 this.initControlCenterFromFile(selectedFile.getPath());
    	 }
    	 
    }
    
    @FXML
    private void handleClose() {
    	this.canvasViewController.removeSelectedController();
    	this.manager.removeSelectedController();
    }

    private boolean initControlCenterFromFile(String filename) {
    	LDControlCenter controlCenter = new LDControlCenter(filename);
    	this.manager.addControlCenter(controlCenter);
    	controlCenter.setFocus(true);
    	//this.setControlCenter();
    	this.initComponents();
    	return true;
    }
    
//    private void setControlCenter() {
//    	this.canvasViewController.setControlCenter(controlCenter);
//    	this.toolboxViewController.setControlCenter(controlCenter);
//    }
    
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
            canvasViewController = loader.getController();
            //canvasViewController.setControlCenter(controlCenter);
            canvasViewController.setManager(manager);
            
            // load toolbox view
            loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/ToolboxView.fxml"));
            FlowPane toolboxView = (FlowPane) loader.load();
            mainPane.setTop(toolboxView);
            toolboxViewController = loader.getController();
            //toolboxViewController.setControlCenter(controlCenter);
            toolboxViewController.setManager(manager);
            
//            LDWavPlayer player = toolboxViewController.getPlayer();
//            if (player.isInitializationSuceeded()) {
//            	player.initGraphics(canvasViewController.getCanvas());
//            }
            
            
            // load information view
            loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/InformationView.fxml"));
            AnchorPane informationView = (AnchorPane) loader.load();
            mainPane.setBottom(informationView);
            mainPane.setRight(null);
            informationViewController = loader.getController();
            
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
    
    private void initComponents() {
    	this.canvasViewController.init();
    	this.informationViewController.init();
    	this.toolboxViewController.init();
    	this.canvasViewController.addNew();
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

//	public LDControlCenter getControlCenter() {
//		return controlCenter;
//	}
//
//	public void setControlCenter(LDControlCenter controlCenter) {
//		this.controlCenter = controlCenter;
//	}

	public Stage getMainStage() {
		return mainStage;
	}

	public void setMainStage(Stage mainStage) {
		this.mainStage = mainStage;
	}

	public ToolboxViewController getToolboxViewController() {
		return toolboxViewController;
	}

	public void setToolboxViewController(ToolboxViewController toolboxViewController) {
		this.toolboxViewController = toolboxViewController;
	}

	public InformationViewController getInformationViewController() {
		return informationViewController;
	}

	public void setInformationViewController(InformationViewController informationViewController) {
		this.informationViewController = informationViewController;
	}
    
    
}
