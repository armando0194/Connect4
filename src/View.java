import java.awt.CardLayout;
import java.awt.Color;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class View {

	private final int rows = 6;
	private final int colums = 7;

	private State gameState;
	private JFrame gameFrame;
	private CardLayout currentPanelLayout;
	private String currLayoutCard;

	private JMenuBar menuBar;
	private JMenu menu;
	private JPanel currentPanel;
	private JPanel boardPanel;
	private JLabel playerTurn;
	
	private JButton[][] boardButtons; 
	private BoardListener[][] boardListener; 
	
	private final String[] difficultyOptions = { "Easy", "Medium", "Hard" };
    private final String[] playerOptions = { "1", "2" };
	private final JComboBox<String> playerDropDown  = new JComboBox<String>(playerOptions);
    private final JComboBox<String> difficultyDropDown = new JComboBox<String>(difficultyOptions);
    private JTextField[] username = new JTextField[2];

	private Icon openSlot = new ImageIcon("src\\images\\empty.png");
	private Icon redChip = new ImageIcon("src\\images\\Red.png"); 
	private Icon yellowChip = new ImageIcon("src\\images\\Yellow.png");
	
	public View( State gameState) {
		currLayoutCard = "Start Game";
		this.gameState = gameState;
		generateGameFrame();
	}
	
	public void generateGameFrame(){
		//Generate Frame and components
        gameFrame = new JFrame("Connect4");
		currentPanelLayout = new CardLayout();
		currentPanel = new JPanel(currentPanelLayout);
		
		//Generate different cardLayout elements
		generateStartMenu();
		generateSettingPanel();
		generateBoard();
		generateRulesPanel();	
	
		//Frame Settings
		gameFrame.add(currentPanel);
		gameFrame.setSize(440,480);
		gameFrame.setVisible(true);
		gameFrame.setEnabled(true);
		gameFrame.setResizable(false);
		gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gameFrame.setBackground(Color.BLUE);
		gameFrame.setLocationRelativeTo(null);
	}
	
	private void generateSettingPanel() {
        // Initialize panel
		JPanel settingPanel = new JPanel();
        JButton startButton = new JButton("Start");
        JButton backStart = new JButton("Back");
     
        
        //Set Button listeners
        MenuListener startListener = new MenuListener(this, gameState);
        MenuListener backListener = new MenuListener(this, gameState);
        startButton.addActionListener(startListener);
        backStart.addActionListener(backListener);
        
        settingPanel.setLayout(new GridLayout(5, 2, 2, 2));
        settingPanel.setBorder(BorderFactory.createEmptyBorder(100, 20, 100, 20));
        settingPanel.setOpaque(true);
        
        //Input Labels
        JLabel numberOfPlayers = new JLabel("Enter the number of players: ");
        JLabel usernamePlayer1 = new JLabel("Enter username player 1: ");
        JLabel usernamePlayer2 = new JLabel("Enter username player 2 (if any): ");
        JLabel diffculty = new JLabel("Enter Dificulty: ");
		username[0] = new JTextField(20);
		username[1] = new JTextField(20);
               
        //Inputs (dropdowns and textarea)
        settingPanel.add(numberOfPlayers);
        settingPanel.add(playerDropDown);
        settingPanel.add(usernamePlayer1);
        settingPanel.add(username[0]);
        settingPanel.add(usernamePlayer2);
        settingPanel.add(username[1]);
        settingPanel.add(diffculty);
        settingPanel.add(difficultyDropDown);
        settingPanel.add(startButton);
        settingPanel.add(backStart);

        currentPanel.add(settingPanel, "Settings");
		
	}

	public void generateRulesPanel() {
		
	}

	/**
	 * Initiate the board
	 */
	public void generateBoard() {
		
		JLabel turnLabel = new JLabel("Turn: ");
		playerTurn = new JLabel("");
		boardListener = new BoardListener[rows][colums];
		boardButtons = new JButton[rows][colums]; 
		boardPanel = new JPanel(new GridLayout(rows+1, colums));

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < colums; j++) {
				boardButtons[i][j] = new JButton(openSlot);
				boardListener[i][j] = new BoardListener(i, j, boardButtons, redChip, yellowChip, gameState);
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
		
		boardPanel.add(turnLabel);
		boardPanel.add(playerTurn);
		//boardPanel.setBackground(Color.BLUE);
		boardPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
		currentPanel.add(boardPanel, "Board");
		//gameFrame.add(boardPanel, BorderLayout.CENTER);
	}

	/**
	 * Generates a menu at the top left of the window
	 */
	public void generateBarMenu() {
		// initiate the menu
		menuBar = new JMenuBar();
		menu = new JMenu("Game");
		
		//Create options inside the menu
		JMenuItem newGameItem = new JMenuItem("New Game"); // new game menu item
		JMenuItem quitGameItem = new JMenuItem("Quit");
		
		// Add listener for the options
		MenuListener newGameListener = new MenuListener(this, gameState); 
		MenuListener quitListener = new MenuListener(this, gameState); 
		newGameItem.addActionListener(newGameListener);
		quitGameItem.addActionListener(quitListener);
		
		//Add options to the menu and add menu to Frame
		menu.add(newGameItem);
		menu.add(quitGameItem);
		menuBar.add(menu);
		gameFrame.setJMenuBar(menuBar);
	}
	
	public void generateStartMenu(){

		//Initialize panel and layout
		JPanel menuPanel = new JPanel(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		
		//Initialize panel elements
		BufferedImage logo = readLogoImage();
		JLabel logoLabel = new JLabel(new ImageIcon(logo));
		JButton newGame = new JButton("Play");
		JButton rules = new JButton("Rules");
		
		//Adding functionality to Buttons
		MenuListener newGameListener = new MenuListener(this, gameState);
		MenuListener rulesListener = new MenuListener(this, gameState);
		newGame.addActionListener(newGameListener);
		rules.addActionListener(rulesListener);
		
		//Add elements to panel
		menuPanel.add(logoLabel, gbc);
		menuPanel.add(newGame, gbc);
		menuPanel.add(rules, gbc);
		menuPanel.setBackground(Color.WHITE);
		currentPanel.add(menuPanel, "Start Menu");
	}
	
	public void setTurn(){
		playerTurn.setText("hola");
	}
	
	public BufferedImage readLogoImage(){
		BufferedImage logo = null;
		try {
			logo = ImageIO.read(new File("src\\images\\logo.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return logo;
	}
	
	public CardLayout getCurrentPanelLayout() {
		return currentPanelLayout;
	}

	public JPanel getCurrentPanel() {
		return currentPanel;
	}
	
	public JComboBox<String> getPlayerDropDown() {
		return playerDropDown;
	}

	public JComboBox<String> getDifficultyDropDown() {
		return difficultyDropDown;
	}
	
	public JTextField[] getUsername() {
		return username;
	}
	
	public JTextField getUsername(int i){
		return username[i];
	}
	
	public void initializeUsername(){
		username[0] = new JTextField(20);
		username[0] = new JTextField(20);
	}
	
	public JFrame getFrame(){
		return gameFrame;
	}
	
	public boolean isGameReady(){
		if(currLayoutCard.equalsIgnoreCase("Board"))
			return true;
		return false;
	}
	
	public void setCurrLayoutCard(String currLayoutCard){
		this.currLayoutCard = currLayoutCard;
	}
	
	public String getCurrLayoutCard(){
		return currLayoutCard;
	}
}
