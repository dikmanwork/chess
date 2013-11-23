/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule;

import me.dikman.chess.ChessGame;
import me.dikman.chess.ChessGameRule;
import me.dikman.chess.Player;
import me.dikman.chess.Round;

/**
 *
 * @author HuangDiWen
 * @created Nov 22, 2013 10:43:25 PM
 */
public class CheckTurnRule implements ChessGameRule {

    public boolean move(ChessGame game, Player player, char nowFile, int nowRank, char newFile, int newRank) {
        Round round = game.getCurrentRound();
        if (!round.nextTurn().equals(player.getColor())) {
            System.out.println(String.format("It is not %s’s turn to move!", player.getColor()));
            return false;
        } else {
            return true;
        }
    }
}
