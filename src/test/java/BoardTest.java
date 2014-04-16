import io.alexthornburg.solution.Board;
import org.junit.Before;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertTrue;

/**
 * User: alexthornburg
 * Date: 4/15/14
 * Time: 7:51 PM
 */
public class BoardTest {
    Board board;

    @Before
    public void getBoard(){
       board = Board.getInstance();
    }

    public void testInit(){
        board.initBoard();
        assertEquals("_",board.getRow1().getA());
        assertEquals("_",board.getRow1().getB());
        assertEquals("_",board.getRow1().getC());
        assertEquals("_",board.getRow2().getA());
        assertEquals("_",board.getRow2().getB());
        assertEquals("_",board.getRow2().getC());
        assertEquals("_",board.getRow3().getA());
        assertEquals("_",board.getRow3().getB());
        assertEquals("_",board.getRow3().getC());
    }

    public void testMove(){
        fillBoardWithX();
        board.initBoard();
    }

    public void testBoardAsArray(){
        String[] array = board.getBoardAsArray();
        for(int i =0;i<array.length;i++){
            assertEquals("_",array[i]);
        }
    }

    public void testBoardAs2DArray(){
        String[][] array = board.getBoardAs2D();
        for(int i =0;i<array.length;i++){
            for(int j=0;j<array.length;j++){
                assertEquals("_",array[i][j]);
            }
        }
    }

    public void testWin(){
        board.processMove(0,"X");
        assertEquals("X",board.getRow1().getA());
        board.processMove(1,"X");
        assertEquals("X",board.getRow1().getB());
        board.processMove(2,"X");
        assertEquals("X wins!",board.getStatus());
        board.initBoard();
    }

    public void testFull(){
        fillBoardWithX();
        assertTrue(board.full());

    }

    public void fillBoardWithX(){
        board.processMove(0,"X");
        assertEquals("X",board.getRow1().getA());
        board.processMove(1,"X");
        assertEquals("X",board.getRow1().getB());
        board.processMove(2,"X");
        assertEquals("X",board.getRow1().getC());
        board.processMove(3,"X");
        assertEquals("X",board.getRow2().getA());
        board.processMove(4,"X");
        assertEquals("X",board.getRow2().getB());
        board.processMove(5,"X");
        assertEquals("X",board.getRow2().getC());
        board.processMove(6,"X");
        assertEquals("X",board.getRow3().getA());
        board.processMove(7,"X");
        assertEquals("X",board.getRow3().getB());
        board.processMove(8,"X");
        assertEquals("X",board.getRow3().getC());
    }


}
