package dx.leesdy.controller;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import dx.leesdy.view.BasicViewController;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;
import dx.leesdy.model.layout.*;
import dx.leesdy.utils.LDExecutor;

public class Main extends Application {
	
	
	private AnchorPane root;
	private LDScene scene;
	//private LDControlCenter controlCenter;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// init thread pool
			primaryStage.setTitle("Leesdy");
			primaryStage.getIcons().add(new Image("file:resources/icons/Icon2.png"));
			setInitScene(primaryStage);
			//scene.setOnKeyPressed(controlCenter);
			//scene.setOnKeyReleased(controlCenter);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setInitScene(Stage primaryStage) {
		
		// Load basic view
        try {
        	FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Main.class.getResource("../view/basiclayout.fxml"));
			AnchorPane BasicView = (AnchorPane) loader.load();
			root = BasicView;
			
			// init control center
//			controlCenter = new LDControlCenter();
//			controlCenter.init();
			
			BasicViewController controller = (BasicViewController)loader.getController();
			controller.setRoot(BasicView);
			//controller.setControlCenter(controlCenter);
			controller.setMainStage(primaryStage);
			
			controller.init();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
		scene = new LDScene(root);
		scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
		primaryStage.setScene(scene);
		primaryStage.show();
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {

			@Override
			public void handle(WindowEvent event) {
				// TODO Auto-generated method stub
				shutdownAndAwaitTermination(LDExecutor.getExecutor());
			}
			
		});
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void stop() throws Exception {
		
	}
	
	private void shutdownAndAwaitTermination(ExecutorService pool) {
		pool.shutdown();
		try {
			// Wait a while for existing task to terminate
			if (!pool.awaitTermination(1, TimeUnit.SECONDS)) {
				pool.shutdownNow();
				// Wait a while for tasks to respond to being cancelled
				if (!pool.awaitTermination(1, TimeUnit.SECONDS))
					System.err.println("pool did not terminate");
			}
		} catch (InterruptedException ie) {
			// (Re-)Cancel if current thread also interrupted
			pool.shutdownNow();
			// Preserve interrupt status
			Thread.currentThread().interrupt();
			}
	}
}
