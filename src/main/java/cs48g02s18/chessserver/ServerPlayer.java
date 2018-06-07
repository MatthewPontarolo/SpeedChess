package cs48g02s18.chessserver;

import cs48g02s18.chessgame.Move;
import cs48g02s18.chessgame.Piece;
import cs48g02s18.chessgame.Player;

import java.awt.*;

class ServerPlayer {
    private String username;
    private String password;
    private GameInstance currentGame;
    private Player gamePlayer;

    //private ArrayList<PastGame> pastGames;
    private MoveData nextMoveData;


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

    //returns 1 if next move has been successfully set & is valid
    public boolean setNextMoveData(MoveData moveData){
        if (moveData == null) return false;

        Piece movePiece = currentGame.getGameBoard().getPiece(moveData.getFromX(), moveData.getFromY());
        if (movePiece == null) return false;

        if ( movePiece.getValidMoves(currentGame.getGameBoard(), gamePlayer.getPlayerType())
                .contains(moveData.getEndPosition()) ) {

            gamePlayer.setNextMove(new Move(movePiece, moveData.getFromX(), moveData.getFromY()));
            currentGame.resolveTurn();
        }
        System.out.print("needed" + movePiece.getValidMoves(currentGame.getGameBoard(), gamePlayer.getPlayerType()) +
                "\n" + "had: " + moveData.getEndPosition());
        System.out.print(this.username + "submitted illegal move");
        return false;
    }

    public Move getNextMove(){
        if (nextMoveData == null) return null;

        Point location = nextMoveData.getStartPosition();
        Piece piece = gamePlayer.getPieceAt(location.x, location.y);

        location = nextMoveData.getEndPosition();
        Move move = new Move(piece, location.x, location.y);
        return move;
    }

    public Player getGamePlayer() {
        return gamePlayer;
    }

    public void setGamePlayer(Player gamePlayer) {
        this.gamePlayer = gamePlayer;
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

    public MoveData getNextMoveData(){
        return this.nextMoveData;
    }
}