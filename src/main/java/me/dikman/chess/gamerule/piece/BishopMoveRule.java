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
 * @created Nov 20, 2013 9:07:30 PM
 */
public class BishopMoveRule implements PieceRule {

    public boolean moveable(ChessGame game, Piece piece, Square targetSquare) {
        return this.moveable(game.getChess(), piece.getCurrent(), targetSquare);
    }

    private boolean moveable(Chess chess, Square current, Square targetSquare) {
        Piece piece = chess.locatePiece(current);
        Piece targetPiece = chess.locatePiece(targetSquare);
        PieceDirection direction = piece.getDirection(targetSquare);
        if (direction != null) {
            final int maxDistance = 7;
            for (int distance = 1; distance <= maxDistance; distance++) {
                Square square1 = null;
                switch (direction) {
                    case LeftFront:
                        square1 = piece.moveLeftFront(distance);
                        break;
                    case LeftBack:
                        square1 = piece.moveLeftBack(distance);
                        break;
                    case RightFront:
                        square1 = piece.moveRightFront(distance);
                        break;
                    case RightBack:
                        square1 = piece.moveRightBack(distance);
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
