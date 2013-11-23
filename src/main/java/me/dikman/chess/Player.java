/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess;

/**
 *
 * @author HuangDiWen
 * @created Nov 20, 2013 8:28:53 PM
 */
public class Player {

    private ChessGame game;
    private PieceColor color;

    public Player(ChessGame game, PieceColor color) {
        this.game = game;
        this.color = color;
    }

    public void move(char nowFile, int nowRank, char newFile, int newRank) {
        this.game.move(this, nowFile, nowRank, newFile, newRank);
    }

    public PieceColor getColor() {
        return color;
    }
}
