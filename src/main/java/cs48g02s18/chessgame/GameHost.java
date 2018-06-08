package cs48g02s18.chessgame;

public class GameHost {

	public Player whitePlayer = new Player(1);
	public Player blackPlayer = new Player(0);
	public Board gameBoard = new Board(whitePlayer, blackPlayer);

	public static boolean endTurn = false;
	public static boolean forfeit = false;
	public static boolean gameEnded = false;
	public static int timestamp;
	public static TurnTimer timer = new TurnTimer();

	/**
	 * empty constructor
	 */
	public GameHost() {

	}

	/**
	 * does nothing
	 */
	public void initialize() {

	}

	public static void startTimer()
	{
		forfeit = false;
		timer.start();
	}

	public static void stopTimer()
	{
		timer.stop();
		timestamp = timer.getTimeStamp();
		endTurn = true;
	}

	public static void forfeit()
	{
		endTurn = true;
		forfeit = true;
		Player player;
		if (SpeedChess.playerPerspective == 1)
		{
			player = whitePlayer;
		}
		else if (SpeedChess.playerPerspective == 0)
		{
			player = blackPlayer;
		}
		Move randomMove = randomMove();

		player.setNextMove(randomMove);
		SpeedChess.confirm();
	}

	public static int getTimeStamp()
	{
		return timestamp;
	}

	//Decides if it's time to go ahead and run executeGameTurn()
	/**
	 * Decides if it's time to go ahead and run executeGameTurn()
	 */
	public void checkIfReady()
	{
		Move whiteMove = whitePlayer.getNextMove();
		Move blackMove = blackPlayer.getNextMove();
		System.out.println("wM: " + whiteMove);
		System.out.println("bM: " + blackMove);
		if (gameEnded == false)
		{
			if (whiteMove != null && blackMove != null)
			{
				executeGameTurn();
				SpeedChess.redrawBoard();
			}
			SpeedChess.kingCheck();
		}

	}

	public void updatePlayersForUI(){
		whitePlayer = gameBoard.getPlayer(1);
		blackPlayer = gameBoard.getPlayer(0);
	}

