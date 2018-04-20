public class GameHost
{


  public Player whitePlayer = new Player(true);
  public Player blackPlayer = new Player(false);

  public Board gameBoard = new Board(whitePlayer, blackPlayer);

  public GameHost() {


  }



}
