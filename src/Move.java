/**
 * This class is used by the alpha beta pruning algorithm
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

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}
	
	
}