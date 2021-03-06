/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule.piece;

import java.util.List;
import me.dikman.chess.Chess;
import me.dikman.chess.Square;
import me.dikman.chess.game.Game;
import me.dikman.chess.Piece;
import me.dikman.chess.PieceColor;
import static me.dikman.chess.PieceColor.Black;
import static me.dikman.chess.PieceColor.White;
import me.dikman.chess.game.GameRound;

/**
 *
 * @author HuangDiWen
 * @created Nov 20, 2013 9:06:05 PM
 */
public class PawnMoveRule implements PieceRule {

    public boolean moveable(Game game, Piece piece, Square targetSquare) {
        Chess chess = game.getChess();
        boolean firstStep = this.isFirstStep(game, piece);
        Piece targetPiece = chess.locatePiece(targetSquare.getFile(), targetSquare.getRank());
        //
        Square[] moveableSquares = this.getMoveableSquares(chess, piece, firstStep);
        Square[] checkableSquares = this.getCheckableSquares(chess, piece);
        if (this.inMoveableSquares(targetSquare, moveableSquares)) {
            return firstStep ? (moveableSquares.length == 1 ? targetPiece == null
                    : chess.locatePiece(moveableSquares[0]) == null
                    && chess.locatePiece(moveableSquares[1]) == null) : targetPiece == null;
        } else if (this.inCheckableSquares(targetSquare, checkableSquares)) {
            return targetPiece != null && targetPiece.diffColor(piece);
        } else {
            return false;
        }
    }

    private boolean inCheckableSquares(Square targetSquare, Square[] checkableSquares) {
        return this.inArray(targetSquare, checkableSquares);
    }

    private boolean inMoveableSquares(Square targetSquare, Square[] moveableSquares) {
        return this.inArray(targetSquare, moveableSquares);
    }

    private boolean inArray(Square square, Square[] squares) {
        for (Square s : squares) {
            if (square.equals(s)) {
                return true;
            }
        }
        return false;
    }

    private Square[] getMoveableSquares(Chess chess, Piece piece, boolean firstStep) {
        Square current = piece.getCurrent();
        switch (piece.getColor()) {
            case White:
                return firstStep ? new Square[]{chess.locateSquare(current.getFile(), current.getRank() + 1),
                    chess.locateSquare(current.getFile(), current.getRank() + 2)}
                        : new Square[]{chess.locateSquare(current.getFile(), current.getRank() + 1)};
            case Black:
                return firstStep ? new Square[]{chess.locateSquare(current.getFile(), current.getRank() - 1),
                    chess.locateSquare(current.getFile(), current.getRank() - 2)}
                        : new Square[]{chess.locateSquare(current.getFile(), current.getRank() - 1)};
            default:
                return null;
        }
    }

    private Square[] getCheckableSquares(Chess chess, Piece piece) {
        Square current = piece.getCurrent();
        switch (piece.getColor()) {
            case White:
                return new Square[]{chess.locateSquare((char) (current.getFile() + 1), current.getRank() + 1),
                    chess.locateSquare((char) (current.getFile() - 1), current.getRank() + 1)};
            case Black:
                return new Square[]{chess.locateSquare((char) (current.getFile() + 1), current.getRank() - 1),
                    chess.locateSquare((char) (current.getFile() - 1), current.getRank() - 1)};
            default:
                return null;
        }
    }

    private boolean isFirstStep(Game game, Piece piece) {
        List<GameRound> rounds = game.getRounds();
        for (GameRound round : rounds) {
            if ((PieceColor.White.equals(piece.getColor()) && piece.equals(round.getWhitePiece()))
                    || (PieceColor.Black.equals(piece.getColor()) && piece.equals(round.getBlackPiece()))) {
                return false;
            }
        }
        return true;
    }
}
