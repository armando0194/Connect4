import javax.swing.JTextField;

public class State {
	private final char empty = '-';
	private final char[] chip = { 'X', 'O' };

	private final int rows = 6;
	private final int columns = 7;
	private int playerTurn = 0;
	
	private final int verticalValue = 1;
	private final int diagonalValue = 2;
	private final int horizontalValue = 3;
	private final int twoInARowValue = 10;
	private final int threeInARowValue = 1000;

	private char[][] board;
	private Player[] player = new Player[2];

	public State() {
		initBoard();
	}

	/**
	 * Sets board and player turn 
	 * @param playerToMove - new player turn
	 * @param board        - new board
	 */
	public State(int playerToMove, char[][] board) {
		this.playerTurn = playerToMove;
		setBoard(board);
	}

	/**
	 * Initiate all the slots in the board as empty
	 */
	public void initBoard() {
		playerTurn = 0;
		board = new char[6][7];

		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				board[row][col] = empty;
			}
		}
	}

	/**
	 * getter for rows
	 * @return - number of rows
	 */
	public int getRows() {
		return rows;
	}

	/**
	 * getter for columns
	 * @return - number of columns
	 */
	public int getColumns() {
		return columns;
	}

	/**
	 * Determines if the board is full
	 * @return - true if the board is full, false otherwise
	 */
	public boolean isBoardFull() {
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				if (board[row][col] == empty) {
					return false;
				}
			}
		}
		return true;
	}

	/**
	 * Prints the current state of the board *Testing*
	 */
	public void printBoard() {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[row].length; col++) {
				System.out.print(board[row][col] + " ");
			}
			System.out.println();
		}
		System.out.println();
	}

	/**
	 * Checks if there is a winner
	 * @return true if there is a winner, false otherwise
	 */
	public boolean checkWinner() {
		// Check horizontal
		for (int currRow = 0; currRow < rows - 3; currRow++) {
			for (int currCol = 0; currCol < columns; currCol++) {
				if ((board[currRow][currCol] != empty) && (board[currRow][currCol] == board[currRow + 1][currCol])
						&& (board[currRow][currCol] == board[currRow + 2][currCol])
						&& (board[currRow][currCol] == board[currRow + 3][currCol]))
					return true;
			}
		}

		// Check Vertically
		for (int currRow = 0; currRow < rows; currRow++) {
			for (int currCol = 0; currCol < columns - 3; currCol++) {
				if ((board[currRow][currCol] != empty) && (board[currRow][currCol] == board[currRow][currCol + 1])
						&& (board[currRow][currCol] == board[currRow][currCol + 2])
						&& (board[currRow][currCol] == board[currRow][currCol + 3]))
					return true;
			}
		}

		// Check Diagonally right
		for (int currRow = 3; currRow < rows; currRow++) {
			for (int currCol = 0; currCol < columns - 3; currCol++) {
				if ((board[currRow][currCol] != empty) && (board[currRow][currCol] == board[currRow - 1][currCol + 1])
						&& (board[currRow][currCol] == board[currRow - 2][currCol + 2])
						&& (board[currRow][currCol] == board[currRow - 3][currCol + 3]))
					return true;
			}

		}

		// Check Diagonally left
		for (int currRow = 3; currRow < rows; currRow++) {
			for (int currCol = 3; currCol < columns; currCol++) {
				if ((board[currRow][currCol] != empty) && (board[currRow][currCol] == board[currRow - 1][currCol - 1])
						&& (board[currRow][currCol] == board[currRow - 2][currCol - 2])
						&& (board[currRow][currCol] == board[currRow - 3][currCol - 3]))
					return true;
			}
		}

		return false;
	}

	/**
	 * Switch player turn
	 */
	public void switchTurn() {
		playerTurn = (playerTurn == 0) ? 1 : 0;
	}

	/**
	 * Creates the players either computer and human, or human and human
	 * @param username      - Array of strings that contains usernames
	 * @param numOfPlayers  - number of human players
	 * @param difficulty    - computer difficulty
	 */
	public void createPlayers(JTextField[] username, int numOfPlayers, int difficulty) {
		// Create human players
		for (int i = 0; i < numOfPlayers; i++) {
			player[i] = new HumanPlayer(username[i].getText());
		}
		// There is only one human player, so a computer player is created
		if (numOfPlayers == 1) {
			player[1] = new ComputerPlayer(board, difficulty);
		}
	}

	/**
	 * Places a chip in the board
	 * @param row - board row
	 * @param col - board column 
	 */
	public void move(int row, int col) {
		board[row][col] = chip[playerTurn];
	}

	/**
	 * Places a chip in a specific column
	 * @param col - column in which the chip will be placed
	 */
	public void move(int col) {
		int r = board.length - 1;
		while (r > 0 && r < rows && board[r][col] != empty) {
			r--;
		}
		board[r][col] = chip[playerTurn];
		switchTurn();
	}

	/**
	 * Getter player
	 * @return - player array
	 */
	public Player[] getPlayers() {
		return player;
	}

	/**
	 * Getter board
	 * @return - board
	 */
	public char[][] getBoard() {
		return board;
	}

	/**
	 * getter playerTurn
	 * @return - current player turn
	 */
	public int getTurn() {
		return playerTurn;
	}

	/**
	 * @return - a string with the username of the current user
	 */
	public String getCurrentUsername() {
		return player[playerTurn].getUsername();
	}

	/**
	 * Returns the username of an specific index in the array player
	 * @param i - the index of the username inside the array player
	 * @return - username
	 */
	public String getUsername(int i) {
		return player[i].getUsername();
	}

	/**
	 * Returns the player of an specific index in the array player
	 * @param i - the index of the player inside the array player
	 * @return - player object
	 */
	public Player getPlayer(int i) {
		return player[i];
	}

	/**
	 * Sets the board to an specific instance of an array
	 * @param currBaord - new board
	 */
	public void setBoard(char[][] currBaord) {
		this.board = new char[rows][columns];
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				this.board[row][col] = currBaord[row][col];
			}
		}
	}

	/**
	 * Determines if a move is valid
	 * @param row - row number
	 * @param col - column number
	 * @return - true if move is valid, false otherwise
	 */
	public boolean isMoveValid(int row, int col) {
		if (board[row][col] == '-') {
			return true;
		}
		return false;
	}

	/**
	 * Checks the last row in the board in order to determine 
	 * if the column is full
	 * @param col - column number
	 * @return true if the column is full, otherwise false
	 */
	public boolean isColFull(int col) {
		if (board[0][col] == empty) {
			return false;
		}
		return true;
	}

	/**
	 * Setter player turn
	 * @param playerTurn - new player turn
	 */
	public void setTurn(int playerTurn) {
		this.playerTurn = playerTurn;
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
		char player = chip[getTurn()];
		
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
		
		// Check for the following pattern 
		// -
		// X
		// X
		if (board[row][col] == player && 
			board[row-1][col] == player && 
			board[row-2][col] == empty) {
			score += twoInARowValue * verticalValue;
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
		
		//    -
		//   -
		//  X
		// X
		if (board[row][col] == player && 
			board[row-1][col+1] == player && 
			board[row-2][col+2] == empty && 
			board[row-3][col+3] == empty) {
			score += twoInARowValue * diagonalValue;
		} 
		
		//	  X
		//   -
		//  -
		// X
		else if (board[row][col] == player && 
				   board[row-1][col+1] == empty && 
				   board[row-2][col+2] == empty && 
				   board[row-3][col+3] == player) {
			score += twoInARowValue * diagonalValue;
		}
		
		//	  X
		//   X
		//  -
		// -				
		else if (board[row][row] == empty && 
				   board[row-1][col+1] == empty && 
				   board[row-2][col+2] == player && 
				   board[row-3][col+3] == player) {
			score += twoInARowValue * diagonalValue;
		}
		
		//	  X
		//   -
		//  X
		// -				
		else if (board[row][row] == empty && 
				   board[row-1][col+1] == player && 
				   board[row-2][col+2] == empty && 
				   board[row-3][col+3] == player) {
			score += twoInARowValue * diagonalValue;
		} 
		
		//	  -
		//   X
		//  -
		// X
		else if (board[row][row] == player && 
				   board[row-1][col+1] == empty && 
				   board[row-2][col+2] == player && 
				   board[row-3][col+3] == empty) {
			score += twoInARowValue * diagonalValue;
		} 
		
		//	  -
		//   X
		//  X
		// -				
		else if ( board[row][row] == empty && 
				   board[row-1][col+1] == player && 
				   board[row-2][col+2] == player && 
				   board[row-3][col+3] == empty) {
			score += 2 * twoInARowValue * diagonalValue;
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
	
		// -
		//  -
		//   X
		//    X
		if (board[row][col] == player && 
			board[row-1][col-1] == player && 
			board[row-2][col-2] == empty && 
			board[row-3][col-3] == empty) {
			score += twoInARowValue * diagonalValue;
		} 
		
		// X
		//  -
		//   -
		//    X
		else if (board[row][col] == player && 
				   board[row-1][col-1] == empty && 
				   board[row-2][col-2] == empty && 
				   board[row-3][col-3] == player) {
			score += twoInARowValue * diagonalValue;
		} 
		
		// X
		//  X
		//   -
		//    -
		else if (board[row][col] == empty && 
				   board[row-1][col-1] == empty && 
				   board[row-2][col-2] == player && 
				   board[row-3][col-3] == player) {
			score += twoInARowValue * diagonalValue;
		} 
		
		// X
		//  -
		//   X
		//    -
		else if (board[row][col] == empty && 
				   board[row-1][col-1] == player && 
				   board[row-2][col-2] == empty && 
				   board[row-3][col-3] == player) {
			score += twoInARowValue * diagonalValue;
		} 
		
		// -
		//  X
		//   -
		//    X
		else if (board[row][col] == player && 
				   board[row-1][col-1] == empty && 
				   board[row-2][col-2] == player && 
				   board[row-3][col-3] == empty) {
			score += twoInARowValue * diagonalValue;
		} 
		
		// -
		//  X
		//   X
		//    -
		else if (board[row][col] == empty && 
				   board[row-1][col-1] == player && 
				   board[row-2][col-2] == player && 
				   board[row-3][col-3] == empty) {
			score += twoInARowValue * 2 * diagonalValue;
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
	
		// XX-X
		if (board[row][col] == player && 
			board[row][col+1] == player && 
			board[row][col+2] == empty && 
			board[row][col+3] == player) {
			score += threeInARowValue * horizontalValue;
		}
		
		// X-XX
		else if (board[row][col] == player && 
				 board[row][col+1] == empty && 
				 board[row][col+2] == player && 
				 board[row][col+3] == player) {
			score += threeInARowValue * horizontalValue;
		}
		
		// -XXX
		else if (board[row][col] == empty && 
				 board[row][col+1] == player && 
				 board[row][col+2] == player && 
				 board[row][col+3] == player) {
			score += threeInARowValue * horizontalValue;
		}
		
		// XXX-
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
		
		// Check for the following pattern
		// 0
		// x
		// x
		// x
		if (board[row][col] == player &&
			board[row-1][col] == player && 
			board[row-2][col] == player && 
			board[row-3][col] == empty) {
			score += threeInARowValue * verticalValue;
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
		
		//X
		// X
		//  X
		//   -
		if (board[row][col] == empty && 
			board[row-1][col-1] == player && 
			board[row-2][col-2] == player && 
			board[row-3][col-3] == player) {
			score += threeInARowValue * diagonalValue;
		} 
		
		//X
		// X
		//  -
		//   X
		else if (board[row][col] == player && 
				   board[row-1][col-1] == empty && 
				   board[row-2][col-2] == player && 
				   board[row-3][col-3] == player) {
			score += threeInARowValue * diagonalValue;
		}
		//X
		// -
		//	X
		//   X
		else if (board[row][col] == player && 
			   	   board[row-1][col-1] == player && 
			   	   board[row-2][col-2] == empty && 
			   	   board[row-3][col-3] == player) {
			score += threeInARowValue * diagonalValue;
		} 
		
		//-
		// X
		//  X
		//   X
		else if (board[row][col] == player && 
				   board[row-1][col-1] == player && 
				   board[row-2][col-2] == player && 
				   board[row-3][col-3] == empty) {
			score += threeInARowValue * diagonalValue;
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
		
		//    -
		//   X
		//  X
		// X
		if (board[row][col] == player &&
			board[row-1][col+1] == player && 
			board[row-2][col+2] == player && 
			board[row-3][col+3] == empty) {
			score += threeInARowValue * diagonalValue;
		} 
		//    X
		//   -
		//  X
		// X
		else if (board[row][col] == player &&
				   board[row-1][col+1] == player && 
				   board[row-2][col+2] == empty && 
				   board[row-3][col+3] == player) {
			score += threeInARowValue * diagonalValue;
		} 
		
		//    X
		//   X
		//  -
		// X
		else if (board[row][col] == player &&
				   board[row-1][col+1] == empty && 
				   board[row-2][col+2] == player && 
				   board[row-3][col+3] == player) {
			score += threeInARowValue * diagonalValue;
		} 
		
		//    X
		//   X
		//  X
		// -
		else if (board[row][col] == empty &&
				   board[row-1][col+1] == player && 
				   board[row-2][col+2] == player && 
				   board[row-3][col+3] == player) {
			score += threeInARowValue * diagonalValue;
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
		
		//	   - 
		//   X
		//  X
		// X
		//-
		if (board[row][col] == empty && 
			board[row-1][col-1] == player && 
			board[row-2][col-3] == player && 
			board[row-3][col-3] == player && 
			board[row-4][col-4] == empty) {
			score += 2 * threeInARowValue * diagonalValue;
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
		
		//- 
		// X
		//  X
		//   X
		//    -
		if (board[row][col] == empty &&
			board[row-1][col+1] == player && 
			board[row-2][col+2] == player && 
			board[row-3][col+3] == player && 
			board[row-4][col+4] == empty) {
			score += 2 * threeInARowValue * diagonalValue;
		}
			
		return score;
	}
}
