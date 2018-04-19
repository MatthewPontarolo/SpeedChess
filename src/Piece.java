interface Piece
{

  // check if piece is still on board
  public boolean isAlive()
  {
  }

  public void setAlive()
  {

  }
  /** SUBJECT TO CHANGE DEPENDING ON HOW WE DECIDE TO STORE BOARD POSITIONS **/
  public void setPosition()
  {

  }
  public int getXPosition()
  {
  }
  public int getYPosition()
  {
  }
  // Precondition: Piece is on board
  // Postcondition: returns array of viable move options and stores into 'moves' arraylist
  public int validMoves(int position)
  {
  }

  public int move(Board board, int position)
  {
    // check if the position the piece wants to move to is in array of possible moves
    // if yes, update position -- setCurrentPosition(position)
    // if no, give error message -- depending on implementation of UI
    // function could just do nothing
    // call function from board!

  }

}
