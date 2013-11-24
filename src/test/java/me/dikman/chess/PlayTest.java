package me.dikman.chess;

import me.dikman.chess.game.GamePlayer;
import me.dikman.chess.game.Game;
import org.junit.Test;

/**
 *
 * @author HuangDiWen
 * @created Nov 20, 2013 8:27:42 PM
 */
public class PlayTest {
    @Test
    public void simulate() {
        Game game = new Game();
        GamePlayer white = game.getWhitePlayer();
        GamePlayer black = game.getBlackPlayer();
        //
        game.start();
        black.move('D', 4, 'D', 5);
        white.move('D', 4, 'D', 5);
        white.move('D', 2, 'D', 4);
        black.move('C', 7, 'B', 4);
        //
        game.start();
        white.move('E', 2, 'E', 4);
        black.move('E', 7, 'E', 6);
        white.move('D', 2, 'D', 4);
        black.move('D', 7, 'D', 5);
        white.move('B', 1, 'C', 3);
        black.move('F', 8, 'B', 4);
        white.move('F', 1, 'D', 3);
        black.move('B', 4, 'C', 3);
        white.move('B', 2, 'C', 3);
        black.move('H', 7, 'H', 6);
        white.move('C', 1, 'A', 3);
        black.move('B', 8, 'D', 7);
        white.move('D', 1, 'E', 2);
        black.move('D', 5, 'E', 4);
        white.move('D', 3, 'E', 4);
        black.move('G', 8, 'F', 6);
        white.move('E', 4, 'D', 3);
        black.move('B', 7, 'B', 6);
        white.move('E', 2, 'E', 6);
        black.move('F', 7, 'E', 6);
        white.move('D', 3, 'G', 6);
    }
}
