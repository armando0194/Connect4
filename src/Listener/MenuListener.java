package Listener;

import Game.View;
import Game.State;
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 * This class handles the user input on the menu and menu bar screen.
 * It gets the parameters needed to start a new game. Also, it manipulates
 * the cardlayout 
 * @author Manuel Hernandez
 * @author Danner Pacheco
 */
public class MenuListener implements ActionListener{
	
	private View gameView;
	private State gameState;
	
	/**
	 * Constructor
	 * @param gameView  - current game view
	 * @param gameState - current game state
	 */
	public MenuListener(View gameView, State gameState){
		this.gameView = gameView;
		this.gameState = gameState;
	}

	/**
	 * Changes the current cardboard layout
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		String optionSelected = e.getActionCommand(); // get which option in the menu was clicked
		if(optionSelected.equals("New Game")){  // remove current board and generate a new one
			newGamesSettings();
		}
		else if(optionSelected.equals("Quit")){  // close the game
			gameView.getFrame().dispose();
		}
		else if(optionSelected.equals("Play")){
			gameView.setCurrLayoutCard("Settings");
			gameView.getCurrentPanelLayout().show(gameView.getCurrentPanel(), "Settings");
		}
		else if(optionSelected.equals("Rules")){
			gameView.setCurrLayoutCard("Rules");
			gameView.getCurrentPanelLayout().show(gameView.getCurrentPanel(), "Rules");
		}
		else if(optionSelected.equals("Start")){
			setNewGame();
			gameView.generateBarMenu();
			gameView.setCurrLayoutCard("Board");
			gameView.getCurrentPanelLayout().show(gameView.getCurrentPanel(), "Board");
		}
		else if(optionSelected.equals("Back")){
			gameView.setCurrLayoutCard("Start Menu");
			gameView.getCurrentPanelLayout().first(gameView.getCurrentPanel());
		}
	}	
	
	/**
	 * Creates a JPanel that will be display inside an JOptionPanel. 
	 * The JPanel contains all the options needed to start a new game
	 * @return - menu inside a JPanel
	 */
	private JPanel getPanel()
    {
        JPanel basePanel = new JPanel();
		gameView.initializeUsername();
      
        basePanel.setLayout(new BorderLayout(5, 5));
        basePanel.setOpaque(true);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(5, 2, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        centerPanel.setOpaque(true);
        
        //Input Labels
        JLabel numberOfPlayers = new JLabel("Enter the number of players: ");
        JLabel usernamePlayer1 = new JLabel("Enter username player 1: ");
        JLabel usernamePlayer2 = new JLabel("Enter username player 2 (if any): ");
        JLabel diffculty = new JLabel("Enter Dificulty: ");
        JLabel firstTurn = new JLabel("Please select who goes first");
               
        //Inputs dropdowns and textarea
        centerPanel.add(numberOfPlayers);
        centerPanel.add(gameView.getPlayerDropDown());
        centerPanel.add(usernamePlayer1);
        centerPanel.add(gameView.getUsername(0));
        centerPanel.add(usernamePlayer2);
        centerPanel.add(gameView.getUsername(1));
        centerPanel.add(diffculty);
        centerPanel.add(gameView.getDifficultyDropDown());
        centerPanel.add(firstTurn);
        centerPanel.add(gameView.getFirstTurnDropDown());
        
        basePanel.add(centerPanel);
		
        return basePanel;
    }
	
	/**
	 * Creates a JOptionPane that asks the user if the want to start a new game
	 */
	private void newGamesSettings(){
		int selection = JOptionPane.showConfirmDialog(null, getPanel(), "Settings : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(selection == JOptionPane.OK_OPTION){
			gameView.generateBoard();
			gameView.getCurrentPanelLayout().show(gameView.getCurrentPanel(), "Board");
			gameView.getFrame().revalidate();
			setNewGame();
		}
		else if(selection == JOptionPane.CANCEL_OPTION){
			System.out.println("Canceled"); 
		}	
	}
	
	/**
	 * It creates players, sets new board and sets new difficulty
	 */
	private void setNewGame(){
		int numOfPlayers = Integer.parseInt( (String)gameView.getPlayerDropDown().getSelectedItem() );
		boolean computerFirst = gameView.getFirstTurnDropDown().getSelectedItem().equals("Computer") ? true : false;
		String difficultySelected = gameView.getDifficultyDropDown().getSelectedItem().toString();
		int movesAhead = 0; // set default difficulty to easy
		
		if(difficultySelected.equals("Medium"))
			movesAhead = 2;
		else if(difficultySelected.equals("Hard"))
			movesAhead = 4;
		
		gameState.createPlayers(gameView.getUsername(), numOfPlayers, movesAhead);
		gameState.initBoard();
		
		// if the computer goes first and there is only one player
		if(numOfPlayers == 1 && computerFirst){
			// A chip in the middle is the best first move possible
			gameState.switchTurn();
			gameView.makeMove(5, 3);
			gameState.move(5, 3);
			gameState.switchTurn();
		}
	}
}