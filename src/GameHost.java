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

	// UI should give this function the moves
	public void gameTurn()
	{
		// timer
		// if two move selections received, determine who gets a move
		// call gameBoard.movePiece()
	}

}
