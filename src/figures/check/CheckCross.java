package figures.check;

import dataBase.PlayerDatas;
import figures.Figure;

/**
 * This class checks whether a cross move is valid or not.
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
	private PlayerDatas playerDatas;
	private Figure[][] figures;

	// -------------------------------------------------------------
	// REQUIRED GETTERS AND SETTERS
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

	/**
	 * Actualizes all variables with current game informations.
	 * 
	 * @param givenCurrentX
	 *            The current x-coordinate (which the player selected)
	 * @param givenCurrentY
	 *            The current y-coordinate (which the player selected)
	 * @param givenToX
	 *            The selected aim x-coordinate
	 * @param givenToY
	 *            The selected aim y-coordinate
	 * @param givenMaxX
	 *            The maximal x-coordinates to move
	 * @param givenMaxY
	 *            The maximal y-coordinates to move
	 * @param givenPlayerDatas
	 *            Requires the PlayerDatas to check colors
	 * @param givenFigures
	 *            Requires actual figures positions
	 */
	public void actualize(int givenCurrentX, int givenCurrentY, int givenToX,
			int givenToY, int givenMaxX, int givenMaxY,
			PlayerDatas givenPlayerDatas, Figure[][] givenFigures) {

		currentX = givenCurrentX;
		currentY = givenCurrentY;
		toX = givenToX;
		toY = givenToY;
		maxX = givenMaxX;
		maxY = givenMaxY;
		playerDatas = givenPlayerDatas;
		figures = givenFigures;

	}

	/**
	 * Checks the figures move, returns true if it is valid.
	 * 
	 * @return Returns true if the move across is valid
	 */
	public boolean check() {

		// -------------------------------------------------------------
		// CHECKS COLOR OF THE FIGURE TO MOVE
		// -------------------------------------------------------------

		if (playerDatas.getOnTurn().equals(playerDatas.getNamePlayer1())) {

			if (getFigure(currentX, currentY).getColor() != playerDatas
					.getColorPlayerOnTurn()) {

				return false;

			}

		} else {

			if (getFigure(currentX, currentY).getColor() != playerDatas
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

		int higherLowerX = toX - currentX;
		int higherLowerY = toY - currentY;

		// -------------------------------------------------------------
		// VALIDATION
		// -------------------------------------------------------------

		if (toX >= 0 && toX <= 7 && toY >= 0 && toY <= 7) {

			if (higherLowerX != 0 && higherLowerY != 0) {

				if (higherLowerX == higherLowerY
						|| higherLowerX * (-1) == higherLowerY) {

					// -------------------------------------------------------------
					// CHECKS FROM BOTTOM RIGHT TO TOP LEFT
					// -------------------------------------------------------------

					if (higherLowerX < 0 && higherLowerY < 0) {

						if (higherLowerX * (-1) > maxX
								&& higherLowerY * (-1) > maxY) {

							return false;

						}

						int i = -1;
						int j = -1;

						while (i >= higherLowerX && j >= higherLowerY) {

							if (getFigure(currentX + i, currentY + j) != null) {

								if (currentX + i == toX && currentY + j == toY) {

									if (getFigure(currentX + i, currentY + j)
											.getColor() != playerDatas
											.getColorPlayerOnTurn()) {

										checkvalue = true;

									}

								} else {

									return false;

								}

							} else {

								if (currentX + i == toX && currentY + j == toY) {

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

						if (higherLowerX * (-1) > maxX && higherLowerY > maxY) {

							return false;

						}

						int i = (-1);
						int j = 1;

						while (i >= higherLowerX && j <= higherLowerY) {

							if (getFigure(currentX + i, currentY + j) != null) {

								if (currentX + i == toX && currentY + j == toY) {

									if (getFigure(currentX + i, currentY + j)
											.getColor() != playerDatas
											.getColorPlayerOnTurn()) {

										checkvalue = true;

									}

								} else {

									return false;

								}

							} else {

								if (currentX + i == toX && currentY + j == toY) {

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

						if (higherLowerX > maxX && higherLowerY * (-1) > maxY) {

							return false;

						}

						int i = 1;
						int j = (-1);

						while (i <= higherLowerX && j >= higherLowerY) {

							if (getFigure(currentX + i, currentY + j) != null) {

								if (currentX + i == toX && currentY + j == toY) {

									if (getFigure(currentX + i, currentY + j)
											.getColor() != playerDatas
											.getColorPlayerOnTurn()) {

										checkvalue = true;

									}

								} else {

									return false;

								}

							} else {

								if (currentX + i == toX && currentY + j == toY) {

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

						if (higherLowerX > maxX && higherLowerY > maxY) {

							return false;

						}

						int i = 1;
						int j = 1;

						while (i <= higherLowerX && j <= higherLowerY) {

							if (getFigure(currentX + i, currentY + j) != null) {

								if (currentX + i == toX && currentY + j == toY) {

									if (getFigure(currentX + i, currentY + j)
											.getColor() != playerDatas
											.getColorPlayerOnTurn()) {

										checkvalue = true;

									}

								} else {

									return false;

								}

							} else {

								if (currentX + i == toX && currentY + j == toY) {

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