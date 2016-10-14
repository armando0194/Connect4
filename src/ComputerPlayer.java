import java.util.Random;

public class ComputerPlayer extends Player{
	public ComputerPlayer(){
		super("AI");
	}
	
	public int generateMove(){
		Random random = new Random();
		int min = 0;
		int max = 6;
		
		return random.nextInt( (max - min) + 1 ) + min;
	}
	
	/*To-do Implement AI*/
}
