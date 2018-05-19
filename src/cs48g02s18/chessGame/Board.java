package cs48g02s18.chessServer.cs48g02s18.chessGame;

import java.util.ArrayList;

public class Board {
	// 8x8 matrix to store board positions
	private Piece[][] spots = new Piece[8][8];

	public Board(Player player1, Player player2)
	{
	setUp(player1, player2);
	}

	public void setUp(Player player1, Player player2) {
		for (Piece p : player1.getPieces()) {
			addPiece(p);
		}
		for (Piece p : player2.getPieces()) {
			addPiece(p);
		}
	}

	//return piece at some spot
	public Piece getPiece(int x, int y) {
		return spots[x][y];
	}

	public void addPiece(Piece p) {
		spots[p.getXPosition()][p.getYPosition()] = p;
	}

	public void movePiece(Piece p, int x, int y) {
		spots[p.getXPosition()][p.getYPosition()] = null;
		spots[x][y] = p;
		p.move(x, y);

	}

}
