package figures.figures;

import java.awt.Color;

import dataBase.PlayerDatas;
import figures.Figure;
import figures.check.CheckKnight;

/**
 * 
 * @author Timon Borter
 * 
 */
public class Knight extends Figure {

	// -------------------------------------------------------------
	// CONTENTS
	// -------------------------------------------------------------

	CheckKnight checkKnight;

	/**
	 * Knight() - On creation sets color, range and turn counter
	 * 
	 * @param color
	 *            Requires the figures color
	 */
	public Knight(Color color) {

		// -------------------------------------------------------------
		// FIGURES ATTRIBUTES
		// -------------------------------------------------------------

		setColor(color);
		setMaxX(0);
		setMaxY(0);
		setTurnCounter(0);

		// -------------------------------------------------------------
		// FIGURES VALIDATION
		// -------------------------------------------------------------

		checkKnight = new CheckKnight();

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

		checkKnight.actualize(currentX, currentY, toX, toY, getMaxX(),
				getMaxY(), getTurnCounter(), playerDatas, figures);

		boolean returnValue = false;

		try {

			returnValue = checkKnight.check();

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