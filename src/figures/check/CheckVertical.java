package figures.check;

import dataBase.PlayerDatas;
import figures.Figure;

/**
 * This class checks whether a vertical move is valid or not.
 * 
 * @author Timon Borter
 * 
 */
public class CheckVertical {

	// -------------------------------------------------------------
	// VARIABLES
	// -------------------------------------------------------------

	private int currentX;
	private int currentY;
	private int toX;
	private int toY;
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
	 * @param givenMaxY
	 *            The maximal y-coordinates to move
	 * @param givenPlayerDatas
	 *            Requires the PlayerDatas to check color
	 * @param givenFigures
	 *            Requires actual figures positions
	 */
	public void actualize(int givenCurrentX, int givenCurrentY, int givenToX,
			int givenToY, int givenMaxY, PlayerDatas givenPlayerDatas,
			Figure[][] givenFigures) {

		currentX = givenCurrentX;
		currentY = givenCurrentY;
		toX = givenToX;
		toY = givenToY;
		maxY = givenMaxY;
		playerDatas = givenPlayerDatas;
		figures = givenFigures;

	}

	/**
	 * Checks the figures move, returns true if it is valid.
	 * 
	 * @return Returns true if the move vertical is valid
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
		// VERTICAL: |
		// X DOES NOT CHANGE
		// ONLY Y CHANGES
		// AIM HAS TO BE IN RANGE
		// -------------------------------------------------------------

		boolean checkValue = false;

		int higherLower = toY - currentY;

		// -------------------------------------------------------------
		// CHECKS RANGE
		// -------------------------------------------------------------

		if (higherLower < 0) {

			if (higherLower * (-1) > maxY) {

				return false;

			}

		} else {

			if (higherLower > maxY) {

				return false;

			}

		}

		// -------------------------------------------------------------
		// REST OF VALIDATION
		// -------------------------------------------------------------

		if (currentX == toX) {

			if (toY >= 0 && toY <= 7) {

				if (higherLower != 0) {

					if (higherLower > 0) {

						for (int i = 1; i <= higherLower; i = i + 1) {

							if (currentY + i != toY) {

								if (getFigure(currentX, currentY + i) == null) {

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

							if (currentY + i != toY) {

								if (getFigure(currentX, currentY + i) == null) {

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