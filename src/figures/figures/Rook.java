package figures.figures;

import java.awt.Color;

import dataBase.PlayerDatas;
import figures.Figure;
import figures.check.CheckHorizontal;
import figures.check.CheckVertical;

/**
 * This is a rook. Handles ever move of its self. Also implements kill() method.
 * 
 * @author Timon Borter
 * 
 */
public class Rook extends Figure {

	// -------------------------------------------------------------
	// CONTENTS
	// -------------------------------------------------------------

	CheckHorizontal checkHorizontal;
	CheckVertical checkVertical;

	/**
	 * On creation sets color, range and turn counter.
	 * 
	 * @param color
	 *            Requires the figures color
	 */
	public Rook(Color color) {

		// -------------------------------------------------------------
		// FIGURES ATTRIBUTES
		// -------------------------------------------------------------

		setColor(color);
		setMaxX(8);
		setMaxY(8);
		setTurnCounter(0);

		// -------------------------------------------------------------
		// FIGURES VALIDAITONS
		// -------------------------------------------------------------

		checkHorizontal = new CheckHorizontal();
		checkVertical = new CheckVertical();

	}

	/**
	 * Actualizes data with current game informations, then checks if the step
	 * is valid.
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

		checkHorizontal.actualize(currentX, currentY, toX, toY, getMaxX(),
				getMaxY(), getTurnCounter(), playerDatas, figures);
		checkVertical.actualize(currentX, currentY, toX, toY, getMaxX(),
				getMaxY(), getTurnCounter(), playerDatas, figures);

		boolean returnValue = false;

		try {

			returnValue = checkVertical.check() || checkHorizontal.check();

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
	public void kill(String nameOfPlayer) {

		// DO NOTHING

	}

}