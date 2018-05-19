package cs48g02s18.chessServer.cs48g02s18.chessGame;

public class GameHost {

	public static Player whitePlayer = new Player(1);
	public static Player blackPlayer = new Player(0);

	public static Board gameBoard = new Board(whitePlayer, blackPlayer);

	public static void initialize() {

	}

}