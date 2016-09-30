import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
	 *  Board Buttons Listener
	 */
class BoardListener implements ActionListener {
	private int clickedRow; // slot button row
	private int clickedCol; // slot button column 

	/**
	 * Constructor 
	 */
	public BoardListener(int clickedRow, int clickedCol) {
		this.clickedRow = clickedRow;
		this.clickedCol = clickedCol;
	} 

	/**
	 * This method is invoked when an enabled slot is clicked. It disables the clicked slot and enables the slot above it unless it is the last row
	 */
	@Override
	public void actionPerformed(ActionEvent actionEvent) {
		int lastRow = 0;

		GUI.getBoardButton(clickedRow, clickedCol).setEnabled(false); // Disable the slot clicked
		if (clickedRow > lastRow) {                            
			GUI.getBoardButton(clickedRow - 1, clickedCol).setEnabled(true); // enables the slot above the clicked one
		}
		
		System.out.println("turn " + GUI.getTurn());
		// Basic logic to change turns. Needed to make the prototype work - Temporary
		GUI.setTurn((GUI.getTurn()== 1) ? 0 : 1);

		// changes the color of the chip every turn
		for (int i = 0; i < GUI.getRows(); i++) {
			for (int j = 0; j < GUI.getColumns(); j++) {
				if (GUI.getBoardButton(i, j).isEnabled()) {
					if (GUI.getTurn() == 1) {
						GUI.getBoardButton(i, j).setDisabledIcon(GUI.getRedChip());
						GUI.getBoardButton(i, j).setRolloverIcon(GUI.getRedChip());
					} // end if
					else {
						GUI.getBoardButton(i, j).setDisabledIcon(GUI.getYellowChip());
						GUI.getBoardButton(i, j).setRolloverIcon(GUI.getYellowChip());
					}
				}
			}
		}
	}
}