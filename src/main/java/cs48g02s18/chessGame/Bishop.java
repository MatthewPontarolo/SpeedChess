package cs48g02s18.chessgame;

/**
* This class implements the Piece interface and defines the specific behavior
* associated with game piece 'Bishop'
*/

import java.util.ArrayList;
import java.awt.Point;

public class Bishop extends Piece {
    // Assuming that we are storing board positions as something like 'C2' (??)
    // possibly changes
    private ArrayList<Point> moves = new ArrayList<Point>();

    /**
    * Sets position and being alive
    * @param x     the starting X coordinate
    * @param y     the starting Y coordinate
    */
    public Bishop(int x, int y) {
        this.setAlive(true);
        this.setPosition(x, y);
    }

    /**
     * Returns name
     * @return Returns "Bishop"
     */
    public String getName() {
        return "Bishop";
    }

  /**
   * Precondition: Piece is on board.</p>
   * Postcondition: Sets array of viable move options and stores into 'moves' arraylist
   * @param Board board     The gameboard
   * @param int x           Start x pos
   * @param int y           Start y pos
   * @param int playerType  The player (0 or 1)
   */
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

	}

	public ArrayList<Point> getValidMoves(Board board, int playerType) {
		this.setValidMoves(board, Xposition, Yposition, playerType);
		return moves;
	}

}
