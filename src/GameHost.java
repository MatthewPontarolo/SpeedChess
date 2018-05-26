public class GameHost {

	public static Player whitePlayer = new Player(1);
	public static Player blackPlayer = new Player(0);

	public static Board gameBoard = new Board(whitePlayer, blackPlayer);


	public void GameHost()
	{

	}
	public static void initialize() {

	}

	// Precondition: Timer runs out OR both players clicked "CONFIRM" on UI
		// no need to check valid turns bc UI doesn't allow for spot selections that
		// is not in validMoves array
	// PostCondition: GameHost acts as a referee and determines if moves can be executed.
	public void executeGameTurn()
	{
		// get both player's moves
		Move whiteMove = whitePlayer.getNextMove();
		Move blackMove = blackPlayer.getNextMove();
		int whiteX = whiteMove.getXMove();

		int blackX = blackMove.getXMove();
		int whiteY = whiteMove.getYMove();
		int blackY = blackMove.getYMove();

		// check if same spot move conflict
		if (whiteX == blackX && whiteY == blackY)
		{
			// check which player is faster
			if(whiteMove.getTime() < blackMove.getTime())
			{
				gameBoard.setGameTurn(true);
				gameBoard.movePiece(whitePlayer, whiteMove.getTargetPiece(), whiteX, whiteY);
				return;
			}
			else
			{
				gameBoard.setGameTurn(true);
				gameBoard.movePiece(blackPlayer, blackMove.getTargetPiece(), blackX, blackY);
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

		}

	}

}
