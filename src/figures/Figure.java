package figures;

import java.awt.Color;

import dataBase.PlayerDatas;

/**
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
	// PUBLIC GETTERS AND SETTERS
	// -------------------------------------------------------------

	public Color getColor() {
		return this.color;

	}

	public void setColor(Color color) {
		this.color = color;

	}

	public int getMaxX() {

		return this.maxX;

	}

	public void setMaxX(int maxX) {

		this.maxX = maxX;

	}

	public int getMaxY() {

		return this.maxY;

	}

	public void setMaxY(int maxY) {

		this.maxY = maxY;

	}

	public int getTurnCounter() {

		return this.turnCounter;

	}

	public void setTurnCounter(int turnCounter) {

		this.turnCounter = turnCounter;

	}

	/**
	 * move() - Actualizes data with current game informations, then checks if
	 * the step is valid
	 * 
	 * @param currentX
	 *            The current x-coordinate (which the player selected)
	 * @param currentY
	 *            The current y-coordinate (which the player selected)
	 * @param toX
	 *            The selected aim x-coordinate
	 * @param toY
	 *            The selected aim y-coordinate
	 * @param playerDatas
	 *            Required the PlayerDatas to check color
	 * @param figures
	 *            Require actual figures positions
	 * @throws Exception
	 *             Throws null pointer exception for DataBase | Figure[][] ==
	 *             null
	 * @return Returns true if the move is valid
	 */
	public abstract Boolean move(int currentX, int currentY, int toX, int toY,
			PlayerDatas playerDatas, Figure[][] figures) throws Exception;

	/**
	 * kill() - Called if the figure gets slain
	 * 
	 * @param nameOfPlayer
	 *            Required for JOptionPane.showMessageDialog()
	 */
	public abstract void kill(String nameOfPlayer);

}