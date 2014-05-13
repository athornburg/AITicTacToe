import io.alexthornburg.solution.GameBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * User: alexthornburg
 * Date: 4/15/14
 * Time: 8:21 PM
 */
public class GamePlayTest {
    GameBoard board;

    @Before
    public void getBoard(){
        board = GameBoard.getInstance();
    }

    @Test
    public void testMove(){
        fillBoardWithX();
        board.initBoard();
    }

    @Test
    public void testFull(){
        fillBoardWithX();
        assertTrue(board.full());
        board.initBoard();

    }

    @Test
    public void testOpenMoves(){
        board.initBoard();
        board.processMove(0,"O");
        assertEquals("O",board.getBoardAs2D()[0][0]);
        board.processMove(1,"X");
        assertEquals("X",board.getBoardAs2D()[0][1]);
        board.processMove(2,"O");
        assertEquals("O",board.getBoardAs2D()[0][2]);
        board.processMove(3,"X");
        assertEquals("X",board.getBoardAs2D()[1][0]);
        board.processMove(4,"O");
        assertEquals("O",board.getBoardAs2D()[1][1]);
        ArrayList<Integer> openMoves = board.listAvailableMoves();
        assertTrue(openMoves.contains(5));
        assertTrue(openMoves.contains(6));
        assertTrue(openMoves.contains(7));
        assertTrue(openMoves.contains(8));
        board.initBoard();

    }

    @Test
    public void testWin(){
        board.initBoard();
        board.processMove(0, "X");
        assertEquals("X", board.getBoardAs2D()[0][0]);
        board.processMove(1, "X");
        assertEquals("X", board.getBoardAs2D()[0][1]);
        board.processMove(2, "X");
        assertTrue(board.isXWinner());
        assertTrue(board.isWinner("X"));
        board.initBoard();
    }

    @After
    public void clear(){
        board.initBoard();
    }


    public void fillBoardWithX(){
        board.processMove(0,"X");
        assertEquals("X",board.getBoardAs2D()[0][0]);
        board.processMove(1,"X");
        assertEquals("X",board.getBoardAs2D()[0][1]);
        board.processMove(2,"X");
        assertEquals("X",board.getBoardAs2D()[0][2]);
        board.processMove(3,"X");
        assertEquals("X",board.getBoardAs2D()[1][0]);
        board.processMove(4,"X");
        assertEquals("X",board.getBoardAs2D()[1][1]);
        board.processMove(5,"X");
        assertEquals("X",board.getBoardAs2D()[1][2]);
        board.processMove(6,"X");
        assertEquals("X",board.getBoardAs2D()[2][0]);
        board.processMove(7,"X");
        assertEquals("X",board.getBoardAs2D()[2][1]);
        board.processMove(8,"X");
        assertEquals("X",board.getBoardAs2D()[2][2]);
    }

}
