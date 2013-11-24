/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule;

import me.dikman.chess.Chess;
import me.dikman.chess.game.Game;
import me.dikman.chess.game.GameRule;
import me.dikman.chess.game.GamePlayer;
import me.dikman.chess.Square;

/**
 *
 * @author HuangDiWen
 * @created Nov 22, 2013 10:55:01 PM
 */
public class CheckPositionRule implements GameRule {

    public boolean move(Game game, GamePlayer player, Square square, Square targetSquare) {
        Chess chess = game.getChess();
        if (chess.locatePiece(square) != null) {
            return true;
        } else {
            System.out.println(String.format("There is no piece at position %s!", square));
            return false;
        }
    }
}
