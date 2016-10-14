
public class Player {
	String username;
	
	public Player(String username){
		this.username = username;
	}
	
	public String getUsername(){
		return username;

	}
	
	public String getType(){
		return this.getClass().getTypeName();
	}
}
