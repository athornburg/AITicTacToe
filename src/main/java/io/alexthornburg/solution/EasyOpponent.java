package io.alexthornburg.solution;

import java.util.ArrayList;
import java.util.Random;

/**
 * User: alexthornburg
 * Date: 5/12/14
 * Time: 7:56 PM
 */
public class EasyOpponent extends Player {

    public EasyOpponent(GameBoard board) {
        super(board);
    }

    @Override
    public int makeAMove(GameBoard board) {
        ArrayList<Integer> moves = this.board.listAvailableMoves();
        Random pick = new Random();
        int i = pick.nextInt(moves.size());
        return moves.get(i);
    }

}
