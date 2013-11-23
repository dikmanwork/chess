/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess;

/**
 *
 * @author HuangDiWen
 * @created Nov 22, 2013 10:44:28 PM
 */
public class Round {

    private Piece whitePiece;
    private Square whiteSquare;
    private Square whiteMoveSquare;
    private Piece blackPiece;
    private Square blackSquare;
    private Square blackMoveSquare;

    public Piece getWhitePiece() {
        return whitePiece;
    }

    public Piece getBlackPiece() {
        return blackPiece;
    }

    public void setBlackPiece(Piece blackPiece) {
        this.blackPiece = blackPiece;
    }

    public void setWhitePiece(Piece whitePiece) {
        this.whitePiece = whitePiece;
    }

    public Square getWhiteSquare() {
        return whiteSquare;
    }

    public void setWhiteSquare(Square whiteSquare) {
        this.whiteSquare = whiteSquare;
    }

    public Square getWhiteMoveSquare() {
        return whiteMoveSquare;
    }

    public void setWhiteMoveSquare(Square whiteMoveSquare) {
        this.whiteMoveSquare = whiteMoveSquare;
    }

    public Square getBlackSquare() {
        return blackSquare;
    }

    public void setBlackSquare(Square blackSquare) {
        this.blackSquare = blackSquare;
    }

    public Square getBlackMoveSquare() {
        return blackMoveSquare;
    }

    public void setBlackMoveSquare(Square blackMoveSquare) {
        this.blackMoveSquare = blackMoveSquare;
    }

    public PieceColor nextTurn() {
        return this.whiteSquare == null ? PieceColor.White : PieceColor.Black;
    }
}
