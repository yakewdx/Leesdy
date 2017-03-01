package dx.leesdy.controller;
	
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import dx.leesdy.view.BasicViewController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import dx.leesdy.player.*;
import dx.leesdy.utils.LDExecutor;

public class Main extends Application {
	
	
	private static final int POOL_SIZE = 5;
	private BorderPane root;
	private Scene scene;
	
	
	@Override
	public void start(Stage primaryStage) {
		try {
			// init thread pool

			setInitScene(primaryStage);
			setCanvas(primaryStage);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void setInitScene(Stage primaryStage) {
		root = new BorderPane();
		scene = new Scene(root,400,200);
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
	
	public void setCanvas(Stage primaryStage) {
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/basiclayout.fxml"));
        AnchorPane BasicView = (AnchorPane) loader.load();
        
        BasicViewController controller = (BasicViewController)loader.getController();
        
        root.setCenter(BasicView);
        // init Media Player
        LDWavPlayer player = new LDWavPlayer("resources/8k16bitpcm.wav", root);
        controller.setPlayer(player);
        player.setViewController(controller);
        
        player.initGraphics(controller.getCanvas());
        
		} catch (Exception e) {
			e.printStackTrace();
		}
        
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
