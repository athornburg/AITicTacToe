import io.alexthornburg.solution.GameBoard;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * User: alexthornburg
 * Date: 4/15/14
 * Time: 7:51 PM
 */
public class BoardTest {
    GameBoard board;

    @Before
    public void getBoard(){
       board = GameBoard.getInstance();
    }

    @Test
    public void testInit(){
        assertEquals("_", board.getRow1().getA());
        assertEquals("_",board.getRow1().getB());
        assertEquals("_",board.getRow1().getC());
        assertEquals("_",board.getRow2().getA());
        assertEquals("_",board.getRow2().getB());
        assertEquals("_",board.getRow2().getC());
        assertEquals("_",board.getRow3().getA());
        assertEquals("_",board.getRow3().getB());
        assertEquals("_",board.getRow3().getC());
    }

    @Test
    public void testBoardAsArray(){
        String[] array = board.getBoardAsArray();
        for(int i =0;i<array.length;i++){
            assertEquals("_",array[i]);
        }
    }

    @Test
    public void testBoardAs2DArray(){
        String[][] array = board.getBoardAs2D();
        for(int i =0;i<array.length;i++){
            for(int j=0;j<array.length;j++){
                assertEquals("_",array[i][j]);
            }
        }
    }

    @Test
    public void testWin(){
        board.initBoard();
        board.processMove(0, "X");
        assertEquals("X", board.getRow1().getA());
        board.processMove(1, "X");
        assertEquals("X", board.getRow1().getB());
        board.processMove(2, "X");
        assertEquals("X wins!", board.getStatus());
        assertTrue(board.isWinner("X"));
        board.initBoard();
    }

    @After
    public void clear(){
        board.initBoard();
    }


}
