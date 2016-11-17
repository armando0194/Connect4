import static org.junit.Assert.*;

import javax.swing.JTextField;

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
		
		
		//Test if isBoardFull method returns appropriate  value
		
		char[][] board1 = {
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'}
				};
		
		test.setBoard(board1);
		
		assertFalse(test.isBoardFull());
		
		char[][] board2 = {
				{'X', 'X', 'X', 'X', 'X', 'X' , 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X' , 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X' , 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X' , 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X' , 'X'},
				{'X', 'X', 'X', 'X', 'X', 'X' , 'X'}
				};
		
		test.setBoard(board2);
		assertTrue(test.isBoardFull());
		
		
		//Test if method switchTurn works properly
		assertEquals(1,test.getTurn());
		test.switchTurn();
		assertNotEquals(1,test.getTurn());
		assertEquals(0,test.getTurn());
		
		//Test that initBoard works as intended
		test.initBoard();
		assertEquals(0,test.getTurn());
		assertEquals(6,(test.getBoard()).length);
		assertEquals(7,(test.getBoard()[0].length));
		assertFalse(test.isBoardFull());
		
		//Test move method
		test.move(1);
		assertEquals('X',test.getBoard()[5][1]);
		test.move(1);
		assertEquals('O',test.getBoard()[4][1]);
		test.move(0);
		assertEquals('X',test.getBoard()[5][0]);
		
		//Test setBoard
		char[][] board3 = {
				{'-', 'X', '-', 'X', 'X', 'X' , 'X'},
				{'X', 'X', 'X', 'O', 'O', 'X' , 'O'},
				{'X', 'O', 'X', 'X', 'X', 'X' , 'X'},
				{'-', 'X', 'O', 'O', 'O', 'X' , 'X'},
				{'X', 'X', 'X', 'X', '-', 'O' , 'O'},
				{'X', 'O', 'X', 'X', 'X', 'X' , 'X'}
				};
		
		test.setBoard(board3);
		for (int row = 0; row < 6; row++) 
			for (int col = 0; col < 7; col++)
				assertEquals((board3[row][col]), (test.getBoard()[row][col]));
			
		
		//Test  isMoveValid
			test.initBoard();
			test.move(2);
			assertFalse(test.isMoveValid(5, 2));
			assertTrue(test.isMoveValid(5, 3));
	
		//Test isColFull
			test.move(2);
			test.move(2);
			test.move(2);
			test.move(2);
			test.move(2);
			assertTrue(test.isColFull(2));
			assertFalse(test.isColFull(3));
	
	
	}
}
