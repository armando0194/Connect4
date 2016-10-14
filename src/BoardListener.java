import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.JButton;

/**
	 *  Board Buttons Listener
	 */
class BoardListener implements ActionListener {
	private static int turn;
	private int clickedRow; // slot button row
	private int clickedCol; // slot button column 
	private JButton[][] boardButton;
	private Icon redChip;
	private Icon yellowChip;
	private State gameState;
	
	/**
	 * Constructor 
	 */
	public BoardListener(int clickedRow, int clickedCol, JButton[][] boardButton, Icon redChip, Icon yellowChip, State gameState) {
		this.clickedRow = clickedRow;
		this.clickedCol = clickedCol;
		this.boardButton = boardButton;
		this.yellowChip = yellowChip;
		this.redChip = redChip;
		this.gameState = gameState;
	} 
	
	/**
	 * This method is invoked when an enabled slot is clicked. It disables the clicked slot and enables the slot above it unless it is the last row
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		int lastRow = 0;

		boardButton[clickedRow][clickedCol].setEnabled(false); // Disable the slot clicked
		if (clickedRow > lastRow) {                            
			boardButton[clickedRow - 1][clickedCol].setEnabled(true); // enables the slot above the clicked one
		}

		// Basic logic to change turns. Needed to make the prototype work - Temporary
		turn = (turn== 1) ? 0 : 1;

		// changes the color of the chip every turn
		for (int i = 0; i < boardButton.length; i++) {
			for (int j = 0; j < boardButton[i].length; j++) {
				if (boardButton[i][j].isEnabled()) {
					if (turn == 1) {
						boardButton[i][j].setDisabledIcon(redChip);
						boardButton[i][j].setRolloverIcon(redChip);
					} // end if
					else {
						boardButton[i][j].setDisabledIcon(yellowChip);
						boardButton[i][j].setRolloverIcon(yellowChip);
					}
				}
			}
		}
	}
}