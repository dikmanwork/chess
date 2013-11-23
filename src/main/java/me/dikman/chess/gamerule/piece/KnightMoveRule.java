/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess.gamerule.piece;

import java.util.ArrayList;
import java.util.List;
import me.dikman.chess.Chess;
import me.dikman.chess.Square;
import me.dikman.chess.ChessGame;
import me.dikman.chess.Piece;

/**
 *
 * @author HuangDiWen
 * @created Nov 20, 2013 9:06:19 PM
 */
public class KnightMoveRule implements PieceRule {

    public boolean moveable(ChessGame game, Piece piece, Square targetSquare) {
        Square[] moveableSquares = this.getMoveableArea(game.getChess(), piece.getCurrent());
        return this.inArray(targetSquare, moveableSquares);
    }

    private Square[] getMoveableArea(Chess chess, Square current) {
        List<Square> squares = new ArrayList();
        squares.add(this.getMoveableSquare(chess, current, 1, 2));
        squares.add(this.getMoveableSquare(chess, current, 1, -2));
        squares.add(this.getMoveableSquare(chess, current, 2, 1));
        squares.add(this.getMoveableSquare(chess, current, 2, -1));
        squares.add(this.getMoveableSquare(chess, current, -1, 2));
        squares.add(this.getMoveableSquare(chess, current, -1, -2));
        squares.add(this.getMoveableSquare(chess, current, -2, 1));
        squares.add(this.getMoveableSquare(chess, current, -2, -1));
        return squares.toArray(new Square[squares.size()]);
    }

    private Square getMoveableSquare(Chess chess, Square current, int diffFile, int diffRank) {
        return chess.locateSquare((char) (current.getFile() + diffFile), current.getRank() + diffRank);
    }

    private boolean inArray(Square square, Square[] squares) {
        for (Square s : squares) {
            if (square.equals(s)) {
                return true;
            }
        }
        return false;
    }
}
