package io.alexthornburg.solution;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * User: alexthornburg
 * Date: 4/10/14
 * Time: 12:22 PM
 */
public class Opponent {
    private boolean hardMode;
    private GameBoard board;
    protected String[][] cells;
    private String goodGuy;
    private String badGuy;

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

    public int[] winningCombos = {
            Integer.parseInt("111000000",2), Integer.parseInt("000111000",2),
            Integer.parseInt("000000111",2),
            Integer.parseInt("100100100",2), Integer.parseInt("010010010",2),
            Integer.parseInt("001001001",2),
            Integer.parseInt("100010001",2), Integer.parseInt("001010100",2)
    };

    public Opponent(GameBoard board){
        cells = board.getBoardAs2D();
        this.board = board;
    }


    public int[] prunedMiniMax(String maximizingPlayer, int alpha, int beta,int depth) {
        List<int[]> nextMoves = getOpenSpots();

        int score;
        int bestRow = -1;
        int bestCol = -1;

        if (nextMoves.isEmpty() || depth == 0) {
            score = heuristic();
            return new int[] {score, bestRow, bestCol};
        } else {
            for (int[] move : nextMoves) {
                cells[move[0]][move[1]]=maximizingPlayer;
                if (maximizingPlayer == goodGuy) {
                    score = prunedMiniMax(badGuy, alpha, beta,depth - 1)[0];
                    if (score > alpha) {
                        alpha = score;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                } else {
                    score = prunedMiniMax(goodGuy, alpha, beta,depth - 1)[0];
                    if (score < beta) {
                        beta = score;
                        bestRow = move[0];
                        bestCol = move[1];
                    }
                }
                cells[move[0]][move[1]] = "_";
                if (alpha >= beta) break;
            }
            if(maximizingPlayer == goodGuy){
                return new int[] {alpha,bestRow,bestCol};
            }else{
                return new int[] {beta,bestRow,bestCol};
            }
        }
    }


    public int getBestMove(GameBoard board) {
        this.board = board;
        cells = this.board.getBoardAs2D();
        if(hardMode){
            int[] result = prunedMiniMax(goodGuy, Integer.MIN_VALUE, Integer.MAX_VALUE, 2);
            if(result[1]==0){
                return result[2];
            }else if(result[1] == 1){
                return result[2]+3;
            }else{
                return result[2]+6;
            }
        }else{
            ArrayList<Integer> moves = this.board.listAvailableMoves();
            Random pick = new Random();
            int i = pick.nextInt(moves.size());
            return moves.get(i);
        }
    }

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

    public int heuristic() {
        int score = 0;
        score += check(0, 0, 0, 1, 0, 2);
        score += check(1, 0, 1, 1, 1, 2);
        score += check(2, 0, 2, 1, 2, 2);
        score += check(0, 0, 1, 0, 2, 0);
        score += check(0, 1, 1, 1, 2, 1);
        score += check(0, 2, 1, 2, 2, 2);
        score += check(0, 0, 1, 1, 2, 2);
        score += check(0, 2, 1, 1, 2, 0);
        return score;
    }

    public boolean isHardMode() {
        return hardMode;
    }

    public void setHardMode(boolean hardMode) {
        this.hardMode = hardMode;
    }

    public int check(int row1, int col1, int row2, int col2, int row3, int col3) {
        int score = 0;


        if (cells[row1][col1] == goodGuy) {
            score = 1;
        } else if (cells[row1][col1] == badGuy) {
            score = -1;
        }

        if (cells[row2][col2] == goodGuy) {
            if (score == 1) {
                score = 10;
            } else if (score == -1) {
                return 0;
            } else {
                score = 1;
            }
        } else if (cells[row2][col2] == badGuy) {
            if (score == -1) {
                score = -10;
            } else if (score == 1) {
                return 0;
            } else {
                score = -1;
            }
        }


        if (cells[row3][col3] == goodGuy) {
            if (score > 0) {
                score *= 10;
            } else if (score < 0) {
                return 0;
            } else {
                score = 1;
            }
        } else if (cells[row3][col3] == badGuy) {
            if (score < 0) {
                score *= 10;
            } else if (score > 1) {
                return 0;
            } else {
                score = -1;
            }
        }
        return score;
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

}
