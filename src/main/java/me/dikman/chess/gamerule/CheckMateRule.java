/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule;

import java.util.HashMap;
import java.util.Map;
import me.dikman.chess.Chess;
import me.dikman.chess.game.Game;
import me.dikman.chess.game.GameRule;
import me.dikman.chess.Piece;
import me.dikman.chess.PieceColor;
import me.dikman.chess.game.GamePlayer;
import me.dikman.chess.Square;
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
 * @created Nov 23, 2013 12:08:59 PM
 */
public class CheckMateRule implements GameRule {

    private Map<String, PieceRule> rules = new HashMap();

    public CheckMateRule() {
        this.rules.put("King", new KingMoveRule());
        this.rules.put("Knight", new KnightMoveRule());
        this.rules.put("Rook", new RookMoveRule());
        this.rules.put("Bishop", new BishopMoveRule());
        this.rules.put("Queue", new QueenMoveRule());
        this.rules.put("Pawn", new PawnMoveRule());
    }

    public boolean move(Game game, GamePlayer player, Square square, Square targetSquare) {
        Chess chess = game.getChess();
        Piece piece = chess.locatePiece(targetSquare);
        Square kingSquare = this.findKingSquare(chess, piece.getDiffColor());
        Piece kingPiece = chess.locatePiece(kingSquare);
        boolean checked = this.kingChecked(game, kingPiece);
        if (checked) {
            boolean checkMate = this.checkMate(game, kingPiece);
            if (checkMate) {
                System.out.println(String.format("%s is in checkmate", piece.getDiffColor()));
                return false;
            } else {
                System.out.println(String.format("%s is in check", piece.getDiffColor()));
            }

        }
        return true;
    }

    private Square findKingSquare(Chess chess, PieceColor color) {
        Piece[] pieces = chess.getPieces();
        for (Piece piece : pieces) {
            if (piece.getColor().equals(color) && piece.getName().equals("King")) {
                return piece.getCurrent();
            }
        }

        return null;
    }

    private boolean checkMate(Game game, Piece kingPiece) {
        Piece[] pieces = game.getChess().getAlivePieces(kingPiece.getColor());
        Square[][] squares = game.getChess().getChessBoard().getSquares();
        for (Square[] squareFile : squares) {
            for (Square squareFileRank : squareFile) {
                for (Piece piece : pieces) {
                    PieceRule pieceRule = this.rules.get(piece.getName());
                    if (pieceRule.moveable(game, piece, squareFileRank)) {
                        if (!this.kingWillBeChecked(game, piece, squareFileRank, kingPiece)) {
                            return false;
                        }
                    }
                }
            }
        }

        return true;
    }

    private boolean kingChecked(Game game, Piece kingPiece) {
        Piece[] pieces = game.getChess().getAlivePieces(kingPiece.getDiffColor());
        for (Piece piece : pieces) {
            PieceRule pieceRule = this.rules.get(piece.getName());
            if (pieceRule.moveable(game, piece, kingPiece.getCurrent())) {
                return true;
            }
        }
        return false;
    }

    private boolean kingWillBeChecked(Game game, Piece movingPiece, Square targetSquare, Piece kingPiece) {
        Square square = movingPiece.getCurrent();
        Piece targetPiece = game.getChess().locatePiece(targetSquare);
        try {
            //detect
            movingPiece.setCurrent(targetSquare);
            if (targetPiece != null) {
                targetPiece.setCurrent(null);
            }
            return this.kingChecked(game, kingPiece);
        } finally {
            movingPiece.setCurrent(square);
            if (targetPiece != null) {
                targetPiece.setCurrent(targetSquare);
            }
        }
    }
}
