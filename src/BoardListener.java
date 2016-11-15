import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


/**
 *  Board Buttons Listener
*/
class BoardListener implements ActionListener {
	private int clickedRow; 
	private int clickedCol; 
	private JButton[][] boardButton;
	private JLabel turnLabel;
	private Icon redChip;
	private Icon yellowChip;
	private Icon openSlot;
	private State gameState;
	
	/**
	 * Constructor
	 * @param clickedRow  - new clicked row
	 * @param clickedCol  - new clicked column
	 * @param boardButton - new boardButton
	 * @param redChip     - new red chip
	 * @param yellowChip  - new yellow chip
	 * @param openSlot    - new open slot
	 * @param gameState   - new game state
	 * @param turnLabel   - new turn label
	 */
	public BoardListener(int clickedRow, int clickedCol, JButton[][] boardButton, Icon redChip, Icon yellowChip, Icon openSlot, State gameState, JLabel turnLabel) {
		this.clickedRow = clickedRow;
		this.clickedCol = clickedCol;
		this.boardButton = boardButton;
		this.turnLabel = turnLabel;
		this.yellowChip = yellowChip;
		this.redChip = redChip;
		this.openSlot = openSlot;
		this.gameState = gameState;
	} 
	
	/**
	 * Draws placed chips on the view and reports winners
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {		
		// Disable the slot clicked and enable button above
		boardButton[clickedRow][clickedCol].setEnabled(false); 
		gameState.move(clickedRow, clickedCol);
		enableButtonAbove(clickedRow, clickedCol);
		
		//if there is a winner, disable board and report winner
		if(gameState.checkWinner()){
			disableBoard();
			reportWinner(gameState.getCurrentUsername());
		}
		//Check if it is a draw
		else if(gameState.isBoardFull()){
			disableBoard();
			reportDraw();
		}
		else{
			gameState.switchTurn();
			paintChip();
			//if player 2 is a computer
			if(gameState.getPlayer(1).getType().equals("ComputerPlayer")){
				computerMakeMove();
			}
		}
		reportTurn();
		gameState.printBoard();
	}

	/**
	 * Generates the next computer move
	 */
	private void computerMakeMove() {
		ComputerPlayer computerPlayer = (ComputerPlayer)gameState.getPlayer(1);
		int col = computerPlayer.getMove(gameState);
		int row = getEmptyRow(col);	
		
		boardButton[row][col].setEnabled(false);
		gameState.move(row, col);
		enableButtonAbove(row, col);
		
		if(gameState.checkWinner()){
			disableBoard();
			reportWinner(gameState.getCurrentUsername());
		}	
		else if(gameState.isBoardFull()){
			disableBoard();
			reportDraw();
		}
		
		gameState.switchTurn();
		paintChip();
	}


	/**
	 * Generates a pop up that reports the game winner
	 * @param winner - username of the winner
	 */
	private void reportWinner(String winner) {
		JPanel winnerMessage = new JPanel();
		winnerMessage.add(new JLabel("Winner: " + winner));
		JOptionPane.showMessageDialog(null,winnerMessage,"Winner",JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Generates a pop up that reports the players that it is a draw
	 */
	private void reportDraw(){
		JPanel drawMessage = new JPanel();
		drawMessage.add(new JLabel("It is a Draw"));
		JOptionPane.showMessageDialog(null,drawMessage,"Draw",JOptionPane.INFORMATION_MESSAGE);
	}
	
	/**
	 * Prints the player turn in a JLabel
	 */
	private void reportTurn(){
		turnLabel.setText(gameState.getCurrentUsername());
	}
	
	/**
	 * Paints the slots in the board depending on which players makes a move
	 */
	private void paintChip(){
		// changes the color of the chip every turn
		for (int row = 0; row < boardButton.length; row++) {
			for (int col = 0; col < boardButton[row].length; col++) {
				if (boardButton[row][col].isEnabled()) {
					if (gameState.getTurn() == 1) {
						boardButton[row][col].setDisabledIcon(redChip);
						boardButton[row][col].setRolloverIcon(redChip);
					} // end if
					else {
						boardButton[row][col].setDisabledIcon(yellowChip);
						boardButton[row][col].setRolloverIcon(yellowChip);
					}
				}
			}
		}
	}

	/**
	 * Disables the board, so players can't keep moving when the game is done
	 */
	private void disableBoard() {
		for (int row = 0; row < boardButton.length; row++) {
			for (int col = 0; col < boardButton[row].length; col++) {
				if(boardButton[row][col].isEnabled()){
					boardButton[row][col].setDisabledIcon(openSlot);
					boardButton[row][col].setEnabled(false);
				}	
			}
		}
	}
	
	/**
	 * Enables the slot above the clicked button
	 * unless it is the last row
	 * @param row - row number
	 * @param col - column number
	 */
	private void enableButtonAbove(int row, int col){
		// Enables the slot above the clicked one if there is not the last row
		if (row > 0) {                            
			boardButton[row - 1][col].setEnabled(true); 
		}
	}
	
	/**
	 * Given a column, determines which row is empty
	 * @param col - column in which the user wants to place a chip
	 * @return - the row in which a chip can be placed
	 */
	private int getEmptyRow(int col){
		//Check 
		for (int row = boardButton.length - 1; row > 0; row--) {
			if(gameState.isMoveValid(row, col)){
				return row;
			}
		}
		return 0;
	}
}