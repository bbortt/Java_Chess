package figures.figures;

import java.awt.Color;

import javax.swing.JFrame;

import dataBase.PlayerDatas;
import figures.Figure;
import figures.check.CheckKnight;

/**
 * This is a knight. Handles ever move of its self. Also implements kill()
 * method.
 * 
 * @author Timon Borter
 * 
 */
public class Knight extends Figure {

	// -------------------------------------------------------------
	// VARIABLES
	// -------------------------------------------------------------

	private Color color;
	private int turnCounter;
	CheckKnight checkKnight;

	// -------------------------------------------------------------
	// REQUIRED GETTERS AND SETTERS
	// -------------------------------------------------------------

	/**
	 * Returns the figures color
	 * 
	 * @return The figures color
	 */
	public Color getColor() {

		return this.color;

	}

	/**
	 * Sets the figures color
	 * 
	 * @param color
	 *            Required to set the figures color, this can not be null
	 * @throws Exception
	 *             If no color was set
	 */
	public void setColor(Color color) throws Exception {

		if (color == null) {

			throw new Exception("No color given!");

		} else {

			this.color = color;

		}

	}

	/**
	 * Returns the figures count of moves
	 * 
	 * @return Counted moves as Integer
	 */
	public int getTurnCounter() {

		return this.turnCounter;

	}

	/**
	 * Sets the figures turn counter
	 * 
	 * @param turnCounter
	 *            Required to set the turnCounter
	 */
	public void setTurnCounter(int turnCounter) {

		this.turnCounter = turnCounter;

	}

	/**
	 * On creation sets color, range and turn counter.
	 * 
	 * @param givenColor
	 *            Requires the figures color
	 */
	public Knight(Color givenColor) {

		// -------------------------------------------------------------
		// FIGURES ATTRIBUTES
		// -------------------------------------------------------------

		try {

			setColor(givenColor);

		} catch (Exception e) {

			System.out.println(e.getMessage());
			System.exit(1);

		}

		setTurnCounter(0);
		// -------------------------------------------------------------
		// FIGURES VALIDATIONS
		// -------------------------------------------------------------

		checkKnight = new CheckKnight();

	}

	/**
	 * Actualizes data with current game informations, then checks if the step
	 * is valid.
	 * 
	 * @param givenCurrentX
	 *            The current x-coordinate (which the player selected)
	 * @param givenCurrentY
	 *            The current y-coordinate (which the player selected)
	 * @param givenToX
	 *            The selected aim x-coordinate
	 * @param givenToY
	 *            The selected aim y-coordinate
	 * @param givenPlayerDatas
	 *            Required the PlayerDatas to check color
	 * @param givenFigures
	 *            Require actual figures positions
	 * @throws Exception
	 *             Throws null pointer exception for DataBase | Figure[][] ==
	 *             null
	 * @return Returns true if the move is valid
	 */
	@Override
	public Boolean move(int givenCurrentX, int givenCurrentY, int givenToX,
			int givenToY, PlayerDatas givenPlayerDatas, Figure[][] givenFigures)
			throws Exception {

		checkKnight.actualize(givenCurrentX, givenCurrentY, givenToX, givenToY,
				givenPlayerDatas, givenFigures);

		boolean returnValue = false;

		try {

			returnValue = checkKnight.check();

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}

		return returnValue;

	}

	/**
	 * kill() - Called if the figure gets slain
	 * 
	 * @param givenNameOfPlayer
	 *            Required for JOptionPane.showMessageDialog()
	 * @param givenMainFrame
	 *            Required for JOptionPane.showMessageDialog()
	 */
	public void kill(String givenNameOfPlayer, JFrame givenMainFrame) {

		// DO NOTHING

	}

}