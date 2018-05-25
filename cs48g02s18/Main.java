package cs48g02s18.chessGame;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.swing.*;

public class Main extends Application {
	public static Stage mainStage;
	public static cs48g02s18.chessServer.ClientConnector clientConnector;
	
	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override 
	public void start(Stage primaryStage) {
		clientConnector.setLogin((String) JOptionPane.showInputDialog("Username:"));
		clientConnector.setPass((String) JOptionPane.showInputDialog("Password:"));

		mainStage = primaryStage;
		primaryStage.setTitle("Speed Chess");
		SpeedChess scene = new SpeedChess();
		primaryStage.setScene(new Scene(scene, 800, 600));
		primaryStage.show();
	}
}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
