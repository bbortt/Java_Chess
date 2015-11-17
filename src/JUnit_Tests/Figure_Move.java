package JUnit_Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
public class Figure_Move {

	private PlayerDatas playerDatas;
	private Game game;

	private PlayerDatas getPlayerDatas() {

		return this.playerDatas;

	}

	private void setPlayerDatas(PlayerDatas playerDatas) {

		this.playerDatas = playerDatas;

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
		setGame(new Game(getPlayerDatas(), null, null, null, null));
		getGame().defaultSetUp();

	}

	// -------------------------------------------------------------
	// TESTS
	// -------------------------------------------------------------

	/**
	 * testValidMove() - Tests a valid move
	 * 
	 * @throws Exception
	 *             Throws null pointer exception for DataBas | Figure[][] ==
	 *             null
	 */
	@Test
	public void testValidMove() throws Exception {

		int y = 6;

		for (int x = 0; x < 8; x = x + 1) {

			// UPDATES PLAYERDATAS
			getPlayerDatas().setFromSelectedJButton(x + "." + y);
			getPlayerDatas().setToSelectedJButton(x + "." + (y - 2));

			// EXECUTES THE MOVE --> RETURN VALUE HAS TO BE TRUE
			assertTrue(
					"Own pawn can move 2 forward in his first turn.",
					getGame().getFigure(x, y).move(x, y, x, (y - 2),
							getPlayerDatas(), getGame().getAllFigures()));

			// UPDATES THE FIGURES, AMOUNTS THE TURN COUNTER
			getGame().setFigure(x, (y - 2), getGame().getFigure(x, y));
			getGame().setFigure(x, y, null);
			getGame().getFigure(x, (y - 2)).setTurnCounter(
					getGame().getFigure(x, (y - 2)).getTurnCounter() + 1);

		}

	}

	/**
	 * testColorChecking() - Tests if the color is checked
	 * 
	 * @throws Exception
	 *             Throws null pointer exception for DataBase | Figure[][] ==
	 *             null
	 */
	@Test
	public void testColorChecking() throws Exception {

		// TESTS MOVE METHOD FOR EACH BLACK FIGURE
		for (int y = 0; y < 2; y = y + 1) {

			for (int x = 0; x < 8; x = x + 1) {

				// UPDATES PLAYERDATAS (NOTE: ONLY THE FROM DOES MATTER)
				getPlayerDatas().setFromSelectedJButton(x + "." + y);
				getPlayerDatas().setToSelectedJButton(x + "." + y);

				// EXECUTES THE MOVE --> RETURN VALUE HAS TO BE FALSE
				assertFalse(
						"Enemies pawn could not be moved.",
						getGame().getFigure(x, y).move(x, y, x, y,
								getPlayerDatas(), getGame().getAllFigures()));

			}

		}

	}

	/**
	 * testRangeChecking() - Tests if the range is checked
	 * 
	 * @throws Exception
	 *             Throws null pointer exception for DataBase | Figure[][] ==
	 *             null
	 */
	@Test
	public void testRangeChecking() throws Exception {

		// TESTS MOVE METHOD FOR EACH WHITE PAWN
		int y = 6;

		for (int x = 0; x < 8; x = x + 1) {

			// UPDATES PLAYERDATAS
			getPlayerDatas().setFromSelectedJButton(x + "." + y);
			getPlayerDatas().setToSelectedJButton(x + "." + (y - 3));

			// EXECUTES THE MOVE --> RETURN VALUE HAS TO BE FALSE
			assertFalse(
					"Own pawn could not be moved.",
					getGame().getFigure(x, y).move(x, y, x, (y - 3),
							getPlayerDatas(), getGame().getAllFigures()));

		}

	}

	/**
	 * testTurnCounter() - Tests if the turn counter is checked
	 * 
	 * @throws Exception
	 *             Throws null pointer exception for DataBase | Figure[][] ==
	 *             null
	 */
	@Test
	public void testTurnCounter() throws Exception {

		// FIRST TURN
		int y = 6;

		for (int x = 0; x < 8; x = x + 1) {

			// UPDATES PLAYERDATAS
			getPlayerDatas().setFromSelectedJButton(x + "." + y);
			getPlayerDatas().setToSelectedJButton(x + "." + (y - 2));

			// EXECUTES THE MOVE --> RETURN VALUE HAS TO BE TRUE
			assertTrue(
					"Own pawn can move 2 forward in his first turn.",
					getGame().getFigure(x, y).move(x, y, x, (y - 2),
							getPlayerDatas(), getGame().getAllFigures()));

			// UPDATES THE FIGURES, AMOUNTS THE TURN COUNTER
			getGame().setFigure(x, (y - 2), getGame().getFigure(x, y));
			getGame().setFigure(x, y, null);
			getGame().getFigure(x, (y - 2)).setTurnCounter(
					getGame().getFigure(x, (y - 2)).getTurnCounter() + 1);

		}

		// SECOND TURN
		y = 4;

		for (int x = 0; x < 8; x = x + 1) {

			// UPDATES PLAYERDATAS
			getPlayerDatas().setFromSelectedJButton(x + "." + y);
			getPlayerDatas().setToSelectedJButton(x + "." + (y - 2));

			// EXECUTES THE MOVE --> RETURN VALUE HAS TO BE FALSE
			assertFalse(
					"Own pawn cannot move 2 forward in his second turn.",
					getGame().getFigure(x, y).move(x, y, x, (y - 2),
							getPlayerDatas(), getGame().getAllFigures()));

		}

	}

	/**
	 * testPlayerDatasException() - Tests PlayerDatas-Exception
	 * 
	 * @throws Exception
	 *             Throws null pointer exception for DataBase | Figure[][] ==
	 *             null
	 */
	@Test(expected = Exception.class)
	public void testPlayerDatasException() throws Exception {

		// EXECUTES THE MOVE --> Exception.NullPointerException
		getGame().getFigure(3, 7).move(3, 7, 4, 6, null,
				getGame().getAllFigures());

	}

	/**
	 * testFigureException() - Tests Figure[][]-Exception
	 * 
	 * @throws Exception
	 *             Throws null pointer exception for DataBase | Figure[][] ==
	 *             null
	 */
	@Test(expected = Exception.class)
	public void testFigureException() throws Exception {

		// EXECUTES THE MOVE --> Exception.NullPointerException
		getGame().getFigure(3, 7).move(3, 7, 4, 6, getPlayerDatas(), null);

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

	}

}