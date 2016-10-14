
public class test {
	public static void main(String[] args) {
		State gameState = new State();
		View gameView = new View(gameState);
		
		while( gameView.isGameReady() ){
			System.out.println(gameView.getCurrLayoutCard());
		}
	}
	
}
