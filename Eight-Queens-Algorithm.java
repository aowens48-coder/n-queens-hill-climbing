import java.util.*;

public class Eight_Queens_Algorithm{

    private static int stateChanges = 0;
    private static int restarts = 0;


    public static int calculate_Hvalue(int[][] board){
        int h_value= 0; //sets hueristic to 0
    
        for(int row1 = 0; row1 < 8; row1++){ //iterate through every row in the board to find each queen
            for(int col1 = 0; col1 < 8; col1++){ //Iterate through each column to find queen starting with lwftmost.
                if(board[row1][col1] == 1){ // If the index equals 1 than a queen has been found.
                
                    for(int col2 = col1 + 1; col2 < 8; col2++){ //Check each column to the right of the current queen in the loop 
                        for(int row2 = 0; row2 < 8; row2++){
                            if(board[row2][col2] == 1){ //If the index has a value of 1 than the other queen has been found
                        
                                if(row1 == row2){ //Checks whether the found queens occupy the same row number.
                                    h_value++; //If true than incriments the hueristic value.
                                }
                            
                                else if(Math.abs(row1 - row2) == Math.abs(col1 - col2)){ //checks diagonals by seeing if row and column distance are equal. 
                                    h_value++; //If true increments heuristic value by 1.
                                }
                            }
                        
                        }  
                    }
                 }
            }
        }
    
    return h_value;
    }

    public static void randomInitialState(int[][] board){
        
        Random rand = new Random();
        
        for(int i = 0; i < 8; i++){ //fills entire chesz board with values of 0.
            Arrays.fill(board[i], 0);
        }
        
        //insert a queen in a random row for each column
        for(int col = 0; col < 8; col++){
            int randomRow = rand.nextInt(8);
            board[randomRow][col] = 1;    
        }

    }
    
    public static void printArray(int[][] board){//prints the 2d array by iterating through each column and row using a nested loop.
        System.out.println("Current State");
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                System.out.print(board[i][j]);
                if(j < 7){
                    System.out.print(",");//separates each printed value by a comma for the output.
                }
            }
            System.out.println();//prints a new line for every new row to be printed
        }
    }

    public static void solveQueenBoard(int[][] board){
    
        randomInitialState(board); //Creates an inital state using the created method
        int currentH = calculate_Hvalue(board); //Calculates the heuristic value using the created method
        
        while(true){ //The loop that will run the hill-climbing-algorithm.
            System.out.println("Current h: " +currentH);
            printArray(board);
            
            if(currentH == 0){ //If heuristic value is 0 than solution is found and no queens should conflict with each other.
                System.out.println("Solution Found!");
                System.out.println("State changes: " + stateChanges);
                System.out.println("Restarts: " + restarts);
                break;// breaks the loop once heuristic value is 0.
            }
            
            int bestNeighborH = currentH; // Sets best h value to the calculated h value for the current board.  
            int bestCol = -1, bestRow = -1; //Initializes best row and column values
            int lowerHNeighborCount = 0;
            
            for(int col = 0; col < 8; col++){ //Loop that iterates through each column of the board to find the queen
            
                int currentQueenRow = -1;
                for(int row = 0; row < 8; row++){
                    if(board[row][col] == 1){
                    
                    currentQueenRow = row;
                    break; //breaks the loop once the queen in the column is found 
                }
            }
        
            for(int targetRow = 0; targetRow < 8; targetRow++){
            
                if(targetRow == currentQueenRow)
                    continue; //skips the row the queen is currently on since we have the heuristic value for that state 
                    
                board[currentQueenRow][col] = 0; 
                board[targetRow][col] = 1; //switches values of current row and target to "move" the queen up and down and get neighbor states.
                
                int neighborH = calculate_Hvalue(board); //Calcs the H value for the neighbor state
                
                if(neighborH < currentH){
                    lowerHNeighborCount++;//track lower heuristic states
                }
                
                if(neighborH < bestNeighborH){
                    bestNeighborH = neighborH;
                    bestCol = col;
                    bestRow = targetRow; 
                }
                
                board[targetRow][col] = 0;
                board[currentQueenRow][col] = 1; 
            }
        }
    
        
        System.out.println("Neighbors found with lower h: " +lowerHNeighborCount);
        
        if(bestNeighborH < currentH){
            System.out.println("Setting new current state\n");
            
            for(int i = 0; i < 8; i++)
                board[i][bestCol] = 0; //sets entire row to 0 to move queen to new row
                
            board[bestRow][bestCol] = 1; //Creates the next neighbor state by moving queen to the new best row.
            currentH = bestNeighborH;//updates heuristic value.
            stateChanges++; 
            }else{
            System.out.println("RESTART\n");//creates new initial state if loop does not find a lower heurisitic value.
            randomInitialState(board);
            currentH = calculate_Hvalue(board);
            restarts++;//tracks the amount of restarts.
            }
        }
     
    }




   public static void main (String[] args){
   
       int[][] chessBoard = new int[8][8]; //creates 2d array for chess board. 
       solveQueenBoard(chessBoard); //runs the algorithm.
   } 
}