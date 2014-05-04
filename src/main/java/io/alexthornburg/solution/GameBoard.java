package io.alexthornburg.solution;

import java.util.ArrayList;

/**
 * User: alexthornburg
 * Date: 4/10/14
 * Time: 11:59 AM
 */
public class GameBoard {
    private static final GameBoard board;
    private static final String EMPTY_VALUE = "_";
    public String[] boardArray = new String[9];
    private String status = "in progress";

    static {
        try {
            board = new GameBoard();
        } catch (Exception e) {
            throw new RuntimeException("Error creating board", e);
        }
    }

    private GameBoard(){
        initBoard();
    }

    public static GameBoard getInstance() {
        return board;
    }

    public boolean isWinner(String player){
        if(status.equals(player+ " wins!")) return true;
        return false;
    }


    public GameBoard exampleBoard(){
        String[] abc = {"A1","B1","C1",
                "A2","B2","C2",
                "A3","B3","C3"};
        for(int i=0;i<boardArray.length;i++){
            boardArray[i]=abc[i];
        }

        return this;
    }

    public void initBoard(){
        for(int i=0;i<boardArray.length;i++){
            boardArray[i]="_";
        }
    }

    public boolean processMove(int move,String xOrO){
        System.out.println(move);
        if(!boardArray[move].equals("_")){
            return false;
        }else{
            boardArray[move]=xOrO;
            update(xOrO);
            return true;
        }

    }

    public String[][] getBoardAs2D(){
        String[][] results = new String[3][3];
        for(int i=0;i<boardArray.length;i++){
            if(i<3){
                results[0][i]=boardArray[i];
            }if(i>2&&i<6){
                results[1][i-3]=boardArray[i];
            }if(i>5&&i<9){
                results[2][i-6]=boardArray[i];
            }

        }
        return results;
    }

    public String[] getBoardAsArray(){

        return boardArray;
    }

    public boolean full() {
        int moves = 0;
        for (int i = 0; i < 9; i++) {
            if (!boardArray[i].equals(EMPTY_VALUE)) moves++;
        }
        if (moves == 9) return true;
        return false;
    }

    private boolean checkWin(String xOrO, String a, String b, String c) {
        return (a.equals(xOrO) && b.equals(xOrO) && c.equals(xOrO));
    }

    private boolean checkRow(String xOrO) {
        if (checkWin(xOrO, boardArray[0], boardArray[1], boardArray[2]))
            return true;
        if (checkWin(xOrO, boardArray[3], boardArray[4], boardArray[5]))
            return true;
        if (checkWin(xOrO, boardArray[6], boardArray[7], boardArray[8]))
            return true;
        return false;
    }

    private boolean checkColumn(String xOrO) {
        if (checkWin(xOrO, boardArray[0], boardArray[3], boardArray[6]))
            return true;
        if (checkWin(xOrO, boardArray[1], boardArray[4], boardArray[7]))
            return true;
        if (checkWin(xOrO, boardArray[2], boardArray[5], boardArray[8]))
            return true;
        return false;
    }

    private boolean checkDiagonal(String xOrO) {
        if (checkWin(xOrO, boardArray[0], boardArray[4], boardArray[8]))
            return true;
        if (checkWin(xOrO, boardArray[2], boardArray[4], boardArray[6]))
            return true;
        return false;
    }

    public void update(String xOrO) {
        if (checkRow(xOrO) || checkColumn(xOrO) || checkDiagonal(xOrO)) {
            status = xOrO +" wins!";
        } else if (full()) {
            status = "draw";
        } else {
            status = "in progress";
        }

    }

    public ArrayList<Integer> listAvailableMoves(){
        ArrayList<Integer> results = new ArrayList<Integer>();
        for(int i=0;i<boardArray.length;i++){
            if(boardArray[i].equals("_")){
                results.add(i);
            }
        }
        return results;
    }


    public String getStatus() {
        return status;
    }


}
