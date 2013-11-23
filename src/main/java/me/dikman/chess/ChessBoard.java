/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess;

/**
 *
 * @author HuangDiWen
 * @created Nov 20, 2013 8:21:09 PM
 */
public class ChessBoard {

    private Square[][] squares;

    public ChessBoard() {
        this.squares = new Square[8][8];
        int minRank = 1;
        int maxRank = 8;
        char minFile = 'A';
        char maxFile = 'H';
        for (int file = minFile; file <= maxFile; file++) {
            for (int rank = minRank; rank <= maxRank; rank++) {
                Square square = new Square((char) file, rank);
                this.squares[file - 'A'][rank - 1] = square;
            }
        }
    }

    public Square[][] getSquares() {
        return squares;
    }

    public void setSquares(Square[][] squares) {
        this.squares = squares;
    }

    public Square locateSquare(char file, int rank) {
        try {
            return this.squares[file - 'A'][rank - 1];
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
}
