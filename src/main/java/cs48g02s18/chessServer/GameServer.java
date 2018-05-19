package cs48g02s18.chessServer;

import javax.xml.crypto.Data;
import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Timer;

/*** ChessGameSever.java
   subclasses for all the GAME data
    player class, list/map of all players' client's address to their nickname (option to change nickname)
    so data comes in --> figure out which player sent the data --> initiate actions on games/etc owned by that player
                     --> upon completion, send confirmation of completion to client
    log of activity and games
    time
    ChessServerClientInterface
ChessClientServerInterface
*/
//may want Timer for future stuff

class GameServer implements Serializable {
    HashMap<String, Player> usersByUsername;
    HashMap<String, GameInstance> gameLobby;

    public GameServer() {
        this.usersByUsername = new HashMap<String, Player>();
    }

    public String getLobby(){
        String lobbyString = new String[];
        Iterator<String> iterator = gameLobby.keySet().iterator();
        int i = 0;
        while (iterator.hasNext()){
            lobbyString += iterator.next() + "\n";
        }
    }

    public void createGame(String name, Player player){
        GameInstance newGame = new GameInstance(name, player);
        player.setCurrentGame(newGame);
    }

    public void joinGame(String name, Player player){
        GameInstance gameInstance = gameLobby.get(name);
        gameInstance.addPlayer(player);
        player.setCurrentGame(gameInstance);
    }

    public void takeRequest(DataPass request){
        Player player = accessPlayer(request);

        if (player != null){
            if (request instanceof DataPassCreateGame){
                createGame();
            }
            else if (request instanceof DataPassJoinGame){
                joinGame();
            }
            else if (request instanceof DataPassMoveData){

            }
        }
    }

    public String addUser(DataPass userData){
        if (usersByUsername.containsKey(userData.getUsername())){
            return "username already taken.";
        }
        else {
            Player newUser = new Player(userData.getUsername(), userData.getPassword());
            usersByUsername.put(newUser.getUsername(), newUser);
        }
    }

    public Player accessPlayer(DataPass userData) {
        Player accessed = usersByUsername.get(userData.getUsername());
        if (userData.getPassword().equals(accessed.getPassword())){
            return accessed;
        }
        else {
            return null;
        }
    }
}