package JUnitTests;

import Game.State;
import Player.ComputerPlayer;

import static org.junit.Assert.*;
import javax.swing.JTextField;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * JUnit class created to test {@link State} class
 * @see ComputerPlayer
 * @author Manuel Hernandez
 */
public class StateTest {
	State gameState;

	@Before
	public void setUp(){
		gameState = new State();
	}
	
	/**
	 * JUnit Test for the initBoard method
	 */
	@Test
	public void testInitBoard(){
		//Test that initBoard works as intended
		gameState.initBoard();
		//check turn 
		assertEquals(0,gameState.getTurn());
		//check if the array dimensions are correct
		assertNotNull(gameState.getBoard());
		//check if the board is empty
		assertFalse(gameState.isBoardFull());
	}
	
	/**
	 * JUnit Test for the check winner method
	 */
	@Test 
	public void testCheckWinner(){
		char[][] board = {
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'X', 'X', 'X', 'X', '-', '-' , '-'}
				};
		gameState.setBoard(board);
		
		//Test winning case and a case in which there is no winner
		assertTrue(gameState.checkWinner());
		gameState.setCell('-', 5, 3);
		assertFalse(gameState.checkWinner());
	}
	/**
	 * JUnit Test for the isBoardFull method
	 */
	@Test
	public void testIsBoardFull(){
		//Test if isBoardFull method returns appropriate  value
		char[][] emptyBoard = {
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'}
				};
		
		char[][] fullBoard = {
				{'X', 'X', 'O', 'X', 'X', 'O' , 'O'},
				{'O', 'O', 'X', 'O', 'O', 'X' , 'X'},
				{'X', 'X', 'O', 'X', 'X', 'O' , 'O'},
				{'O', 'O', 'X', 'O', 'O', 'X' , 'X'},
				{'X', 'X', 'O', 'X', 'X', 'O' , 'O'},
				{'O', 'O', 'X', 'O', 'O', 'X' , 'X'}
				};
		
		gameState.setBoard(emptyBoard);
		assertFalse(gameState.isBoardFull());
		
		gameState.setBoard(fullBoard);
		assertTrue(gameState.isBoardFull());
	}
	
	/**
	 * JUnit Test for the switchTurn method
	 */
	@Test
	public void testSwitchTurn(){
		//Test if method switchTurn works properly
		gameState.setTurn(1);
		assertEquals(1, gameState.getTurn());
		
		gameState.switchTurn();
		assertNotEquals(1, gameState.getTurn());
		assertEquals(0, gameState.getTurn());
	}
	
	/**
	 * JUnit Test for the move method
	 */
	@Test
	public void testMove(){
		char[][] board = {
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'}
				};
		
		gameState.setBoard(board);
		
		//move and check if the chip was placed
		gameState.move(1);
		assertEquals('X', gameState.getCell(5, 1));
		//check that the cell above is empty
		assertNotEquals('X', gameState.getCell(4, 1));
		assertNotEquals('O', gameState.getCell(4, 1));
		//move and check if the chip was placed
		gameState.move(1);
		assertEquals('O',gameState.getCell(4, 1));
	}
	
	/**
	 * JUnit Test for the setBoard method
	 */
	@Test
	public void testSetBoard(){
		char[][] board = {
				{'-', '-', '-', '-', '-', 'X' , '-'},
				{'X', '-', 'X', '-', '-', 'X' , '-'},
				{'X', 'O', 'X', 'X', '-', 'X' , '-'},
				{'O', 'X', 'O', 'O', 'O', 'X' , '-'},
				{'X', 'X', 'X', 'X', 'X', 'O' , '-'},
				{'X', 'O', 'X', 'X', 'X', 'X' , 'X'}
				};
		
		gameState.setBoard(board);
		
		assertNotNull(gameState.getBoard());
		//check that every cell is the same
		for (int row = 0; row < 6; row++) 
			for (int col = 0; col < 7; col++)
				assertEquals((board[row][col]), gameState.getCell(row, col));
	}
	
	/**
	 * JUnit Test for the isValid method
	 */
	@Test
	public void testIsMoveValid(){
		char[][] board = {
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'X', '-', '-', '-', '-', '-' , '-'},
				{'O', '-', '-', '-', '-', '-' , '-'},
				{'X', '-', '-', '-', '-', '-' , '-'},
				{'O', '-', '-', '-', '-', '-' , '-'},
				{'X', '-', '-', '-', '-', '-' , '-'}
				};
		//Test  isMoveValid
		gameState.setBoard(board);
		assertFalse(gameState.isMoveValid(5, 0));
		assertTrue(gameState.isMoveValid(0, 0));
	}
	
	/**
	 * JUnit Test for the isColFull method
	 */
	@Test 
	public void testIsColFull(){
		char[][] board = {
				{'0', '-', '-', '-', '-', '-' , '-'},
				{'X', '-', '-', '-', '-', '-' , '-'},
				{'O', '-', '-', '-', '-', '-' , '-'},
				{'X', '-', '-', '-', '-', '-' , '-'},
				{'O', '-', '-', '-', '-', '-' , '-'},
				{'X', '-', '-', '-', '-', '-' , '-'}
				};
		gameState.setBoard(board);
		assertTrue(gameState.isColFull(0));
		assertFalse(gameState.isColFull(2));
	}
	
	/**
	 * JUnit Test for the createPlayer method
	 */
	@Test
	public void testCreatePlayers(){
		JTextField[] username = new JTextField[2];
		username[0] = new JTextField();
		username[0].setText("Human");
		username[1] = new JTextField();
		username[1].setText("AI");
		int numberOfPlayers = 1;
		int difficulty = 4;
		
		//check if player are null
		assertNull(gameState.getPlayer(0));
		assertNull(gameState.getPlayer(1));
		
		//Create players and check that players are not null
		gameState.createPlayers(username, numberOfPlayers, difficulty);
		assertNotNull(gameState.getPlayer(0));
		assertNotNull(gameState.getPlayer(1));
		assertNotSame(gameState.getPlayer(0), gameState.getPlayer(1));
	}
	
	/**
	 * JUnit Test for the getBoardScore method
	 */
	@Test
	public void testGetScoreBoard(){
		char[][] board = {
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'-', '-', '-', '-', '-', '-' , '-'},
				{'X', 'X', 'X', '-', '-', '-' , '-'}
				};
		gameState.setBoard(board);
		//Test that the correct value is generated according to board condition
		assertEquals(3030, gameState.getBoardScore());
		gameState.setCell('X', 4, 0);
		
		assertEquals(3040, gameState.getBoardScore());
		gameState.setCell('X', 3, 0);
		
		assertEquals(4040, gameState.getBoardScore());
		gameState.setCell('O', 5, 3);
		
		gameState.setCell('X', 4, 3);
		assertEquals(1060, gameState.getBoardScore());
		
		gameState.setCell('O', 5, 4);
		gameState.switchTurn();
		assertEquals(30, gameState.getBoardScore());
	}
	
	@After
	public void tearDown(){
		gameState = null;
	}
}
