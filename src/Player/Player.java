package Player;

/**
 * Player super class that has two children: 
 * {@link ComputerPlayer} and {@link HumanPlayer}
 * @see ComputerPlayer
 * @see HumanPlayer
 * @author Manuel Hernandez
 */
public class Player {
	private String username;
	
	/**
	 * Constructor
	 * @param username - new username
	 */
	public Player(String username){
		this.username = username;
	}
	
	/**
	 * Username Getter
	 * @return username
	 */
	public String getUsername(){
		return username;

	}
	
	/**
	 * Determines the type of object
	 * @return - ComputerPlayer or HumanPlayer
	 */
	public String getType(){
		return this.getClass().getTypeName();
	}
}
