package Game;

/**
 * This class consists exclusively of a main method that initializes
 * the game. It instantiates the State and View classes
 * @author  Manuel Hernandez
 * @author  Danner Pacheco
*/
public class Connect4Game {
	public static void main(String[] args) {
		State gameState = new State();
		new View(gameState);
	}	
}
