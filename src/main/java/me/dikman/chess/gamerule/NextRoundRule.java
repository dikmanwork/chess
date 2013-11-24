/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule;

import me.dikman.chess.Chess;
import me.dikman.chess.game.Game;
import me.dikman.chess.game.GameRule;
import static me.dikman.chess.PieceColor.Black;
import static me.dikman.chess.PieceColor.White;
import me.dikman.chess.game.GamePlayer;
import me.dikman.chess.game.GameRound;
import me.dikman.chess.Square;

/**
 *
 * @author HuangDiWen
 * @created Nov 22, 2013 11:13:21 PM
 */
public class NextRoundRule implements GameRule {

    public boolean move(Game game, GamePlayer player, Square square, Square targetSquare) {
        Chess chess = game.getChess();
        GameRound round = game.getCurrentRound();
        switch (player.getColor()) {
            case White:
                round.setWhitePiece(chess.locatePiece(targetSquare));
                round.setWhiteSquare(square);
                round.setWhiteMoveSquare(targetSquare);
                break;
            case Black:
                round.setBlackPiece(chess.locatePiece(targetSquare));
                round.setBlackSquare(square);
                round.setBlackMoveSquare(targetSquare);
                game.getRounds().add(new GameRound());
                break;
            default:
        }

        return false;
    }
}
