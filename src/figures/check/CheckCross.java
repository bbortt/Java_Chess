package figures.check;

import dataBase.PlayerDatas;
import figures.Figure;

/**
 * 
 * @author Timon Borter
 * 
 */
public class CheckCross {

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
	 * @return Returns true if the move across is valid
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
		// CROSS: / OR \
		// X MUSST CHANGE IN RELATION WITH Y
		// RELATION 1 : 1
		// AIM HAS TO BE IN RANGE
		// -------------------------------------------------------------

		boolean checkvalue = false;

		int higherLowerX = getToX() - getCurrentX();
		int higherLowerY = getToY() - getCurrentY();

		// -------------------------------------------------------------
		// VALIDATION
		// -------------------------------------------------------------

		if (getToX() >= 0 && getToX() <= 7 && getToY() >= 0 && getToY() <= 7) {

			if (higherLowerX != 0 && higherLowerY != 0) {

				if (higherLowerX == higherLowerY
						|| higherLowerX * (-1) == higherLowerY) {

					// -------------------------------------------------------------
					// CHECKS FROM BOTTOM RIGHT TO TOP LEFT
					// -------------------------------------------------------------

					if (higherLowerX < 0 && higherLowerY < 0) {

						if (higherLowerX * (-1) > getMaxX()
								&& higherLowerY * (-1) > getMaxY()) {

							return false;

						}

						int i = -1;
						int j = -1;

						while (i >= higherLowerX && j >= higherLowerY) {

							if (getFigure(getCurrentX() + i, getCurrentY() + j) != null) {

								if (getCurrentX() + i == getToX()
										&& getCurrentY() + j == getToY()) {

									if (getFigure(getCurrentX() + i,
											getCurrentY() + j).getColor() != getPlayerDatas()
											.getColorPlayerOnTurn()) {

										checkvalue = true;

									}

								} else {

									return false;

								}

							} else {

								if (getCurrentX() + i == getToX()
										&& getCurrentY() + j == getToY()) {

									checkvalue = true;

								}

							}

							i = i - 1;
							j = j - 1;

						}

						// -------------------------------------------------------------
						// CHECK FROM BOTTOM LEFT TO TOP RIGHT
						// -------------------------------------------------------------

					} else if (higherLowerX < 0 && higherLowerY > 0) {

						if (higherLowerX * (-1) > getMaxX()
								&& higherLowerY > getMaxY()) {

							return false;

						}

						int i = (-1);
						int j = 1;

						while (i >= higherLowerX && j <= higherLowerY) {

							if (getFigure(getCurrentX() + i, getCurrentY() + j) != null) {

								if (getCurrentX() + i == getToX()
										&& getCurrentY() + j == getToY()) {

									if (getFigure(getCurrentX() + i,
											getCurrentY() + j).getColor() != getPlayerDatas()
											.getColorPlayerOnTurn()) {

										checkvalue = true;

									}

								} else {

									return false;

								}

							} else {

								if (getCurrentX() + i == getToX()
										&& getCurrentY() + j == getToY()) {

									checkvalue = true;

								}

							}

							i = i - 1;
							j = j + 1;

						}

						// -------------------------------------------------------------
						// CHECKS FROM TOP RIGHT TO BOTTOM LEFT
						// -------------------------------------------------------------

					} else if (higherLowerX > 0 && higherLowerY < 0) {

						if (higherLowerX > getMaxX()
								&& higherLowerY * (-1) > getMaxY()) {

							return false;

						}

						int i = 1;
						int j = (-1);

						while (i <= higherLowerX && j >= higherLowerY) {

							if (getFigure(getCurrentX() + i, getCurrentY() + j) != null) {

								if (getCurrentX() + i == getToX()
										&& getCurrentY() + j == getToY()) {

									if (getFigure(getCurrentX() + i,
											getCurrentY() + j).getColor() != getPlayerDatas()
											.getColorPlayerOnTurn()) {

										checkvalue = true;

									}

								} else {

									return false;

								}

							} else {

								if (getCurrentX() + i == getToX()
										&& getCurrentY() + j == getToY()) {

									checkvalue = true;

								}

							}

							i = i + 1;
							j = j - 1;

						}

						// -------------------------------------------------------------
						// CHECKS FROM TOP LEFT TO BOTTOM RIGHT
						// -------------------------------------------------------------

					} else if (higherLowerX > 0 && higherLowerY > 0) {

						if (higherLowerX > getMaxX()
								&& higherLowerY > getMaxY()) {

							return false;

						}

						int i = 1;
						int j = 1;

						while (i <= higherLowerX && j <= higherLowerY) {

							if (getFigure(getCurrentX() + i, getCurrentY() + j) != null) {

								if (getCurrentX() + i == getToX()
										&& getCurrentY() + j == getToY()) {

									if (getFigure(getCurrentX() + i,
											getCurrentY() + j).getColor() != getPlayerDatas()
											.getColorPlayerOnTurn()) {

										checkvalue = true;

									}

								} else {

									return false;

								}

							} else {

								if (getCurrentX() + i == getToX()
										&& getCurrentY() + j == getToY()) {

									checkvalue = true;

								}

							}

							i = i + 1;
							j = j + 1;

						}

					}

					// -------------------------------------------------------------
					// END OF VALIDATION
					// -------------------------------------------------------------

				}

			}

		}

		return checkvalue;

	}

}