package figures.figures;

import java.awt.Color;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import dataBase.PlayerDatas;
import figures.Figure;
import figures.check.CheckCross;
import figures.check.CheckHorizontal;
import figures.check.CheckVertical;
import gui.ChessBoard;

/**
 * 
 * @author Timon Borter
 * 
 */
public class King extends Figure {

	// -------------------------------------------------------------
	// CONTENTS
	// -------------------------------------------------------------

	CheckCross checkCross;
	CheckHorizontal checkHorizontal;
	CheckVertical checkVertical;

	/**
	 * King() - On creation sets color, range and turn counter
	 * 
	 * @param color
	 *            Requires the figures color
	 */
	public King(Color color) {

		// -------------------------------------------------------------
		// FIGURES ATTRIBUTES
		// -------------------------------------------------------------

		setColor(color);
		setMaxX(1);
		setMaxY(1);
		setTurnCounter(0);

		// -------------------------------------------------------------
		// FIGURES VALIDATIONS
		// -------------------------------------------------------------

		checkCross = new CheckCross();
		checkHorizontal = new CheckHorizontal();
		checkVertical = new CheckVertical();

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

		checkCross.actualize(currentX, currentY, toX, toY, getMaxX(),
				getMaxY(), getTurnCounter(), playerDatas, figures);
		checkHorizontal.actualize(currentX, currentY, toX, toY, getMaxX(),
				getMaxY(), getTurnCounter(), playerDatas, figures);
		checkVertical.actualize(currentX, currentY, toX, toY, getMaxX(),
				getMaxY(), getTurnCounter(), playerDatas, figures);

		boolean returnValue = false;

		try {

			returnValue = checkCross.check() || checkHorizontal.check()
					|| checkVertical.check();

		} catch (Exception e) {

			throw new Exception(e.getMessage());

		}

		return returnValue;

	}

	/**
	 * kill() - Called if the figure gets slain
	 * 
	 * @param nameOfPlayer
	 *            Required for JOptionPane.showMessageDialog
	 */
	public void kill(String nameOfPlayer) {

		JOptionPane.showMessageDialog(null, nameOfPlayer + " lost!", "Lost",
				JOptionPane.INFORMATION_MESSAGE);

		ChessBoard.getMainFrame().dispatchEvent(
				new WindowEvent(ChessBoard.getMainFrame(),
						WindowEvent.WINDOW_CLOSING));

	}

}