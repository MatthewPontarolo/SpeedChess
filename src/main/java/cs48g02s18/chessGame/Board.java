package cs48g02s18.chessGame;

import java.awt.*;

public class Board {
	// 8x8 matrix to store board positions
	private Piece[][] spots = new Piece[8][8];
	private Player whitePlayer;
	private Player blackPlayer;
	private boolean validGameTurn = false;

	public Board(Player whitePlayer, Player blackPlayer)
	{
		this.whitePlayer = whitePlayer;
		this.blackPlayer = blackPlayer;
		setUp(whitePlayer, blackPlayer);
	}

	public void setUp(Player whitePlayer, Player blackPlayer) {
		for (Piece p : whitePlayer.getPieces()) {
			addPiece(p);
		}
		for (Piece p : blackPlayer.getPieces()) {
			addPiece(p);
		}
	}

	public Player getPlayer(int playerType)
	{
		if (playerType == 1)
		{
			return this.whitePlayer;
		}
		else
		{
			return this.blackPlayer;
		}
	}
	//return piece at some spot
	public Piece getPiece(int x, int y) {
		return spots[x][y];
	}

	public void addPiece(Piece p) {
		spots[p.getXPosition()][p.getYPosition()] = p;
	}
    public Piece getPiece(Point point) {
        return spots[point.x][point.y];
    }


    public void setGameTurn(boolean valid)
	{
		validGameTurn = valid;
	}

	// Should be called through gameHost after it allows it
	// Precondition: GameHost validates and allows gameTurn to execute
	// PostCondition: Board situation is updated, player's piece is updated, move is executed
		// Board and Player pieces are in the same situation
	public void movePiece(Player player, Piece p, int x, int y) {
		if (validGameTurn)
		{
			// updates board's pieces
			spots[x][y] = p;
			// updates player's pieces
			player.movePiece(p, x, y);
		}
	}

    public void pickUpPiece(Piece p) {
        spots[p.getXPosition()][p.getYPosition()] = null;
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
                    boardString.append(spotName.toUpperCase());
                }
                else {
                    boardString.append(spotName);
                };
            }
        }

        return boardString.toString();
    }

    public Board(String boardString) {
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