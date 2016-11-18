package JUnitTests;

import Game.State;
import Player.ComputerPlayer;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit class created to test {@link ComputerPlayer} class
 * @see ComputerPlayer
 * @author Manuel Hernandez
 * @author Danner Pacheco
 */
public class ComputerPlayerTest {
	ComputerPlayer ai;
	State gameState;
	
	@Before
	public void setUp(){
		ai = new ComputerPlayer(0);
		gameState = new State();
	}
	
	@Test
	public void testGetMoveRandomNumber() {
		//getMove will return random value because difficulty is easy
		int move = ai.getMove(gameState);
		assertTrue(move >= 0 && move < 7);
		move = ai.getMove(gameState);
		assertFalse(move < 0 && move > 6);
	}

	@Test
	public void testComputerAI(){
		char[][] blockBoard = {
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'X', '-', '-', '-', '-', '-' , '-'},
				{'X', '-', '-', '-', '-', '-' , '-'},
				{'X', '-', 'O', 'O', '-', '-' , '-'}
				};
		char[][] winBoard = {
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', 'X', '-' , '-'},
				{'-', '-', '-', '-', 'X', 'X' , '-'},
				{'-', 'X', '-', 'O', 'O', 'O' , '-'}
				};
		
		//set computer turn
		gameState.setTurn(1);
		//set board
		gameState.setBoard(blockBoard);
		//change difficulty to hard
		ai.setMovesAhead(4);
		//getMove should return 0 in order to block the other player attempt
		int bestMove = ai.getMove(gameState);
		gameState.printBoard();
		assertTrue(bestMove == 0);
		System.out.println(bestMove);
		
		//Game should return 1 in order to win
		gameState.setBoard(winBoard);
		bestMove = ai.getMove(gameState);
		gameState.printBoard();
		System.out.println(bestMove);
		
		assertTrue(bestMove == 6);
	}
	
	@After
	public void tearDown(){
		ai = null;
		gameState = null;
	}
}
