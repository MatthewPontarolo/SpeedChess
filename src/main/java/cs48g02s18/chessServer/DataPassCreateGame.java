package cs48g02s18.chessServer;

public class DataPassCreateGame extends DataPass {
    private String gameName;

    public DataPassCreateGame(String username, String password, String newGameName) {
        super(username, password);
        gameName = newGameName;
    }

    public String getGameName() {
        return gameName;
    }
}

