package cs48g02s18.chessgame;


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	public static Stage mainStage;

	public static SpeedChess scene;

	/**
	 * Launches the application
	 */
	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	/**
	 * Starts the set of the game
	 */
	public void start(Stage primaryStage) {
		mainStage = primaryStage;
		primaryStage.setTitle("Speed Chess");
		scene = new SpeedChess();
		primaryStage.setScene(new Scene(scene, 600, 600));
		primaryStage.show();
	}
}
