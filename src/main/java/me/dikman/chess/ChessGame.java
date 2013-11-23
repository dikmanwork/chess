/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess;

import java.util.ArrayList;
import java.util.List;
import me.dikman.chess.gamerule.CheckMateRule;
import me.dikman.chess.gamerule.CheckPositionRule;
import me.dikman.chess.gamerule.CheckTurnRule;
import me.dikman.chess.gamerule.NextRoundRule;
import me.dikman.chess.gamerule.MovePieceRule;

/**
 *
 * @author HuangDiWen
 * @created Nov 22, 2013 10:00:58 PM
 */
public class ChessGame {

    private Chess chess;
    private Player whitePlayer;
    private Player blackPlayer;
    private List<ChessGameRule> chessGameRules;
    private List<Round> rounds;

    public ChessGame() {
        this.chess = new Chess();
        this.whitePlayer = new Player(this, PieceColor.White);
        this.blackPlayer = new Player(this, PieceColor.Black);
    }

    public void start() {
        this.chess.setup();
        //
        this.rounds = new ArrayList();
        this.rounds.add(new Round());
        //rules
        this.chessGameRules = new ArrayList();
        this.chessGameRules.add(new CheckTurnRule());
        this.chessGameRules.add(new CheckPositionRule());
        this.chessGameRules.add(new MovePieceRule());
        this.chessGameRules.add(new CheckMateRule());
        this.chessGameRules.add(new NextRoundRule());
        //
        System.out.println("A new chess game is started!");
    }

    public void move(Player player, char nowFile, int nowRank, char newFile, int newRank) {
        for (ChessGameRule rule : this.chessGameRules) {
            boolean continueNextRule = rule.move(this, player, nowFile, nowRank, newFile, newRank);
            if (!continueNextRule) {
                break;
            }
        }
    }

    public Player getBlackPlayer() {
        return blackPlayer;
    }

    public Player getWhitePlayer() {
        return whitePlayer;
    }

    public List<Round> getRounds() {
        return rounds;
    }

    public Round getCurrentRound() {
        return this.rounds.get(this.rounds.size() - 1);
    }

    public Chess getChess() {
        return chess;
    }
}
