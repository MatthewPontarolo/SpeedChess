public class GameHost
{


  public Player whitePlayer = new Player();
  public Player blackPlayer = new Player(FALSE);

  public Board gameBoard = new Board(whitePlayer, blackPlayer);

  public GameHost()
  {


  }



}
