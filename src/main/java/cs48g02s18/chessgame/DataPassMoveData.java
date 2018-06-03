package cs48g02s18.chessgame;

public class DataPassMoveData extends DataPass {
    private Move move;

    public DataPassMoveData(String username, String password, Move move) {
        super(username, password);
        this.move = move;
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
