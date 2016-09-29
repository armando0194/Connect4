import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.xml.soap.SAAJResult;

public class Average
{
    private String[] marks;
    private JTextField[] username;
    private JLabel resultLabel;

    public Average()
    {
        marks = new String[2];
        username = new JTextField[2];
        username[0] = new JTextField(10);
        username[1] = new JTextField(10);
    }

    private void displayGUI()
    {
        int selection = JOptionPane.showConfirmDialog(
                null, getPanel(), "Input Form : "
                                , JOptionPane.OK_CANCEL_OPTION
                                , JOptionPane.PLAIN_MESSAGE);

        if (selection == JOptionPane.OK_OPTION) 
        {
        	System.out.println(username[0].getText());
            /*for ( int i = 0; i < 3; i++)
            {
                marks[i] = Double.valueOf(marksField[i].getText());             
            }
            Arrays.sort(marks);
            double average = (marks[1] + marks[2]) / 2.0;
            JOptionPane.showMessageDialog(null
                    , "Average is : " + Double.toString(average)
                    , "Average : "
                    , JOptionPane.PLAIN_MESSAGE);*/
        }
        /*else if (selection == JOptionPane.CANCEL_OPTION)
        {
            // Do something here.
        }*/
    }

    private JPanel getPanel()
    {
        JPanel basePanel = new JPanel();
        //basePanel.setLayout(new BorderLayout(5, 5));
        basePanel.setOpaque(true);
        
        String[] playerOptions = { "1", "2" };
        JComboBox playerDropDown = new JComboBox(playerOptions);
        String[] difficultyOptions = { "Easy", "Medium", "Hard" };
        JComboBox difficultyDropDown = new JComboBox(difficultyOptions);
        
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

    public static void main(String... args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new Average().displayGUI();
            }
        });
    }
}