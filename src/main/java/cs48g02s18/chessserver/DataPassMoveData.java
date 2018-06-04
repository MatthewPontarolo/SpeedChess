package cs48g02s18.chessserver;

public class DataPassMoveData extends DataPass {
    private Move move;

    public DataPassMoveData(String username, String password, Move move) {
        super(username, password);
        this.move = move;
    }

    public DataPassMoveData() {
        super();
        this.move = null;
    }

    public Move getMove() {
        return move;
    }

    @Override
    public String toString() {
        return "DataPassMoveData{" +
                "move=" + move +
                '}';
    }
}
