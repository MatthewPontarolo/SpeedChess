package cs48g02s18.chessServer;

public class GameInstance {
    Player hostPlayer;
    Player guestPlayer;
    String name;
    cs48g02s18.chessServer.cs48g02s18.chessGame.Player whitePlayer;
    cs48g02s18.chessServer.cs48g02s18.chessGame.Player blackPlayer;
    cs48g02s18.chessServer.cs48g02s18.chessGame.Board gameBoard;

    public GameInstance(String name, Player hostPlayer) {
        this.hostPlayer = hostPlayer;
        this.name = name;
    }

    public void addPlayer(Player guestPlayer){
        if (this.guestPlayer == null){
            this.guestPlayer = guestPlayer;
            this.startGame();
        }
    }

    public void startGame(){

    }
}
