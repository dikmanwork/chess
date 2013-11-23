/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess;

/**
 *
 * @author HuangDiWen
 * @created Nov 20, 2013 8:25:39 PM
 */
public class Piece {

    private Chess chess;
    private String name;
    private PieceColor color;
    private Square current;

    public Piece(Chess chess, String name, PieceColor color, Square current) {
        this.chess = chess;
        this.name = name;
        this.color = color;
        this.current = current;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PieceColor getColor() {
        return color;
    }

    public void setColor(PieceColor color) {
        this.color = color;
    }

    public Square getCurrent() {
        return current;
    }

    public void setCurrent(Square current) {
        this.current = current;
    }

    public Square moveFront(int distance) {
        return this.chess.locateSquare(current.getFile(), current.getRank()
                + (PieceColor.White.equals(this.color) ? distance : -distance));
    }

    public Square moveBack(int distance) {
        return this.chess.locateSquare(current.getFile(), current.getRank()
                + (PieceColor.White.equals(this.color) ? -distance : distance));
    }

    public Square moveLeft(int distance) {
        return this.chess.locateSquare((char) (current.getFile() - distance), current.getRank());
    }

    public Square moveRight(int distance) {
        return this.chess.locateSquare((char) (current.getFile() + distance), current.getRank());
    }

    public Square moveLeftFront(int distance) {
        return this.chess.locateSquare((char) (current.getFile() - distance), current.getRank()
                + (PieceColor.White.equals(this.color) ? distance : -distance));
    }

    public Square moveRightFront(int distance) {
        return this.chess.locateSquare((char) (current.getFile() + distance), current.getRank()
                + (PieceColor.White.equals(this.color) ? distance : -distance));
    }

    public Square moveLeftBack(int distance) {
        return this.chess.locateSquare((char) (current.getFile() - distance), current.getRank()
                + (PieceColor.White.equals(this.color) ? -distance : distance));
    }

    public Square moveRightBack(int distance) {
        return this.chess.locateSquare((char) (current.getFile() + distance), current.getRank()
                + (PieceColor.White.equals(this.color) ? -distance : distance));
    }

    public PieceDirection getDirection(Square target) {
        int diffFile = target.getFile() - this.current.getFile();
        int diffRank = target.getRank() - this.current.getRank();
        switch (this.color) {
            case White:
                if (diffRank > 0 && diffFile == 0) {
                    return PieceDirection.Front;
                } else if (diffRank > 0 && diffFile > 0) {
                    return PieceDirection.RightFront;
                } else if (diffRank > 0 && diffFile < 0) {
                    return PieceDirection.LeftFront;
                } else if (diffRank < 0 && diffFile == 0) {
                    return PieceDirection.Back;
                } else if (diffRank < 0 && diffFile > 0) {
                    return PieceDirection.RightBack;
                } else if (diffRank < 0 && diffFile < 0) {
                    return PieceDirection.LeftBack;
                } else if (diffRank == 0 && diffFile > 0) {
                    return PieceDirection.Right;
                } else if (diffRank == 0 && diffFile < 0) {
                    return PieceDirection.Left;
                } else {
                    return null;
                }
            case Black:
                if (diffRank > 0 && diffFile == 0) {
                    return PieceDirection.Back;
                } else if (diffRank > 0 && diffFile > 0) {
                    return PieceDirection.RightBack;
                } else if (diffRank > 0 && diffFile < 0) {
                    return PieceDirection.LeftBack;
                } else if (diffRank < 0 && diffFile == 0) {
                    return PieceDirection.Front;
                } else if (diffRank < 0 && diffFile > 0) {
                    return PieceDirection.RightFront;
                } else if (diffRank < 0 && diffFile < 0) {
                    return PieceDirection.LeftFront;
                } else if (diffRank == 0 && diffFile > 0) {
                    return PieceDirection.Right;
                } else if (diffRank == 0 && diffFile < 0) {
                    return PieceDirection.Left;
                } else {
                    return null;
                }
            default:
                return null;
        }
    }

    public PieceColor getOpponentColor() {
        return this.color.equals(PieceColor.White) ? PieceColor.Black : PieceColor.White;
    }

    public boolean alive() {
        return this.current != null;
    }
}