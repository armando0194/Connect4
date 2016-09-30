import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *  Menu Buttons Listener
 */
class MenuListener implements ActionListener{
	
	private JFrame gameFrame;
	private JTextField[] username;
	private JComboBox<String> difficultyDropDown;
	private JComboBox<String> playerDropDown;
	private JPanel baordPanel;

	public MenuListener(JFrame gameFrame, JPanel baordPanel) {
		this.gameFrame = gameFrame;
		username = new JTextField[2];
		username[0] = new JTextField(10);
        username[1] = new JTextField(10);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String optionSelected = e.getActionCommand(); // get which option in the menu was clicked
		if(optionSelected.equals("New Game")){  // remove current board and generate a new one
			int selection = JOptionPane.showConfirmDialog(null, getPanel(), "Settings : ", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
			if(selection == JOptionPane.OK_OPTION){
				//Test///////////////////////////////////////////////////
				System.out.println(playerDropDown.getSelectedItem());
				System.out.println(username[0].getText());
				System.out.println(username[1].getText());
				System.out.println(difficultyDropDown.getSelectedItem());
				/////////////////////////////////////////////////////////
				gameFrame.remove(gameFrame.);
				GUI.generateBoard();
				gameFrame.revalidate();
			}
			else if(selection == JOptionPane.CANCEL_OPTION){
				System.out.println("Canceled"); //test
			}	
		}
		else if(optionSelected.equals("Quit")){  // close the game
			gameFrame.dispose();
		}
	}	
	
	private JPanel getPanel()
    {
        JPanel basePanel = new JPanel();
        basePanel.setLayout(new BorderLayout(5, 5));
        basePanel.setOpaque(true);
        
        String[] playerOptions = { "1", "2" };
        playerDropDown = new JComboBox<String>(playerOptions);
        String[] difficultyOptions = { "Easy", "Medium", "Hard" };
        difficultyDropDown = new JComboBox<String>(difficultyOptions);
        
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridLayout(4, 2, 5, 5));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        centerPanel.setOpaque(true);
        
        JLabel numberOfPlayers = new JLabel("Enter the number of players: ");
        JLabel usernamePlayer1 = new JLabel("Enter username player 1: ");
        JLabel usernamePlayer2 = new JLabel("Enter username player 2 (if any): ");
        JLabel diffculty = new JLabel("Enter Dificulty: ");
        
        
        centerPanel.add(numberOfPlayers);
        centerPanel.add(playerDropDown);
        centerPanel.add(usernamePlayer1);
        centerPanel.add(username[0]);
        centerPanel.add(usernamePlayer2);
        centerPanel.add(username[1]);
        centerPanel.add(diffculty);
        centerPanel.add(difficultyDropDown);
        
        
        basePanel.add(centerPanel);
		
        return basePanel;
    }

}