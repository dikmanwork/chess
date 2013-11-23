/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess;

/**
 *
 * @author HuangDiWen
 * @created Nov 22, 2013 10:01:05 PM
 */
public interface ChessGameRule {

    boolean move(ChessGame game, Player player, char nowFile, int nowRank, char newFile, int newRank);
}
