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

	// UI should make new Move objects to store move selections
	// give it to GameHost::gameTurn so it can determine if move conflicts
	// then decide who gets to move
	public void gameTurn(Move whitePlayer, Move blackPlayer)
	{
		// timer
		// if two move selections received, determine who gets a move
		// call gameBoard.movePiece()
	}

}
