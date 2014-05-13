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

        Player opponent;
        printBoard(board);

        Player human = new Human(board);


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
        if(hard.toUpperCase().equals("Y")){
            opponent = new Opponent(board);
        }else{
            opponent = new EasyOpponent(board);
        }

        System.out.println("After the coinflip...");

        double coinFlip = Math.random();
        boolean compWentFirst = false;
        if(coinFlip<.5){
            System.out.println("You're X");
            opponent.setBadGuy("X");
            opponent.setGoodGuy("O");
            System.out.println("It's your move!");
           userMakePlay(human,board,sc,opponent);
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
            userMakePlay(human,board,sc,opponent);
            if(!board.isInprogress()) break;
            computerMakePlay(board,opponent);

        }else{
            computerMakePlay(board,opponent);
            if(!board.isInprogress()) break;
            userMakePlay(human,board,sc,opponent);
        }
            if(!board.isInprogress()) break;
        }
        if(board.isOWinner()){
            System.out.println("O wins");
        }else if(board.isXWinner()){
            System.out.println("X wins");
        }else if(board.isInprogress()){
            System.out.println("In progress");
        }else{
            System.out.println("Draw");
        }

    }

    public static void userMakePlay(Player human, GameBoard board, Scanner sc,Player opponent){
        System.out.println("Enter a position:");
        String position = sc.nextLine();
        boolean validMove;
        while(true){
            human.setMove(position);
            validMove = board.processMove(human.makeAMove(board),opponent.getBadGuy());
            if(validMove)break;
            System.out.println("Try again: ");
            String position1 = sc.nextLine();
            human.setMove(position1);
            validMove = board.processMove(human.makeAMove(board),opponent.getBadGuy());
            if(validMove)break;

        }
        printBoard(board);

    }

    public static void computerMakePlay(GameBoard board, Player opponent){
        int move = opponent.makeAMove(board);
        board.processMove(move,opponent.getGoodGuy());
        printBoard(board);

    }

    public static void printBoard(GameBoard board){
        String[] headers = { "A", "B","C" };
        String [][]body = board.getBoardAs2D();
        System.out.println(FlipTable.of(headers, body));
    }

}

