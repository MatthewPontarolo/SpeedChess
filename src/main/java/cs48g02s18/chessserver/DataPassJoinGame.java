package cs48g02s18.chessserver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties
public class DataPassJoinGame extends DataPass {
    String gameName;

    public DataPassJoinGame(String username, String password, String gameName) {
        super(username, password);
        this.gameName = gameName;
    }

    public DataPassJoinGame() {
        super();
        this.gameName = null;
    }

    public String getGameName() {
        return gameName;
    }

}
