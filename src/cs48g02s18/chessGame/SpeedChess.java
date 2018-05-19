package cs48g02s18.chessServer.cs48g02s18.chessGame;

import java.awt.*;
import java.awt.Graphics;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
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
import java.util.ArrayList;
import java.awt.Point;

public class SpeedChess extends BorderPane {

	Button[][] buttons = new Button[8][8];
	Label[][] highlights = new Label[8][8];
	Piece selectedPiece = null;

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
			grid.getColumnConstraints().add(new ColumnConstraints(68));
			grid.getRowConstraints().add(new RowConstraints(60));
			for (int j = 0; j < 8; j++) {
				Button button = new Button();
				grid.add(button, i, j);
				buttons[i][j] = button;
				button.setAlignment(Pos.CENTER);

				final int x = i;
				final int y = j;
				button.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						Board b = GameHost.gameBoard;
						System.out.println("Clicked at " + x + " " + y);
						System.out.println("selected piece: " + selectedPiece);

						if (selectedPiece != null) {
							if (selectedPiece == b.getPiece(x, y)) {
								selectedPiece = null;
							} else {
								if (selectedPiece.getValidMoves(b, selectedPiece.getPlayer()).contains(new Point(x, y))) {
									//check whether or not it's a capture move (need to check for friendly piece)
									if (b.getPiece(x, y) != null) {
										b.getPiece(x, y).capture();
									}
									b.movePiece(selectedPiece, x, y);
									selectedPiece = null;
								} else {
									System.out.println("Invalid move!");
								}
							}
						} else {
							selectedPiece = b.getPiece(x, y);
						}

						System.out.println("selected piece is now: " + selectedPiece);
						redrawBoard();
						drawHighlights();
					}
				});
			}
		}
		grid.setAlignment(Pos.CENTER);

		GridPane overlay = new GridPane();
		overlay.setPickOnBounds(false);
		for (int i = 0; i < 8; i++) {
			overlay.getColumnConstraints().add(new ColumnConstraints(68));
			overlay.getRowConstraints().add(new RowConstraints(60));
			for (int j = 0; j < 8; j++) {
				Label label = new Label();
				overlay.add(label, i, j);
				label.setAlignment(Pos.CENTER);
				label.setMouseTransparent(true);
				highlights[i][j] = label;
			}
		}
		overlay.setAlignment(Pos.CENTER);

		StackPane stack = new StackPane();
		setCenter(stack);
		stack.getChildren().addAll(grid, overlay);

		redrawBoard();
	}

	public void redrawBoard(){
		for (Button[] bt : buttons) {
			for (Button b : bt) {
				b.setText("");
				//Later I can use the coords to determine if it should be a black or white tile
				Image im = new Image(getClass().getResourceAsStream("BlankSlot.png"));
				b.setGraphic(new ImageView(im));
			}
		}
		//Place pieces
		Player p1 = GameHost.whitePlayer;
		for (Piece p : p1.getPieces()) {
			if (p.isAlive()) {
				Button b = buttons[p.getXPosition()][p.getYPosition()];
				Image im = new Image(getClass().getResourceAsStream(p.getName() + "WhitePiece.png"));
				b.setGraphic(new ImageView(im));
			}
		}
		Player p2 = GameHost.blackPlayer;
		for (Piece p : p2.getPieces()) {
			if (p.isAlive()) {
				Button b = buttons[p.getXPosition()][p.getYPosition()];
				Image im = new Image(getClass().getResourceAsStream(p.getName() + "BlackPiece.png"));
				b.setGraphic(new ImageView(im));
			}
		}
	}

	public void drawHighlights() {
		if (selectedPiece != null) {
			Image im;
			ArrayList<Point> moves = selectedPiece.getValidMoves(GameHost.gameBoard, selectedPiece.getPlayer());
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (moves.contains(new Point(i, j))) {
						if (GameHost.gameBoard.getPiece(i, j) != null && GameHost.gameBoard.getPiece(i, j).getPlayer() != selectedPiece.getPlayer()) {
							im = new Image(getClass().getResourceAsStream("HighlightCapture.png"));
						} else {
							im = new Image(getClass().getResourceAsStream("HighlightValid.png"));
						}
						highlights[i][j].setGraphic(new ImageView(im));
					} else {
						highlights[i][j].setGraphic(null);
					}
				}
			}
			im = new Image(getClass().getResourceAsStream("HighlightSelected.png"));
			highlights[selectedPiece.getXPosition()][selectedPiece.getYPosition()].setGraphic(new ImageView(im));
		} else {
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++)
					highlights[i][j].setGraphic(null);
		}
	}

}
