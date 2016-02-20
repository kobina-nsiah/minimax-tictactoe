package tictactoe;


import java.util.ArrayList;
import java.util.Random;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Megamind
 */
public class Minimax {
    
    public int bestMove;  //store the possible move that gives the best score that minimax returns
    
    public Minimax(){
        
    }
    
    //return best move
    public int getBestMove(){

        return bestMove;
    }
    
    //minimax algorithm, returns the best score depending who the active player is
    public int minimax(int depth, TicTacToeBoard b){
        // System.out.println();
        // b.printBoard();
        if (b.isGameOver()){
            return score(depth, b);
        }
        
        depth +=1;
        
        
        ArrayList<Integer> gameScores = new ArrayList(); //store scores for possible moves
        ArrayList<Integer> moves = new ArrayList();  //store respective possible moves that generates
                                                     //a particular score at the same index as the score is stored in
       /* System.out.println(":::::::::::");
        b.printBoard();
        System.out.println("`````````");*/
        int possibleMoves[] = b.getPossibleMoves();  //get possible moves for the board b
        int numFreeGrid = possibleMoves.length;
        
        //make the moves with the right  of the active player
        //and generate new game states (boards)
        //recurse minimax on new states
        for (int i = 0; i < numFreeGrid; i++){
            TicTacToeBoard newBoard = new TicTacToeBoard();
             newBoard.setBoardValues(b.getBoard());
            if(numFreeGrid % 2 == 0){
                newBoard.makeMove(possibleMoves[i], 'O');
            }
            else{
                newBoard.makeMove(possibleMoves[i], 'X');
            }
            
            /* myBoard.printBoard();
             System.out.println(".....");
             b.printBoard();*/
            
            gameScores.add(minimax(depth, newBoard)); //add minimax result of new game state to score list
            moves.add(possibleMoves[i]);  //store move in an index with respect to score
            
                
             /*myBoard.printBoard();
             System.out.println("----------");
             b.printBoard();*/
            
        }
        
        
        //get the  max of scores from score list if X is active player
        if (b.getActivePlayer() == 'X'){
            ArrayList<Integer> multiMax = new ArrayList();   //if there are multiple max values, store them here
            int multiMaxIndex = 1;   //iterator for multiMax ArrayList
            int maxScore = gameScores.get(0);
            int maxScoreIndex = 0;
            multiMax.add(0,0);
            for(int i = 1; i < gameScores.size(); i++){   
                if (gameScores.get(i) > maxScore){
                    maxScore = gameScores.get(i);
                    maxScoreIndex = i;
                    multiMaxIndex = 0;
                    multiMax.add(multiMaxIndex, i);
                    multiMaxIndex++;
                }
                else if (gameScores.get(i) == maxScore){
                    maxScore = gameScores.get(i);
                    multiMax.add(multiMaxIndex, i);
                    multiMaxIndex++;
                }
            }
            
            //make a random choice if there are multiple max valaues to make the game dynamic
            if (multiMaxIndex == 1){
                bestMove = moves.get(maxScoreIndex);  //get the best move from the matching index of max score
            }
            else{
                Random randGenerator = new Random();
                int randomNum = randGenerator.nextInt(multiMaxIndex);
                maxScoreIndex = multiMax.get(randomNum);
                bestMove = moves.get(maxScoreIndex);    //get the best move from the matching index of max score
            }
            return maxScore;
        }
        //get the min of scores from score list if O is active player
        else {
            ArrayList<Integer> multiMin = new ArrayList();   //if there are multiple min values, store them here
            int multiMinIndex = 1;                      //iterator for multiMin ArrayList
            int minScore = gameScores.get(0);
            int minScoreIndex = 0;
            multiMin.add(0,0);
            for(int i = 1; i < gameScores.size(); i++){   
                if (gameScores.get(i) < minScore){
                    minScore = gameScores.get(i);
                    minScoreIndex = i;
                    multiMinIndex = 0;
                    multiMin.add(multiMinIndex, i);
                    multiMinIndex++;
                }
                 else if (gameScores.get(i) == minScore){
                    minScore = gameScores.get(i);
                    multiMin.add(multiMinIndex, i);
                    multiMinIndex++;
                }
            }
            
            //make a random choice if there are multiple min valaues to make the game dynamic
            if (multiMinIndex == 1){
                bestMove = moves.get(minScoreIndex);  //get the best move from the matching index of min score
            }
            else{
                Random randGenerator = new Random();
                int randomNum = randGenerator.nextInt(multiMinIndex);
                minScoreIndex = multiMin.get(randomNum);
                bestMove = moves.get(minScoreIndex);   //get the best move from the matching index of max score
            }
            
            return minScore;  
        }
        
        
        //get a list of new game states for every possible move
        //create a score list
        
        //for each of these states, add the minimax result of that state to the scores list
        
        //if its x's turn return max score from list
        //if its o's turn return min score from list
    }
    
    
    //Scoring Mechanism of a tic-tac-toe board state
    public int score(int depth, TicTacToeBoard b){
        if (b.hasWinner()){
            if (b.getWinner() == 'X')
                return 10 - depth;
            else if (b.getWinner() == 'O')
                return depth - 10;
        }
                
        return 0;
    }
    
    public static void main (String[] args){
      // TicTacToeBoard game= new TicTacToeBoard('O','1','2','X','X','5','O','7','8');
       TicTacToeBoard game= new TicTacToeBoard('X','1','2','3','O','5','6','7','8');
       //TicTacToeBoard game= new TicTacToeBoard();
            //System.out.println("klklk");
        //TicTacToeBoard game= new TicTacToeBoard('X','O','2','O','O','X','X','X','');
        Minimax ai = new Minimax();
        game.printBoard();
        System.out.println("Score: " + ai.minimax(0,game));
        System.out.println("Current Player: " + game.getActivePlayer());
        System.out.println();
        game.printBoard();
        //game.getPossibleMoves();
        System.out.println("Your Best Move is: " + ai.getBestMove());
  }
}
