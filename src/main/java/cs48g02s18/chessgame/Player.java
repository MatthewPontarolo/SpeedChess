package cs48g02s18.chessgame;

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
	private Piece kingPiece;

	//1 = white
	//0 = black
	private int playerType;
	private Move moveSelection;
	private Board board;

	// CONSTRUCTOR
	public Player(int playerType) {
		setUp(playerType);
		this.playerType = playerType;
	}
	public Player(int playerType, Board b) {
		this.playerType = playerType;
		board = b;
		for (int x = 0; x < 8; x++){
			for (int y = 0; y < 8; y++){
				Piece piece = board.getPiece(x, y);
				if (piece != null){
					if (piece.getPlayer() == playerType){
						this.pieces.add(piece);
					}
				}
			}
		}
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
		kingPiece = new King(4, offset);
		pieces.add(kingPiece);

		for (int i = 0; i < 16; i++) {
			pieces.get(i).setPlayer(playerType);
		}
	}

	public void filterPieces()
	{
		for (int i = 0; i < pieces.size(); i++)
		{
			Piece p = pieces.get(i);
			if (p.getXPosition() == -1 || p.getYPosition() == -1)
			{
				int idx = pieces.indexOf(p);
				pieces.remove(idx);
			}
		}
	}

	public ArrayList<Piece> getPieces() {
		filterPieces();
		return pieces;
	}

	public boolean hasKing() {
		for (Piece p : pieces)
			if (p.getName() == "King")
				kingPiece = p;
		if (kingPiece == null)
			return false;
		return kingPiece.isAlive();
	}

	// get Piece in pieces arraylist at given spot on board
	public int getPieceIdx(int x, int y) {
		// get the piece from pieces array in player class
		int index = 0;
		for (Piece p : pieces) {
			int xPos = (int) p.getXPosition();
			int yPos = (int) p.getYPosition();
			if (xPos == x && yPos == y) {
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
	public void removePiece(Piece p)
	{
		int idx = pieces.indexOf(p);
		System.out.println("Remove idx: " + idx);
		pieces.remove(idx);
	}


	/**
		Makes more sense to have the UI interact directly with the player because right
		now the UI makes direct calls to the pieces and it actually never updates the pieces
		the player holds.
	**/
	public void movePiece(Piece p, int x, int y) {
		int x_i = p.getXPosition();
		int y_i = p.getYPosition();
		// find in pieces array the Piece at x_i and y_i
		int target_idx = this.getPieceIdx(x_i, y_i);
		// move Piece in pieces
		System.out.println("target_idx: " + target_idx);
		this.pieces.get(target_idx).move(x, y);
	}


}
