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
    if (first) {
      setUp();
    } else if (!first) {
      // some other
      setUp();
    }
  }

  public void setUp() {
    int PLACEHOLDER = 0; // FOR POSITION BC WE HAVEN'T DECIDED
    for (int i = 0; i < pawns; i++) {
      pieces.add(new Pawn(PLACEHOLDER, PLACEHOLDER));
    }
	pieces.add(new Rook(PLACEHOLDER, PLACEHOLDER));
	pieces.add(new Rook(PLACEHOLDER, PLACEHOLDER));

	pieces.add(new Knight(PLACEHOLDER, PLACEHOLDER));
	pieces.add(new Knight(PLACEHOLDER, PLACEHOLDER));

	pieces.add(new Bishop(PLACEHOLDER, PLACEHOLDER));
	pieces.add(new Bishop(PLACEHOLDER, PLACEHOLDER));

	pieces.add(new Queen(PLACEHOLDER, PLACEHOLDER));
	pieces.add(new King(PLACEHOLDER, PLACEHOLDER));

  }

  public ArrayList<Piece> getPieces()
  {
    return pieces;
  }

}
