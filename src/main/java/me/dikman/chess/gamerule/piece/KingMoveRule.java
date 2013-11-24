/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule.piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.dikman.chess.Chess;
import me.dikman.chess.Square;
import me.dikman.chess.game.Game;
import me.dikman.chess.Piece;
import me.dikman.chess.PieceColor;

/**
 *
 * @author HuangDiWen
 * @created Nov 20, 2013 9:07:07 PM
 */
public class KingMoveRule implements PieceRule {

    private Map<String, PieceRule> rules = new HashMap();

    public KingMoveRule() {
        this.rules.put("King", this);
        this.rules.put("Knight", new KnightMoveRule());
        this.rules.put("Rook", new RookMoveRule());
        this.rules.put("Bishop", new BishopMoveRule());
        this.rules.put("Queue", new QueenMoveRule());
        this.rules.put("Pawn", new PawnMoveRule());
    }

    public boolean moveable(Game game, Piece piece, Square targetSquare) {
        Chess chess = game.getChess();
        Square square = piece.getCurrent();
        Piece targetPiece = chess.locatePiece(targetSquare);
        if (chess.isLeapOver(piece.getCurrent(), targetSquare)) {
            return false;
        } else if (targetPiece != null && targetPiece.getColor().equals(piece.getColor())) {
            return false;
        } else if (this.kingWillBeChecked(game, piece, targetSquare)) {
            return false;
        } else {
            return Math.abs(targetSquare.getFile() - square.getFile()) <= 1
                    && Math.abs(targetSquare.getRank() - square.getRank()) <= 1
                    && (targetSquare.getFile() != square.getFile()
                    || targetSquare.getRank() != square.getRank());
        }
    }

    private boolean kingWillBeChecked(Game game, Piece kingPiece, Square targetSquare) {
        Square square = kingPiece.getCurrent();
        Piece targetPiece = game.getChess().locatePiece(targetSquare);
        try {
            //detect
            kingPiece.setCurrent(targetSquare);
            if (targetPiece != null) {
                targetPiece.setCurrent(null);
            }
            //
            Piece[] pieces = this.getPieces(game.getChess(), kingPiece.getOpponentColor());
            for (Piece piece : pieces) {
                PieceRule pieceRule = this.rules.get(piece.getName());
                if (pieceRule.moveable(game, piece, kingPiece.getCurrent())) {
                    return true;
                }
            }
            return false;
        } finally {
            kingPiece.setCurrent(square);
            if (targetPiece != null) {
                targetPiece.setCurrent(targetSquare);
            }
        }
    }

    private Piece[] getPieces(Chess chess, PieceColor color) {
        List<Piece> pieces = new ArrayList();
        Piece[] all = chess.getPieces();
        for (Piece piece : all) {
            if (piece.getColor().equals(color) && piece.alive()) {
                pieces.add(piece);
            }
        }
        return pieces.toArray(new Piece[pieces.size()]);
    }
}
