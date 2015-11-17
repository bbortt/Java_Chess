package figures.check;

import dataBase.PlayerDatas;
import figures.Figure;

/**
 * 
 * @author Timon Borter
 * 
 */
public class CheckKnight {

	// -------------------------------------------------------------
	// VARIABLES
	// -------------------------------------------------------------

	private int currentX;
	private int currentY;
	private int toX;
	private int toY;
	private int maxX;
	private int maxY;
	private int turnCounter;
	private PlayerDatas playerDatas;
	private Figure[][] figures;

	// -------------------------------------------------------------
	// PUBLIC SETTERS AND GETTERS
	// -------------------------------------------------------------

	public int getCurrentX() {

		return currentX;

	}

	public void setCurrentX(int currentX) {

		this.currentX = currentX;

	}

	public int getCurrentY() {

		return currentY;

	}

	public void setCurrentY(int currentY) {

		this.currentY = currentY;

	}

	public int getToX() {

		return toX;

	}

	public void setToX(int toX) {

		this.toX = toX;

	}

	public int getToY() {

		return toY;

	}

	public void setToY(int toY) {

		this.toY = toY;

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

	public Figure[][] getAllFigures() {

		return this.figures;

	}

	public void setAllFigures(Figure[][] figures) {

		this.figures = figures;

	}

	public int getTurnCounter() {

		return this.turnCounter;

	}

	public void setTurnCounter(int turnCounter) {

		this.turnCounter = turnCounter;

	}

	public PlayerDatas getPlayerDatas() {

		return this.playerDatas;

	}

	public void setPlayerDatas(PlayerDatas playerDatas) {

		this.playerDatas = playerDatas;

	}

	public Figure getFigure(int x, int y) {

		return this.figures[x][y];

	}

	public void setFigure(int x, int y, Figure figure) {

		this.figures[x][y] = figure;

	}

	/**
	 * actualize() - Actualizes all variables with current game informations
	 * 
	 * @param currentX
	 *            The current x-coordinate (which the player selected)
	 * @param currentY
	 *            The current y-coordinate (which the player selected)
	 * @param toX
	 *            The selected aim x-coordinate
	 * @param toY
	 *            The selected aim y-coordinate
	 * @param maxX
	 *            The maximal x-coordinates to move
	 * @param maxY
	 *            The maximal y-coordinates to move
	 * @param turnCounter
	 *            Number of turns the figure moved
	 * @param playerDatas
	 *            Required the PlayerDatas to check color
	 * @param figures
	 *            Require actual figures positions
	 */
	public void actualize(int currentX, int currentY, int toX, int toY,
			int maxX, int maxY, int turnCounter, PlayerDatas playerDatas,
			Figure[][] figures) {

		setCurrentX(currentX);
		setCurrentY(currentY);
		setToX(toX);
		setToY(toY);
		setMaxX(maxX);
		setMaxY(maxY);
		setTurnCounter(turnCounter);
		setPlayerDatas(playerDatas);
		setAllFigures(figures);

	}

	/**
	 * check() - Checks the figures move, returns true if he is valid
	 * 
	 * @return Returns true if the move of the knight is valid
	 */
	public boolean check() {

		// -------------------------------------------------------------
		// CHECKS COLOR OF THE FIGURE TO MOVE
		// -------------------------------------------------------------

		if (getPlayerDatas().getOnTurn().equals(
				getPlayerDatas().getNamePlayer1())) {

			if (getFigure(getCurrentX(), getCurrentY()).getColor() != getPlayerDatas()
					.getColorPlayerOnTurn()) {

				return false;

			}

		} else {

			if (getFigure(getCurrentX(), getCurrentY()).getColor() != getPlayerDatas()
					.getColorPlayerOnTurn()) {

				return false;

			}

		}

		// -------------------------------------------------------------
		// CHECK KNIGHT: |_
		// VARIANT 1:
		// X CHANGES FOR 2, Y CHANGES FOR 1
		// VARIANT 2:
		// X CHANGES FOR 1, Y CHANGES FOR 2
		// -------------------------------------------------------------

		Boolean checkValue = false;

		if (getToX() >= 0 && getToX() <= 7) {

			if (getToY() >= 0 && getToY() <= 7) {

				if (getFigure(getToX(), getToY()) == null
						|| getFigure(getToX(), getToY()).getColor() != getPlayerDatas()
								.getColorPlayerOnTurn()) {

					if ((getToX() == getCurrentX() + 2 && getToY() == getCurrentY() + 1)
							|| (getToX() == getCurrentX() + 2 && getToY() == getCurrentY() - 1)
							|| (getToX() == getCurrentX() - 2 && getToY() == getCurrentY() + 1)
							|| (getToX() == getCurrentX() - 2 && getToY() == getCurrentY() - 1)
							|| (getToX() == getCurrentX() + 1 && getToY() == getCurrentY() + 2)
							|| (getToX() == getCurrentX() + 1 && getToY() == getCurrentY() - 2)
							|| (getToX() == getCurrentX() - 1 && getToY() == getCurrentY() + 2)
							|| (getToX() == getCurrentX() - 1 && getToY() == getCurrentY() - 2)) {

						checkValue = true;

					}

				}

			}

		}

		return checkValue;

	}

}