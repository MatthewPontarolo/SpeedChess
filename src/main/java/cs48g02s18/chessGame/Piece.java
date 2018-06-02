package cs48g02s18.chessgame;


import java.awt.Point;
import java.util.ArrayList;

public abstract class Piece {
	protected int Xposition;
	protected int Yposition;
	protected boolean status;
	protected int player;

	// check if piece is still on board
	/**
	 * @return status whether the piece is alive or not
	 */
	public boolean isAlive()
	{
		return status;
	}

	/**
	 * @param boolean new condition set into the alive
	 * sets the condition whether the piece is alive or not
	 */
	public void setAlive(boolean alive)
	{
		status = alive;
	}

	/** sets the position of the piece
	 * @param int x and y coordinates of the piece
	 */
	public void setPosition(int x, int y) {
		Xposition = x;
		Yposition = y;
	}

	/**
	 * @return the x position of the piece
	 */
	public int getXPosition()
	{
		return Xposition;
	}

	/**
	 * @return the y position of the piece
	 */
	public int getYPosition()
	{
		return Yposition;
	}

	/**
	 * @param Board board of the game
	 * @param int x and y of the coordinates from a piece
	 * @param int which player the piece belongs
	 */
	public abstract void setValidMoves(Board board, int x, int y, int playerType);

	/**
	 * @param Board board of the game
	 * @param int which player the piece belongs
	 */
	public abstract ArrayList<Point> getValidMoves(Board board, int playerType);

	/**
	 * get the name of each piece
	 */
	public abstract String getName();

	/**
	 *@return which player the piece belongs to
	 */
	public int getPlayer() {
		return player;
	}

	/**
	 * sets which player the piece belongs to
	 */
	public void setPlayer(int p) {
		player = p;
	}

	/**
	 * captures the piece and removed off the board
	 */
	public void capture() {
		setAlive(false);
		setPosition(-1, -1);
	}

	/**
	 * sets the piece to a new position
	 */
	public abstract void move(int x, int y);


	@Override
	public String toString() { return getName(); }
}
