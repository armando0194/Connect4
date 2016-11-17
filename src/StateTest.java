import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class StateTest {
	State test;
	/**
	 * char[][] board = {
							{'-', '-', '-', '-', '-', '-' , '-'},
							{'-', '-', '-', '-', '-', '-' , '-'},
							{'-', '-', '-', '-', '-', '-' , '-'},
							{'-', '-', '-', '-', '-', '-' , '-'},
							{'-', '-', '-', '-', '-', '-' , '-'},
							{'-', '-', '-', '-', '-', '-' , '-'}
						 };
	 */
	
	@Test
	public void test() {
		char[][] board = {
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'X', 'X', 'X', 'X', '-', '-' , '-'}
				};
		
		test = new State(1, board);
		
		
		//Test winning case and a case in which there is no winner
		assertTrue(test.checkWinner());
		test.getBoard()[5][3] = '-';
		assertFalse(test.checkWinner());
		
		test.switchTurn();
		
		//Test that the correct value is generated according to board condition
		assertEquals(3030, test.getBoardScore());
		test.getBoard()[4][0] = 'X';
		
		assertEquals(3040, test.getBoardScore());
		test.getBoard()[3][0] = 'X';
		
		assertEquals(4040, test.getBoardScore());
		test.getBoard()[5][3] = 'O';
		
		test.getBoard()[4][3] = 'X';
		assertEquals(1060, test.getBoardScore());
		
		test.getBoard()[5][4] = 'O';
		test.switchTurn();
		assertEquals(30, test.getBoardScore());
		
		
		
		}
	
	
	
}
