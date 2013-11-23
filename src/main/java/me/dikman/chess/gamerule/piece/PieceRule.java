/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule.piece;

import me.dikman.chess.ChessGame;
import me.dikman.chess.Piece;
import me.dikman.chess.Square;

/**
 *
 * @author HuangDiWen
 * @created Nov 22, 2013 10:01:10 PM
 */
public interface PieceRule {

    boolean moveable(ChessGame game, Piece piece, Square targetSquare);
}
