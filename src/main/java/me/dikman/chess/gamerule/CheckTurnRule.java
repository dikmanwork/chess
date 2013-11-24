/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule;

import me.dikman.chess.game.Game;
import me.dikman.chess.game.GameRule;
import me.dikman.chess.game.GamePlayer;
import me.dikman.chess.game.GameRound;
import me.dikman.chess.Square;

/**
 *
 * @author HuangDiWen
 * @created Nov 22, 2013 10:43:25 PM
 */
public class CheckTurnRule implements GameRule {

    public boolean move(Game game, GamePlayer player, Square square, Square targetSquare) {
        GameRound round = game.getCurrentRound();
        if (!round.nextTurn().equals(player.getColor())) {
            System.out.println(String.format("It is not %s’s turn to move!", player.getColor()));
            return false;
        } else {
            return true;
        }
    }
}
