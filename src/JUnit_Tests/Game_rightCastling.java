package JUnit_Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import gui.ChessBoard;

import java.awt.Color;

import main.Game;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataBase.PlayerDatas;

/**
 * 
 * @author Timon Borter
 * 
 */
public class Game_rightCastling {

	private PlayerDatas playerDatas;
	private ChessBoard chessBoard;
	private Game game;

	private PlayerDatas getPlayerDatas() {

		return this.playerDatas;

	}

	private void setPlayerDatas(PlayerDatas playerDatas) {

		this.playerDatas = playerDatas;

	}

	public ChessBoard getChessBoard() {

		return this.chessBoard;

	}

	private void setChessBoard(ChessBoard chessBoard) {

		this.chessBoard = chessBoard;

	}

	private Game getGame() {

		return this.game;

	}

	private void setGame(Game game) {

		this.game = game;

	}

	// -------------------------------------------------------------
	// SET UP
	// -------------------------------------------------------------

	/**
	 * setUp() - Sets up the required datas
	 */
	@Before
	public void setUp() {

		setPlayerDatas(new PlayerDatas());

		// SETS UP DATAS
		preparePlayerDatas();
		setChessBoard(new ChessBoard(getPlayerDatas()));
		getChessBoard().fillChessBoard();

		// STARTS THE GAME
		setGame(new Game(getPlayerDatas(), getChessBoard().getAllFields(),
				null, null, null));
		getGame().defaultSetUp();

	}

	// -------------------------------------------------------------
	// TESTS
	// -------------------------------------------------------------

	/**
	 * testValidCastling() - Tests a valid castling
	 */
	@Test
	public void testValidCastling() {

		// REMOVE UNUSED FIGURES
		// ROOK: System.out.println(getGame().getFigure(7, 7).getClass());
		getGame().setFigure(6, 7, null);
		getGame().setFigure(5, 7, null);
		getGame().setFigure(4, 7, null);
		// KING: System.out.println(getGame().getFigure(3, 7).getClass());

		// TRY TO RUN CASTLING
		getGame().rightCastling();

		// IF CASTLING WAS RUNNED, CHECKSUMMS ARE BOTH AT 20 --> THEY SHOULD
		assertTrue("Castling could be executed.", getGame().getPlayerDatas()
				.getFromSelectedJButton()[0] == 20
				&& getGame().getPlayerDatas().getToSelectedJButton()[0] == 20);

	}

	/**
	 * testColorChecking() - Tests if the color is checked
	 */
	@Test
	public void testColorChecking() {

		// REMOVE UNUSED FIGURES
		// ROOK: System.out.println(getGame().getFigure(7, 7).getClass());
		getGame().setFigure(6, 7, null);
		getGame().setFigure(5, 7, null);
		getGame().setFigure(4, 7, null);
		// KING: System.out.println(getGame().getFigure(3, 7).getClass());

		// CHANGE THE ROOKS COLOR TO Color.BLACK
		getGame().getFigure(7, 7).setColor(Color.BLACK);

		// TRY TO RUN CASTLING
		getGame().rightCastling();

		// IF CASTLING WAS RUNNED, CHECKSUMMS ARE BOTH AT 20 --> THEY SHOULDNT
		assertFalse("Castling could not be executed.", getGame()
				.getPlayerDatas().getFromSelectedJButton()[0] == 20
				&& getGame().getPlayerDatas().getToSelectedJButton()[0] == 20);

	}

	/**
	 * testTurnCounter() - Tests if the turn counter is checked
	 */
	@Test
	public void testTurnCounter() {

		// REMOVE UNUSED FIGURES
		// ROOK: System.out.println(getGame().getFigure(7, 7).getClass());
		getGame().setFigure(6, 7, null);
		getGame().setFigure(5, 7, null);
		getGame().setFigure(4, 7, null);
		// KING: System.out.println(getGame().getFigure(3, 7).getClass());

		// SET TURN COUNTER OF ROOK TO 1
		getGame().getFigure(7, 7).setTurnCounter(1);

		// TRY TO RUN CASTLING
		getGame().rightCastling();

		// IF CASTLING WAS RUNNED, CHECKSUMMS ARE BOTH AT 20 --> THEY SHOULDNT
		assertFalse("Castling could not be executed.", getGame()
				.getPlayerDatas().getFromSelectedJButton()[0] == 20
				&& getGame().getPlayerDatas().getToSelectedJButton()[0] == 20);

	}

	/**
	 * testWayChecking() - Tests if figures in between are checked
	 */
	@Test
	public void testWayChecking() {

		// REMOVE UNUSED FIGURES
		// ROOK: System.out.println(getGame().getFigure(7, 7).getClass());
		// DONT REMOVE: getGame().setFigure(6, 7, null);
		// DONT REMOVE: getGame().setFigure(6, 7, null);
		getGame().setFigure(4, 7, null);
		// KING: System.out.println(getGame().getFigure(3, 7).getClass());

		// TRY TO RUN CASTLING
		getGame().rightCastling();

		// IF CASTLING WAS RUNNED, CHECKSUMMS ARE BOTH AT 20 --> THEY SHOULDNT
		assertFalse("Castling could not be executed.", getGame()
				.getPlayerDatas().getFromSelectedJButton()[0] == 20
				&& getGame().getPlayerDatas().getToSelectedJButton()[0] == 20);

	}

	// -------------------------------------------------------------
	// AFTER TESTING
	// -------------------------------------------------------------

	/**
	 * tearDown() - Unbinds the test datas
	 */
	@After
	public void tearDown() {

		setPlayerDatas(null);
		getChessBoard().setAllFields(null);
		getGame().setAllFigures(null);

	}

	// -------------------------------------------------------------
	// REQUIRED METHODS
	// -------------------------------------------------------------

	/**
	 * preparePlayerDatas() - Fill PlayerDatas with test datas
	 */
	private void preparePlayerDatas() {

		// CREATES TESTERS
		getPlayerDatas().setNamePlayer1("Tester 1");
		getPlayerDatas().setNamePlayer2("Tester 2");
		getPlayerDatas().setOnTurn("Tester 1");
		getPlayerDatas().setColorPlayerOnTurn(Color.WHITE);
		getPlayerDatas().setFromSelectedJButton("11.11");
		getPlayerDatas().setToSelectedJButton("11.11");

	}

}