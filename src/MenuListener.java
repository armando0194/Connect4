import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

class MenuListener implements ActionListener{
	
	private JFrame gameFrame;
	private JPanel currentPanel;
	private CardLayout currentPanelLayout;
	private JTextField[] usernameInput;
	private Icon empty;
	private Icon yellowChip;
	private JButton[][] boardButton;
	private JComboBox<String> difficultyDropDown;
	private JComboBox<String> playerDropDown;
	private State gameState;

	public MenuListener(JFrame gameFrame, JButton[][] boardButton, Icon empty, Icon yellowChip) {
		this.gameFrame = gameFrame;
		this.boardButton = boardButton;
		this.empty = empty;
		this.yellowChip = yellowChip;
		usernameInput = new JTextField[2];
		usernameInput[0] = new JTextField(10);
		usernameInput[1] = new JTextField(10);
	}
	
	public MenuListener(JFrame gameFrame) {
		this.gameFrame = gameFrame;
	}
	
	public MenuListener(JPanel currentPanel, CardLayout currentPanelLayout) {
		this.currentPanel = currentPanel;
		this.currentPanelLayout = currentPanelLayout;
	}

	public MenuListener(JPanel currentPanel, CardLayout currentPanelLayout, JComboBox<String> playerDropDown, JComboBox<String> difficultyDropDown, JTextField[] usernameInput, State gameState) {
		this.currentPanel = currentPanel;
		this.currentPanelLayout = currentPanelLayout;
		this.playerDropDown = playerDropDown;
		this.difficultyDropDown = difficultyDropDown;
		this.gameState = gameState;
		this.usernameInput = usernameInput;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String optionSelected = e.getActionCommand(); // get which option in the menu was clicked
		if(optionSelected.equals("New Game")){  // remove current board and generate a new one
			settingsGame();
		}
		else if(optionSelected.equals("Quit")){  // close the game
			gameFrame.dispose();
		}
		else if(optionSelected.equals("Play")){
			currentPanelLayout.show(currentPanel, "Settings");
		}
		else if(optionSelected.equals("Rules")){
			currentPanelLayout.show(currentPanel, "Rules");
		}
		else if(optionSelected.equals("Start")){
			setNewGame();
			currentPanelLayout.show(currentPanel, "Board");
		}
		else if(optionSelected.equals("Back")){
			System.out.println("Back");
			currentPanelLayout.first(currentPanel);
		}
	}	
	
	/**
	 * Creates a JPanel that will be display inside an JOptionPanel
	 */
	private JPanel getPanel()
    {
        JPanel basePanel = new JPanel();
        String[] difficultyOptions = { "Easy", "Medium", "Hard" };
        String[] playerOptions = { "1", "2" };
        
        basePanel.setLayout(new BorderLayout(5, 5));
        basePanel.setOpaque(true);
        
        //Create dropdowns
        playerDropDown = new JComboBox<String>(playerOptions);
        difficultyDropDown = new JComboBox<String>(difficultyOptions);
        
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
        centerPanel.add(playerDropDown);
        centerPanel.add(usernamePlayer1);
        centerPanel.add(usernameInput[0]);
        centerPanel.add(usernamePlayer2);
        centerPanel.add(usernameInput[1]);
        centerPanel.add(diffculty);
        centerPanel.add(difficultyDropDown);
        
        basePanel.add(centerPanel);
		
        return basePanel;
    }
	
	/**
	 * Cleans the board so players can start a new game
	 */
	public void cleanBoard(){
		for (int i = 0; i < boardButton.length; i++) {
			for (int j = 0; j < boardButton[i].length; j++) {
				boardButton[i][j].setIcon(empty);
				//disable all the rows but the first one
				if (i < boardButton.length - 1) {
					boardButton[i][j].setDisabledIcon(empty); 
					boardButton[i][j].setEnabled(false); 
				} 
				else{
					boardButton[i][j].setDisabledIcon(yellowChip);
					boardButton[i][j].setEnabled(true); 
				}
			}
		}
	}
	
	public void settingsGame(){
		int selection = JOptionPane.showConfirmDialog(null, getPanel(), "Settings : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
		if(selection == JOptionPane.OK_OPTION){
			setNewGame();
			cleanBoard();
			gameFrame.revalidate();
		}
		else if(selection == JOptionPane.CANCEL_OPTION){
			System.out.println("Canceled"); //test
		}	
	}
	
	public void setNewGame(){
		String[] username = new String[2];
		int numOfPlayers = Integer.parseInt( (String)playerDropDown.getSelectedItem() );
		
		for (int i = 0; i < usernameInput.length; i++) {
			System.out.println(usernameInput[i].getText());
			username[i] = usernameInput[i].getText();
		}
		
		gameState.createPlayers(username, numOfPlayers);
		gameState.initBoard();
		System.out.println("chale");
	}
}