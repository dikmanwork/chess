/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule.piece;

import me.dikman.chess.Chess;
import me.dikman.chess.Square;
import me.dikman.chess.ChessGame;
import me.dikman.chess.Piece;
import me.dikman.chess.PieceDirection;

/**
 *
 * @author HuangDiWen
 * @created Nov 20, 2013 9:07:22 PM
 */
public class RookMoveRule implements PieceRule {

    public boolean moveable(ChessGame game, Piece piece, Square targetSquare) {
        Chess chess = game.getChess();
        Piece targetPiece = chess.locatePiece(targetSquare);
        PieceDirection direction = piece.getDirection(targetSquare);
        if (direction != null) {
            final int maxDistance = 7;
            for (int distance = 1; distance <= maxDistance; distance++) {
                Square square1 = null;
                switch (direction) {
                    case Front:
                        square1 = piece.moveFront(distance);
                        break;
                    case Back:
                        square1 = piece.moveBack(distance);
                        break;
                    case Left:
                        square1 = piece.moveLeft(distance);
                        break;
                    case Right:
                        square1 = piece.moveRight(distance);
                        break;
                    default:
                        break;
                }

                if (square1 == null) {
                    return false;
                } else if (square1.equals(targetSquare)) {
                    return targetPiece == null || !targetPiece.getColor().equals(piece.getColor());
                } else if (chess.locatePiece(square1) != null) {
                    return false;
                }
            }
        }

        return false;
    }
}
