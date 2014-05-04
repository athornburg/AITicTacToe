package io.alexthornburg.solution;

import com.jakewharton.fliptables.FlipTable;

import java.util.Scanner;
/**
 * User: alexthornburg
 * Date: 4/10/14
 * Time: 11:37 AM
 */
public class TicTacToe {

    public static void main(String args[]){
        GameBoard example = GameBoard.getInstance();
        System.out.println("Positions: \n");
        example.exampleBoard();
        printBoard(example);

        GameBoard board = GameBoard.getInstance();
        board.initBoard();

        Opponent opponent = new Opponent();
        printBoard(board);



        Scanner sc = new Scanner(System.in);
        System.out.println("Would you like to play in hard mode?(Y/N)");
        String hard = sc.nextLine();
        while(true){
            if(hard.equals("N")||hard.equals("Y")){
                break;
            }else{
               System.out.println("That isn't a real mode! ");
               hard = sc.nextLine();
            }

        }
        if(hard.equals("Y")){
            opponent.setHardMode(true);
        }else if(hard.equals("N")){
            opponent.setHardMode(false);
        }

        System.out.println("After the coinflip...");

        double coinFlip = Math.random();
        boolean compWentFirst = false;
        if(coinFlip<.5){
            System.out.println("You're X");
            opponent.setBadGuy("X");
            opponent.setGoodGuy("O");
            System.out.println("It's your move!");
           userMakePlay(board,sc,opponent);
        }else{
            System.out.println("You're O");
            opponent.setBadGuy("O");
            opponent.setGoodGuy("X");
            System.out.println("Its my move!");
            computerMakePlay(board,opponent);
            compWentFirst = true;

        }
        while(true){

        if(compWentFirst){
            userMakePlay(board,sc,opponent);
            if(!board.getStatus().equals("in progress")) break;
            computerMakePlay(board,opponent);

        }else{
            computerMakePlay(board,opponent);
            if(!board.getStatus().equals("in progress")) break;
            userMakePlay(board,sc,opponent);
        }
            if(!board.getStatus().equals("in progress")) break;
        }
        System.out.println(board.getStatus());

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

    public static void userMakePlay(GameBoard board, Scanner sc,Opponent opponent){
        System.out.println("Enter a position:");
        String position = sc.nextLine();
        boolean validMove;
        while(true){
            validMove = board.processMove(moveToInteger(position),opponent.getBadGuy());
            if(validMove)break;
            System.out.println("Try again: ");
            String position1 = sc.nextLine();
            validMove = board.processMove(moveToInteger(position1),opponent.getBadGuy());
            if(validMove)break;

        }
        printBoard(board);

    }

    public static void computerMakePlay(GameBoard board, Opponent opponent){
        int move = opponent.getBestMove(board);
        board.processMove(move,opponent.getGoodGuy());
        System.out.println(board.boardArray[move]);
        printBoard(board);

    }

    public static void printBoard(GameBoard board){
        String[] headers = { "A", "B","C" };
        String [][]body = board.getBoardAs2D();
        System.out.println(FlipTable.of(headers, body));
    }

}

