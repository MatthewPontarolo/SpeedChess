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
import javafx.scene.layout.Pane;
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
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

public class SpeedChess extends BorderPane {

	public static Button[][] buttons = new Button[8][8];
	public static Label[][] highlights = new Label[8][8];
	Piece selectedPiece = null;
	public static int playerPerspective = 0;
	public static boolean readyToSend = false;
	public static BorderPane masterOverlay;
	public static Label overlayLabel;

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

		Button serverButton = new Button("Black Player");
		Button clientButton = new Button("White Player");

		serverButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				//Main.tryToBeServer();
				//Black
				setupBoard();
				redrawBoard();
				northBox.getChildren().remove(1);
				northBox.getChildren().remove(1);
			}
		});
		clientButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				//Main.tryToBeClient();
				playerPerspective = 1;//White
				setupBoard();
				redrawBoard();
				northBox.getChildren().remove(1);
				northBox.getChildren().remove(1);
			}
		});

		northBox.getChildren().addAll(testLabel, serverButton, clientButton);

		//Setting the bottom as text for now
		HBox southBox = new HBox(10);
		setBottom(southBox);
		southBox.setPadding(new Insets(5, 5, 5, 5));
		southBox.setAlignment(Pos.CENTER);
		southBox.setStyle("-fx-background-color: #667788;");

		Button confirmButton = new Button("Confirm");
		confirmButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {

				Move m = GameHost.players[playerPerspective].getNextMove();

				if (m != null) {
					masterOverlay.setVisible(true);

					Timestamp timestamp = new Timestamp(System.currentTimeMillis());
					m.setTime(timestamp.getTime());
					System.out.println(timestamp.getTime());

					readyToSend = true;
					if (playerPerspective == 0) {
						Server.setMoveToSend(m);
						Main.tryToBeServer();
					} else {
						Client.setMoveToSend(m);
						Main.tryToBeClient();
					}
					GameHost.checkIfReady();
					readyToSend = false;

					masterOverlay.setVisible(false);
				}
			}
		});

		southBox.getChildren().addAll(confirmButton);
	}

	public void setupBoard() {
		//Setting up the chess grid in the center
		GridPane grid = new GridPane();
		for (int i = 0; i < 8; i++) {
			grid.getColumnConstraints().add(new ColumnConstraints(68));
			grid.getRowConstraints().add(new RowConstraints(60));
			for (int j = 0; j < 8; j++) {
				Button button = new Button();
				if (playerPerspective == 0)
					grid.add(button, i, j);
				else
					grid.add(button, 7-i, 7-j);
				buttons[i][j] = button;
				button.setAlignment(Pos.CENTER);

				final int x = i;
				final int y = j;
				button.setOnAction(new EventHandler<ActionEvent>() {
					public void handle(ActionEvent event) {
						Board b = GameHost.gameBoard;
						//System.out.println("Clicked at " + x + " " + y);
						//System.out.println("selected piece: " + selectedPiece);

						if (selectedPiece != null) {
							if (selectedPiece == b.getPiece(x, y)) {
								selectedPiece = null;
							} else {
								if (selectedPiece.getValidMoves(b, selectedPiece.getPlayer()).contains(new Point(x, y))) {
									int playerType = selectedPiece.getPlayer();
									b.getPlayer(playerType).setNextMove(new Move(selectedPiece, x, y));
									selectedPiece = null;
								} else {
									System.out.println("Invalid move!");
								}
							}
						} else if (b.getPiece(x, y) != null && b.getPiece(x, y).getPlayer() == playerPerspective) {
							selectedPiece = b.getPiece(x, y);
						}

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
				if (playerPerspective == 0)
					overlay.add(label, i, j);
				else
					overlay.add(label, 7-i, 7-j);
				label.setAlignment(Pos.CENTER);
				label.setMouseTransparent(true);
				highlights[i][j] = label;
			}
		}
		overlay.setAlignment(Pos.CENTER);

		masterOverlay = new BorderPane();
		masterOverlay.setMouseTransparent(true);
		masterOverlay.setVisible(false);

		overlayLabel = new Label("Waiting for other player to make their move...");
		overlayLabel.setFont(new Font("Lucida Grande", 18));
		overlayLabel.setAlignment(Pos.CENTER);
		//masterOverlay.setAlignment(Pos.CENTER);
		masterOverlay.setCenter(overlayLabel);

		StackPane stack = new StackPane();
		setCenter(stack);
		stack.getChildren().addAll(grid, overlay, masterOverlay);
	}

	public static void redrawBoard() {
		for (Button[] bt : buttons) {
			for (Button b : bt) {
				b.setText("");
				//Later I can use the coords to determine if it should be a black or white tile
				Image im = new Image(SpeedChess.class.getResourceAsStream("BlankSlot.png"));
				b.setGraphic(new ImageView(im));
			}
		}
		//Place pieces
		Player p1 = GameHost.whitePlayer;
		for (Piece p : p1.getPieces()) {
			if (p.isAlive()) {
				Button b = buttons[p.getXPosition()][p.getYPosition()];
				Image im = new Image(SpeedChess.class.getResourceAsStream(p.getName() + "WhitePiece.png"));
				b.setGraphic(new ImageView(im));
			}
		}
		Player p2 = GameHost.blackPlayer;
		for (Piece p : p2.getPieces()) {
			if (p.isAlive()) {
				Button b = buttons[p.getXPosition()][p.getYPosition()];
				Image im = new Image(SpeedChess.class.getResourceAsStream(p.getName() + "BlackPiece.png"));
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
							im = new Image(SpeedChess.class.getResourceAsStream("HighlightCapture.png"));
						} else {
							im = new Image(SpeedChess.class.getResourceAsStream("HighlightValid.png"));
						}
						highlights[i][j].setGraphic(new ImageView(im));
					} else {
						highlights[i][j].setGraphic(null);
					}
				}
			}
			im = new Image(SpeedChess.class.getResourceAsStream("HighlightSelected.png"));
			highlights[selectedPiece.getXPosition()][selectedPiece.getYPosition()].setGraphic(new ImageView(im));
		} else {
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++)
					highlights[i][j].setGraphic(null);
		}
	}

	public static void kingCheck() {
		System.out.println("kings? " + GameHost.players[0].hasKing() + " " + GameHost.players[1].hasKing()));
		if (!GameHost.players[0].hasKing() && !GameHost.players[1].hasKing()) {
			overlayLabel.setText("Tie game...");
			masterOverlay.setVisible(true);
		} else if (!GameHost.players[0].hasKing()) {
			if (playerPerspective == 0) {
				overlayLabel.setText("You have been defeated...");
			} else {
				overlayLabel.setText("You have won!");
			}
			masterOverlay.setVisible(true);
		} else if (GameHost.players[1].hasKing()) {
			if (playerPerspective == 1) {
				overlayLabel.setText("You have been defeated...");
			} else {
				overlayLabel.setText("You have won!");
			}
			masterOverlay.setVisible(true);
		}
	}

}
