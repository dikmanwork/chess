/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package me.dikman.chess;

/**
 *
 * @author HuangDiWen
 * @created Nov 20, 2013 8:21:34 PM
 */
public class Square {

    private char file;
    private int rank;

    public Square(char file, int rank) {
        this.file = file;
        this.rank = rank;
    }

    public int getRank() {
        return rank;
    }

    public char getFile() {
        return file;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Square) {
            Square that = (Square) obj;
            return this.rank == that.rank && this.file == that.file;
        }
        return super.equals(obj);
    }

    @Override
    public String toString() {
        return Character.toString(this.file) + this.rank;
    }
}
