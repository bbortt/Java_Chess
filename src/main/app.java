package main;

import gui.Settings;
import dataBase.PlayerDatas;

/**
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
	 * main() - Main method to call the first GUI
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