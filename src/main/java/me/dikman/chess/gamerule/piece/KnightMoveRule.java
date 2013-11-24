/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule.piece;

import me.dikman.chess.Square;
import me.dikman.chess.game.Game;
import me.dikman.chess.Piece;

/**
 *
 * @author HuangDiWen
 * @created Nov 20, 2013 9:06:19 PM
 */
public class KnightMoveRule implements PieceRule {

    public boolean moveable(Game game, Piece piece, Square targetSquare) {
        Square currentSquare = piece.getCurrent();
        Piece targetPiece = game.getChess().locatePiece(targetSquare);
        //
        if (targetPiece != null && targetPiece.sameColor(piece)) {
            return false;
        } else {
            return (Math.abs(currentSquare.getFile() - targetSquare.getFile()) == 1
                    && Math.abs(currentSquare.getRank() - targetSquare.getRank()) == 2)
                    || (Math.abs(currentSquare.getFile() - targetSquare.getFile()) == 2
                    && Math.abs(currentSquare.getRank() - targetSquare.getRank()) == 1);
        }
    }
}
