package Game;

import Player.ComputerPlayer;

/**
 * This class handles the board case analysis.
 * @author Manuel Hernandez
 */
public class BoardScorer {
	private static int verticalValue = 1;
	private static int diagonalValue = 2;
	private static int horizontalValue = 3;
	private static int twoInARowValue = 10;
	private static int threeInARowValue = 1000;
	private int rows;
	private int columns;
	private char[][] board;
	char empty = '-';
	char player;
	
	/**
	 * Initialize variables
	 * @param rows    - rows number
	 * @param columns - columns number
	 * @param board   - connect4 board
	 * @param player  - player turn 
	 */
	public BoardScorer(int rows, int columns, char[][] board, char player){
		this.rows = rows;
		this.columns = columns;
		this.board = board;
		this.player = player;
	}
	
	/**
	 * Calculates the heuristic of the current board.
	 * it is used by the computer player to determine the next move
	 * It checks for two and three in a row in a different pattern 
	 * @see ComputerPlayer negamax
	 * @return - board score
	 */
	public int getBoardScore() {
		int score = 0;
		
		for (int row = rows-1; row >= 0; row--) {
			for (int col = 0; col < columns; col++) {
				if(row > 1){ 
					score += checkTwoInARowVertical(player, row, col);
				}
				if (row > 2){ 
					score += checkThreeInARowVertical(player, row, col);
					if(col < 4){
						score += checkTwoInARowDiagonalRight(player, row, col);
						score += checkThreeInARowDiagonalRight(player, row, col);
					}
					else if(col > 2){
						score += checkTwoInARowDiagonalLeft(player, row, col);
						score += checkThreeInARowDiagonalLeft(player, row, col);
					}
				}
				if(row > 3){
					if(col < 3){
						 checkThreeInARowOpenEndedHorizontal(player, row, col);
						 checkThreeInARowOpenEndedDiagonalLeft(player, row, col);
					}
					else if(col > 3){
						checkThreeInARowOpenEndedDiagonalRight(player, row, col);
					}
				}
				if(col < 4){ 
					score += checkTwoInARowHorizontal(player, row, col);
					score += checkThreeInARowHorizontal(player, row, col);
				}
			}
		}
		return score;
	}

	/**
	 * Scores the horizontal two in a row patterns 
	 * @param player - playerChip
	 * @param row    - board row
	 * @param col    - board column
	 * @return - board score
	 */
	private int checkTwoInARowHorizontal(char player, int row, int col){
		int score = 0;
		
		// Check for the following pattern XX--
		if (board[row][col] == player && 
			board[row][col+1] == player && 
			board[row][col+2] == empty && 
			board[row][col+3] == empty) {
			score += twoInARowValue * horizontalValue;
		}
		
		// Check for the following pattern -XX-
		else if (board[row][col] == empty && 
				 board[row][col+1] == player && 
				 board[row][col+2] == player && 
				 board[row][col+3] == empty) {
			// This has double he value because there is space in both sides 
			score += 2 * twoInARowValue * horizontalValue;
		}
		
		// Check for the following pattern --XX
		else if (board[row][col] == empty && 
				 board[row][col+1] == empty && 
				 board[row][col+2] == player && 
				 board[row][col+3] == player) {
			score += twoInARowValue * horizontalValue;
		}
		
		// Check for the following pattern X-X-
		else if (board[row][col] == player && 
				 board[row][col+1] == empty && 
				 board[row][col+2] == player && 
				 board[row][col+3] == empty) {
			score += twoInARowValue * horizontalValue;
		}
		
		// Check for the following pattern -X-X
		else if (board[row][col] == empty && 
				 board[row][col+1] == player && 
				 board[row][col+2] == empty && 
				 board[row][col+3] == player) {
			score += twoInARowValue * horizontalValue;
		}
		
		// Check for the following pattern X--X
		else if (board[row][col] == player && 
				 board[row][col+1] == empty && 
				 board[row][col+2] == empty && 
				 board[row][col+3] == player) {
			score += twoInARowValue * horizontalValue;
		}
		
		return score;
	}
	
	/**
	 * Scores the vertical two in a row patterns 
	 * @param player - player chip
	 * @param row    - board row
	 * @param col    - board column
	 * @return - board score
	 */
	private int checkTwoInARowVertical(char player, int row, int col) {
		int score = 0;
		                  
		if (board[row][col] == player &&              // Check for the following pattern
			board[row-1][col] == player && 			  // -
			board[row-2][col] == empty) {             // X
			score += twoInARowValue * verticalValue;  // X
		}
		
		return score;
	}
	
