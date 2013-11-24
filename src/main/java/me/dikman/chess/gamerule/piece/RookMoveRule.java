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
 * @created Nov 20, 2013 9:07:22 PM
 */
public class RookMoveRule implements PieceRule {

    public boolean moveable(Game game, Piece piece, Square targetSquare) {
        Square currentSquare = piece.getCurrent();
        Piece targetPiece = game.getChess().locatePiece(targetSquare);
        //
        if (game.getChess().isLeapOver(piece.getCurrent(), targetSquare)) {
            return false;
        } else if (targetPiece != null && targetPiece.sameColor(piece)) {
            return false;
        } else {
            return currentSquare.getFile() == targetSquare.getFile()
                    || currentSquare.getRank() == targetSquare.getRank();
        }
    }
}
