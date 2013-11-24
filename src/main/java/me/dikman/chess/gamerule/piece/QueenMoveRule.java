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
 * @created Nov 20, 2013 9:07:39 PM
 */
public class QueenMoveRule implements PieceRule {

    private RookMoveRule rookMoveRule;
    private BishopMoveRule bishopMoveRule;

    public QueenMoveRule() {
        this.bishopMoveRule = new BishopMoveRule();
        this.rookMoveRule = new RookMoveRule();
    }

    public boolean moveable(Game game, Piece piece, Square targetSquare) {
        return this.bishopMoveRule.moveable(game, piece, targetSquare)
                || this.rookMoveRule.moveable(game, piece, targetSquare);
    }
}
