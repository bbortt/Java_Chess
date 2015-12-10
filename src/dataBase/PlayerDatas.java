package dataBase;

import java.awt.Color;

/**
 * This class handles all data like names, color and time for each player. If
 * any other class wants to know something about the players, it has to call a
 * getter of this class.
 * 
 * ATTENTION: THIS IS A GETTER-AND-SETTER-ONLY CLASS! THE METHODS ARE NOT COMMENTED FURTHER!
 * 
 * @author Timon Borter
 * 
 */
public class PlayerDatas {

	// -------------------------------------------------------------
	// VARIABLES
	// -------------------------------------------------------------

	private String namePlayer1;
	private String namePlayer2;
	private String onTurn;
	private Color colorPlayerOnTurn;
	private int timePlayer1;
	private int timePlayer2;
	private int[] fromSelectedJButton;
	private int[] toSelectedJButton;

	// -------------------------------------------------------------
	// PUBLIC GETTERS AND SETTERS
	// -------------------------------------------------------------

	public String getNamePlayer1() {

		return namePlayer1;

	}

	public void setNamePlayer1(String namePlayer1) {

		this.namePlayer1 = namePlayer1;

	}

	public String getNamePlayer2() {

		return namePlayer2;

	}

	public void setNamePlayer2(String namePlayer2) {

		this.namePlayer2 = namePlayer2;

	}

	public String getOnTurn() {

		return onTurn;

	}

	public void setOnTurn(String onTurn) {

		this.onTurn = onTurn;

	}

	public Color getColorPlayerOnTurn() {

		return this.colorPlayerOnTurn;

	}

	public void setColorPlayerOnTurn(Color colorPlayerOnTurn) {

		this.colorPlayerOnTurn = colorPlayerOnTurn;

	}

	public int getTimePlayer1() {

		return timePlayer1;

	}

	public void setTimePlayer1(int timePlayer1) {

		this.timePlayer1 = timePlayer1;

	}

	public int getTimePlayer2() {

		return timePlayer2;

	}

	public void setTimePlayer2(int timePlayer2) {

		this.timePlayer2 = timePlayer2;

	}

	public int[] getFromSelectedJButton() {

		return this.fromSelectedJButton;

	}

	public void setFromSelectedJButton(String toArray) {

		int[] intArray = { Integer.parseInt(toArray.split("\\.")[0]) - 1,
				Integer.parseInt(toArray.split("\\.")[1]) - 1 };
		this.fromSelectedJButton = intArray;

	}

	public int[] getToSelectedJButton() {

		return this.toSelectedJButton;

	}

	public void setToSelectedJButton(String toArray) {

		int[] intArray = { Integer.parseInt(toArray.split("\\.")[0]) - 1,
				Integer.parseInt(toArray.split("\\.")[1]) - 1 };
		this.toSelectedJButton = intArray;

	}

}