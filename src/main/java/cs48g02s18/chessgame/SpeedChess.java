package cs48g02s18.chessgame;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javax.security.auth.login.CredentialException;
import java.util.ArrayList;
import java.awt.Point;
import java.util.Timer;
import java.util.TimerTask;

public class SpeedChess extends BorderPane {

	Button[][] buttons = new Button[8][8];
	Label[][] highlights = new Label[8][8];
	Piece selectedPiece = null;
	int playerPerspective = 0;
	ClientConnector clientConnector;
	GameHost gameHost = new GameHost();
	Point lastClickPosition;//todo using this make requests get sent coherently
	public static BorderPane masterOverlay;
	public static Label overlayLabel;
	Timer boardUpdateScheduler;

	public Move selectedMove;

	public SpeedChess() {
		GameHost gameHost = new GameHost();
		gameHost.initialize();
		this.clientConnector = new ClientConnector();
		clientConnector.setUpAndConnect();
		clientConnector.updateBoardFromServer();
		playerPerspective = clientConnector.getPlayerNumber();

		final ClientConnector connector = this.clientConnector;
		final GameHost host = this.gameHost;
		final SpeedChess thisGame = this;
		boardUpdateScheduler = new Timer("speedchess board updater");
		TimerTask timerTask = new TimerTask() {
			@Override
			public void run() {
				Platform.runLater(new Runnable() {
					@Override
					public void run(){
					connector.updateBoardFromServer();
					host.gameBoard = connector.getNewBoard();
					host.updatePlayersForUI();
					thisGame.redrawBoard();
					}
				});
			}
		};
		boardUpdateScheduler.schedule(timerTask, 1000, 1000);

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
		final GameHost gameHostFinal = gameHost;

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
						System.out.println("Clicked at " + x + " " + y);
						System.out.println("selected piece: " + selectedPiece);

						clientConnector.registerAccount();

						if (selectedPiece != null) {
							if (selectedPiece == gameHostFinal.gameBoard.getPiece(x, y)) {
								selectedPiece = null;
							} else {
								if (selectedPiece.getValidMoves(gameHostFinal.gameBoard, selectedPiece.getPlayer()).contains(new Point(x, y))) {
									Move move = new Move(selectedPiece, x, y);
									selectedMove = move;
									selectedPiece = null;
								} else {
								    selectedPiece = null;
									System.out.println("Invalid move!");
								}
							}
						} else if (gameHostFinal.gameBoard.getPiece(x, y) != null) {
							System.out.println("playerNumber? " + clientConnector.getPlayerNumber());
							if (clientConnector.getPlayerNumber() == gameHostFinal.gameBoard.getPiece(x, y).getPlayer()) {
								gameHostFinal.checkIfReady();
								clientConnector.updateBoardFromServer();
								gameHostFinal.gameBoard = clientConnector.getNewBoard();

								selectedPiece = gameHostFinal.gameBoard.getPiece(x, y);
							}
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

		StackPane stack = new StackPane();
		setCenter(stack);
		stack.getChildren().addAll(grid, overlay);

		//Setting the bottom as text for now
		HBox southBox = new HBox(10);
		setBottom(southBox);
		southBox.setPadding(new Insets(5, 5, 5, 5));
		southBox.setAlignment(Pos.CENTER);
		southBox.setStyle("-fx-background-color: #667788;");

		masterOverlay = new BorderPane();
		masterOverlay.setMouseTransparent(true);

		overlayLabel = new Label("");
		overlayLabel.setFont(new Font("Lucida Grande", 18));
		masterOverlay.setCenter(overlayLabel);

		Button confirmButton = new Button("Confirm");
		confirmButton.setOnAction(new EventHandler<ActionEvent>() {
			public void handle(ActionEvent event) {
				overlayLabel.setFont(new Font("Lucida Grande", 18));
				overlayLabel.setTextFill(Color.BLACK);
				overlayLabel.setText("");
				if (GameHost.endTurn == false) {
					confirm();
				}
			}
		});

		southBox.getChildren().addAll(confirmButton);

		redrawBoard();
	}

	public void redrawBoard() {
		for (Button[] bt : buttons) {
			for (Button b : bt) {
				b.setText("");
				//Later I can use the coords to determine if it should be a black or white tile
				Image im = new Image(getClass().getResourceAsStream("/images/BlankSlot.png"));
				b.setGraphic(new ImageView(im));
			}
		}
		Player p1 = gameHost.whitePlayer;
		for (Piece p : p1.getPieces()) {
			if (p.isAlive()) {
				Button b = buttons[p.getXPosition()][p.getYPosition()];
				Image im = new Image(getClass().getResourceAsStream("/images/" + p.getName() + "WhitePiece.png"));
				b.setGraphic(new ImageView(im));
			}
		}
		Player p2 = gameHost.blackPlayer;
		for (Piece p : p2.getPieces()) {
			if (p.isAlive()) {
				Button b = buttons[p.getXPosition()][p.getYPosition()];
				Image im = new Image(getClass().getResourceAsStream("/images/" + p.getName() + "BlackPiece.png"));
				b.setGraphic(new ImageView(im));
			}
		}
	}

	public void drawHighlights() {
		if (selectedPiece != null) {
			Image im;
			ArrayList<Point> moves = selectedPiece.getValidMoves(gameHost.gameBoard, selectedPiece.getPlayer());
			for (int i = 0; i < 8; i++) {
				for (int j = 0; j < 8; j++) {
					if (moves.contains(new Point(i, j))) {
						if (gameHost.gameBoard.getPiece(i, j) != null && gameHost.gameBoard.getPiece(i, j).getPlayer() != selectedPiece.getPlayer()) {
							im = new Image(getClass().getResourceAsStream("/images/HighlightCapture.png"));
						} else {
							im = new Image(getClass().getResourceAsStream("/images/HighlightValid.png"));
						}
						highlights[i][j].setGraphic(new ImageView(im));
					} else {
						highlights[i][j].setGraphic(null);
					}
				}
			}
			im = new Image(getClass().getResourceAsStream("/images/HighlightSelected.png"));
			highlights[selectedPiece.getXPosition()][selectedPiece.getYPosition()].setGraphic(new ImageView(im));
		} else {
			for (int i = 0; i < 8; i++)
				for (int j = 0; j < 8; j++)
					highlights[i][j].setGraphic(null);
		}
	}

	public void confirm() {
		clientConnector.submitMove(selectedMove);
		redrawBoard();
	}

	public void kingCheck() {
		GameHost.gameEnded = false;
		if (!gameHost.blackPlayer.hasKing() && !gameHost.whitePlayer.hasKing()) {
			Platform.runLater(new Runnable() {
				@Override
				public void run() {
					overlayLabel.setText("Tie game...");
				}
			});
			GameHost.gameEnded = true;
		}
		else if (!gameHost.blackPlayer.hasKing()) {
			if (playerPerspective == 0) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						overlayLabel.setText("You have been defeated...");
					}
				});
			}
			else {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						overlayLabel.setText("You have won!");
					}
				});
			}
			GameHost.gameEnded = true;
		} else if (!gameHost.whitePlayer.hasKing()) {
			if (playerPerspective == 1) {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						overlayLabel.setText("You have been defeated...");
					}
				});
			} else {
				Platform.runLater(new Runnable() {
					@Override
					public void run() {
						overlayLabel.setText("You have won!");
					}
				});

			}
			GameHost.gameEnded = true;
		}

		if (GameHost.gameEnded) {
			overlayLabel.setFont(new Font("Lucida Grande", 20));
			redrawBoard();

			for (Button[] bt : buttons) {
				for (Button b : bt) {
					b.setDisable(true);
				}
			}
			gameHost.stopTimer();
		}
	}

}
