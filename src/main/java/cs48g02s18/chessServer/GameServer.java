package cs48g02s18.chessServer;

import java.io.Serializable;
import java.util.HashMap;

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
    private HashMap<String, ServerPlayer> usersByUsername;
    private HashMap<String, GameInstance> gameLobby;

    public GameServer() {
        this.usersByUsername = new HashMap<>();
        this.gameLobby = new HashMap<>();
    }

    public String getLobby() {
        StringBuilder lobbyStringBuilder = new StringBuilder();

        for (String next: gameLobby.keySet()) {
            lobbyStringBuilder.append(next);
            lobbyStringBuilder.append("\n");
        }
        return lobbyStringBuilder.toString();
    }

    public void createGame(String name, ServerPlayer serverPlayer){
        GameInstance newGame = new GameInstance(serverPlayer);
        serverPlayer.setCurrentGame(newGame);
        gameLobby.put(name, newGame);
    }

    public void joinGame(String name, ServerPlayer serverPlayer){
        GameInstance gameInstance = gameLobby.get(name);
        gameInstance.addPlayer(serverPlayer);
        serverPlayer.setCurrentGame(gameInstance);
    }

    public String takeRequest(DataPass request){
        ServerPlayer serverPlayer = accessPlayer(request);

        if (serverPlayer != null){
            if (request instanceof DataPassCreateGame){
                createGame(((DataPassCreateGame) request).getGameName(), serverPlayer);
            }
            else if (request instanceof DataPassJoinGame){
                joinGame(((DataPassJoinGame) request).getGameName(), serverPlayer);
            }
            else if (request instanceof DataPassMoveData) {
                serverPlayer.setNextMove(((DataPassMoveData) request).getMove());
            }
        }

        return "data received";
    }

    public String addUser(DataPass userData){
        if (usersByUsername.containsKey(userData.getUsername())){
            return "username already taken.";
        }
        else {
            ServerPlayer newUser = new ServerPlayer(userData.getUsername(), userData.getPassword());
            usersByUsername.put(newUser.getUsername(), newUser);
        }

        return "user added";
    }

    public ServerPlayer accessPlayer(DataPass userData) {
        ServerPlayer accessed = usersByUsername.get(userData.getUsername());
        if (userData.getPassword().equals(accessed.getPassword())){
            return accessed;
        }
        else {
            return null;
        }
    }

    public String stepGameForward(DataPass userData){
        ServerPlayer player = accessPlayer(userData);
        player.getCurrentGame().resolveTurn();

        return "game stepped forward";
    }

    public DataPassBoardState getBoardState(DataPass userData){
        ServerPlayer player = accessPlayer(userData);
        if (player != null){
            DataPassBoardState boardState = new DataPassBoardState(player.getGameString()); //todo update to pass the new move made by opponent as well
            return boardState;
        }
        else {
            return null;
        }
    }
}