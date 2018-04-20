public static class GameHost {

  public static Player whitePlayer = new Player(true);
  public static Player blackPlayer = new Player(false);

  public static Board gameBoard = new Board(whitePlayer, blackPlayer);

  public static void initialize() {

  }

}