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
		if (validGameTurn == true)
		{
			// updates board's pieces
			spots[x][y] = p;
			// updates player's pieces
			player.movePiece(p, x, y);
		}
	}
	/**
	 * Set the position where the piece was originally to null
	 * @param Piece piece chosen 
	 */
	public void pickUpPiece(Piece p) {
		spots[p.getXPosition()][p.getYPosition()] = null;
	}

}
