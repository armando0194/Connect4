import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GUI {

	private int rows = 7;
	private int colums = 7;

	private JFrame gameFrame;
	private JMenuBar menuBar;
	private JMenu menu;
	private JPanel boardPanel;

	private JButton[][] boardButtons; // the slots in the game
	private BoardListener[][] boardListener; // slot button listeners

	private Icon openSlot = new ImageIcon("C:\\Users\\Armando\\workspace\\Connect4Example\\src\\images\\Open.GIF");
	private Icon redChip = new ImageIcon("C:\\Users\\Armando\\workspace\\Connect4Example\\src\\images\\Red.GIF"); // slot
	private Icon blackChip = new ImageIcon("C:\\Users\\Armando\\workspace\\Connect4Example\\src\\images\\Black.GIF"); // slot

	int turn = 0;

	public GUI(int rows, int colums) {
		this.rows = rows;
		this.colums = colums;
		gameFrame = new JFrame("Connect4");
		gameFrame.setLayout(new BorderLayout());
		generateMenu();
		generateBoard();
		gameFrame.setSize(500, 500);
		gameFrame.setVisible(true);
		gameFrame.setEnabled(true);
	}

	/**
	 * Initiate the board
	 */
	private void generateBoard() {
		
		boardListener = new BoardListener[rows][colums];
		boardButtons = new JButton[rows][colums]; 
		boardPanel = new JPanel(new GridLayout(rows, colums));

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < colums; j++) {
				boardButtons[i][j] = new JButton(openSlot);
				boardListener[i][j] = new BoardListener(i, j);
				boardButtons[i][j].addActionListener(boardListener[i][j]); 
				boardButtons[i][j].setBackground(Color.WHITE); 
				boardPanel.add(boardButtons[i][j]);
				
				//disable all the rows but the first one
				if (i < rows - 1) {
					boardButtons[i][j].setDisabledIcon(openSlot); 
					boardButtons[i][j].setEnabled(false); 
				} 
				// black chip goes first
				else {
					boardButtons[i][j].setDisabledIcon(blackChip); 
				}
			}
		}

		boardPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5)); 
		gameFrame.add(boardPanel, BorderLayout.CENTER);
	}

	/**
	 * Generates a menu at the top left of the window
	 */
	public void generateMenu() {
		// initiate the menu
		menuBar = new JMenuBar();
		menu = new JMenu("Game");
		
		//Create options inside the menu
		JMenuItem newGameItem = new JMenuItem("New Game"); // new game menu item
		JMenuItem quitGameItem = new JMenuItem("Quit");
		
		// Add listener for the options
		MenuListener newGameListener = new MenuListener(); 
		MenuListener quitListener = new MenuListener(); 
		newGameItem.addActionListener(newGameListener);
		quitGameItem.addActionListener(quitListener);
		
		//Add options to the menu and add menu to Frame
		menu.add(newGameItem);
		menu.add(quitGameItem);
		menuBar.add(menu);
		gameFrame.setJMenuBar(menuBar);
	}

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

			boardButtons[clickedRow][clickedCol].setEnabled(false); // Disable the slot clicked
			if (clickedRow > lastRow) {                            
				boardButtons[clickedRow - 1][clickedCol].setEnabled(true); // enables the slot above the clicked one
			}

			// Basic logic to change turns. Needed to make the prototype work - Temporary
			turn = (turn == 1) ? 0 : 1;

			// changes the color of the chip every turn
			for (int i = 0; i < rows; i++) {
				for (int j = 0; j < colums; j++) {
					if (boardButtons[i][j].isEnabled()) {
						if (turn == 1) {
							boardButtons[i][j].setDisabledIcon(redChip);
							boardButtons[i][j].setRolloverIcon(redChip);
						} // end if
						else {
							boardButtons[i][j].setDisabledIcon(blackChip);
							boardButtons[i][j].setRolloverIcon(blackChip);
						}
					}
				}
			}
		}
	}
	
	/**
	 *  Menu Buttons Listener
	 */
	class MenuListener implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			String optionSelected = e.getActionCommand(); // get which option in the menu was clicked
			if(optionSelected.equals("New Game")){  // remove current board and generate a new one
				gameFrame.remove(boardPanel);
				generateBoard();
				gameFrame.revalidate();
			}
			else if(optionSelected.equals("Quit")){  // close the game
				gameFrame.dispose();
			}
			
		}
		
	}
}
