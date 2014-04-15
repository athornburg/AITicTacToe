import io.alexthornburg.solution.Board;
import io.alexthornburg.solution.Opponent;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotSame;

/**
 * User: alexthornburg
 * Date: 4/12/14
 * Time: 1:42 PM
 */
public class AITest {
    Board board;
    Opponent opponent;
    @Before
    public void setup(){
       board = Board.getInstance();
       opponent = new Opponent("O","X");
       board.initBoard();

    }

    @Test
    public void BlockTest(){
        opponent.setHardMode(true);
        board.processMove(0,"X");
        board.processMove(4, "X");
        assertEquals(8, opponent.getBestMove(board));
        board.initBoard();
        board.processMove(2,"X");
        board.processMove(5,"X");
        assertEquals(8,opponent.getBestMove(board));
    }

    @Test
    public void winTest(){
        opponent.setHardMode(true);
        board.processMove(0, "O");
        board.processMove(4, "O");
        assertEquals(8, opponent.getBestMove(board));
        board.initBoard();
        board.processMove(2, "O");
        board.processMove(5,"O");
        assertEquals(8, opponent.getBestMove(board));
    }

    @Test
    public void neverLoseStressTest(){
        long start = System.currentTimeMillis();
        opponent.setHardMode(true);
        Opponent opponent1 = new Opponent("X","O");
        opponent1.setHardMode(false);
        double winPercentage=0;
        double drawPercentage=0;
        double lossPercentage=0;
        for(int i=0;i<10000;i++){
            while(true){
                board.processMove(opponent1.getBestMove(board),"X");
                board.processMove(opponent.getBestMove(board),"O");

                if(!board.getStatus().equals("in progress")){
                    break;
                }
            }
            if(board.getStatus().equals("O wins!")){
                winPercentage++;
            }
            if(board.getStatus().equals("draw")){
                drawPercentage++;
            }
            if(board.getStatus().equals("X wins!")){
                lossPercentage++;
            }
            assertNotSame("X wins!",board.getStatus());
            board.initBoard();
        }
        System.out.println("==========Random Guy Percentages==========");
        System.out.println("Win percent: "+winPercentage/10000*100);
        System.out.println("Draw percent: "+drawPercentage/10000*100);
        System.out.println("Loss percent: "+lossPercentage/10000*100);
        assertEquals(0.0,lossPercentage/10000*100);
        long end = System.currentTimeMillis();
        long time = end-start;
        System.out.println("10,000 rando's played in "+time+" milliseconds");
    }

    @Test
    public void showDownStressTest(){
        long start = System.currentTimeMillis();
        opponent.setHardMode(true);
        Opponent opponent1 = new Opponent("X","O");
        opponent1.setHardMode(true);
        double winPercentage=0;
        double drawPercentage=0;
        double lossPercentage=0;
        for(int i=0;i<10000;i++){
            while(true){
                board.processMove(opponent1.getBestMove(board),"X");
                board.processMove(opponent.getBestMove(board),"O");

                if(!board.getStatus().equals("in progress")){
                    break;
                }
            }
            if(board.getStatus().equals("O wins!")){
                winPercentage++;
            }
            if(board.getStatus().equals("draw")){
                drawPercentage++;
            }
            if(board.getStatus().equals("X wins!")){
                lossPercentage++;
            }
            assertNotSame("X wins!",board.getStatus());
            board.initBoard();
        }
        System.out.println("==========Showdown Percentages==========");
        System.out.println("Win percent: "+winPercentage/10000*100);
        System.out.println("Draw percent: "+drawPercentage/10000*100);
        System.out.println("Loss percent: "+lossPercentage/10000*100);
        assertEquals(0.0,lossPercentage/10000*100);
        assertEquals(100.0,drawPercentage/10000*100);
        long end = System.currentTimeMillis();
        long time = end-start;
        System.out.println("10,000 AI players played in "+time+" milliseconds");

    }

}
