package io.alexthornburg.solution;

/**
 * User: alexthornburg
 * Date: 5/12/14
 * Time: 6:32 PM
 */
import java.util.ArrayList;
import java.util.List;
public abstract class Player {
    String move;
    protected GameBoard board;
    protected String[][] cells;
    protected String goodGuy;
    protected String badGuy;
    /**
     * 111000000 000111000 & 000000111 are row wins
     * 100100100 010010010 & 001001001 are column wins
     * 100010001 & 001010100 are diagonal wins
     */
    public int[] winningCombos = {
            Integer.parseInt("111000000",2),
            Integer.parseInt("000111000",2),
            Integer.parseInt("000000111",2),
            Integer.parseInt("100100100",2),
            Integer.parseInt("010010010",2),
            Integer.parseInt("001001001",2),
            Integer.parseInt("100010001",2),
            Integer.parseInt("001010100",2)
    };

    public Player(GameBoard board){
        cells = board.getBoardAs2D();
        this.board = board;
    }

    public abstract int makeAMove(GameBoard board);

    public List<int[]> getOpenSpots() {
        List<int[]> nextMoves = new ArrayList<int[]>();
        if (isAWinner(goodGuy) || isAWinner(badGuy)) {
            return nextMoves;
        }

        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                if (cells[row][col] == "_") {
                    nextMoves.add(new int[] {row, col});
                }
            }
        }
        return nextMoves;
    }

    public boolean isAWinner(String player) {
        int pattern = Integer.parseInt("000000000",2);
        for (int row = 0; row < 3; ++row) {
            for (int col = 0; col < 3; ++col) {
                if (cells[row][col] == player) {
                    pattern |= (1 << (row * 3 + col));
                }
            }
        }
        for (int winningPattern : winningCombos) {
            if ((pattern & winningPattern) == winningPattern) return true;
        }
        return false;
    }

    public void setGoodGuy(String goodGuy){
        this.goodGuy = goodGuy;
    }

    public String getGoodGuy(){
        return goodGuy;
    }

    public void setBadGuy(String badGuy){
        this.badGuy = badGuy;
    }

    public String getBadGuy(){
        return badGuy;
    }

    public GameBoard getBoard(){
        return board;
    }

    public String[][] getCells(){
        return cells;
    }

    public void setCells(String[][] cells){
        this.cells = cells;
    }

    public void setMove(String move){
        this.move = move;
    }


}
