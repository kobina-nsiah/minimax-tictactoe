package tictactoe;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Megamind
 */
public class TicTacToeBoard {
  
    //named contants
  public static final int BOARD_SIZE = 9;
  public static final char TOP_LEFT = '0';
  public static final char TOP_MIDDLE = '1';
  public static final char TOP_RIGHT = '2';
  public static final char MID_LEFT = '3';
  public static final char MID_MIDDLE = '4';
  public static final char MID_RIGHT = '5';
  public static final char BOTTOM_LEFT = '6';
  public static final char BOTTOM_MIDDLE = '7';
  public static final char BOTTOM_RIGHT = '8';
  
  public static final int TL = 0;
  public static final int TM = 1;
  public static final int TR = 2;
  public static final int ML = 3;
  public static final int MM = 4;
  public static final int MR= 5;
  public static final int BL = 6;
  public static final int BM = 7;
  public static final int BR = 8;
  
  
  private char[] board; 
  
  //default constructor
  public TicTacToeBoard(){
    board = new char[BOARD_SIZE];
    
    for( int i = 0; i < BOARD_SIZE; i++){
        board[i] = Integer.toString(i).charAt(0);
    }
  }
  
  //other constructor
  public TicTacToeBoard(char a, char b, char c, char d, char e, char f, char g, char h, char i){
    board = new char[BOARD_SIZE];
    
        board[TL] = a;
        board[TM] = b;
        board[TR] = c;
        board[ML] = d;
        board[MM] = e;
        board[MR] = f;
        board[BL] = g;
        board[BM] = h;
        board[BR] = i; 
  }
  
  public void setBoard(char[] b){
      this.board = b;
  }
  
  //return a board
  public char[] getBoard(){
      return board;
  }
  
  //change values of a board to a the specified char array
  public void setBoardValues(char[] b){
      for (int i = 0; i < b.length; i++){
          board[i] = b[i];
      }
  }
  
  public void printBoard(){
    for( int i = 0; i < BOARD_SIZE; i++){
        if (board[i] == Integer.toString(i).charAt(0)){
            System.out.print("-");
        }
        else {
            System.out.print(board[i]);
        }
        
        if ((i+1) % 3 == 0){
            System.out.println();
        }    
    }  
  }
  
  //check if array[i] of board is free
  public boolean isGridFree(int i){ 
    return board[i] == Integer.toString(i).charAt(0);
  }
  
  //make a move at i for play with symbol c
  public boolean makeMove(int i, char c){
    if (isGridFree(i)){
      board[i] = c;
      return true;
    }
    else
      return false;
  }
  
  //get all free grids
  public int[] getPossibleMoves(){
      int[] moves = new int[BOARD_SIZE];
      
     /*for(int i = 0; i < BOARD_SIZE; i++){
          moves[i] = BOARD_SIZE;
      }*/
      
      int a = 0;
      
      for(int i = 0; i < BOARD_SIZE; i++){
          if (isGridFree(i)){
              moves[a] = i;
              //System.out.println(i);
              a++;
          }
      }
      
      //System.out.println(a);
      int myMoves[] = new int[a];
      
      for(int i = 0; i < a; i++){
          myMoves[i] = moves[i];
      }
      
      return myMoves;
  }
  
  // is board full?
  public boolean isFull(){
     return board[TL] != TOP_LEFT && board[TM] != TOP_MIDDLE && board[TR] != TOP_RIGHT &&
      board[ML] != MID_LEFT && board[MM] != MID_MIDDLE && board[MR] != MID_RIGHT &&
      board[BL] != BOTTOM_LEFT && board[BM] != BOTTOM_MIDDLE && board[BR] != BOTTOM_RIGHT;     
  }
  
  //is there a winner?
  public boolean hasWinner(){
      boolean aRowWin = 
      ((board[TL] == board[TM]) && (board[TL] == board[TR])) ||
      ((board[ML] == board[MM]) && (board[ML] == board[MR])) || 
      ((board[BL] == board[BM]) && (board[BL] == board[BR]));
    
    boolean aColumnWin =
      ((board[TL] == board[ML]) && (board[TL] == board[BL])) ||
      ((board[TM] == board[MM]) && (board[TM] == board[BM])) ||
      ((board[TR] == board[MR]) && (board[TR] == board[BR]));
    

   boolean aDiagonalWin =
      ((board[TL] == board[MM]) && (board[TL] == board[BR])) ||
      ((board[TR] == board[MM]) && (board[TR] == board[BL]));
    
   return aRowWin || aColumnWin || aDiagonalWin;

      
  }
  
  //is Game over?
  public boolean isGameOver(){
        return isFull() || hasWinner();
  }
  
  //return symbol of wiinner
  public char getWinner(){
    
    char winner = ' ';
    
    if (hasWinner()){
      
      if ((board[TL] == board[TM]) && (board[TL] == board[TR]))
        winner = board[TL];
      else if ((board[ML] == board[MM]) && (board[ML] == board[MR]))
        winner = board[ML];
      else if ((board[BL] == board[BM]) && (board[BL] == board[BR]))
        winner = board[BL];
      else if ((board[TL] == board[ML]) && (board[TL] == board[BL]))
        winner = board[TL];
      else if ((board[TM] == board[MM]) && (board[TM] == board[BM]))
        winner = board[TM];
      else if ((board[TR] == board[MR]) && (board[TR] == board[BR]))
        winner = board[TR];
      else if ((board[TL] == board[MM]) && (board[TL] == board[BR]))
        winner = board[TL];
      else if ((board[TR] == board[MM]) && (board[TR] == board[BL]))
        winner = board[TR];
    }
    
    return winner;
  }
  
  
  //who's turn is it to play?
  public char getActivePlayer(){
    char player = 'X';
    
    
    int a = 0;
      
    for(int i = 0; i < BOARD_SIZE; i++){
          if (isGridFree(i)){
              a++;
          }
    }
    
    if (a % 2 == 0)
        player = 'O';
     
    return player;
  }
  
  public static void main (String[] args){
    TicTacToeBoard game= new TicTacToeBoard('X','O','O','X','4','5','6','7','8');
    TicTacToeBoard newGame = new TicTacToeBoard();

    newGame.setBoardValues(game.getBoard());
    game.printBoard();
    System.out.println();
    newGame.makeMove(8,newGame.getActivePlayer() );
        game.makeMove(5,newGame.getActivePlayer() );

    //System.out.println("klklk");
   // game.printBoard();
    //newGame.getBoardAfterMove(8,game.getActivePlayer());
    //game.getBoardAfterMove(8,game.getActivePlayer()).printBoard();
    game.printBoard();
    System.out.println();
    newGame.printBoard();
   //System.out.println(game.getActivePlayer());
  }  
}

