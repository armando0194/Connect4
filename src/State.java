
public class State {
	private final char empty = '-';
	private final char redChip = 'X';
	private final char yellowChip = 'O';
	
	private final int rows = 6;
	private final int columns = 7;
	private int playerTurn;
	
	private char[][] board;
	private int numOfPlayers;
	private Player[] player = new Player[2];
	
	
	public State(){
		initBoard();
	}
	
	public void initBoard(){
		board = new char[6][7];
		
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				board[i][j] = empty;
			}
		}
	}
	
	public boolean isBoardFull(){
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				if(board[i][j] == empty){
					return false;
				}
			}
		}
		return true;
	}
	
	public boolean checkWinner(){
		
		//Check horizontal
		for (int currRow = 0; currRow < rows - 3; currRow++) {
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
			for (int currCol = 0; currCol < columns - 3; currCol++) {
				if ( (board[currRow][currCol] != empty) && 
					 (board[currRow][currCol] == board[currRow][currCol+1]) && 
					 (board[currRow][currCol] == board[currRow][currCol+2]) && 
					 (board[currRow][currCol] == board[currRow][currCol+3]) ) 
					return true;
			}
		}
		
		//Check Diagonally right
		for (int currRow = 0; currRow < rows - 3; currRow++) {
			for (int currCol = 0; currCol < columns - 3; currCol++) {
				if ( (board[currRow][currCol] != empty) && 
					 (board[currRow][currCol] == board[currRow+1][currCol+1]) && 
					 (board[currRow][currCol] == board[currRow+2][currCol+2]) && 
					 (board[currRow][currCol] == board[currRow+3][currCol+3]) ) 
					return true;
			}
		}
		
		//Check Diagonally left
		for (int currRow = 3; currRow < rows; currRow++) {
			for (int currCol = 0; currCol < columns-3; currCol++) {
				if ( (board[currRow][currCol] != 0) && 
					 (board[currRow][currCol] == board[currRow+1][currCol+1]) && 
					 (board[currRow][currCol] == board[currRow+2][currCol+2]) && 
					 (board[currRow][currCol] == board[currRow+3][currCol+3]) ) 
					return true;
			}
		}
	
		return false;
	}
	
	
	public void createPlayers(String[] username, int numOfPlayers){
		this.numOfPlayers = numOfPlayers;
		//Create human players
		for (int i = 0; i < numOfPlayers; i++) {
			player[i] = new HumanPlayer(username[i]);
		}
		//There is only one human player, so a computer player is created
		if(numOfPlayers < 2){
			player[1] = new ComputerPlayer();
		}
	}
	
	public Player[] getPlayers(){
		return player;
	}
}
