package cs48g02s18.chessserver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPassCreateGame extends DataPass {
    private String gameName;

    public DataPassCreateGame(String username, String password, String newGameName) {
        super(username, password);
        gameName = newGameName;
    }
    public DataPassCreateGame() {
        super();
        gameName = null;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public String toString() {
        return "DataPassCreateGame{" + super.toString() +
                "gameName='" + gameName + '\'' +
                '}';
    }
}

