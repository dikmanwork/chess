/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.game;

import me.dikman.chess.Square;

/**
 *
 * @author HuangDiWen
 * @created Nov 22, 2013 10:01:05 PM
 */
public interface GameRule {

    boolean move(Game game, GamePlayer player, Square square, Square targetSquare);
}
