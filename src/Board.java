import java.util.ArrayList;

public class Board {
	// 8x8 matrix to store board positions
	private Piece[][] spots = new Piece[8][8];
	private Player whitePlayer;
	private Player blackPlayer;

	public Board(Player whitePlayer, Player blackPlayer)
	{
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
		setUp(whitePlayer, blackPlayer);
	}

	public void setUp(Player whitePlayer, Player blackPlayer) {
		for (Piece p : whitePlayer.getPieces()) {
			addPiece(p);
		}
		for (Piece p : blackPlayer.getPieces()) {
			addPiece(p);
		}
	}

	public Player getPlayer(int playerType)
	{
		if (playerType == 1)
		{
			return this.whitePlayer;
		}
		else
		{
			return this.blackPlayer;
		}
	}
	//return piece at some spot
	public Piece getPiece(int x, int y) {
		return spots[x][y];
	}

	public void addPiece(Piece p) {
		spots[p.getXPosition()][p.getYPosition()] = p;
	}

	// instead of board moving piece, seems to be more logical to make player
	// or gameHost class move the piece instead/store the move for gameTurn
	public void movePiece(Player player, Piece p, int x, int y) {
		// if gameTurn == valid
		spots[p.getXPosition()][p.getYPosition()] = null;
		spots[x][y] = p;
		//p.move(x, y);
		player.movePiece(p, x, y);

	}

}
