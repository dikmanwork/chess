/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule;

import me.dikman.chess.Chess;
import me.dikman.chess.ChessGame;
import me.dikman.chess.ChessGameRule;
import static me.dikman.chess.PieceColor.Black;
import static me.dikman.chess.PieceColor.White;
import me.dikman.chess.Player;
import me.dikman.chess.Round;

/**
 *
 * @author HuangDiWen
 * @created Nov 22, 2013 11:13:21 PM
 */
public class NextRoundRule implements ChessGameRule {

    public boolean move(ChessGame game, Player player, char nowFile, int nowRank, char newFile, int newRank) {
        Chess chess = game.getChess();
        Round round = game.getCurrentRound();
        switch (player.getColor()) {
            case White:
                round.setWhitePiece(chess.locatePiece(newFile, newRank));
                round.setWhiteSquare(chess.locateSquare(nowFile, nowRank));
                round.setWhiteMoveSquare(chess.locateSquare(newFile, newRank));
                break;
            case Black:
                round.setBlackPiece(chess.locatePiece(newFile, newRank));
                round.setBlackSquare(chess.locateSquare(nowFile, nowRank));
                round.setBlackMoveSquare(chess.locateSquare(newFile, newRank));
                game.getRounds().add(new Round());
                break;
            default:
        }

        return false;
    }
}
