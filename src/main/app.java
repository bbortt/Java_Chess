package main;

import gui.Settings;
import dataBase.PlayerDatas;

/**
 * This is the very first class with the main(String[] args) method. It starts
 * an instance of Settings.java.
 * 
 * @author Timon Borter
 * @version 2.0
 * 
 */
public class app {

	// -------------------------------------------------------------
	// CONTENTS
	// -------------------------------------------------------------

	static Settings settings;

	// -------------------------------------------------------------
	// VARIABLES
	// -------------------------------------------------------------

	static PlayerDatas playerDatas = new PlayerDatas();

	/**
	 * Main method to call the first GUI.
	 * 
	 * @param args
	 *            Default parameters
	 */
	public static void main(String[] args) {

		// -------------------------------------------------------------

		settings = new Settings(playerDatas);

		// -------------------------------------------------------------

	}

}