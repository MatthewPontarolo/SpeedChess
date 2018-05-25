package cs48g02s18.chessGame;

import java.awt.*;

//todo move this to server package
public class Board {
	// 8x8 matrix to store board positions
	private Piece[][] spots = new Piece[8][8];

	public Board(Player player1, Player player2)
	{
	setUp(player1, player2);
	}

	public void setUp(Player player1, Player player2) {
		for (Piece p : player1.getPieces()) {
			addPiece(p);
		}
		for (Piece p : player2.getPieces()) {
			addPiece(p);
		}
	}

	//return piece at some spot
	public Piece getPiece(int x, int y) {
		return spots[x][y];
	}

	public Piece getPiece(Point point) {
		return spots[point.x][point.y];
	}


	public void addPiece(Piece p) {
		spots[p.getXPosition()][p.getYPosition()] = p;
	}

	public void movePiece(Piece p, int x, int y) {
		spots[p.getXPosition()][p.getYPosition()] = null;
		spots[x][y] = p;
		p.move(x, y);
	}

	public Piece pickUpPieceAt(Point point){
	    Piece pickedUp = getPiece(point);
	    spots[point.x][point.y] = null;
	    return pickedUp;
    }

    public void putPiece(Piece piece) {
	    Point putAt = piece.getPosition();
	    spots[putAt.x][putAt.y] = piece;
    }

	public void capturePieceAt(Point capturePoint) {
	    Piece captureMe = getPiece(capturePoint);

        if (captureMe != null) {
            captureMe.capture();
        }
    }

    @Override
    public String toString() {
        StringBuilder boardString = new StringBuilder();
        Piece spot;
        String spotName;
        for (int i = 0; i < 64; i++) {
            spot = spots[i / 8][i % 8];
            if (spot == null) {
                boardString.append("0");
            } else {
                if (spot instanceof Pawn) {
                    spotName = "p";
                } else if (spot instanceof Rook) {
                    spotName = "r";
                } else if (spot instanceof Queen) {
                    spotName = "q";
                } else if (spot instanceof King) {
                    spotName = "k";
                } else if (spot instanceof Bishop) {
                    spotName = "b";
                } else if (spot instanceof Knight) {
                    spotName = "n";
                } else {
                    spotName = "!";
                }
                if (spot.getPlayer() == 0) {
                    spotName.toUpperCase();
                }
                boardString.append(spotName);
            }
        }

        return boardString.toString();
    }

    public Board(String boardString) {
        int playerNumber;
        Character currentChar;
	    for (int i = 0; i < 64; i++) {
            currentChar = boardString.charAt(i);
            if (currentChar == '0'){
                spots[i / 8][i % 8] = null;
            }
            else if (currentChar == 'p'){
                spots[i / 8][i % 8] = new Pawn(i / 8, i % 8);
                spots[i / 8][i % 8].setPlayer(1);
            }
            else if (currentChar == 'P'){
                spots[i / 8][i % 8] = new Pawn(i / 8, i % 8);
                spots[i / 8][i % 8].setPlayer(0);
            }
            else if (currentChar == 'r'){
                spots[i / 8][i % 8] = new Rook(i / 8, i % 8);
                spots[i / 8][i % 8].setPlayer(1);
            }
            else if (currentChar == 'R'){
                spots[i / 8][i % 8] = new Rook(i / 8, i % 8);
                spots[i / 8][i % 8].setPlayer(0);
            }
            else if (currentChar == 'q'){
                spots[i / 8][i % 8] = new Queen(i / 8, i % 8);
                spots[i / 8][i % 8].setPlayer(1);
            }
            else if (currentChar == 'Q'){
                spots[i / 8][i % 8] = new Queen(i / 8, i % 8);
                spots[i / 8][i % 8].setPlayer(0);
            }
            else if (currentChar == 'k'){
                spots[i / 8][i % 8] = new King(i / 8, i % 8);
                spots[i / 8][i % 8].setPlayer(1);
            }
            else if (currentChar == 'K'){
                spots[i / 8][i % 8] = new King(i / 8, i % 8);
                spots[i / 8][i % 8].setPlayer(0);
            }
            else if (currentChar == 'b'){
                spots[i / 8][i % 8] = new Bishop(i / 8, i % 8);
                spots[i / 8][i % 8].setPlayer(1);
            }
            else if (currentChar == 'B'){
                spots[i / 8][i % 8] = new Bishop(i / 8, i % 8);
                spots[i / 8][i % 8].setPlayer(0);
            }
            else if (currentChar == 'n'){
                spots[i / 8][i % 8] = new Knight(i / 8, i % 8);
                spots[i / 8][i % 8].setPlayer(1);
            }
            else if (currentChar == 'N'){
                spots[i / 8][i % 8] = new Knight(i / 8, i % 8);
                spots[i / 8][i % 8].setPlayer(0);
            }

        }
    }
}
