public class Board
{
  // 8x8 matrix to store board positions
  private Piece[][] spots = new Piece[8][8];
  private Player[] players;
  private int currentPlayer;
  private final static int A = 1;
  private final char horizontal[] = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};
  private final int vertical[] = {1, 2, 3, 4, 5, 6, 7, 8};

  public Board()
  {
     setUp();
  }

  public void setUp(Player player1, Player player2)
  {
    ArrayList<? extends Pieces> pieces_1 = player1.getPieces();
    ArrayList<? extends Pieces> pieces_2 = player2.getPieces();

    for (int i = 0; i < pieces_1.length(); i++)
    {
      int Xposition = pieces_1.get(i).getXPosition();
      int Yposition = pieces_1.get(i).getYPosition();
      this.spots[Xposition][Yposition] = pieces_1.get(i);

      int Xposition = pieces_2.get(i).getXPosition();
      int Yposition = pieces_2.get(i).getYPosition();
      this.spots[Xposition][Yposition] = pieces_2.get(i);
    }

  }

  public Piece getPiece(int x, int y) //return piece at some spot
  {
      //return some combination of horizontal and vertical
  }

  public void addPlayer(Player player, int index)
  {
    //idk i thought we should use this to keep track of players??
  }

}
