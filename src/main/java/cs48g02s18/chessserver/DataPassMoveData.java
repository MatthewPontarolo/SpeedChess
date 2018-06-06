package cs48g02s18.chessserver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import cs48g02s18.chessgame.Move;

@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPassMoveData extends DataPass {
    private MoveData move;

    public DataPassMoveData(String username, String password, MoveData move) {
        super(username, password);
        this.move = move;
    }

    public DataPassMoveData(String username, String password, Move gameMove) {
        super(username, password);
        MoveData moveData;
        moveData = new MoveData(gameMove.getInitX(), gameMove.getInitY(),
                gameMove.getXMove(), gameMove.getYMove());
        this.move = moveData;
    }

    public DataPassMoveData() {
        super();
        this.move = null;
    }

    public MoveData getMove() {
        return move;
    }

    public void setMove(MoveData move) {
        this.move = move;
    }

    @Override
    public String toString() {
        return "DataPassMoveData{" +
                "move=" + move +
                '}';
    }
}
