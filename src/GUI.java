import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class GUI {

	private int rows;
	private int colums;

	private JFrame gameFrame;
	private JMenuBar menuBar;
	private JMenu menu;
	private JPanel boardPanel;

	private JButton[][] boardButtons; 
	private BoardListener[][] boardListener; 

	private Icon openSlot = new ImageIcon("src\\images\\empty.png");
	private Icon redChip = new ImageIcon("src\\images\\Red.png"); 
	private Icon yellowChip = new ImageIcon("src\\images\\Yellow.png");
	
	public GUI(int rows, int colums) {
		
		//initiate Values
		this.rows = rows;
		this.colums = colums;
		
        //Generate Frame and components
        gameFrame = new JFrame("Connect4");
		gameFrame.setLayout(new BorderLayout());
		
		generateBoard();
		generateMenu();
		
		//Frame Settings
		gameFrame.setSize(440,440);
		gameFrame.setVisible(true);
		gameFrame.setEnabled(true);
		gameFrame.setResizable(false);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setBackground(Color.BLUE);
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
				boardListener[i][j] = new BoardListener(i, j, boardButtons, redChip, yellowChip);
				boardButtons[i][j].addActionListener(boardListener[i][j]); 
				boardButtons[i][j].setBackground(Color.BLUE); 
				boardButtons[i][j].setBorderPainted(false);//Removes weird white lines
				boardPanel.add(boardButtons[i][j]);
				
				//disable all the rows but the first one
				if (i < rows - 1) {
					boardButtons[i][j].setDisabledIcon(openSlot); 
					boardButtons[i][j].setEnabled(false); 
				} 
				// yellow chip goes first
				else {
					boardButtons[i][j].setDisabledIcon(yellowChip); 
				}
			}
		}

		boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
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
		MenuListener newGameListener = new MenuListener(gameFrame, boardButtons, openSlot, yellowChip); 
		MenuListener quitListener = new MenuListener(gameFrame); 
		newGameItem.addActionListener(newGameListener);
		quitGameItem.addActionListener(quitListener);
		
		//Add options to the menu and add menu to Frame
		menu.add(newGameItem);
		menu.add(quitGameItem);
		menuBar.add(menu);
		gameFrame.setJMenuBar(menuBar);
	}
}
