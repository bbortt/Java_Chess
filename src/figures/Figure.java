package figures;

import java.awt.Color;

import javax.swing.JFrame;

import dataBase.PlayerDatas;

/**
 * This is an abstract super class for each figure. Variables and methods
 * (move() and kill()) are defined here.
 * 
 * @author Timon Borter
 * 
 */
public abstract class Figure {

	// -------------------------------------------------------------
	// VARIABLES
	// -------------------------------------------------------------

	private Color color;
	private int maxX;
	private int maxY;
	private int turnCounter;

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
	public abstract Boolean move(int givenCurrentX, int givenCurrentY,
			int givenToX, int givenToY, PlayerDatas givenPlayerDatas,
			Figure[][] givenFigures) throws Exception;

	/**
	 * Called if the figure gets slain.
	 * 
	 * @param givenNameOfPlayer
	 *            Required for JOptionPane.showMessageDialog()
	 * @param givenMainFrame
	 *            Required for JOptionPane.showMessageDialog()
	 */
	public abstract void kill(String givenNameOfPlayer, JFrame givenMainFrame);

}