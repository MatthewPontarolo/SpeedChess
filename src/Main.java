import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

import java.net.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class Main extends Application {
	public static Stage mainStage;
	public static Boolean clientCheck = false;

	public static void main(String[] args) {
		Application.launch(args);
	}

	@Override
	public void start(Stage primaryStage) {
		mainStage = primaryStage;
		primaryStage.setTitle("Speed Chess");
		SpeedChess scene = new SpeedChess();
		primaryStage.setScene(new Scene(scene, 600, 600));
		primaryStage.show();
	}

	public static void tryToBeServer() {
		try {
			Server.setup();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static void tryToBeClient() {
		try {
			while (!clientCheck) {
				Client.setup();
			}
			clientCheck = false;
		} catch (IOException ex) {
			System.out.println(ex.getMessage());
		}
	}
}
