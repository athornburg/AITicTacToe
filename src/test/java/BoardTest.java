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
        assertEquals("_", board.getBoardAs2D()[0][0]);
        assertEquals("_",board.getBoardAs2D()[0][1]);
        assertEquals("_",board.getBoardAs2D()[0][2]);
        assertEquals("_",board.getBoardAs2D()[1][0]);
        assertEquals("_",board.getBoardAs2D()[1][1]);
        assertEquals("_",board.getBoardAs2D()[1][2]);
        assertEquals("_",board.getBoardAs2D()[2][0]);
        assertEquals("_",board.getBoardAs2D()[2][1]);
        assertEquals("_",board.getBoardAs2D()[2][2]);
    }

    @Test
    public void testStatus(){
        board.initBoard();
        board.processMove(0, "X");
        assertEquals("in progress",board.getStatus());
        board.initBoard();
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
        assertEquals("X", board.getBoardAsArray()[0]);
        board.processMove(1, "X");
        assertEquals("X", board.getBoardAsArray()[1]);
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
