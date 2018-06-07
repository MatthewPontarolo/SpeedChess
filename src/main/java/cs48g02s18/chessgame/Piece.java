package cs48g02s18.chessgame;

import java.awt.Point;
import java.util.ArrayList;

public abstract class Piece {
	protected int Xposition;
	protected int Yposition;
	protected boolean status;
	protected int player;

	// check if piece is still on board
	public boolean isAlive()
	{
		return status;
	}

	public void setAlive(boolean alive)
	{
		status = alive;
	}

	public void setPosition(int x, int y) {
		Xposition = x;
		Yposition = y;
	}

	public Point getPosition() {return new Point(Xposition, Yposition);}

	public int getXPosition()
	{
		return Xposition;
	}

	public int getYPosition()
	{
		return Yposition;
	}

	public abstract void setValidMoves(Board board, int x, int y, int playerType);

	public abstract ArrayList<Point> getValidMoves(Board board, int playerType);

	public abstract String getName();

	public int getPlayer() {
		return player;
	}
	public void setPlayer(int p) {
		player = p;
	}

	public void capture() {
		setAlive(false);
		setPosition(-1, -1);
	}
	public void move(int x, int y){
		this.Xposition = x;
		this.Yposition = y;
	};


	@Override
	public String toString() { return getName(); }
}
