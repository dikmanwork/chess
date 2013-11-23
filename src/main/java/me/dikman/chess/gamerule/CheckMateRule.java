/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import me.dikman.chess.Chess;
import me.dikman.chess.ChessGame;
import me.dikman.chess.ChessGameRule;
import me.dikman.chess.Piece;
import me.dikman.chess.PieceColor;
import me.dikman.chess.Player;
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
public class CheckMateRule implements ChessGameRule {

    private Map<String, PieceRule> rules = new HashMap();

    public CheckMateRule() {
        this.rules.put("King", new KingMoveRule());
        this.rules.put("Knight", new KnightMoveRule());
        this.rules.put("Rook", new RookMoveRule());
        this.rules.put("Bishop", new BishopMoveRule());
        this.rules.put("Queue", new QueenMoveRule());
        this.rules.put("Pawn", new PawnMoveRule());
    }

    public boolean move(ChessGame game, Player player, char nowFile, int nowRank, char newFile, int newRank) {
        Chess chess = game.getChess();
        Piece piece = chess.locatePiece(newFile, newRank);
        Square kingSquare = this.findKingSquare(chess, piece.getOpponentColor());
        Piece kingPiece = chess.locatePiece(kingSquare);
        PieceRule pieceRule = this.rules.get(piece.getName());
        boolean checked = pieceRule.moveable(game, piece, kingSquare);
        if (checked) {
            boolean checkMate = true;
            //
            Piece[] opponenntPieces = this.getPieces(chess, piece.getOpponentColor());
            for (Piece opponent : opponenntPieces) {
                PieceRule moveRule = this.rules.get(opponent.getName());
                if (moveRule.moveable(game, opponent, piece.getCurrent())) {
                    checkMate = false;
                    break;
                }
            }

            //
            PieceRule kingMoveRule = this.rules.get(kingPiece.getName());
            Square[] squares = this.getMoveableArea(kingPiece);
            for (Square square : squares) {
                if (square != null && kingMoveRule.moveable(game, kingPiece, square)) {
                    checkMate = false;
                    break;
                }
            }

            if (checkMate) {
                System.out.println(String.format("%s is in checkmate", piece.getOpponentColor()));
                return false;
            } else {
                System.out.println(String.format("%s is in check", piece.getOpponentColor()));
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

    private Square[] getMoveableArea(Piece king) {
        List<Square> squares = new ArrayList();
        squares.add(king.moveFront(1));
        squares.add(king.moveBack(1));
        squares.add(king.moveLeft(1));
        squares.add(king.moveLeftBack(1));
        squares.add(king.moveLeftFront(1));
        squares.add(king.moveRight(1));
        squares.add(king.moveRightBack(1));
        squares.add(king.moveRightFront(1));
        return squares.toArray(new Square[squares.size()]);
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
