/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule;

import me.dikman.chess.Square;
import java.util.HashMap;
import java.util.Map;
import me.dikman.chess.Chess;
import me.dikman.chess.game.Game;
import me.dikman.chess.game.GameRule;
import me.dikman.chess.Piece;
import me.dikman.chess.game.GamePlayer;
import me.dikman.chess.gamerule.piece.BishopMoveRule;
import me.dikman.chess.gamerule.piece.KingMoveRule;
import me.dikman.chess.gamerule.piece.KnightMoveRule;
import me.dikman.chess.gamerule.piece.PawnMoveRule;
import me.dikman.chess.gamerule.piece.PieceRule;
import me.dikman.chess.gamerule.piece.QueenMoveRule;
import me.dikman.chess.gamerule.piece.RookMoveRule;

/**
 *
 * @author HuangDiWen
 * @created Nov 22, 2013 11:01:23 PM
 */
public class MovePieceRule implements GameRule {

    private Map<String, PieceRule> rules = new HashMap();

    public MovePieceRule() {
        this.rules.put("King", new KingMoveRule());
        this.rules.put("Knight", new KnightMoveRule());
        this.rules.put("Rook", new RookMoveRule());
        this.rules.put("Bishop", new BishopMoveRule());
        this.rules.put("Queue", new QueenMoveRule());
        this.rules.put("Pawn", new PawnMoveRule());
    }

    public boolean move(Game game, GamePlayer player, Square square, Square targetSquare) {
        Chess chess = game.getChess();
        Piece piece = chess.locatePiece(square);
        PieceRule pieceRule = this.rules.get(piece.getName());
        if (pieceRule.moveable(game, piece, targetSquare)) {
            StringBuilder builder = new StringBuilder();
            builder.append(String.format("%s's %s moves from %s to %s", player.getColor(), piece.getName(), square, targetSquare));
            Piece taretPiece = chess.locatePiece(targetSquare);
            piece.setCurrent(targetSquare);
            if (taretPiece != null) {
                builder.append(String.format(" taking %s's %s", taretPiece.getColor(), taretPiece.getName()));
                taretPiece.setCurrent(null);
            }
            System.out.println(builder.toString());
            return true;
        } else {
            System.out.println(String.format("%s’s %s cannot move to %s!", new Object[]{player.getColor(), piece.getName(), targetSquare}));
            return false;
        }
    }
}
