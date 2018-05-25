public class GameHost {

	public static Player whitePlayer = new Player(1);
	public static Player blackPlayer = new Player(0);

	public static Board gameBoard = new Board(whitePlayer, blackPlayer);
	public static boolean gameTurn = false;

	public void GameHost()
	{

	}
	public static void initialize() {

	}


	public static void gameTurn()
	{
		// timer
		// if both players have selected moves, execute and move
		//this.gameTurn = true;
	}

}
