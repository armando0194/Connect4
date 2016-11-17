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
		assertTrue(test.checkWinner());
		test.getBoard()[5][0] = '-';
		assertFalse(test.checkWinner());
	}

}
