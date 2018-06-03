package cs48g02s18.chessserver;

class ServerPlayer {
    private String username;
    private String password;
    private GameInstance currentGame;
    //private ArrayList<PastGame> pastGames;
    private Move nextMove;


    public ServerPlayer(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public void setUsername(String setTo){
        this.username = setTo;
    }
    public void setPassword(String setTo){
        this.password = setTo;
    }
    public void setCurrentGame(GameInstance setTo){
        this.currentGame = setTo;
    }

    public void setNextMove(Move setTo){
        //if( currentGame.isValid(this, setTo)){
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public GameInstance getCurrentGame(){
        return this.currentGame;
    }

    //public ArrayList<PastGame> getPastGames(){
    //    return this.pastGames;
    //}


    public String getGameString(){
        return  this.currentGame.getGameBoard().toString(); //todo make this mirror between players appropriately
    }

    public Move getNextMove(){
        return this.nextMove;
    }

}