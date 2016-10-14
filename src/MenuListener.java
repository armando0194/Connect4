import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


class MenuListener implements ActionListener{
	
	private View gameView;
	private JFrame gameFrame;
	private State gameState;
	
	public MenuListener(View gameView, State gameState){
		this.gameView = gameView;
		this.gameState = gameState;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String optionSelected = e.getActionCommand(); // get which option in the menu was clicked
		if(optionSelected.equals("New Game")){  // remove current board and generate a new one
			newGamesSettings();
		}
		else if(optionSelected.equals("Quit")){  // close the game
			gameFrame.dispose();
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
	 * Creates a JPanel that will be display inside an JOptionPanel
	 */
	private JPanel getPanel()
    {
        JPanel basePanel = new JPanel();
		gameView.initializeUsername();
      
        basePanel.setLayout(new BorderLayout(5, 5));
        basePanel.setOpaque(true);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 2, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        centerPanel.setOpaque(true);
        
        //Input Labels
        JLabel numberOfPlayers = new JLabel("Enter the number of players: ");
        JLabel usernamePlayer1 = new JLabel("Enter username player 1: ");
        JLabel usernamePlayer2 = new JLabel("Enter username player 2 (if any): ");
        JLabel diffculty = new JLabel("Enter Dificulty: ");
               
        //Inputs (dropdowns and textarea)
        centerPanel.add(numberOfPlayers);
        centerPanel.add(gameView.getPlayerDropDown());
        centerPanel.add(usernamePlayer1);
        centerPanel.add(gameView.getUsername(0));
        centerPanel.add(usernamePlayer2);
        centerPanel.add(gameView.getUsername(1));
        centerPanel.add(diffculty);
        centerPanel.add(gameView.getDifficultyDropDown());
        
        basePanel.add(centerPanel);
		
        return basePanel;
    }
	
	public void newGamesSettings(){
		int selection = JOptionPane.showConfirmDialog(null, getPanel(), "Settings : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(selection == JOptionPane.OK_OPTION){
			setNewGame();
			gameView.generateBoard();
			gameView.getCurrentPanelLayout().show(gameView.getCurrentPanel(), "Board");
			gameView.getFrame().revalidate();
		}
		else if(selection == JOptionPane.CANCEL_OPTION){
			System.out.println("Canceled"); //test
		}	
	}
	
	public void setNewGame(){
		String[] username = new String[2];
		int numOfPlayers = Integer.parseInt( (String)gameView.getPlayerDropDown().getSelectedItem() );
		
		for (int i = 0; i < gameView.getUsername().length; i++) {
			System.out.println(gameView.getUsername(i).getText());
			username[i] = gameView.getUsername(i).getText();
		}
		
		gameState.createPlayers(username, numOfPlayers);
		gameState.initBoard();
		System.out.println("chale");
	}
}