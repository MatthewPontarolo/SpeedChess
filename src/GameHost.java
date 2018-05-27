public class GameHost {

	public static Player whitePlayer = new Player(1);
	public static Player blackPlayer = new Player(0);

	public static Board gameBoard = new Board(whitePlayer, blackPlayer);


	public GameHost()
	{

	}
	public static void initialize() {

	}

	//Decides if it's time to go ahead and run executeGameTurn()
	public static void checkIfReady() {
		Move whiteMove = whitePlayer.getNextMove();
		Move blackMove = blackPlayer.getNextMove();
		System.out.println("wM: " + whiteMove);
		System.out.println("bM: " + blackMove);
		if (whiteMove != null && blackMove != null)
			executeGameTurn();
	}

	// Precondition: Timer runs out OR both players clicked "CONFIRM" on UI
		// no need to check valid turns bc UI doesn't allow for spot selections that
		// is not in validMoves array
	// PostCondition: GameHost acts as a referee and determines if moves can be executed.
		// calls board::movePiece on the valid pieces that can be moved
	public static void executeGameTurn()
	{
		// get both player's moves
		Move whiteMove = whitePlayer.getNextMove();
		Move blackMove = blackPlayer.getNextMove();

		int whiteX = whiteMove.getXMove();
		int whiteY = whiteMove.getYMove();

		int blackX = blackMove.getXMove();
		int blackY = blackMove.getYMove();

		whitePlayer.setNextMove(null);
		blackPlayer.setNextMove(null);

		// check if same spot move conflict
		if (whiteX == blackX && whiteY == blackY)
		{
			// check which player is faster
			if(whiteMove.getTime() < blackMove.getTime())
			{
				gameBoard.setGameTurn(true);
				gameBoard.movePiece(whitePlayer, whiteMove.getTargetPiece(), whiteX, whiteY);
				// end the turn so set it back to false
				gameBoard.setGameTurn(false);
				return;
			}
			else
			{
				gameBoard.setGameTurn(true);
				gameBoard.movePiece(blackPlayer, blackMove.getTargetPiece(), blackX, blackY);
				// end the turn so set it back to false
				gameBoard.setGameTurn(false);
				return;
			}
			// RARE but if times are exactly the same?? how to handle
		}
		// not same spot move conflict, sort out scenarios
		else
		{

			// if target is of type pawn, check if going diagonally
			// if yes, check if diagonal piece is moving
			// if yes, pawn does not move. Diagonal piece moves
			// if not going diagonally, don't check

			// capturing sequence must be implemented/specified clearly to make sure
			// piece that moves away isn't captured by other player's turn

			// passes all conflict tests
			// execute original turns
			gameBoard.setGameTurn(true);
			gameBoard.movePiece(whitePlayer, whiteMove.getTargetPiece(), whiteX, whiteY);
			gameBoard.movePiece(blackPlayer, blackMove.getTargetPiece(), blackX, blackY);
			// end the turn so set it back to false
			gameBoard.setGameTurn(false);
			return;

		}

	}

}
