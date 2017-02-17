package dx.leesdy.controller;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

import dx.leesdy.player.*;

public class Main extends Application {
	
	
	private BorderPane root;
	private Scene scene;
	
	@Override
	public void start(Stage primaryStage) {
		try {
			
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
	}
	
	public void setCanvas(Stage primaryStage) {
		try {
		FXMLLoader loader = new FXMLLoader();
        loader.setLocation(Main.class.getResource("../view/basiclayout.fxml"));
        AnchorPane BasicView = (AnchorPane) loader.load();
        
        root.setCenter(BasicView);
        // init Media Player
        LDWavPlayer player = new LDWavPlayer("file:resources/test.mp3", root);
        
		} catch (Exception e) {
			e.printStackTrace();
		}
        
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
