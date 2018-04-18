public class Player
{
  // should default, hold starting # of pieces in arraylist
  // 8 PAWNS, 2 ROOKS, 2 KNIGHTS, 2 BISHOPS, 1 QUEEN, 1 KING
  private ArrayList<? extends Piece> Pieces = new ArrayList<? extends Piece>();
  private int pawns = 8;
  private int rooks = 2;
  private int knights = 2;
  private int bishops = 2;
  private int queen = 1;
  private int king = 1;

  // CONSTRUCTOR
  public Player()
  {
    int PLACEHOLDER = 0;
    for (int i = 0; i < pawns; i++)
    {
      Pieces.add(new Pawn(PLACEHOLDER));
    }
    Piece.add(new Rook(PLACEHOLDER));
    Piece.add(new Rook(PLACEHOLDER));

    Piece.add(new Knight(PLACEHOLDER));
    Piece.add(new Knight(PLACEHOLDER));

    Piece.add(new Bishop(PLACEHOLDER));
    Piece.add(new Bishop(PLACEHOLDER));

    Piece.add(new Queen(PLACEHOLDER));
    Piece.add(new King(PLACEHOLDER));

  }

  public ArrayList<? extends Piece> getPieces()
  {
    return Pieces;
  }
}
