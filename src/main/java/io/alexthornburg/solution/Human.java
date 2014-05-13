package io.alexthornburg.solution;

/**
 * User: alexthornburg
 * Date: 5/12/14
 * Time: 6:46 PM
 */
public class Human extends Player {
    String move;

    public Human(GameBoard board) {
        super(board);
    }

    @Override
    public int makeAMove(GameBoard board) {
        return moveToInteger(move);
    }

    public void setMove(String move){
        this.move = move;
    }

    public static int moveToInteger(String move){
        if(move.equals("A1")){
            return 0;
        }else if(move.equals("B1")){
            return 1;
        }else if(move.equals("C1")){
            return 2;
        }else if(move.equals("A2")){
            return 3;
        }else if(move.equals("B2")){
            return 4;
        }else if(move.equals("C2")){
            return 5;
        }else if(move.equals("A3")){
            return 6;
        }else if(move.equals("B3")){
            return 7;
        }else if(move.equals("C3")){
            return 8;
        }else{
            return 100;
        }

    }
}
