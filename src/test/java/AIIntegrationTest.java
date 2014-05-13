import com.jakewharton.fliptables.FlipTable;
import io.alexthornburg.solution.EasyOpponent;
import io.alexthornburg.solution.GameBoard;
import io.alexthornburg.solution.Opponent;
import io.alexthornburg.solution.Player;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * User: alexthornburg
 * Date: 4/12/14
 * Time: 1:42 PM
 */
public class AIIntegrationTest {
    GameBoard board;
    Opponent opponent;
    @Before
    public void setup(){
       board = GameBoard.getInstance();
       opponent = new Opponent(board);
       opponent.setGoodGuy("O");
       opponent.setBadGuy("X");
       board.initBoard();

    }

    @Test
    public void BlockTest(){
        board.processMove(0,"X");
        board.processMove(4, "X");
        assertEquals(8, opponent.makeAMove(board));
        board.initBoard();
        board.processMove(2,"X");
        board.processMove(5,"X");
        assertEquals(8,opponent.makeAMove(board));
    }

    @Test
    public void winTest(){
        board.processMove(0, "O");
        board.processMove(4, "O");
        assertEquals(8, opponent.makeAMove(board));
        board.initBoard();
        board.processMove(2, "O");
        board.processMove(5,"O");
        assertEquals(8, opponent.makeAMove(board));
    }

    @Test
    public void neverLoseStressTest(){
        long start = System.currentTimeMillis();
        Player opponent1 = new EasyOpponent(board);
        opponent1.setGoodGuy("X");
        opponent1.setBadGuy("O");
        double winPercentage=0;
        double drawPercentage=0;
        double lossPercentage=0;
        for(int i=0;i<1000;i++){
            while(true){
                board.processMove(opponent1.makeAMove(board),"X");
                board.processMove(opponent.makeAMove(board),"O");
                if(!board.isInprogress()){
                    break;
                }
            }
            if(board.isOWinner()){
                winPercentage++;
            }else if(board.isXWinner()){
                printBoard(board);
                lossPercentage++;
            }else{
                drawPercentage++;
            }

            board.initBoard();
        }
        System.out.println("==========Random Guy Percentages==========");
        System.out.println("Win percent: "+winPercentage/1000*100);
        System.out.println("Draw percent: "+drawPercentage/1000*100);
        System.out.println("Loss percent: "+lossPercentage/1000*100);
        assertEquals(0.0,lossPercentage/1000*100);
        long end = System.currentTimeMillis();
        long time = end-start;
        System.out.println("10,000 rando's played in " + time + " milliseconds");
    }

    @Test
    public void showDownStressTest(){
        long start = System.currentTimeMillis();
        Player opponent1 = new Opponent(board);
        opponent1.setGoodGuy("X");
        opponent1.setBadGuy("O");
        double winPercentage=0;
        double drawPercentage=0;
        double lossPercentage=0;
        for(int i=0;i<10000;i++){
            while(true){
                board.processMove(opponent1.makeAMove(board),"X");
                board.processMove(opponent.makeAMove(board),"O");

                if(!board.isInprogress()){
                    break;
                }
            }
            if(board.isOWinner()){
                winPercentage++;
            }else if(board.isXWinner()){
                printBoard(board);
                lossPercentage++;
            }else{
                drawPercentage++;
            }
            assertFalse(board.isXWinner());
            board.initBoard();
        }
        System.out.println("==========Showdown Percentages==========");
        System.out.println("Win percent: "+winPercentage/10000*100);
        System.out.println("Draw percent: " + drawPercentage / 10000 * 100);
        System.out.println("Loss percent: "+lossPercentage/10000*100);
        assertEquals(0.0,lossPercentage/10000*100);
        assertEquals(100.0, drawPercentage / 10000 * 100);
        long end = System.currentTimeMillis();
        long time = end-start;
        System.out.println("10,000 AI players played in "+time+" milliseconds");

    }

    public static void printBoard(GameBoard board){
        String[] headers = { "A", "B","C" };
        String [][]body = board.getBoardAs2D();
        System.out.println(FlipTable.of(headers, body));
    }

    @After
    public void clear(){
        board.initBoard();
    }

}
