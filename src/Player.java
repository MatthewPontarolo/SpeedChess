import java.util.ArrayList;

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

  // CONSTRUCTOR
  public Player(boolean first) {
    setUp(first);
  }

  public void setUp(bool first) {
	int PLACEHOLDER = 0; // FOR POSITION BC WE HAVEN'T DECIDED
	int offset = 0;
	if (!first)
		offset = 7;

	for (int i = 0; i < pawns; i++) {
		pieces.add(new Pawn(i, offset));
	}
	if (!first)
		offset = 6;

	pieces.add(new Rook(0, 1+offset));
	pieces.add(new Rook(7, 1+offset));

	pieces.add(new Knight(1, 1+offset));
	pieces.add(new Knight(6, 1+offset));

	pieces.add(new Bishop(2, 1+offset));
	pieces.add(new Bishop(5, 1+offset));

	pieces.add(new Queen(3, 1+offset));
	pieces.add(new King(4, 1+offset));

  }

  public ArrayList<Piece> getPieces()
  {
    return pieces;
  }

}
