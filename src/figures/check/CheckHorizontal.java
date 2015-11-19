package figures.check;

import dataBase.PlayerDatas;
import figures.Figure;

/**
 * This class checks whether a horizontal move is valid or not.
 * 
 * @author Timon Borter
 * 
 */
public class CheckHorizontal {

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
	 * Actualizes all variables with current game informations.
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
	 * Checks the figures move, returns true if it is valid.
	 * 
	 * @return Returns true if the move horizontal is valid
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
		// HORIZONTAL: -
		// Y DOES NOT CHANGE
		// ONLY X CHANGES
		// AIM HAS TO BE IN RANGE
		// -------------------------------------------------------------

		boolean checkValue = false;

		int higherLower = getToX() - getCurrentX();

		// -------------------------------------------------------------
		// CHECKS RANGE
		// -------------------------------------------------------------

		if (higherLower < 0) {

			if (higherLower * (-1) > getMaxY()) {

				return false;

			}

		} else {

			if (higherLower > getMaxY()) {

				return false;

			}

		}

		// -------------------------------------------------------------
		// REST OF VALIDATION
		// -------------------------------------------------------------

		if (getCurrentY() == getToY()) {

			if (getToX() >= 0 && getToX() <= 7) {

				if (higherLower != 0) {

					if (higherLower > 0) {

						for (int i = 1; i <= higherLower; i = i + 1) {

							if (getCurrentX() + i != getToX()) {

								if (getFigure(getCurrentX() + i, getCurrentY()) == null) {

									if (getFigure(getToX(), getToY()) == null
											|| getFigure(getToX(), getToY())
													.getColor() != getPlayerDatas()
													.getColorPlayerOnTurn()) {

										checkValue = true;

									} else {

										return false;

									}

								} else {

									return false;

								}

							} else {

								if (getFigure(getToX(), getToY()) == null
										|| getFigure(getToX(), getToY())
												.getColor() != getPlayerDatas()
												.getColorPlayerOnTurn()) {

									checkValue = true;

								} else {

									return false;

								}

							}

						}

					} else {

						for (int i = (-1); i >= higherLower; i = i - 1) {

							if (getCurrentX() + i != getToX()) {

								if (getFigure(getCurrentX() + i, getCurrentY()) == null) {

									if (getFigure(getToX(), getToY()) == null
											|| getFigure(getToX(), getToY())
													.getColor() != getPlayerDatas()
													.getColorPlayerOnTurn()) {

										checkValue = true;

									} else {

										return false;

									}

								} else {

									return false;

								}

							} else {

								if (getFigure(getToX(), getToY()) == null
										|| getFigure(getToX(), getToY())
												.getColor() != getPlayerDatas()
												.getColorPlayerOnTurn()) {

									checkValue = true;

								} else {

									return false;

								}

							}

						}

					}

				}

			}

		}

		return checkValue;

	}

}