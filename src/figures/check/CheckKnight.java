package figures.check;

import dataBase.PlayerDatas;
import figures.Figure;

/**
 * This class checks whether a knights move is valid or not.
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
	 * @param givenPlayerDatas
	 *            Requires the PlayerDatas to check color
	 * @param givenFigures
	 *            Requires actual figures positions
	 */
	public void actualize(int givenCurrentX, int givenCurrentY, int givenToX,
			int givenToY, PlayerDatas givenPlayerDatas, Figure[][] givenFigures) {

		currentX = givenCurrentX;
		currentY = givenCurrentY;
		toX = givenToX;
		toY = givenToY;
		playerDatas = givenPlayerDatas;
		figures = givenFigures;

	}

	/**
	 * Checks the figures move, returns true if it is valid.
	 * 
	 * @return Returns true if the move of the knight is valid
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
		// CHECK KNIGHT: |_
		// VARIANT 1:
		// X CHANGES FOR 2, Y CHANGES FOR 1
		// VARIANT 2:
		// X CHANGES FOR 1, Y CHANGES FOR 2
		// -------------------------------------------------------------

		Boolean checkValue = false;

		if (toX >= 0 && toX <= 7) {

			if (toY >= 0 && toY <= 7) {

				if (getFigure(toX, toY) == null
						|| getFigure(toX, toY).getColor() != playerDatas
								.getColorPlayerOnTurn()) {

					if ((toX == currentX + 2 && toY == currentY + 1)
							|| (toX == currentX + 2 && toY == currentY - 1)
							|| (toX == currentX - 2 && toY == currentY + 1)
							|| (toX == currentX - 2 && toY == currentY - 1)
							|| (toX == currentX + 1 && toY == currentY + 2)
							|| (toX == currentX + 1 && toY == currentY - 2)
							|| (toX == currentX - 1 && toY == currentY + 2)
							|| (toX == currentX - 1 && toY == currentY - 2)) {

						checkValue = true;

					}

				}

			}

		}

		return checkValue;

	}

}