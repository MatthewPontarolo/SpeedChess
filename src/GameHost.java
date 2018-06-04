public class GameHost {

	public static Player whitePlayer = new Player(1);
	public static Player blackPlayer = new Player(0);
	public static Player[] players = new Player[2];

	public static Board gameBoard = new Board(whitePlayer, blackPlayer);


	public GameHost() {

	}
	public static void initialize() {
		players[0] = blackPlayer;
		players[1] = whitePlayer;
	}

	//Decides if it's time to go ahead and run executeGameTurn()
	public static void checkIfReady()
	{
		Move whiteMove = whitePlayer.getNextMove();
		Move blackMove = blackPlayer.getNextMove();
		//System.out.println("wM: " + whiteMove);
		//System.out.println("bM: " + blackMove);
		if (whiteMove != null && blackMove != null)
		{
			executeGameTurn();
			SpeedChess.redrawBoard();
		}
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

		long whiteTime = whiteMove.getTime();
		long blackTime = blackMove.getTime();

		System.out.println("whiteTime: " + whiteTime);
		System.out.println("blackTime: " + blackTime);

		whitePlayer.setNextMove(null);
		blackPlayer.setNextMove(null);

		// check if same spot move conflict
		if (whiteX == blackX && whiteY == blackY) {
			// if white is faster than black
			if (whiteTime < blackTime)
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
			else if (whiteTime > blackTime)
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
			// Move Conflict involving Pawns being handled
			if (checkPawns(whiteTarget, whiteMove, blackTarget, blackMove))
			{
				return;
			}

			// PASSES CONFLICT TESTS
			gameBoard.setGameTurn(true);


			//Attempt capturing a black piece if it isn't the moving piece
			if (gameBoard.getPiece(whiteX, whiteY) != null && gameBoard.getPiece(whiteX, whiteY) != blackTarget) {
				gameBoard.getPiece(whiteX, whiteY).capture();
			}
			//Attempt capturing a white piece if it isn't the moving piece
			if (gameBoard.getPiece(blackX, blackY) != null && gameBoard.getPiece(blackX, blackY) != whiteTarget) {
				gameBoard.getPiece(blackX, blackY).capture();
			}

			gameBoard.pickUpPiece(whiteTarget);
			gameBoard.pickUpPiece(blackTarget);
			//Move the white piece
			gameBoard.movePiece(whitePlayer, whiteTarget, whiteX, whiteY);
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


	/**
	 * Pawn Capture Scenario
	 * if target is of type pawn, check if going diagonally to capture
	 * if yes, check if piece will be there after turn is executed
	 * determine if pawn move is legal
	 * @return boolean true if this function handles conflict
	 * @return boolean false if not a pawn conflict, lets general handle it
	**/
	public static boolean checkPawns(Piece whiteTarget, Move whiteMove, Piece blackTarget, Move blackMove)
	{
		int whiteX = whiteMove.getXMove();
		int whiteY = whiteMove.getYMove();

		// black player's move selection coordinates
		int blackX = blackMove.getXMove();
		int blackY = blackMove.getYMove();

		// White Pawn Diagonal Movement Check
		if (whiteTarget.getName() == "Pawn")
		{
			// if white going diagonal
			if (whiteX == (whiteMove.getInitX() - 1) && whiteY == (whiteMove.getInitY() + 1)
			|| (whiteX == (whiteMove.getInitX() + 1) && whiteY == (whiteMove.getInitY() + 1)))
			{
				// both trying to capture each other, both cannot move
				if ((whiteMove.getInitX() == blackX && whiteMove.getInitY() == blackY)
				&& (blackMove.getInitX() == whiteX && blackMove.getInitY() == whiteY))
				{
					if (blackTarget.getName() == "Pawn")
					{
						System.out.println("Illegal Pawn Move.");
						System.out.println("Both players tried to capture each other's game piece. Both game turns forfeited!");
						return true;
					}
					return true;
				}
				// if black initial position isn't where white is moving, the piece white is trying to capture isn't moving
				else if (blackMove.getInitX() != whiteX && blackMove.getInitY() != whiteY)
				{
					return false;
				}
				// piece white is trying to capture is moving, white cannot move
				else if (blackMove.getInitX() == whiteX && blackMove.getInitY() == whiteY)
				{
					System.out.println("Illegal white Pawn Move.");
					System.out.println("Game piece white player is trying to capture has moved away. Game Turn forfeited.");
					gameBoard.setGameTurn(true);
					gameBoard.movePiece(blackPlayer, blackTarget, blackX, blackY);
					gameBoard.setGameTurn(false);
					return true;
				}
				// if black piece ends up moving to where white player anticipated (??), it can capture (?)
				// NOTE: TBD
				else if (blackX == whiteX && blackY == whiteY)
				{

					return false;
				}
			}
		}

		// Black Pawn Diagonal Movement Check
		if (blackTarget.getName() == "Pawn")
		{
			// if black going diagonal
			if (blackX == (blackMove.getInitX() - 1) && blackY == (blackMove.getInitY() - 1)
			|| (blackX == (blackMove.getInitX() + 1) && blackY == (blackMove.getInitY() - 1)))
			{
				// both trying to capture each other
				if ((blackMove.getInitX() == whiteX && blackMove.getInitY() == whiteY)
				&& (whiteMove.getInitX() == blackX && whiteMove.getInitY() == blackY))
				{
					// if both pawns, cannot move
					if (whiteTarget.getName() == "Pawn") {
						System.out.println("Illegal Pawn Move.");
						System.out.println("Both players tried to capture each other's game piece. Both game turns forfeited!");
						return true;
					}
					return false;
				}
				// if its initial position isn't where black is moving, the piece black is trying to capture isn't moving
				else if (whiteMove.getInitX() != blackX && whiteMove.getInitY() != blackY)
				{
					return false;
				}
				// piece black is trying to capture is moving, black cannot move
				else if (whiteMove.getInitX() == blackX && whiteMove.getInitY() == blackY)
				{
					System.out.println("Illegal black Pawn Move.");
					System.out.println("Game piece black player is trying to capture has moved away. Game Turn forfeited.");
					gameBoard.setGameTurn(true);
					gameBoard.movePiece(whitePlayer, whiteTarget, whiteX, whiteY);
					gameBoard.setGameTurn(false);
					return true;
				}
				// if white piece ends up moving to where black player anticipated (??), it can capture (?)
				// NOTE: TBD
				else if (blackX == whiteX && blackY == whiteY)
				{

					return false;
				}
			}
		}
		return false;
	}

	public static void processMove(String m) {
		System.out.println("MOVE MADE IS: " + m);

		String[] data = m.split("\\s+");

		System.out.println("parse attempt: " + Long.parseLong(data[4]));

		Move mv = new Move(gameBoard.getPiece(Integer.parseInt(data[0]), Integer.parseInt(data[1])), Integer.parseInt(data[2]), Integer.parseInt(data[3]));
		mv.setTime(Long.parseLong(data[4]));
		if (SpeedChess.playerPerspective == 0) {
			players[1].setNextMove(mv);
		} else {
			players[0].setNextMove(mv);
		}
	}

}
