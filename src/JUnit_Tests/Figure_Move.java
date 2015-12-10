package JUnit_Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataBase.PlayerDatas;
import figures.Figure;
import figures.figures.Bishop;
import figures.figures.King;
import figures.figures.Knight;
import figures.figures.Pawn;
import figures.figures.Queen;
import figures.figures.Rook;

/**
 * 
 * @author Timon Borter
 * 
 */
public class Figure_Move {

	// -------------------------------------------------------------
	// VARIABLES
	// -------------------------------------------------------------

	private PlayerDatas playerDatas;
	private Figure[][] figures;

	// -------------------------------------------------------------
	// PUBLIC GETTERS AND SETTERS
	// -------------------------------------------------------------

	public Figure getFigure(int x, int y) {

		return this.figures[x][y];

	}

	public void setFigure(int x, int y, Figure figure) {

		this.figures[x][y] = figure;

	}

	// -------------------------------------------------------------
	// SET UP
	// -------------------------------------------------------------

	/**
	 * Sets up the required data
	 */
	@Before
	public void setUp() {

		playerDatas = new PlayerDatas();
		figures = new Figure[8][8];

		// SETS UP DATAS
		preparePlayerDatas();
		defaultSetUp();

	}

	// -------------------------------------------------------------
	// TESTS
	// -------------------------------------------------------------

	/**
	 * Tests a valid move
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
			playerDatas.setFromSelectedJButton(x + "." + y);
			playerDatas.setToSelectedJButton(x + "." + (y - 2));

			// EXECUTES THE MOVE --> RETURN VALUE HAS TO BE TRUE
			assertTrue("Own pawn can move 2 forward in his first turn.",
					getFigure(x, y)
							.move(x, y, x, (y - 2), playerDatas, figures));

			// UPDATES THE FIGURES, AMOUNTS THE TURN COUNTER
			setFigure(x, (y - 2), getFigure(x, y));
			setFigure(x, y, null);
			getFigure(x, (y - 2)).setTurnCounter(
					getFigure(x, (y - 2)).getTurnCounter() + 1);

		}

	}

	/**
	 * Tests if the color is checked
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
				playerDatas.setFromSelectedJButton(x + "." + y);
				playerDatas.setToSelectedJButton(x + "." + y);

				// EXECUTES THE MOVE --> RETURN VALUE HAS TO BE FALSE
				assertFalse("Enemies pawn could not be moved.", getFigure(x, y)
						.move(x, y, x, y, playerDatas, figures));

			}

		}

	}

	/**
	 * Tests if the range is checked
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
			playerDatas.setFromSelectedJButton(x + "." + y);
			playerDatas.setToSelectedJButton(x + "." + (y - 3));

			// EXECUTES THE MOVE --> RETURN VALUE HAS TO BE FALSE
			assertFalse("Own pawn could not be moved.",
					getFigure(x, y)
							.move(x, y, x, (y - 3), playerDatas, figures));

		}

	}

	/**
	 * Tests if the turn counter is checked
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
			playerDatas.setFromSelectedJButton(x + "." + y);
			playerDatas.setToSelectedJButton(x + "." + (y - 2));

			// EXECUTES THE MOVE --> RETURN VALUE HAS TO BE TRUE
			assertTrue("Own pawn can move 2 forward in his first turn.",
					getFigure(x, y)
							.move(x, y, x, (y - 2), playerDatas, figures));

			// UPDATES THE FIGURES, AMOUNTS THE TURN COUNTER
			setFigure(x, (y - 2), getFigure(x, y));
			setFigure(x, y, null);
			getFigure(x, (y - 2)).setTurnCounter(
					getFigure(x, (y - 2)).getTurnCounter() + 1);

		}

		// SECOND TURN
		y = 4;

		for (int x = 0; x < 8; x = x + 1) {

			// UPDATES PLAYERDATAS
			playerDatas.setFromSelectedJButton(x + "." + y);
			playerDatas.setToSelectedJButton(x + "." + (y - 2));

			// EXECUTES THE MOVE --> RETURN VALUE HAS TO BE FALSE
			assertFalse("Own pawn cannot move 2 forward in his second turn.",
					getFigure(x, y)
							.move(x, y, x, (y - 2), playerDatas, figures));

		}

	}

	/**
	 * Tests PlayerDatas-Exception
	 * 
	 * @throws Exception
	 *             Throws null pointer exception for DataBase | Figure[][] ==
	 *             null
	 */
	@Test(expected = Exception.class)
	public void testPlayerDatasException() throws Exception {

		// EXECUTES THE MOVE --> Exception.NullPointerException
		getFigure(3, 7).move(3, 7, 4, 6, null, figures);

	}

