/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HuangDiWen
 * @created Nov 22, 2013 9:57:14 PM
 */
public class Chess {

    private ChessBoard chessBoard;
    private Piece[] pieces;

    public Chess() {
        this.chessBoard = new ChessBoard();
        this.pieces = new Piece[32];
    }

    public void setup() {
        int index = 0;
        //white pieces
        this.pieces[index++] = new Piece(this, "Rook", PieceColor.White, new Square('A', 1));
        this.pieces[index++] = new Piece(this, "Knight", PieceColor.White, new Square('B', 1));
        this.pieces[index++] = new Piece(this, "Bishop", PieceColor.White, new Square('C', 1));
        this.pieces[index++] = new Piece(this, "Queue", PieceColor.White, new Square('D', 1));
        this.pieces[index++] = new Piece(this, "King", PieceColor.White, new Square('E', 1));
        this.pieces[index++] = new Piece(this, "Bishop", PieceColor.White, new Square('F', 1));
        this.pieces[index++] = new Piece(this, "Knight", PieceColor.White, new Square('G', 1));
        this.pieces[index++] = new Piece(this, "Rook", PieceColor.White, new Square('H', 1));
        this.pieces[index++] = new Piece(this, "Pawn", PieceColor.White, new Square('A', 2));
        this.pieces[index++] = new Piece(this, "Pawn", PieceColor.White, new Square('B', 2));
        this.pieces[index++] = new Piece(this, "Pawn", PieceColor.White, new Square('C', 2));
        this.pieces[index++] = new Piece(this, "Pawn", PieceColor.White, new Square('D', 2));
        this.pieces[index++] = new Piece(this, "Pawn", PieceColor.White, new Square('E', 2));
        this.pieces[index++] = new Piece(this, "Pawn", PieceColor.White, new Square('F', 2));
        this.pieces[index++] = new Piece(this, "Pawn", PieceColor.White, new Square('G', 2));
        this.pieces[index++] = new Piece(this, "Pawn", PieceColor.White, new Square('H', 2));
        //black pieces
        this.pieces[index++] = new Piece(this, "Rook", PieceColor.Black, new Square('A', 8));
        this.pieces[index++] = new Piece(this, "Knight", PieceColor.Black, new Square('B', 8));
        this.pieces[index++] = new Piece(this, "Bishop", PieceColor.Black, new Square('C', 8));
        this.pieces[index++] = new Piece(this, "Queue", PieceColor.Black, new Square('D', 8));
        this.pieces[index++] = new Piece(this, "King", PieceColor.Black, new Square('E', 8));
        this.pieces[index++] = new Piece(this, "Bishop", PieceColor.Black, new Square('F', 8));
        this.pieces[index++] = new Piece(this, "Knight", PieceColor.Black, new Square('G', 8));
        this.pieces[index++] = new Piece(this, "Rook", PieceColor.Black, new Square('H', 8));
        this.pieces[index++] = new Piece(this, "Pawn", PieceColor.Black, new Square('A', 7));
        this.pieces[index++] = new Piece(this, "Pawn", PieceColor.Black, new Square('B', 7));
        this.pieces[index++] = new Piece(this, "Pawn", PieceColor.Black, new Square('C', 7));
        this.pieces[index++] = new Piece(this, "Pawn", PieceColor.Black, new Square('D', 7));
        this.pieces[index++] = new Piece(this, "Pawn", PieceColor.Black, new Square('E', 7));
        this.pieces[index++] = new Piece(this, "Pawn", PieceColor.Black, new Square('F', 7));
        this.pieces[index++] = new Piece(this, "Pawn", PieceColor.Black, new Square('G', 7));
        this.pieces[index++] = new Piece(this, "Pawn", PieceColor.Black, new Square('H', 7));
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Piece[] getPieces() {
        return pieces;
    }

    public Piece locatePiece(char file, int rank) {
        Square square = this.chessBoard.locateSquare(file, rank);
        for (Piece piece : this.pieces) {
            if (square.equals(piece.getCurrent())) {
                return piece;
            }
        }

        return null;
    }

    public Piece[] getAlivePieces(PieceColor color) {
        List<Piece> pieces = new ArrayList();
        Piece[] all = this.getPieces();
        for (Piece piece : all) {
            if (piece.getColor().equals(color) && piece.alive()) {
                pieces.add(piece);
            }
        }
        return pieces.toArray(new Piece[pieces.size()]);
    }

    public Square locateSquare(char file, int rank) {
        return this.chessBoard.locateSquare(file, rank);
    }

    public Piece locatePiece(Square square) {
        return this.locatePiece(square.getFile(), square.getRank());
    }

    public boolean isLeapOver(Square square, Square targetSquare) {
        Piece piece = this.locatePiece(square);
        int diff = 1;
        while (diff++ < Math.max(Math.abs(square.getFile() - targetSquare.getFile()),
                Math.abs(square.getRank() - targetSquare.getRank()))) {
            Square nextSquare = piece.move(piece.getDirection(targetSquare), diff);
            if (nextSquare != null && !nextSquare.equals(targetSquare)
                    && this.locatePiece(nextSquare) != null) {
                return true;
            }
        }

        return false;
    }
}
