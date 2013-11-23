/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule.piece;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import me.dikman.chess.Chess;
import me.dikman.chess.Square;
import me.dikman.chess.ChessGame;
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

    public boolean moveable(ChessGame game, Piece piece, Square targetSquare) {
        Square[] movableSquares = this.getMoveableArea(piece);
        Piece[] opponentPiecs = this.getPieces(game.getChess(), piece.getOpponentColor());
        for (Square moveableSquare : movableSquares) {
            if (targetSquare.equals(moveableSquare)) {
                Piece existPiece = game.getChess().locatePiece(moveableSquare);
                if (existPiece != null && existPiece.getColor().equals(piece.getColor())) {
                    return false;
                }
                for (Piece opponent : opponentPiecs) {
                    Piece opponentPiece = game.getChess().locatePiece(opponent.getCurrent());
                    PieceRule moveRule = this.rules.get(opponentPiece.getName());
                    if (moveRule.moveable(game, opponentPiece, targetSquare)) {
                        return false;
                    }
                }

                return true;
            }
        }

        return false;
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
        Iterator<Square> it = squares.iterator();
        while (it.hasNext()) {
            if (it.next() == null) {
                it.remove();
            }
        }
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
