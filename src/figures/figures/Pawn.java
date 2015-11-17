package figures.figures;

import java.awt.Color;

import dataBase.PlayerDatas;
import figures.Figure;
import figures.check.CheckCross;
import figures.check.CheckVertical;

/**
 * 
 * @author Timon Borter
 * 
 */
public class Pawn extends Figure {

	// -------------------------------------------------------------
	// CONTENTS
	// -------------------------------------------------------------

	CheckVertical checkVertical;
	CheckCross checkCross;

	/**
	 * Pawn() - On creation sets color, range and turn counter
	 * 
	 * @param color
	 *            Requires the figures color
	 */
	public Pawn(Color color) {

		// -------------------------------------------------------------
		// FIGURES ATTRIBUTES
		// -------------------------------------------------------------

		setColor(color);
		setMaxX(1);
		setMaxY(2);
		setTurnCounter(0);

		// -------------------------------------------------------------
		// FIGURES VALIDATIONS
		// -------------------------------------------------------------

		checkVertical = new CheckVertical();
		checkCross = new CheckCross();

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
	@Override
	public Boolean move(int currentX, int currentY, int toX, int toY,
			PlayerDatas playerDatas, Figure[][] figures) throws Exception {

		Boolean returnValue = false;

		if (getTurnCounter() > 0) {

			setMaxY(1);

		}

		checkVertical.actualize(currentX, currentY, toX, toY, getMaxX(),
				getMaxY(), getTurnCounter(), playerDatas, figures);
		checkCross.actualize(currentX, currentY, toX, toY, getMaxX(),
				getMaxY(), getTurnCounter(), playerDatas, figures);

		try {

			if (currentX == toX) {

				if (figures[toX][toY] == null) {

					returnValue = checkVertical.check();

				}

			}

			if (currentX != toX) {

				if (figures[toX][toY] != null) {

					returnValue = checkCross.check();

				}

			}

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}

		return returnValue;

	}

	/**
	 * kill() - Called if the figure gets slain
	 * 
	 * @param nameOfPlayer
	 *            Required for JOptionPane.showMessageDialog()
	 */
	@Override
	public void kill(String nameOfPlayer) {

		// DOING NOTHING

	}

}