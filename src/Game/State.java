package Game;

import javax.swing.JTextField;
import Player.Player;
import Player.ComputerPlayer;
import Player.HumanPlayer;

/**
 * This class handles of the game logic. It consists of methods that determine
 * the current state of the game such as get winner, iniatiate board, get player turn,
 * check if board is full, etc.
 * @author  Manuel Hernandez
 * @author  Danner Pacheco
 */
public class State {
	private final char empty = '-';
	private final char[] chip = { 'X', 'O' };

	private final int rows = 6;
	private final int columns = 7;
	private int playerTurn = 0;

	private char[][] board;
	private Player[] player = new Player[2];
 
	/**
	 * Initiates the board
	 */
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
			player[1] = new ComputerPlayer(difficulty);
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
	 * Setter a cell in the board to an specific chip
	 * @param chip - current player's chip
	 * @param row  - row number
	 * @param col  - column number
	 */
	public void setCell(char chip, int row, int col){
		this.board[row][col] = chip;
	}
	
	/**
	 * Getter for a cell
	 * @param row - row number
	 * @param col - column number
	 * @return chip placed in the specific cell
	 */
	public char getCell(int row, int col){
		return this.board[row][col];
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
		BoardScorer boardScorer = new BoardScorer(rows, columns, board, chip[getTurn()]);
		return boardScorer.getBoardScore();
	}
}
