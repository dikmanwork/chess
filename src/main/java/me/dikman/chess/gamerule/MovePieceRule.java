/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule;

import me.dikman.chess.Square;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.dikman.chess.Chess;
import me.dikman.chess.ChessGame;
import me.dikman.chess.ChessGameRule;
import me.dikman.chess.Piece;
import me.dikman.chess.Player;
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
public class MovePieceRule implements ChessGameRule {

    private Map<String, PieceRule> rules = new HashMap();

    public MovePieceRule() {
        this.rules.put("King", new KingMoveRule());
        this.rules.put("Knight", new KnightMoveRule());
        this.rules.put("Rook", new RookMoveRule());
        this.rules.put("Bishop", new BishopMoveRule());
        this.rules.put("Queue", new QueenMoveRule());
        this.rules.put("Pawn", new PawnMoveRule());
    }

    public boolean move(ChessGame game, Player player, char nowFile, int nowRank, char newFile, int newRank) {
        Chess chess = game.getChess();
        Square square = chess.locateSquare(nowFile, nowRank);
        Square targetSquare = chess.locateSquare(newFile, newRank);
        Piece piece = chess.locatePiece(nowFile, nowRank);
        PieceRule pieceRule = this.rules.get(piece.getName());
        if (pieceRule.moveable(game, piece, targetSquare)) {
            List list = new ArrayList();
            StringBuilder builder = new StringBuilder();
            builder.append("%s’s %s moves from %s to %s");
            list.add(player.getColor());
            list.add(piece.getName());
            list.add(square);
            list.add(targetSquare);
            //
            Piece taretPiece = chess.locatePiece(newFile, newRank);
            if (taretPiece != null) {
                builder.append(" taking %s's %s");
                list.add(taretPiece.getColor());
                list.add(taretPiece.getName());
                taretPiece.setCurrent(null);
            }
            piece.setCurrent(targetSquare);

            System.out.println(String.format(builder.toString(), list.toArray()));
            return true;
        } else {
            System.out.println(String.format("%s’s %s cannot move to %s!", new Object[]{player.getColor(), piece.getName(), targetSquare}));
            return false;
        }
    }
}
