import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class GUI {

	private int rows = 6;
	private int colums = 7;

	private JFrame gameFrame;
	private JMenuBar menuBar;
	private JMenu menu;
	private JPanel boardPanel;

	private JButton[][] boardButtons; 
	private BoardListener[][] boardListener; 

	private Icon openSlot = new ImageIcon("C:\\Users\\Armando\\Desktop\\Connect4\\src\\Open.GIF");
	private Icon redChip = new ImageIcon("C:\\Users\\Armando\\Desktop\\Connect4\\src\\Red.GIF"); 
	private Icon blackChip = new ImageIcon("C:\\Users\\Armando\\Desktop\\Connect4\\src\\Black.GIF");

	private int turn = 0;
	
	public GUI(int rows, int colums) {
		
		//initiate Values
		this.rows = rows;
		this.colums = colums;
		
        //Generate Frame and components
        gameFrame = new JFrame("Connect4");
		gameFrame.setLayout(new BorderLayout());
		generateMenu();
		generateBoard();
		
		//Frame Settings
		gameFrame.setSize(500, 500);
		gameFrame.setVisible(true);
		gameFrame.setEnabled(true);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setLocationRelativeTo(null);
	}

	/**
	 * Initiate the board
	 */
	public void generateBoard() {
		
		boardListener = new BoardListener[rows][colums];
		boardButtons = new JButton[rows][colums]; 
		boardPanel = new JPanel(new GridLayout(rows, colums));

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < colums; j++) {
				boardButtons[i][j] = new JButton(openSlot);
				//boardListener[i][j] = new BoardListener(i, j);
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
		MenuListener newGameListener = new MenuListener(gameFrame, boardPanel); 
		MenuListener quitListener = new MenuListener(gameFrame); 
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
}
