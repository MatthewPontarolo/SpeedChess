package cs48g02s18.chessgame;

import java.util.ArrayList;

public class Board {
	// 8x8 matrix to store board positions
	private Piece[][] spots = new Piece[8][8];
	private Player whitePlayer;
	private Player blackPlayer;
	private boolean validGameTurn = false;

	public Board(Player whitePlayer, Player blackPlayer)
	{
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
		setUp(whitePlayer, blackPlayer);
	}

	public void setUp(Player whitePlayer, Player blackPlayer) {
		for (Piece p : whitePlayer.getPieces()) {
			addPiece(p);
		}
		for (Piece p : blackPlayer.getPieces()) {
			addPiece(p);
		}
	}

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
	//return piece at some spot
	public Piece getPiece(int x, int y) {
		return spots[x][y];
	}

	public void addPiece(Piece p) {
		spots[p.getXPosition()][p.getYPosition()] = p;
	}

	public void setGameTurn(boolean valid)
	{
		validGameTurn = valid;
	}

	// Should be called through gameHost after it allows it
	// Precondition: GameHost validates and allows gameTurn to execute
	// PostCondition: Board situation is updated, player's piece is updated, move is executed
		// Board and Player pieces are in the same situation
	public void movePiece(Player player, Piece p, int x, int y) {
		if (validGameTurn == true)
		{
			// updates board's pieces
			spots[x][y] = p;
			// updates player's pieces
			player.movePiece(p, x, y);
		}
	}

	public void pickUpPiece(Piece p) {
		spots[p.getXPosition()][p.getYPosition()] = null;
	}

}
