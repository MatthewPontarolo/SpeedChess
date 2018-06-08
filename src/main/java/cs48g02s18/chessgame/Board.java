package cs48g02s18.chessgame;

import java.util.ArrayList;

/**
 * Manages game board's situation in chess game
 */
public class Board {
	/**
	 * 8x8 matrix to store positions on gameboard
	 */
	private Piece[][] spots = new Piece[8][8];
	/**
	 * stores the whitePlayer in game
	 */
	private Player whitePlayer;
	/**
	 * stores the blackPlayer in game
	 */
	private Player blackPlayer;
	/**
	 * determines if a game turn is allowed to be executed
	 */
	private boolean validGameTurn = false;

	/**
	 * Constructor
	 * @param Player whitePlayer
	 * @param Player blackPlayer
	 */
	public Board(Player whitePlayer, Player blackPlayer)
	{
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
		setUp(whitePlayer, blackPlayer);
	}

	/**
	 * Sets up the pieces on the board
	 * @param Player whitePlayer
	 * @param Player blackPlayer
	 */
	public void setUp(Player whitePlayer, Player blackPlayer) {
		for (Piece p : whitePlayer.getPieces()) {
			addPiece(p);
		}
		for (Piece p : blackPlayer.getPieces()) {
			addPiece(p);
		}
	}

	/**
	 * Gets player when given playertype index
	 * @param int playerType
	 * @return Player
	 */
	public Player getPlayer(int playerType)
	{
		if (playerType == 1)
		{
			return this.whitePlayer;
		}
		else
		{
			return this.blackPlayer;
		}
	}
	/**
	 * Returns piece at a position on the board
	 * @param int x: X coordinate of board position
	 * @param int y: Y coordinate of board position
	 */
	public Piece getPiece(int x, int y) {
		return spots[x][y];
	}

	/**
	 * Adds piece onto board
	 * @param Piece Piece to add to board
	 */
	public void addPiece(Piece p) {
		spots[p.getXPosition()][p.getYPosition()] = p;
	}

	/**
	 * Establishes when a game turn is happening
	 * @param boolean valid holds the condition whether the moves are correct
	 */
	public void setGameTurn(boolean valid)
	{
		validGameTurn = valid;
	}

	// Should be called through gameHost after it allows it
	// Precondition: GameHost validates and allows gameTurn to execute
	// PostCondition: Board situation is updated, player's piece is updated, move is executed
		// Board and Player pieces are in the same situation
		/**
		 * Called through gameHost after it allows it
		 * @param Player player
		 * @param Piece piece
		 * @param int x and y of the coordinates of the piece
		 * board situation is updated, player's piece is udpated, move is executed
		 */
	public void movePiece(Player player, Piece p, int x, int y) {
		if (validGameTurn == true) {
			// updates board's pieces
			spots[x][y] = p;
			// updates player's pieces
			player.movePiece(p, x, y);
		}
	}
	public void removePiece(Player player, Piece p)
	{
		if (p.isAlive() == false)
		{
			player.removePiece(p);
		}
	}

	/**
	 * Set the position where the piece was originally to null
	 * @param Piece piece chosen
	 */
	public void pickUpPiece(Piece p) {
		if (p.getXPosition() >= 0 && p.getXPosition() < 8 &&
			p.getYPosition() >= 0 && p.getYPosition() < 8) {
			spots[p.getXPosition()][p.getYPosition()] = null;
		} else {
			System.out.println("invalid pickup at " + p.getXPosition() + " " + p.getYPosition());
		}
	}


	@Override
	public String toString() {
		StringBuilder boardString = new StringBuilder();
		Piece spot;
		String spotName;
		for (int i = 0; i < 64; i++) {
			spot = spots[i / 8][i % 8];
			if (spot == null) {
				boardString.append("0");
			} else {
				if (spot instanceof Pawn) {
					spotName = "p";
				} else if (spot instanceof Rook) {
					spotName = "r";
				} else if (spot instanceof Queen) {
					spotName = "q";
				} else if (spot instanceof King) {
					spotName = "k";
				} else if (spot instanceof Bishop) {
					spotName = "b";
				} else if (spot instanceof Knight) {
					spotName = "n";
				} else {
					spotName = "!";
				}
				if (spot.getPlayer() == 0) {
					boardString.append(spotName.toUpperCase());
				}
				else {
					boardString.append(spotName);
				};
			}
		}

		return boardString.toString();
	}

	public Board(String boardString) {
		Character currentChar;
		for (int i = 0; i < 64; i++) {
			currentChar = boardString.charAt(i);
			if (currentChar == '0'){
				spots[i / 8][i % 8] = null;
			}
			else if (currentChar == 'p'){
				spots[i / 8][i % 8] = new Pawn(i / 8, i % 8);
				spots[i / 8][i % 8].setPlayer(1);
			}
			else if (currentChar == 'P'){
				spots[i / 8][i % 8] = new Pawn(i / 8, i % 8);
				spots[i / 8][i % 8].setPlayer(0);
			}
			else if (currentChar == 'r'){
				spots[i / 8][i % 8] = new Rook(i / 8, i % 8);
				spots[i / 8][i % 8].setPlayer(1);
			}
			else if (currentChar == 'R'){
				spots[i / 8][i % 8] = new Rook(i / 8, i % 8);
				spots[i / 8][i % 8].setPlayer(0);
			}
			else if (currentChar == 'q'){
				spots[i / 8][i % 8] = new Queen(i / 8, i % 8);
				spots[i / 8][i % 8].setPlayer(1);
			}
			else if (currentChar == 'Q'){
				spots[i / 8][i % 8] = new Queen(i / 8, i % 8);
				spots[i / 8][i % 8].setPlayer(0);
			}
			else if (currentChar == 'k'){
				spots[i / 8][i % 8] = new King(i / 8, i % 8);
				spots[i / 8][i % 8].setPlayer(1);
			}
			else if (currentChar == 'K'){
				spots[i / 8][i % 8] = new King(i / 8, i % 8);
				spots[i / 8][i % 8].setPlayer(0);
			}
			else if (currentChar == 'b'){
				spots[i / 8][i % 8] = new Bishop(i / 8, i % 8);
				spots[i / 8][i % 8].setPlayer(1);
			}
			else if (currentChar == 'B'){
				spots[i / 8][i % 8] = new Bishop(i / 8, i % 8);
				spots[i / 8][i % 8].setPlayer(0);
			}
			else if (currentChar == 'n'){
				spots[i / 8][i % 8] = new Knight(i / 8, i % 8);
				spots[i / 8][i % 8].setPlayer(1);
			}
			else if (currentChar == 'N'){
				spots[i / 8][i % 8] = new Knight(i / 8, i % 8);
				spots[i / 8][i % 8].setPlayer(0);
			}
			whitePlayer = new Player(1, this);
			blackPlayer = new Player(0, this);
		}
	}
}
