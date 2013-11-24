/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule.piece;

import me.dikman.chess.Chess;
import me.dikman.chess.Square;
import me.dikman.chess.game.Game;
import me.dikman.chess.Piece;

/**
 *
 * @author HuangDiWen
 * @created Nov 20, 2013 9:07:30 PM
 */
public class BishopMoveRule implements PieceRule {

    public boolean moveable(Game game, Piece piece, Square targetSquare) {
        Chess chess = game.getChess();
        Square square = piece.getCurrent();
        Piece targetPiece = chess.locatePiece(targetSquare);
        //
        if (chess.isLeapOver(square, targetSquare)) {
            return false;
        } else if (targetPiece != null && targetPiece.sameColor(piece)) {
            return false;
        } else {
            return Math.abs(square.getFile() - targetSquare.getFile())
                    == Math.abs(square.getRank() - targetSquare.getRank());
        }
    }
}
