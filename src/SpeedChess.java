import java.awt.*;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
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
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.geometry.Pos;
import javafx.geometry.Insets;

public class SpeedChess extends BorderPane {

	Button[][] buttons = new Button[8][8];

	public SpeedChess() {
		GameHost.initialize();

		//Setting the top as text for now
		HBox northBox = new HBox(10);
		setTop(northBox);
		northBox.setPadding(new Insets(5, 5, 5, 5));
		northBox.setAlignment(Pos.CENTER);
		northBox.setStyle("-fx-background-color: #667788;");

		Label testLabel = new Label("Speed Chess");
		testLabel.setFont(new Font("Lucida Grande", 18));

		northBox.getChildren().addAll(testLabel);

		//Setting up the chess grid in the center
		GridPane grid = new GridPane();
		for (int i = 0; i < 8; i++) {
			grid.getColumnConstraints().add(new ColumnConstraints(65));
			grid.getRowConstraints().add(new RowConstraints(65));
			for (int j = 0; j < 8; j++) {
				Button button = new Button();
				grid.add(button, i, j);
				buttons[i][j] = button;
				button.setAlignment(Pos.CENTER);

				button.setOnAction(new EventHandler<ActionEvent> {
					Board b = GameHost.gameBoard;
					int x = i;
					int y = j;
					if (selectedPiece != null) {
						if (selectedPiece == b.getPiece(x, y)) {
							selectedPiece = null;
						} else {
							b.movePiece(selectedPiece, x, y);
						}
					} else {
						selectedPiece = b.getPiece(x, y);
					}
					redrawBoard();
				});
			}
		}
		grid.setAlignment(Pos.CENTER);
		setCenter(grid);

		//Create pieces
		Player p1 = GameHost.whitePlayer;
		for (Piece p : p1.getPieces()) {
			buttons[p.getXPosition()][p.getYPosition()].setText(p.getName());
		}
		Player p2 = GameHost.blackPlayer;
		for (Piece p : p2.getPieces()) {
			buttons[p.getXPosition()][p.getYPosition()].setText(p.getName());
		}
	}

	public void redrawBoard(){
		for (Button b : buttons) {
			b.setText("");
		}
		//Place pieces
		Player p1 = GameHost.whitePlayer;
		for (Piece p : p1.getPieces()) {
			buttons[p.getXPosition()][p.getYPosition()].setText(p.getName());
		}
		Player p2 = GameHost.blackPlayer;
		for (Piece p : p2.getPieces()) {
			buttons[p.getXPosition()][p.getYPosition()].setText(p.getName());
		}
	}
	
}