	/**
	 * Scores the right diagonal two in a row patterns 
	 * @param player - playerChip
	 * @param row    - board row
	 * @param col    - board column
	 * @return - board score
	 */
	private int checkTwoInARowDiagonalRight(char player, int row, int col){
		int score = 0;
		
		if (board[row][col] == player &&             // Check for the following pattern
			board[row-1][col+1] == player &&         //    -
			board[row-2][col+2] == empty &&          //   -
			board[row-3][col+3] == empty) {          //  X
			score += twoInARowValue * diagonalValue; // X
		} 
		
		else if (board[row][col] == player &&        // Check for the following pattern
				   board[row-1][col+1] == empty &&   //    X
				   board[row-2][col+2] == empty &&   //   -
				   board[row-3][col+3] == player) {  //  -
			score += twoInARowValue * diagonalValue; // X
		}
					
		else if (board[row][row] == empty &&         // Check for the following pattern
				   board[row-1][col+1] == empty &&   //    X
				   board[row-2][col+2] == player &&  //   X
				   board[row-3][col+3] == player) {  //  -
			score += twoInARowValue * diagonalValue; // -
		}
						
		else if (board[row][row] == empty &&         // Check for the following pattern
				   board[row-1][col+1] == player &&  //    X
				   board[row-2][col+2] == empty &&   //   -
				   board[row-3][col+3] == player) {  //  X
			score += twoInARowValue * diagonalValue; // -
		} 
		
		else if (board[row][row] == player &&         // Check for the following pattern
				   board[row-1][col+1] == empty &&    //    -
				   board[row-2][col+2] == player &&   //   X
				   board[row-3][col+3] == empty) {    //  -
			score += twoInARowValue * diagonalValue;  // X
		} 
				
		else if ( board[row][row] == empty &&            // Check for the following pattern
				   board[row-1][col+1] == player &&      //    -
				   board[row-2][col+2] == player &&      //   X
				   board[row-3][col+3] == empty) {       //  X
 			score += 2 * twoInARowValue * diagonalValue; // -
		}

		return score;
	}
	
	/**
	 * Scores the left diagonal two in a row patterns 
	 * @param player - playerChip
	 * @param row    - board row
	 * @param col    - board column
	 * @return - board score
	 */
	private int checkTwoInARowDiagonalLeft(char player, int row, int col){
		int score = 0;
	
		if (board[row][col] == player &&             // Check for the following pattern
			board[row-1][col-1] == player &&         // -
			board[row-2][col-2] == empty &&          //  -
			board[row-3][col-3] == empty) {          //   X
			score += twoInARowValue * diagonalValue; //    X
		} 
		
		else if (board[row][col] == player &&        // Check for the following pattern 
				   board[row-1][col-1] == empty &&   // X
				   board[row-2][col-2] == empty &&   //  -
				   board[row-3][col-3] == player) {  //   -
			score += twoInARowValue * diagonalValue; //    X
		} 

		else if (board[row][col] == empty &&          // Check for the following pattern
				   board[row-1][col-1] == empty &&    // X 
				   board[row-2][col-2] == player &&   //  X
				   board[row-3][col-3] == player) {   //   -
			score += twoInARowValue * diagonalValue;  //    -
		} 
		
		else if (board[row][col] == empty &&         // Check for the following pattern
				   board[row-1][col-1] == player &&  // X
				   board[row-2][col-2] == empty &&   //  -
				   board[row-3][col-3] == player) {  //   X
			score += twoInARowValue * diagonalValue; //    -
		} 
		
		else if (board[row][col] == player &&         // Check for the following pattern
				   board[row-1][col-1] == empty &&    // - 
				   board[row-2][col-2] == player &&   //  X
				   board[row-3][col-3] == empty) {    //   -
			score += twoInARowValue * diagonalValue;  //    X
		} 
		
		else if (board[row][col] == empty &&             // Check for the following pattern
				   board[row-1][col-1] == player &&      // -
				   board[row-2][col-2] == player &&      //  X
				   board[row-3][col-3] == empty) {       //   X
			score += twoInARowValue * 2 * diagonalValue; //    -
		}
	
		return score;
	}
	
	/**
	 * Scores the horizontal three in a row patterns 
	 * @param player - playerChip
	 * @param row    - board row
	 * @param col    - board column
	 * @return - board score
	 */
	private int checkThreeInARowHorizontal(char player, int row, int col) {
		int score = 0;
	
		// Check for the following pattern XX-X
		if (board[row][col] == player && 
			board[row][col+1] == player && 
			board[row][col+2] == empty && 
			board[row][col+3] == player) {
			score += threeInARowValue * horizontalValue;
		}
		
		// Check for the following pattern X-XX
		else if (board[row][col] == player && 
				 board[row][col+1] == empty && 
				 board[row][col+2] == player && 
				 board[row][col+3] == player) {
			score += threeInARowValue * horizontalValue;
		}
		
		// Check for the following pattern -XXX
		else if (board[row][col] == empty && 
				 board[row][col+1] == player && 
				 board[row][col+2] == player && 
				 board[row][col+3] == player) {
			score += threeInARowValue * horizontalValue;
		}
		
		// Check for the following pattern XXX-
		else if (board[row][col] == player && 
				 board[row][col+1] == player && 
				 board[row][col+2] == player && 
				 board[row][col+3] == empty) {
			score += threeInARowValue * horizontalValue;
		}
			
		return score;		
	}
	
