package speedchess;

import java.util.ArrayList;
import java.awt.Point;

public class Player {
	// should default, hold starting # of pieces in arraylist
	// 8 PAWNS, 2 ROOKS, 2 KNIGHTS, 2 BISHOPS, 1 QUEEN, 1 KING
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	private int pawns = 8;
	private int rooks = 2;
	private int knights = 2;
	private int bishops = 2;
	private int queen = 1;
	private int king = 1;

	private int playerType;
	private Move moveSelection;
	//1 = white
	//0 = black

	// CONSTRUCTOR
	public Player(int playerType) {
		setUp(playerType);
		this.playerType = playerType;
	}

	public void setUp(int playerType) {
		int PLACEHOLDER = 0; // FOR POSITION BC WE HAVEN'T DECIDED
		int offset = 0;
		if (playerType == 0)
			offset = 5;

		for (int i = 0; i < pawns; i++) {
			pieces.add(new Pawn(i, 1+offset));
		}
		if (playerType == 0)
			offset = 7;

		pieces.add(new Rook(0, offset));
		pieces.add(new Rook(7, offset));

		pieces.add(new Knight(1, offset));
		pieces.add(new Knight(6, offset));

		pieces.add(new Bishop(2, offset));
		pieces.add(new Bishop(5, offset));

		pieces.add(new Queen(3, offset));
		pieces.add(new King(4, offset));

		for (int i = 0; i < 16; i++) {
			pieces.get(i).setPlayer(playerType);
		}
	}

	public ArrayList<Piece> getPieces() {
		return pieces;
	}

	// get Piece in pieces arraylist at given spot on board
	public int getPieceIdx(int x, int y)
	{
		// get the piece from pieces array in player class
		int index = 0;
		for (Piece p : pieces)
		{
			int xPos = (int) p.getXPosition();
			int yPos = (int) p.getYPosition();
			if(xPos == x && yPos == y)
			{
				return index;
			}
			index++;
		}
		return 0;
	}

	public int getPlayerType()
	{
		return playerType;
	}

	public void setNextMove(Move moveSelection)
	{
		this.moveSelection = moveSelection;
	}

	public Move getNextMove()
	{
		return moveSelection;
	}


	/**
		Makes more sense to have the UI interact directly with the player because right
		now the UI makes direct calls to the pieces and it actually never updates the pieces
		the player holds.
	**/
	public void movePiece(Piece p, int x, int y)
	{
		int x_i = p.getXPosition();
		int y_i = p.getYPosition();
		// find in pieces array the Piece at x_i and y_i
		int target_idx = this.getPieceIdx(x_i, y_i);
		// move Piece in pieces
		this.pieces.get(target_idx).move(x, y);
	}


}
