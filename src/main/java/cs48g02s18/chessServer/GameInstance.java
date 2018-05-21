package cs48g02s18.chessServer;

import cs48g02s18.chessGame.Board;
import cs48g02s18.chessGame.Piece;
import cs48g02s18.chessGame.Player;

public class GameInstance {
    private ServerPlayer hostServerPlayer; //future change to more generalized form (doesn't need to be host/guest
    private ServerPlayer guestServerPlayer;             //eg if we wanted to have a game queue
    private Player whitePlayer;
    private Player blackPlayer;
    private Board gameBoard;
    private Player gamePlayer;


    public GameInstance(ServerPlayer hostServerPlayer) {
        this.hostServerPlayer = hostServerPlayer;
    }

    public void addPlayer(ServerPlayer guestServerPlayer){
        if (this.guestServerPlayer == null){
            this.guestServerPlayer = guestServerPlayer;
            this.setUpBoard();
            this.startGame();
        }
    //todo
    }

    public void submitNextMove(ServerPlayer player, Move nextMove) {
        if (gameBoard.isLegalMove(Move nextMove, ServerPlayer player)) {
            player.setNextMove(nextMove);
        }
        //todo make return string
    }

    private void setUpBoard(){
        this.gameBoard.setUp(whitePlayer, blackPlayer);
    }

    public void resolveTurn() {
        Move hostNextMove = hostServerPlayer.getNextMove();
        Move guestNextMove = guestServerPlayer.getNextMove();

        if (!isValidMove(hostNextMove, this.hostServerPlayer)){
            hostNextMove = null;
        }
        if (!isValidMove(guestNextMove, this.guestServerPlayer)){
            guestNextMove = null;
        }
        Piece hostPiece = gameBoard.getPiece(hostNextMove.getStartPosition());
        Piece guestPiece = gameBoard.getPiece(hostNextMove.getStartPosition());

        if(hostNextMove.getEndPosition() == guestNextMove.getEndPosition()) {

        }


    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public boolean isValidMove(Move move, ServerPlayer player)

}
