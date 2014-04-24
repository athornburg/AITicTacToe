package io.alexthornburg.solution;

import com.jakewharton.fliptables.FlipTableConverters;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * User: alexthornburg
 * Date: 4/10/14
 * Time: 11:59 AM
 */
public class GameBoard {
    private static final GameBoard board;
    private static final String EMPTY_VALUE = "_";
    private Row row1 = new Row();
    private Row row2 = new Row();
    private Row row3 = new Row();
    private static final String X ="X";
    private static final String Y = "Y";
    private String status = "in progress";

    static {
        try {
            board = new GameBoard();
        } catch (Exception e) {
            throw new RuntimeException("Error creatin board", e);
        }
    }

    private GameBoard(){}

    public static GameBoard getInstance() {
        return board;
    }

    public boolean isWinner(String player){
        if(status.equals(player+ " wins!")) return true;
        return false;
    }

    @Override
    public String toString(){
        List<Row> board = Arrays.asList(row1, row2, row3);
        return FlipTableConverters.fromIterable(board, Row.class);
    }

    public GameBoard exampleBoard(){
        row1.setA("A1");
        row1.setB("B1");
        row1.setC("C1");
        row2.setA("A2");
        row2.setB("B2");
        row2.setC("C2");
        row3.setA("A3");
        row3.setB("B3");
        row3.setC("C3");
        return this;
    }

    public void initBoard(){
        initRow(row1);
        initRow(row2);
        initRow(row3);
    }

    public static void initRow(Row row){
        row.setA(EMPTY_VALUE);
        row.setB(EMPTY_VALUE);
        row.setC(EMPTY_VALUE);
    }

    public boolean processMove(int move,String xOrO){
        if(move==0&&row1.getA().equals("_")){
            row1.setA(xOrO);
            update(xOrO);
            return true;
        }else if(move==1&&row1.getB().equals("_")){
            row1.setB(xOrO);
            update(xOrO);
            return true;
        }else if(move==2&&row1.getC().equals("_")){
            row1.setC(xOrO);
            update(xOrO);
            return true;
        }else if(move==3&&row2.getA().equals("_")){
            row2.setA(xOrO);
            update(xOrO);
            return true;
        }else if(move==4&&row2.getB().equals("_")){
            row2.setB(xOrO);
            update(xOrO);
            return true;
        }else if(move==5&&row2.getC().equals("_")){
            row2.setC(xOrO);
            update(xOrO);
            return true;
        }else if(move==6&&row3.getA().equals("_")){
            row3.setA(xOrO);
            update(xOrO);
            return true;
        }else if(move==7&&row3.getB().equals("_")){
            row3.setB(xOrO);
            update(xOrO);
            return true;
        }else if(move==8&&row3.getC().equals("_")){
            row3.setC(xOrO);
            update(xOrO);
            return true;
        }else{
            update(xOrO);
            return false;
        }

    }

    public String[][] getBoardAs2D(){
        String[][] results = new String[3][3];
        results[0][0] = row1.getA();
        results[0][1] = row1.getB();
        results[0][2] = row1.getC();
        results[1][0] = row2.getA();
        results[1][1] = row2.getB();
        results[1][2] = row2.getC();
        results[2][0] = row3.getA();
        results[2][1] = row3.getB();
        results[2][2] = row3.getC();
        return results;
    }

    public String[] getBoardAsArray(){
        String[] result = new String[9];
        String[] row1Array = row1.rowsAsArray();
        String[] row2Array = row2.rowsAsArray();
        String[] row3Array = row3.rowsAsArray();

        for(int i =0;i<9;i++){
            if(i<3){
                result[i]=row1Array[i];
            }if(i>2&&i<6){
                result[i]=row2Array[i-3];
            }if(i>5&&i<9){
                result[i]=row3Array[i-6];
            }
        }

        return result;
    }

    public boolean full() {
        String[]board = getBoardAsArray();
        int moves = 0;
        for (int i = 0; i < 9; i++) {
            if (!board[i].equals(EMPTY_VALUE)) moves++;
        }
        if (moves == 9) return true;
        return false;
    }

    private boolean checkWin(String xOrO, String a, String b, String c) {
        return (a.equals(xOrO) &&
                b.equals(xOrO) &&
                c.equals(xOrO));
    }

    private boolean checkRow(String xOrO) {
        if (checkWin(xOrO, row1.getA(), row1.getB(), row1.getC()))
            return true;
        if (checkWin(xOrO, row2.getA(), row2.getB(), row2.getC()))
            return true;
        if (checkWin(xOrO, row3.getA(), row3.getB(), row3.getC()))
            return true;
        return false;
    }

    private boolean checkColumn(String xOrO) {
        if (checkWin(xOrO, row1.getA(), row2.getA(), row3.getA()))
            return true;
        if (checkWin(xOrO, row1.getB(), row2.getB(), row3.getB()))
            return true;
        if (checkWin(xOrO, row1.getC(), row2.getC(), row3.getC()))
            return true;
        return false;
    }

    private boolean checkDiagonal(String xOrO) {
        if (checkWin(xOrO, row1.getA(), row2.getB(), row3.getC()))
            return true;
        if (checkWin(xOrO, row1.getC(), row2.getB(), row3.getA()))
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
        if(row1.getA().equals(EMPTY_VALUE)){
            results.add(0);
        }if(row1.getB().equals(EMPTY_VALUE)){
            results.add(1);
        }if(row1.getC().equals(EMPTY_VALUE)){
            results.add(2);
        }if(row2.getA().equals(EMPTY_VALUE)){
            results.add(3);
        }if(row2.getB().equals(EMPTY_VALUE)){
            results.add(4);
        }if(row2.getC().equals(EMPTY_VALUE)){
            results.add(5);
        }if(row3.getA().equals(EMPTY_VALUE)){
            results.add(6);
        }if(row3.getB().equals(EMPTY_VALUE)){
            results.add(7);
        }if(row3.getC().equals(EMPTY_VALUE)){
            results.add(8);
        }
        return results;
    }


    public String getStatus() {
        return status;
    }

    public Row getRow1() {
        return row1;
    }

    public void setRow1(Row row1) {
        this.row1 = row1;
    }

    public Row getRow2() {
        return row2;
    }

    public void setRow2(Row row2) {
        this.row2 = row2;
    }

    public Row getRow3() {
        return row3;
    }

    public void setRow3(Row row3) {
        this.row3 = row3;
    }


}
