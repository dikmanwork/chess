/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule;

import me.dikman.chess.Chess;
import me.dikman.chess.ChessGame;
import me.dikman.chess.ChessGameRule;
import me.dikman.chess.Player;

/**
 *
 * @author HuangDiWen
 * @created Nov 22, 2013 10:55:01 PM
 */
public class CheckPositionRule implements ChessGameRule {

    public boolean move(ChessGame game, Player player, char nowFile, int nowRank, char newFile, int newRank) {
        Chess chess = game.getChess();
        if (chess.locatePiece(nowFile, nowRank) != null) {
            return true;
        } else {
            System.out.println(String.format("There is no piece at position %s!", nowFile + "" + nowRank));
            return false;
        }
    }
}