	/**
	 * Scores the vertical three in a row patterns 
	 * @param player - playerChip
	 * @param row    - board row
	 * @param col    - board column
	 * @return - board score
	 */
	private int checkThreeInARowVertical(char player, int row, int col) {
		int score = 0;
		
		if (board[row][col] == player &&                // Check for the following pattern
			board[row-1][col] == player &&              // -
			board[row-2][col] == player &&              // X
			board[row-3][col] == empty) {               // X
			score += threeInARowValue * verticalValue;  // X
		}

		return score;
	}
	
	/**
	 * Scores the left diagonal three in a row patterns 
	 * @param player - playerChip
	 * @param row    - board row
	 * @param col    - board column
	 * @return - board score
	 */
	private int checkThreeInARowDiagonalLeft(char player, int row, int col) {
		int score = 0;
		
		if (board[row][col] == empty &&                // Check for the following pattern
			board[row-1][col-1] == player &&           // X
			board[row-2][col-2] == player &&           //  X
			board[row-3][col-3] == player) {           //   X
			score += threeInARowValue * diagonalValue; //    -
		} 
		
		else if (board[row][col] == player &&          // Check for the following pattern
 				   board[row-1][col-1] == empty &&     // X 
				   board[row-2][col-2] == player &&    //  X
				   board[row-3][col-3] == player) {    //   -
			score += threeInARowValue * diagonalValue; //    X
		}
		
		else if (board[row][col] == player &&          // Check for the following pattern
			   	   board[row-1][col-1] == player &&    // X
			   	   board[row-2][col-2] == empty &&     //  -
			   	   board[row-3][col-3] == player) {    //   X
			score += threeInARowValue * diagonalValue; //    X
		} 
		
		else if (board[row][col] == player &&          // Check for the following pattern
				   board[row-1][col-1] == player &&    // -
				   board[row-2][col-2] == player &&    //  X
				   board[row-3][col-3] == empty) {     //   X
			score += threeInARowValue * diagonalValue; //    X
		}

		return score;
	}

	/**
	 * Scores the right diagonal three in a row patterns 
	 * @param player - playerChip
	 * @param row    - board row
	 * @param col    - board column
	 * @return - board score
	 */
	private int checkThreeInARowDiagonalRight(char player, int row, int col) {
		int score = 0;
		
		if (board[row][col] == player &&               // Check for the following pattern
			board[row-1][col+1] == player &&           //    -
			board[row-2][col+2] == player &&           //   X
			board[row-3][col+3] == empty) {            //  X
			score += threeInARowValue * diagonalValue; // X
		} 

		else if (board[row][col] == player &&          // Check for the following pattern
				   board[row-1][col+1] == player &&    //    X
				   board[row-2][col+2] == empty &&     //   -  
				   board[row-3][col+3] == player) {    //  X
			score += threeInARowValue * diagonalValue; // X
		} 

		else if (board[row][col] == player &&          // Check for the following pattern
				   board[row-1][col+1] == empty &&     //    X
				   board[row-2][col+2] == player &&    //   X
				   board[row-3][col+3] == player) {    //  -
			score += threeInARowValue * diagonalValue; // X
		} 
		
		else if (board[row][col] == empty &&           // Check for the following pattern
				   board[row-1][col+1] == player &&    //    X
				   board[row-2][col+2] == player &&    //   X
				   board[row-3][col+3] == player) {    //  X
			score += threeInARowValue * diagonalValue; // -
		}
		
		return score;
	}
	
	/**
	 * Scores the horizontal open ended patterns 
	 * @param player - playerChip
	 * @param row    - board row
	 * @param col    - board column
	 * @return - board score
	 */
	private int checkThreeInARowOpenEndedHorizontal(char player, int row, int col){
		int score = 0;
		// Check following pattern -XXX-
		if (board[row][col] == empty && 
			board[row][col+1] == player && 
			board[row][col+2] == player && 
			board[row][col+3] == player && 
			board[row][col+4] == empty) {
			score += 2 * threeInARowValue * horizontalValue;
		}
		
		return score;
	}
	
	/**
	 * Scores the right diagonal open ended patterns 
	 * @param player - playerChip
	 * @param row    - board row
	 * @param col    - board column
	 * @return - board score
	 */
	private int checkThreeInARowOpenEndedDiagonalRight(char player, int row, int col){
		int score = 0;

		if (board[row][col] == empty &&                      // Check for the following pattern
			board[row-1][col-1] == player &&                 //     -
			board[row-2][col-3] == player &&                 //    X
			board[row-3][col-3] == player &&                 //   X
			board[row-4][col-4] == empty) {                  //  X
			score += 2 * threeInARowValue * diagonalValue;   // -
		}

		return score;
	}
	
	/**
	 * Scores the left diagonal open ended patterns 
	 * @param player - playerChip
	 * @param row    - board row
	 * @param col    - board column
	 * @return - board score
	 */
	private int checkThreeInARowOpenEndedDiagonalLeft(char player, int row, int col) {
		int score = 0;
		
		if (board[row][col] == empty &&                     // Check for the following pattern
			board[row-1][col+1] == player &&                // -
			board[row-2][col+2] == player &&                //  X
			board[row-3][col+3] == player &&                //   X
			board[row-4][col+4] == empty) {                 //    X
			score += 2 * threeInARowValue * diagonalValue;  //     -
		}
			
		return score;
	}
}
