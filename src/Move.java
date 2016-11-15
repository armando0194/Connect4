/**
 * This class is used by the negamax algorithm
 * in order to keep track of the moves and their 
 * corresponding scores
 * @see ComputerPlayer
 */
public class Move {
	private int score;       // move score according to the board
	private int col;         // move column
	
	/**
	 * Constructor
	 * @param score - new score 
	 * @param col   - new column number
	 */
	public Move(int score, int col){
	    this.setScore(score);
	    this.setCol(col);
	}

	/**
	 * Score Getter
	 * @return - move score
	 */
	public int getScore() {
		return score;
	}

	/**
	 * Score Setter
	 * @param score - new score
	 */
	public void setScore(int score) {
		this.score = score;
	}

	/**
	 * Column Getter
	 * @return - move column
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Column Setter
	 * @param col - new column
	 */
	public void setCol(int col) {
		this.col = col;
	}
	
	
}