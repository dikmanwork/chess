/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.game;

import java.util.ArrayList;
import java.util.List;
import me.dikman.chess.Chess;
import me.dikman.chess.PieceColor;
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
public class Game {

    private Chess chess;
    private GamePlayer whitePlayer;
    private GamePlayer blackPlayer;
    private List<GameRule> gameRules;
    private List<GameRound> gameRounds;

    public Game() {
        this.chess = new Chess();
        this.whitePlayer = new GamePlayer(this, PieceColor.White);
        this.blackPlayer = new GamePlayer(this, PieceColor.Black);
        this.gameRounds = new ArrayList();
        //rules
        this.gameRules = new ArrayList();
        this.gameRules.add(new CheckTurnRule());
        this.gameRules.add(new CheckPositionRule());
        this.gameRules.add(new MovePieceRule());
        this.gameRules.add(new CheckMateRule());
        this.gameRules.add(new NextRoundRule());
    }

    public void start() {
        this.chess.setup();
        //
        this.gameRounds.clear();
        this.gameRounds.add(new GameRound());
        //
        System.out.println("A new chess game is started!");
    }

    public void move(GamePlayer player, char nowFile, int nowRank, char newFile, int newRank) {
        for (GameRule rule : this.gameRules) {
            boolean continueNextRule = rule.move(this, player, this.chess.locateSquare(nowFile, nowRank),
                    this.chess.locateSquare(newFile, newRank));
            if (!continueNextRule) {
                break;
            }
        }
    }

    public GamePlayer getBlackPlayer() {
        return blackPlayer;
    }

    public GamePlayer getWhitePlayer() {
        return whitePlayer;
    }

    public List<GameRound> getRounds() {
        return gameRounds;
    }

    public GameRound getCurrentRound() {
        return this.gameRounds.get(this.gameRounds.size() - 1);
    }

    public Chess getChess() {
        return chess;
    }
}
