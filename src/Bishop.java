/**
* This class implements the Piece interface and defines the specific behavior
* associated with game piece 'Bishop'
*/

import java.util.ArrayList;
import java.awt.Point;

public class Bishop extends Piece
{
  // Assuming that we are storing board positions as something like 'C2' (??)
  // possibly changes
  private ArrayList<Point> moves = new ArrayList<Point>();

  // CONSTRUCTOR
  public Bishop(int x, int y) {
    this.setAlive(true);
    this.setPosition(x, y);
  }

  public String getName() {
  	return "Bishop";
  }

  // Precondition: Piece is on board. get the position of current spot
  // Postcondition: sets array of viable move options and stores into 'moves' arraylist
  public void setValidMoves(Board board, int x, int y, int playerType) {
	moves.clear();

	//all posible moves up toward the left
	//all possible moves from spot
	// check if occupied, if yes don't add, if no, add
    //if (playerType == 0)
    //{
      // left side bishop -- figure out how to distinguish later
      int xForward = x;
      int yForward = y;
      int xOffset = 0;
      int yOffset = 0;

      // diagonal forward, once anything is in the way, stop
      while (xForward < 8 && yForward < 8 && xForward >= 0 && yForward >=0)
      {
        xOffset++;
        yOffset++;
        int xCoord = x + xOffset;
        int yCoord = y + yOffset;
        Point move = new Point(xCoord, yCoord);
        if (xCoord < 8 && xCoord >= 0 && yCoord < 8 && yCoord >= 0)
        {
          if (board.getPiece(xCoord, yCoord) != null)
          {
            if (board.getPiece(xCoord, yCoord).getPlayer() != this.getPlayer())
            {
              moves.add(move);
            }
            break;
          }
          moves.add(move);
        }
        xForward++;
        yForward++;
      }

      int xBackward = x;
      int yBackward = y;
      // diagonal backwards, once anything is in the way, stop
      xOffset = 0;
      yOffset = 0;
      while (xBackward < 8 && yBackward < 8 && xBackward >= 0 && yBackward >=0)
      {
        xOffset--;
        yOffset--;
        int xCoord = x + xOffset;
        int yCoord = y + yOffset;
        Point move = new Point(xCoord, yCoord);
        if (xCoord < 8 && xCoord >= 0 && yCoord < 8 && yCoord >= 0)
        {
          if (board.getPiece(xCoord, yCoord) != null)
          {
            if (board.getPiece(xCoord, yCoord).getPlayer() != this.getPlayer())
            {
              moves.add(move);
            }
            break;
          }
          moves.add(move);
        }
        xBackward--;
        yBackward--;
      }

      // right side bishop
      xForward = x;
      yForward = y;
      xOffset = 0;
      yOffset = 0;
      // diagonal forward, once anything is in the way, stop
      while (xForward < 8 && yForward < 8 && xForward >= 0 && yForward >= 0)
      {
        xOffset--;
        yOffset++;
        int xCoord = x + xOffset;
        int yCoord = y + yOffset;
        Point move = new Point(xCoord, yCoord);
        if (xCoord < 8 && xCoord >= 0 && yCoord < 8 && yCoord >= 0)
        {
          if (board.getPiece(xCoord, yCoord) != null)
          {
            if (board.getPiece(xCoord, yCoord).getPlayer() != this.getPlayer())
            {
              moves.add(move);
            }
            break;
          }
          moves.add(move);
        }
        xForward--;
        yForward++;
      }

      xBackward = x;
      yBackward = y;
      xOffset = 0;
      yOffset = 0;
      // diagonal backwards, once anything is in the way, stop
      while (xBackward < 8 && yBackward < 8 && xBackward >= 0 && yBackward >= 0)
      {
        xOffset++;
        yOffset--;
        int xCoord = x + xOffset;
        int yCoord = y + yOffset;
        Point move = new Point(xCoord, yCoord);
        if (xCoord < 8 && xCoord >= 0 && yCoord < 8 && yCoord >= 0)
        {
          if (board.getPiece(xCoord, yCoord) != null)
          {
            if (board.getPiece(xCoord, yCoord).getPlayer() != this.getPlayer())
            {
              moves.add(move);
            }
            break;
          }
          moves.add(move);
        }
        xBackward++;
        yBackward--;
      }

    //}
    //else if (playerType == 1)
    //{
      // need to reconsider bc of board perspective
    //}


	}

	public ArrayList<Point> getValidMoves(Board board, int playerType)
	{
		this.setValidMoves(board, Xposition, Yposition, playerType);
		return moves;
	}

  public void move(int x, int y) {
    this.setPosition(x, y);

  }

}
