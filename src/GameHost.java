import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;
public class GameHost {

	public static Player whitePlayer = new Player(1);
	public static Player blackPlayer = new Player(0);
	public static Player[] players = new Player[2];

	public static Board gameBoard = new Board(whitePlayer, blackPlayer);

	public static boolean endTurn = false;
	public static boolean forfeit = false;
	public static int timestamp;
	public static TurnTimer timer = new TurnTimer();

	public GameHost() {

	}
	public static void initialize() {
		players[0] = blackPlayer;
		players[1] = whitePlayer;
	}

	public static void startTimer()
	{
		forfeit = false;
		timer.start();
	}
	// stops timer, sets timestamp to where timer stopped, ends turn
	public static void stopTimer() {
		System.out.println("go in here");
		timer.stop();
		timestamp = timer.getTimeStamp();
		endTurn = true;
	}
	/**
	 * Use forfeit instead of stopTimer when the timer reaches 0
	 * gets a random move and then sets next move for player with that move
	 */
	public static void forfeit() {
		//timer.stop();
		endTurn = true;
		forfeit = true;
		// select player's move
		Player player = players[SpeedChess.playerPerspective];
		Move randomMove = randomMove();

		player.setNextMove(randomMove);

		SpeedChess.confirm();
	}

	public static int getTimeStamp()
	{
		return timestamp;
	}

	//Decides if it's time to go ahead and run executeGameTurn()
	public static void checkIfReady() {
		Move whiteMove = whitePlayer.getNextMove();
		Move blackMove = blackPlayer.getNextMove();
		if (whiteMove != null && blackMove != null) {
			executeGameTurn();
			SpeedChess.redrawBoard();
		}
		SpeedChess.kingCheck();
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

		whitePlayer.setNextMove(null);
		blackPlayer.setNextMove(null);

		// check if same spot move conflict
		if (whiteX == blackX && whiteY == blackY) {
			// if white is faster than black
			if (whiteTime < blackTime)
			{
				gameBoard.setGameTurn(true);
				gameBoard.pickUpPiece(whiteTarget);
				gameBoard.movePiece(whitePlayer, whiteTarget, whiteX, whiteY);
				//Capture the black piece
				blackTarget.capture();
				gameBoard.removePiece(blackPlayer, blackTarget);
				// end the turn so set it back to false
				gameBoard.setGameTurn(false);
				endTurn = false;
				return;
			}
			// if black is faster than white
			else if (whiteTime > blackTime)
			{
				gameBoard.setGameTurn(true);
				gameBoard.pickUpPiece(blackTarget);
				gameBoard.movePiece(blackPlayer, blackTarget, blackX, blackY);
				//Capture the white piece
				whiteTarget.capture();
				gameBoard.removePiece(whitePlayer, whiteTarget);
				// end the turn so set it back to false
				gameBoard.setGameTurn(false);
				endTurn = false;
				return;
			}
			// if both are equally fast, NOTE: TBD
		}
		// not same spot move conflict, sort out scenarios
		else
		{
			// Move Conflict involving Pawns being handled
			if (checkPawns(whiteTarget, whiteMove, blackTarget, blackMove))
			{
				endTurn = false;
				return;
			}

			// PASSES CONFLICT TESTS
			gameBoard.setGameTurn(true);


			//Attempt capturing a black piece if it isn't the moving piece
			if (gameBoard.getPiece(whiteX, whiteY) != null && gameBoard.getPiece(whiteX, whiteY) != blackTarget) {
			  gameBoard.getPiece(whiteX, whiteY).capture();
				gameBoard.removePiece(whitePlayer, gameBoard.getPiece(whiteX, whiteY));
			}
			//Attempt capturing a white piece if it isn't the moving piece
			if (gameBoard.getPiece(blackX, blackY) != null && gameBoard.getPiece(blackX, blackY) != whiteTarget) {
				gameBoard.getPiece(blackX, blackY).capture();
				gameBoard.removePiece(blackPlayer, gameBoard.getPiece(blackX, blackY));
			}

			gameBoard.pickUpPiece(whiteTarget);
			gameBoard.pickUpPiece(blackTarget);
			//Move the white piece
			gameBoard.movePiece(whitePlayer, whiteTarget, whiteX, whiteY);
			//Move the black piece
			gameBoard.movePiece(blackPlayer, blackTarget, blackX, blackY);

			// end the turn so set it back to false
			gameBoard.setGameTurn(false);
			endTurn = false;
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
					return false;
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
					gameBoard.pickUpPiece(blackTarget);
					gameBoard.movePiece(blackPlayer, blackTarget, blackX, blackY);
					gameBoard.setGameTurn(false);
					return true;
				}
				// if black piece ends up moving to where white player anticipated (??), it can capture (?)
				// NOTE: TBD

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
					gameBoard.pickUpPiece(whiteTarget);
					gameBoard.movePiece(whitePlayer, whiteTarget, whiteX, whiteY);
					gameBoard.setGameTurn(false);
					return true;
				}
				// if white piece ends up moving to where black player anticipated (??), it can capture (?)
				// NOTE: TBD
			}
		}
		return false;
	}

	public static void processMove(String m) {
		System.out.println("OTHER PLAYER'S MOVE: " + m);

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

	/**
	 * When timer runs out, call this function to get a random move
	 */
	public static Move randomMove()
	{
		Player player = players[SpeedChess.playerPerspective];
		System.out.println("perspective: " + SpeedChess.playerPerspective);
		int playerType = player.getPlayerType();
		System.out.println("playerType: " + playerType);
		ArrayList<Piece> pieces = player.getPieces();

		for (Piece p : pieces)
		{
			System.out.println("Name: " + p.getName() + "X: " + p.getXPosition() + "Y: " + p.getYPosition());

		}
		System.out.println("size: " + pieces.size());
		int pieceIdx = (int) (Math.random() * pieces.size());

		System.out.println("Idx: " + pieceIdx);

		Piece targetPiece = pieces.get(pieceIdx);
		ArrayList<Point> moves = targetPiece.getValidMoves(gameBoard, playerType);
		for (Point m : moves)
		{
			Piece target = gameBoard.getPiece((int) m.getX(), (int) m.getY());
			System.out.println("MOVE -- Name: " + target.getName() + "X: " + m.getX() + "Y: " + m.getY());
		}
		int moveIdx = (int) (Math.random() * moves.size());

		System.out.println("move: " + moveIdx);

		if (moves.isEmpty())
		{
			Move nextMove = new Move(targetPiece, targetPiece.getXPosition(), targetPiece.getYPosition());
			return nextMove;

		}

			Point targetMove = moves.get(moveIdx);


		System.out.println("x: " + targetMove.getX());
		System.out.println("y: " + targetMove.getY());

		Move nextMove = new Move(targetPiece, (int) targetMove.getX(), (int) targetMove.getY());

		//players[playerType].setNextMove(nextMove);
		return nextMove;

	}

}
