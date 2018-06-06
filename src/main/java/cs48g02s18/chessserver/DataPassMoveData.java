package cs48g02s18.chessserver;

public class DataPassMoveData extends DataPass {
    private MoveData moveData;

    public DataPassMoveData(String username, String password, MoveData moveData) {
        super(username, password);
        this.moveData = moveData;
    }

    public DataPassMoveData() {
        super();
        this.moveData = null;
    }

    public MoveData getMoveData() {
        return moveData;
    }

    public void setMoveData(MoveData moveData) {
        this.moveData = moveData;
    }

    @Override
    public String toString() {
        return "DataPassMoveData{" +
                "moveData=" + moveData +
                '}';
    }
}
