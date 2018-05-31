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
	public static void checkIfReady()
	{
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
		// TODO: deal with capture situations
	public static void executeGameTurn()
	{
		// get both player's moves
		Move whiteMove = whitePlayer.getNextMove();
		Move blackMove = blackPlayer.getNextMove();

		// get both player's target pieces that are moving
		Piece whiteTarget = whiteMove.getTargetPiece();
		Piece blackTarget = blackMove.getTargetPiece();

		// white player's move selection coordinates
		int whiteX = whiteMove.getXMove();
		int whiteY = whiteMove.getYMove();

		// black player's move selection coordinates
		int blackX = blackMove.getXMove();
		int blackY = blackMove.getYMove();

		whitePlayer.setNextMove(null);
		blackPlayer.setNextMove(null);

		// check if same spot move conflict
		if (whiteX == blackX && whiteY == blackY) {
			// if white is faster than black
			if (whiteMove.getTime() < blackMove.getTime())
			{
				gameBoard.setGameTurn(true);
				gameBoard.movePiece(whitePlayer, whiteTarget, whiteX, whiteY);
				//Capture the black piece
				blackTarget.capture();
				// end the turn so set it back to false
				gameBoard.setGameTurn(false);
				return;
			}
			// if black is faster than white
			else if (whiteMove.getTime() > blackMove.getTime())
			{
				gameBoard.setGameTurn(true);
				gameBoard.movePiece(blackPlayer, blackTarget, blackX, blackY);
				//Capture the white piece
				whiteTarget.capture();
				// end the turn so set it back to false
				gameBoard.setGameTurn(false);

				return;
			}
			// if both are equally fast, NOTE: TBD
			else
			{
				// depending on implementation of timer,
				// this might be unnecessary to implement especially if likelihood of such
				// event occurring is low
			}
		}
		// not same spot move conflict, sort out scenarios
		else
		{
			/**
			 * Pawn Capture Scenario
			 * if target is of type pawn, check if going diagonally to capture
			 * if yes, check if piece will be there after turn is executed
			 * determine if pawn move is legal
			**/

			if (whiteTarget.getName() == "Pawn")
			{
				// if white going diagonal
				if (whiteX == (whiteMove.getInitX() - 1) && whiteY == (whiteMove.getInitY() + 1)
				|| (whiteX == (whiteMove.getInitX() + 1) && whiteY == (whiteMove.getInitY() + 1)))
				{
					// if its initial position isn't where white is moving, the piece white is trying to capture isn't moving
					if (blackMove.getInitX() != whiteX && blackMove.getInitY() != whiteY)
					{
						gameBoard.setGameTurn(true);
						gameBoard.getPiece(whiteX, whiteY).capture();
						gameBoard.movePiece(whitePlayer, whiteTarget, whiteX, whiteY);
						gameBoard.setGameTurn(false);
						return;
					}
					// piece white is trying to capture is moving, white cannot move
					else if (blackMove.getInitX() == whiteX && blackMove.getInitY() == whiteY)
					{
						gameBoard.setGameTurn(true);
						gameBoard.movePiece(blackPlayer, blackTarget, blackX, blackY);
						gameBoard.setGameTurn(false);
						return;
					}
						// if black piece ends up moving to where white player anticipated (??), it can capture (?)
					else if (blackX == whiteX && blackY == whiteY)
					{
						gameBoard.setGameTurn(true);

						gameBoard.movePiece(whitePlayer, whiteTarget, whiteX, whiteY);
						gameBoard.setGameTurn(false);

						return;
					}
				}
			}
			// PASSES CONFLICT TESTS
			gameBoard.setGameTurn(true);

			//Attempt capturing a black piece if it isn't the moving piece
			if (gameBoard.getPiece(whiteX, whiteY) != null && gameBoard.getPiece(whiteX, whiteY) != blackTarget)
			{
				gameBoard.getPiece(whiteX, whiteY).capture();
			}
			//Move the white piece
			gameBoard.movePiece(whitePlayer, whiteTarget, whiteX, whiteY);

			//Attempt capturing a black piece if it isn't the moving piece
			if (gameBoard.getPiece(blackX, blackY) != null && gameBoard.getPiece(blackX, blackY) != whiteTarget)
			{
				gameBoard.getPiece(blackX, blackY).capture();
			}

			//Move the black piece
			gameBoard.movePiece(blackPlayer, blackTarget, blackX, blackY);
			// end the turn so set it back to false
			gameBoard.setGameTurn(false);


		// capturing sequence must be implemented/specified clearly to make sure
		// piece that moves away isn't captured by other player's turn

		// passes all conflict tests
		// execute original turns

			return;



		}
	}
}
