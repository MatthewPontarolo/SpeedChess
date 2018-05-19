package cs48g02s18.chessServer;

public class DataPassJoinGame extends DataPass {
    String gameName;

    public DataPassJoinGame(String username, String password, String gameName) {
        super(username, password);
        this.gameName = gameName;
    }

    public String getGameName() {
        return gameName;
    }

}
