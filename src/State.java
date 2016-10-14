
public class State {
	private final char empty = '-';
	private final char redChip = 'X';
	private final char yellowChip = 'O';
	
	private final int rows = 6;
	private final int columns = 7;
	private int playerTurn = 0;
	
	private char[][] board;
	private Player[] player = new Player[2];
	
	public State(){
		initBoard();
	}
	
	/**
	 * Initiate all the slots in the board as empty
	 */
	public void initBoard(){
		playerTurn = 0;
		board = new char[6][7];
		
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				board[row][col] = empty;
			}
		}
	}
	
	/**
	 * Determines if the board is full
	 * @return - true if the board is full, false otherwise
	 */
	public boolean isBoardFull(){
		for (int row = 0; row < rows; row++) {
			for (int col = 0; col < columns; col++) {
				if(board[row][col] == empty){
					return false;
				}
			}
		}
		return true;
	}
	
	/**
	 * Prints the current state of the board *Testing*
	 */
	public void printBoard(){
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
	public boolean checkWinner(){
		//Check horizontal
		for (int currRow = 0; currRow < rows-3; currRow++) {
			for (int currCol = 0; currCol < columns; currCol++) {
				if ( (board[currRow][currCol] != empty) && 
					 (board[currRow][currCol] == board[currRow+1][currCol]) && 
					 (board[currRow][currCol] == board[currRow+2][currCol]) && 
					 (board[currRow][currCol] == board[currRow+3][currCol]) ) 
					return true;
			}
		}
		
		//Check Vertically
		for (int currRow = 0; currRow < rows; currRow++) {
			for (int currCol = 0; currCol < columns-3; currCol++) {
				if ( (board[currRow][currCol] != empty) && 
					 (board[currRow][currCol] == board[currRow][currCol+1]) && 
					 (board[currRow][currCol] == board[currRow][currCol+2]) && 
					 (board[currRow][currCol] == board[currRow][currCol+3]) ) 
					return true;
			}
		}
		
		//Check Diagonally right
		for (int currRow = 3; currRow < rows; currRow++) {
			for (int currCol = 0; currCol < columns-3; currCol++) {
				if ( (board[currRow][currCol] != empty) && 
					 (board[currRow][currCol] == board[currRow-1][currCol+1]) && 
					 (board[currRow][currCol] == board[currRow-2][currCol+2]) && 
					 (board[currRow][currCol] == board[currRow-3][currCol+3]) ) 
					return true;
			}
			
		}
		
		//Check Diagonally left
		for (int currRow = 3; currRow < rows; currRow++) {
			for (int currCol = 3; currCol < columns; currCol++) {
				if ( (board[currRow][currCol] != empty) && 
					 (board[currRow][currCol] == board[currRow-1][currCol-1]) && 
					 (board[currRow][currCol] == board[currRow-2][currCol-2]) && 
					 (board[currRow][currCol] == board[currRow-3][currCol-3]) ) 
					return true;
			}
		}
	
		return false;
	}
	
	/**
	 * Switch player turns
	 */
	public void switchTurn(){
		playerTurn = (playerTurn == 0) ? 1 : 0;
	}
	
	/**
	 * Recieves the usernames and number of human players, and creates
	 * the players 
	 * @param username     - Array of strings that contains the usernames
	 * @param numOfPlayers - number of human players
	 */
	public void createPlayers(String[] username, int numOfPlayers){
		//Create human players
		for (int row = 0; row < numOfPlayers; row++) {
			player[row] = new HumanPlayer(username[row]);
		}
		//There is only one human player, so a computer player is created
		if(numOfPlayers < 2){
			player[1] = new ComputerPlayer();
		}
	}
	
	public void makeMove(int clickedRow, int clickedCol){
		if(playerTurn == 0){
			board[clickedRow][clickedCol] = yellowChip;
		}
		else{
			board[clickedRow][clickedCol] = redChip;
		}
	}
	
	/**
	 * Getter player
	 */
	public Player[] getPlayers(){
		return player;
	}
	
	/**
	 * Getter board
	 */
	public char[][] getBoard(){
		return board;
	}
	
	/**
	 * getter playerTurn
	 */
	public int getTurn(){
		return playerTurn;
	}
	
	/**
	 * @return - a string with the username of the current user
	 */
	public String getCurrentUsername(){
		return player[playerTurn].getUsername();
	}
	
	/**
	 * Returns the username of an spcific index in the array player
	 * @param i - the index of the username inside the array player
	 * @return  - username
	 */
	public String getUsername(int i){
		return player[i].getUsername();
	}
	
	/**
	 * Returns the player of an spcific index in the array player
	 * @param i - the index of the player inside the array player
	 * @return  - player object
	 */
	public Player getPlayer(int i){
		return player[i];
	}

	/**
	 * Determines if a move is valid
	 * @param row - row number
	 * @param col - column number
	 * @return    - true if move is valid, false otherwise
	 */
	public boolean isMoveValid(int row, int col){
		if(board[row][col] == '-'){
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
	public boolean isColFull(int col){
		if(board[0][col] == empty){
			return false;
		}
		return true;
	}
}
