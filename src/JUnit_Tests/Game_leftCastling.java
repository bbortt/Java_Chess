// -------------------------------------------------------------
// -------------------------------------------------------------
// THIS CLASS IS IN TESTING AND CAUSES FAILURES
// -------------------------------------------------------------
// -------------------------------------------------------------

package JUnit_Tests;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.awt.Color;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import dataBase.PlayerDatas;
import figures.Figure;
import figures.check.CastlingCheck;
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
public class Game_leftCastling {

	// -------------------------------------------------------------
	// VARIABLES
	// -------------------------------------------------------------

	private PlayerDatas playerDatas;
	private Figure[][] figures;
	private CastlingCheck castlingCheck;

	// -------------------------------------------------------------
	// PUBLIC GETTERS AND SETTERS
	// -------------------------------------------------------------

	/**
	 * Required to get a specific Figure
	 * 
	 * @param x
	 *            The specific x-coordinate
	 * @param y
	 *            The specific y-coordinate
	 * @return The Figure at the specified coordinates
	 */
	public Figure getFigure(int x, int y) {

		return this.figures[x][y];

	}

	/**
	 * Required to set a specific Figure
	 * 
	 * @param x
	 *            The specific x-coordinate
	 * @param y
	 *            The specific y-coordinate
	 * @param figure
	 *            The specific Figure
	 */
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

		// INITIALIZES CASTLING CLASS
		castlingCheck = new CastlingCheck(null, playerDatas, null, figures,
				null);

	}

	// -------------------------------------------------------------
	// TESTS
	// -------------------------------------------------------------

	/**
	 * Tests a valid castling
	 */
	@Test
	public void testValidCastling() {

		// REMOVE UNUSED FIGURES
		// ROOK: System.out.println(getFigure(0, 7).getClass());
		setFigure(1, 7, null);
		setFigure(2, 7, null);
		// KING: System.out.println(getFigure(3, 7).getClass());

		// TRY TO RUN CASTLING
		castlingCheck.leftCastling();

		// IF CASTLING WAS RUNNED, CHECKSUMMS ARE BOTH AT 20 --> THEY SHOULD
		assertTrue(
				"Castling could be executed.",
				playerDatas.getFromSelectedJButton()[0] == 20
						&& playerDatas.getToSelectedJButton()[0] == 20);

	}

	/**
	 * Tests if the color is checked
	 */
	@Test
	public void testColorChecking() {

		// REMOVE UNUSED FIGURES
		// ROOK: System.out.println(getFigure(0, 7).getClass());
		setFigure(1, 7, null);
		setFigure(2, 7, null);
		// KING: System.out.println(getFigure(3, 7).getClass());

		// CHANGE THE ROOKS COLOR TO Color.BLACK
		try {

			getFigure(0, 7).setColor(Color.BLACK);

		} catch (Exception e) {

			System.out.println(e.getMessage());
			System.exit(1);

		}

		// TRY TO RUN CASTLING
		castlingCheck.leftCastling();

		// IF CASTLING WAS RUNNED, CHECKSUMMS ARE BOTH AT 20 --> THEY SHOULDNT
		assertFalse(
				"Castling could not be executed.",
				playerDatas.getFromSelectedJButton()[0] == 20
						&& playerDatas.getToSelectedJButton()[0] == 20);

	}

	/**
	 * Tests if the turn counter is checked
	 */
	@Test
	public void testTurnCounter() {

		// REMOVE UNUSED FIGURES
		// ROOK: System.out.println(getFigure(0, 7).getClass());
		setFigure(1, 7, null);
		setFigure(2, 7, null);
		// KING: System.out.println(getFigure(3, 7).getClass());

		// SET TURN COUNTER OF ROOK TO 1
		getFigure(0, 7).setTurnCounter(1);

		// TRY TO RUN CASTLING
		castlingCheck.leftCastling();

		// IF CASTLING WAS RUNNED, CHECKSUMMS ARE BOTH AT 20 --> THEY SHOULDNT
		assertFalse(
				"Castling could not be executed.",
				playerDatas.getFromSelectedJButton()[0] == 20
						&& playerDatas.getToSelectedJButton()[0] == 20);

	}

	/**
	 * Tests if figures in between are checked
	 */
	@Test
	public void testWayChecking() {

		// REMOVE UNUSED FIGURES
		// ROOK: System.out.println(getFigure(0, 7).getClass());
		// DONT REMOVE: setFigure(1, 7, null);
		setFigure(2, 7, null);
		// KING: System.out.println(getFigure(3, 7).getClass());

		// TRY TO RUN CASTLING
		castlingCheck.leftCastling();

		// IF CASTLING WAS RUNNED, CHECKSUMMS ARE BOTH AT 20 --> THEY SHOULDNT
		assertFalse(
				"Castling could not be executed.",
				playerDatas.getFromSelectedJButton()[0] == 20
						&& playerDatas.getToSelectedJButton()[0] == 20);

	}

	// -------------------------------------------------------------
	// AFTER TESTING
	// -------------------------------------------------------------

	/**
	 * Unbinds the test data
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