	// Precondition: Timer runs out OR both players clicked "CONFIRM" on UI
		// no need to check valid turns bc UI doesn't allow for spot selections that
		// is not in validMoves array
	// PostCondition: GameHost acts as a referee and determines if moves can be executed.
		// calls board::movePiece on the valid pieces that can be moved
		// TODO: deal with capture situations
		/**
		 * Calls board::movePiece on the valid pieces that can be moved
		 */
	public void executeGameTurn()
	{
		// get both player's moves
		Move whiteMove = whitePlayer.getNextMove();
		Move blackMove = blackPlayer.getNextMove();

		if (blackMove == null || whiteMove == null) {
			Move onlyMove = null;
			Player onlyPlayer = null;


			if (blackMove != null){
				onlyPlayer = blackPlayer;
				onlyMove = blackMove;
			}
			if (whiteMove != null){
				onlyPlayer = whitePlayer;
				onlyMove = whiteMove;
			}

			if (onlyMove != null) ;
			{
				Piece target = onlyMove.getTargetPiece();
				gameBoard.setGameTurn(true);
				gameBoard.pickUpPiece(onlyMove.getTargetPiece());
				gameBoard.movePiece(onlyPlayer, onlyMove.getTargetPiece(), onlyMove.getXMove(), onlyMove.getYMove());
				gameBoard.setGameTurn(false);

			}

			return;
		}
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
			if (!((whiteTarget.getName() == "King" && blackTarget.getName() == "King"))) {
				if ((whiteTarget.getName() == "King")) {
					gameBoard.setGameTurn(true);
					gameBoard.movePiece(whitePlayer, whiteTarget, whiteX, whiteY);
					return;
				}
				else if (blackTarget.getName() == "King")
				{
					gameBoard.setGameTurn(true);
					gameBoard.movePiece(blackPlayer, blackTarget, blackX, blackY);
					return;
				}
			}

			if (whiteMove.wasFirst())
			{
				gameBoard.setGameTurn(true);
				gameBoard.pickUpPiece(whiteTarget);
				gameBoard.movePiece(whitePlayer, whiteTarget, whiteX, whiteY);
				//Capture the black piece
				gameBoard.removePiece(blackPlayer, blackTarget, blackX, blackY);
				blackTarget.capture();
				// end the turn so set it back to false
				gameBoard.setGameTurn(false);
				endTurn = false;
				return;
			}
			// if black is faster than white
			else if (blackMove.wasFirst())
			{
				gameBoard.setGameTurn(true);
				gameBoard.pickUpPiece(blackTarget);
				gameBoard.movePiece(blackPlayer, blackTarget, blackX, blackY);
				//Capture the white piece
				gameBoard.removePiece(whitePlayer, whiteTarget, whiteX, whiteY);
				whiteTarget.capture();
				// end the turn so set it back to false
				gameBoard.setGameTurn(false);
				endTurn = false;
				return;
			}
		}
		// not same spot move conflict, sort out scenarios
		else
		{
			// MoveData Conflict involving Pawns being handled
			if (checkPawns(whiteTarget, whiteMove, blackTarget, blackMove))
			{
				endTurn = false;
				return;
			}

			// PASSES CONFLICT TESTS
			gameBoard.setGameTurn(true);

			//Attempt capturing a black piece if it isn't the moving piece
			if (gameBoard.getPiece(whiteX, whiteY) != null && gameBoard.getPiece(whiteX, whiteY) != blackTarget)
			{
				gameBoard.removePiece(whitePlayer, gameBoard.getPiece(whiteX, whiteY));
				gameBoard.getPiece(whiteX, whiteY).capture();
			}

			//Attempt capturing a white piece if it isn't the moving piece
			if (gameBoard.getPiece(blackX, blackY) != null && gameBoard.getPiece(blackX, blackY) != whiteTarget) {
				gameBoard.removePiece(blackPlayer, gameBoard.getPiece(blackX, blackY));
				gameBoard.getPiece(blackX, blackY).capture();
			}

			gameBoard.pickUpPiece(whiteTarget);
			gameBoard.pickUpPiece(blackTarget);


			//MoveData the white piece
			gameBoard.movePiece(whitePlayer, whiteTarget, whiteX, whiteY);


			//MoveData the black piece
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
	 * @return boolean true if this function handles conflict, false if not a pawn conflict, lets general handle it
	**/
	public boolean checkPawns(Piece whiteTarget, Move whiteMove, Piece blackTarget, Move blackMove) {
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
						System.out.println("Illegal Pawn MoveData.");
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
					System.out.println("Illegal white Pawn MoveData.");
					System.out.println("Game piece white player is trying to capture has moved away. Game Turn forfeited.");
					gameBoard.setGameTurn(true);
					gameBoard.pickUpPiece(blackTarget);
					gameBoard.movePiece(blackPlayer, blackTarget, blackX, blackY);
					gameBoard.setGameTurn(false);
					return true;
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
					if (whiteTarget.getName() == "Pawn")
					{
						System.out.println("Illegal Pawn MoveData.");
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
					System.out.println("Illegal black Pawn MoveData.");
					System.out.println("Game piece black player is trying to capture has moved away. Game Turn forfeited.");
					gameBoard.setGameTurn(true);
					gameBoard.pickUpPiece(whiteTarget);
					gameBoard.movePiece(whitePlayer, whiteTarget, whiteX, whiteY);
					gameBoard.setGameTurn(false);
					return true;
				}
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
		ArrayList<Point> moves = new ArrayList<Point>();
		Piece targetPiece;
		int pieceIdx;
		int moveIdx;
		do
		{

			Player player = players[SpeedChess.playerPerspective];
			int playerType = player.getPlayerType();

			// get Pieces
			ArrayList<Piece> pieces = player.getPieces();

			int count = 0;
			for (Piece p : pieces)
			{
				System.out.println("VALID PIECES(" + count +  ") Name: " + p.getName() + " X: " + p.getXPosition() + " Y: " + p.getYPosition());
				count++;
			}
			System.out.println("size: " + pieces.size());

			// Pick Piece idx
			pieceIdx = (int) (Math.random() * pieces.size());

			System.out.println("Idx CHOSEN: " + pieceIdx);

			// get Piece
			targetPiece = pieces.get(pieceIdx);

			System.out.println("PIECE CHOSEN: " + targetPiece.getName());

			// Get Moves
			moves = targetPiece.getValidMoves(gameBoard, playerType);
			for (Point m : moves)
			{
				//Piece target = gameBoard.getPiece((int) m.getX(), (int) m.getY());
				System.out.println("VALID MOVE --" + "X: " + m.getX() + "Y: " + m.getY());
			}


		}
		while (moves.isEmpty());


		// Pick Move from an nonempty moves arraylist
		moveIdx = (int) (Math.random() * moves.size());
		System.out.println("move IDX: " + moveIdx);
		Point targetMove = moves.get(moveIdx);


		System.out.println("x: " + targetMove.getX());
		System.out.println("y: " + targetMove.getY());

		Move nextMove = new Move(targetPiece, (int) targetMove.getX(), (int) targetMove.getY());

		//players[playerType].setNextMove(nextMove);
		return nextMove;

	}
}
