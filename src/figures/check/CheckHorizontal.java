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
	 * @param givenPlayerDatas
	 *            Requires the PlayerDatas to check color
	 * @param givenFigures
	 *            Requires actual figures positions
	 */
	public void actualize(int givenCurrentX, int givenCurrentY, int givenToX,
			int givenToY, int givenMaxX, PlayerDatas givenPlayerDatas,
			Figure[][] givenFigures) {

		currentX = givenCurrentX;
		currentY = givenCurrentY;
		toX = givenToX;
		toY = givenToY;
		maxX = givenMaxX;
		playerDatas = givenPlayerDatas;
		figures = givenFigures;

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
		// HORIZONTAL: -
		// Y DOES NOT CHANGE
		// ONLY X CHANGES
		// AIM HAS TO BE IN RANGE
		// -------------------------------------------------------------

		boolean checkValue = false;

		int higherLower = toX - currentX;

		// -------------------------------------------------------------
		// CHECKS RANGE
		// -------------------------------------------------------------

		if (higherLower < 0) {

			if (higherLower * (-1) > maxX) {

				return false;

			}

		} else {

			if (higherLower > maxX) {

				return false;

			}

		}

		// -------------------------------------------------------------
		// REST OF VALIDATION
		// -------------------------------------------------------------

		if (currentY == toY) {

			if (toX >= 0 && toX <= 7) {

				if (higherLower != 0) {

					if (higherLower > 0) {

						for (int i = 1; i <= higherLower; i = i + 1) {

							if (currentX + i != toX) {

								if (getFigure(currentX + i, currentY) == null) {

									if (getFigure(toX, toY) == null
											|| getFigure(toX, toY).getColor() != playerDatas
													.getColorPlayerOnTurn()) {

										checkValue = true;

									} else {

										return false;

									}

								} else {

									return false;

								}

							} else {

								if (getFigure(toX, toY) == null
										|| getFigure(toX, toY).getColor() != playerDatas
												.getColorPlayerOnTurn()) {

									checkValue = true;

								} else {

									return false;

								}

							}

						}

					} else {

						for (int i = (-1); i >= higherLower; i = i - 1) {

							if (currentX + i != toX) {

								if (getFigure(currentX + i, currentY) == null) {

									if (getFigure(toX, toY) == null
											|| getFigure(toX, toY).getColor() != playerDatas
													.getColorPlayerOnTurn()) {

										checkValue = true;

									} else {

										return false;

									}

								} else {

									return false;

								}

							} else {

								if (getFigure(toX, toY) == null
										|| getFigure(toX, toY).getColor() != playerDatas
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