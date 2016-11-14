import java.util.Random;

public class ComputerPlayer extends Player {
	final private int maxValue = 1000000, minValue = -1000000;
	private int movesAhead;
	
	public ComputerPlayer(char[][] board, int movesAhead) {
		super("AI");
		this.movesAhead = movesAhead;
	}

	/**
	 * Generates moves for the computer depending on the difficulty	
	 * @param gameState
	 * @return
	 * @see negamax
	 * @see generateRandomMove
	 */
	public int getMove(State gameState) {
		//Easy mode, generate random moveS
		if(movesAhead == 0){
			return generateRandomMove(gameState);
		}
		//Otherwise generate best move using negamax algorithm
		else{
			// if the bottom middle cell is empty, place chip there
			if(gameState.getBoard()[5][3] == '-'){
				return 3;
			}
			return negamax(gameState.getBoard(), Integer.MIN_VALUE, 0, 1).getCol();
		}
	}
	
	/**
	 * Generates a random column number
	 * @return - column number
	 */
	public int generateRandomMove(State gameState) {
		Random random = new Random();
		int min = 0;
		int max = 6;
		int col = random.nextInt((max - min) + 1) + min;

		// Generate a random column until the column is not full
		while (gameState.isColFull(col)) {
			col = random.nextInt((max - min) + 1) + min;
		}

		return col;
	}
	
	/**
	 * This method is a implementation of the negamax algorithm which is a variation
	 * of minimax. This algorithm relies on the fact that max(a, b) = -min(-a, -b) to 
	 * simplify the implementation of the minimax algorithm. 
	 * @param board - current board
	 * @param alpha - board score
	 * @param depth - maximum search depth)
	 * @param color - player turn
	 * @return the best possible move that the computer can make
	 */
	private Move negamax(char[][] board, int alpha, int depth, int color) {
		int player = (color == 1) ? 0 : 1; // determine who's player is moving
		State currentGame = new State(player, board); // create a temporary game state so the original game is not affected
		
		//Check if there is a winner
		if ( currentGame.checkWinner() ){
			// if the human player wins, return max value, otherwise return minValue
			if (currentGame.getTurn() == 1)
				return new Move(color * (maxValue - depth), 0);
			else
				return new Move(color * (minValue + depth), 0);
		}
		
		//it the game is a draw, return 0
		else if(currentGame.isBoardFull())
			return new Move(0, 0);
		
		//if the depth is reached, calculate the current score of the board
		else if (depth == movesAhead) {
			int score = currentGame.getBoardScore();
			
			if (score != 0)
				return new Move(color * (score - depth), 0);
			else 
				return new Move(score, 0);
		}
		
		//Traverse all the possible moves 
		else {
			Move bestMove = new Move(alpha, 0); 
			for(int col = 0; col < currentGame.getColumns(); col++){
				//Create a new state to try a make a move
				State futureState = new State(player, board);

				//If column is not full
				if (!futureState.isColFull(col)) {
					futureState.move(col); //move
					// if the depth is less than moves ahead, keep doing recursive calls
					if (depth < movesAhead) {
						Move currScore = negamax(futureState.getBoard(), minValue, depth+1, color*-1);
						currScore.setScore(-1 * currScore.getScore()); // max(a, b) = -min(-a, -b)
						if( currScore.getScore() >= bestMove.getScore()) {
							bestMove.setCol(col);
							bestMove.setScore(currScore.getScore());
						}
					}
				}
			}
			return bestMove;
		}		
	}
}