	/**
	 * Tests Figure[][]-Exception
	 * 
	 * @throws Exception
	 *             Throws null pointer exception for DataBase | Figure[][] ==
	 *             null
	 */
	@Test(expected = Exception.class)
	public void testFigureException() throws Exception {

		// EXECUTES THE MOVE --> Exception.NullPointerException
		getFigure(3, 7).move(3, 7, 4, 6, playerDatas, null);

	}

	// -------------------------------------------------------------
	// AFTER TESTING
	// -------------------------------------------------------------

	/**
	 * Unbinds the test datas
	 */
	@After
	public void tearDown() {

		playerDatas = null;
		figures = null;

	}

	// -------------------------------------------------------------
	// REQUIRED METHODS
	// -------------------------------------------------------------

	/**
	 * Fill PlayerDatas with test data
	 */
	private void preparePlayerDatas() {

		// CREATES TESTERS
		playerDatas.setNamePlayer1("Tester 1");
		playerDatas.setNamePlayer2("Tester 2");
		playerDatas.setOnTurn("Tester 1");
		playerDatas.setColorPlayerOnTurn(Color.WHITE);

	}

	/**
	 * Sets the figures to their default location.
	 */
	public void defaultSetUp() {

		// -------------------------------------------------------------
		// PUTS KINGS ON BOARD
		// -------------------------------------------------------------

		setFigure(3, 0, createKing(Color.BLACK));

		setFigure(3, 7, createKing(Color.WHITE));

		// -------------------------------------------------------------
		// PUTS QUEENS ON BOARD
		// -------------------------------------------------------------

		setFigure(4, 0, createQueen(Color.BLACK));

		setFigure(4, 7, createQueen(Color.WHITE));

		// -------------------------------------------------------------
		// PUTS BISHOPS ON BOARD
		// -------------------------------------------------------------

		setFigure(2, 0, createBishop(Color.BLACK));
		setFigure(5, 0, createBishop(Color.BLACK));

		setFigure(2, 7, createBishop(Color.WHITE));
		setFigure(5, 7, createBishop(Color.WHITE));

		// -------------------------------------------------------------
		// PUTS KNIGHT ON BOARD
		// -------------------------------------------------------------

		setFigure(1, 0, createKnight(Color.BLACK));
		setFigure(6, 0, createKnight(Color.BLACK));

		setFigure(1, 7, createKnight(Color.WHITE));
		setFigure(6, 7, createKnight(Color.WHITE));

		// -------------------------------------------------------------
		// PUTS ROOKS ON BOARD
		// -------------------------------------------------------------

		setFigure(0, 0, createRook(Color.BLACK));
		setFigure(7, 0, createRook(Color.BLACK));

		setFigure(0, 7, createRook(Color.WHITE));
		setFigure(7, 7, createRook(Color.WHITE));

		// -------------------------------------------------------------
		// PUTS PAWNS ON BOARD
		// -------------------------------------------------------------

		for (int x = 0; x < 8; x = x + 1) {

			setFigure(x, 1, createPawn(Color.BLACK));

			setFigure(x, 6, createPawn(Color.WHITE));

		}

	}

	// -------------------------------------------------------------
	// PRIVATE METHODS TO CREATE NEW FIGURES
	// -------------------------------------------------------------

	private King createKing(Color color) {

		King newKing = new King(color);
		return newKing;

	}

	private Queen createQueen(Color color) {

		Queen newQueen = new Queen(color);
		return newQueen;

	}

	private Bishop createBishop(Color color) {

		Bishop newBishop = new Bishop(color);
		return newBishop;

	}

	private Knight createKnight(Color color) {

		Knight newKnight = new Knight(color);
		return newKnight;

	}

	private Rook createRook(Color color) {

		Rook newRook = new Rook(color);
		return newRook;

	}

	private Pawn createPawn(Color color) {

		Pawn newPawn = new Pawn(color);
		return newPawn;

	}

}