package io.alexthornburg.solution;

import java.util.List;

/**
 * User: alexthornburg
 * Date: 4/10/14
 * Time: 12:22 PM
 */
public class Opponent extends Player{

    public Opponent(GameBoard board) {
        super(board);
    }

    @Override
    public int makeAMove(GameBoard board) {
        this.board = board;
        cells = this.board.getBoardAs2D();
        int[] result = prunedMiniMax(goodGuy, Integer.MIN_VALUE, Integer.MAX_VALUE, 2);
        if(result[1]==0){
            return result[2];
        }else if(result[1] == 1){
            return result[2]+3;
        }else{
            return result[2]+6;
        }

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

    public int checkFirstCell(int row1,int col1,int score){
        if (cells[row1][col1] == goodGuy) {
            score = 1;
        } else if (cells[row1][col1] == badGuy) {
            score = -1;
        }
        return score;

    }

    public int checkSecondCell(int row2,int col2,int score){
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
        return score;
    }

    public int checkThirdCell(int row3,int col3,int score){
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

    public int check(int row1, int col1, int row2, int col2, int row3, int col3) {
        int score = 0;

        score = checkFirstCell(row1,col1,score);

        score = checkSecondCell(row2,col2,score);

        score = checkThirdCell(row3,col3,score);

        return score;
    }

}
