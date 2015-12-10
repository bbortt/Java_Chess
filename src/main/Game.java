package main;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.border.LineBorder;

import dataBase.PlayerDatas;
import dataBase.Timer;
import figures.Figure;
import figures.check.KingCheck;
import figures.figures.Bishop;
import figures.figures.King;
import figures.figures.Knight;
import figures.figures.Pawn;
import figures.figures.Queen;
import figures.figures.Rook;

/**
 * This class is the computer behind the game. It waits for user inputs, next
 * checks the move and does it if it is valid. But if not, it also undoes the
 * turn and asks the user to input a new move. Castlings, default set ups and
 * drawing figures are implemented here too.
 * 
 * @author Timon Borter
 * 
 */
public class Game {

	// -------------------------------------------------------------
	// CONTENTS
	// -------------------------------------------------------------

	Timer timer;

	// -------------------------------------------------------------
	// VARIABLES
	// -------------------------------------------------------------

	private PlayerDatas playerDatas;
	private JLabel labelNamePlayer1;
	private JLabel labelTimeTillEnd;
	private JLabel labelNamePlayer2;
	private Figure copiedFigure;

	private JButton[][] fields;
	private Figure[][] figures;

	private KingCheck kingCheck;

	private JFrame mainFrame;

	// -------------------------------------------------------------
	// PUBLIC GETTERS AND SETTERS
	// -------------------------------------------------------------

	/**
	 * Required to get a specific JButton
	 * 
	 * @param x
	 *            The specific x-coordinate
	 * @param y
	 *            The specific y-coordinate
	 * @return The JButton at the specified coordinates
	 */
	public JButton getField(int x, int y) {

		return this.fields[x][y];

	}

	/**
	 * Required to set a specific JButton
	 * 
	 * @param x
	 *            The specific x-coordinate
	 * @param y
	 *            The specific y-coordinate
	 * @param field
	 *            The specific JButton
	 */
	public void setField(int x, int y, JButton field) {

		this.fields[x][y] = field;

	}

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
	 * Starts a new game, this is not the GUI.
	 * 
	 * @param givenPlayerDatas
	 *            Requires PlayerDatas to check the steps
	 * @param givenFields
	 *            Requires fields to actualize the GUI in ChessBoard.java
	 * @param givenFigures
	 *            Requires figures to handle moves
	 * @param givenLabelNamePlayer1
	 *            Requires label to show or hide
	 * @param givenLabelTimeTillEnd
	 *            Requires label to create the timer (Timer.java)
	 * @param givenLabelNamePlayer2
	 *            Requires label to show or hide
	 */
	public Game(PlayerDatas givenPlayerDatas, JButton[][] givenFields,
			Figure[][] givenFigures, JLabel givenLabelNamePlayer1,
			JLabel givenLabelTimeTillEnd, JLabel givenLabelNamePlayer2,
			JFrame givenMainFrame) {

		// -------------------------------------------------------------
		// SYNCHRONIZES LOCAL VARIABLES WITH ATTRIBUTES
		// -------------------------------------------------------------

		playerDatas = givenPlayerDatas;
		fields = givenFields;
		figures = givenFigures;
		labelNamePlayer1 = givenLabelNamePlayer1;
		labelTimeTillEnd = givenLabelTimeTillEnd;
		labelNamePlayer2 = givenLabelNamePlayer2;
		kingCheck = new KingCheck();
		mainFrame = givenMainFrame;

	}

	/**
	 * Starts a timer for both players in an asynchronous task.
	 */
	public void theTimer() {

		timer = new Timer(playerDatas, labelTimeTillEnd, mainFrame);

	}

	/**
	 * This is a complete turn of a player - once everything was set up, the
	 * whole game runs in this one function.
	 */
	@SuppressWarnings("deprecation")
	public void aTurn() {

		while (true) {

			// -------------------------------------------------------------
			// REMOVES RED BORDERS
			// REDRAWS figureS
			// REMOVES SELECTED FIELDS
			// -------------------------------------------------------------

			clearBoard();
			resetPlayerDatas();

			// -------------------------------------------------------------
			// WHO'S TURN IS IT? SHOWS NAME OF THIS PLAYER
			// -------------------------------------------------------------

			if (playerDatas.getOnTurn().equals(playerDatas.getNamePlayer1())) {

				labelNamePlayer1.show();
				labelNamePlayer2.hide();

			} else {

				labelNamePlayer2.show();
				labelNamePlayer1.hide();

			}

			// -------------------------------------------------------------
			// WAITS TILL PLAYER HAS BOTH BUTTONS CHOOSEN
			// -------------------------------------------------------------

			while ((playerDatas.getFromSelectedJButton()[0] == 10 || playerDatas
					.getToSelectedJButton()[0] == 10)
					&& (playerDatas.getFromSelectedJButton()[0] != 20 && playerDatas
							.getToSelectedJButton()[0] != 20)) {

				try {

					Thread.sleep(100);

				} catch (InterruptedException e) {

					System.err.println(e.getMessage());

				}

			}

			// -------------------------------------------------------------
			// DOES A NORMAL TURN IF NO CASTLING WAS DONE
			// -------------------------------------------------------------

			if (playerDatas.getFromSelectedJButton()[0] != 20
					&& playerDatas.getToSelectedJButton()[0] != 20) {

				// -------------------------------------------------------------
				// CHECKS FOR CORRECT MOVE
				// - CHECKS IF figure IS CHOOSEN
				// - CALLS THE MOVE METHOD (RETURNS TRUE IF MOVE IS OK)
				// - CHECKS IF SOMEONES KING IS CHECKED
				// -------------------------------------------------------------

				if (getFigure(playerDatas.getFromSelectedJButton()[0],
						playerDatas.getFromSelectedJButton()[1]) != null) {

					try {

						if (getFigure(playerDatas.getFromSelectedJButton()[0],
								playerDatas.getFromSelectedJButton()[1]).move(
								playerDatas.getFromSelectedJButton()[0],
								playerDatas.getFromSelectedJButton()[1],
								playerDatas.getToSelectedJButton()[0],
								playerDatas.getToSelectedJButton()[1],
								playerDatas, figures)) {

							// -------------------------------------------------------------
							// AMOUNTS THE TURN COUNTER
							// -------------------------------------------------------------

							getFigure(playerDatas.getFromSelectedJButton()[0],
									playerDatas.getFromSelectedJButton()[1])
									.setTurnCounter(
											getFigure(
													playerDatas
															.getFromSelectedJButton()[0],
													playerDatas
															.getFromSelectedJButton()[1])
													.getTurnCounter() + 1);

							if (getFigure(
									playerDatas.getToSelectedJButton()[0],
									playerDatas.getToSelectedJButton()[1]) != null) {

								// -------------------------------------------------------------
								// CALLS THE KILL METHOD
								// -------------------------------------------------------------

								getFigure(
										playerDatas.getToSelectedJButton()[0],
										playerDatas.getToSelectedJButton()[1])
										.kill(playerDatas.getOnTurn(),
												mainFrame);

								// -------------------------------------------------------------
								// SAVES THE KILLED figure
								// -------------------------------------------------------------

								copiedFigure = figures[playerDatas
										.getToSelectedJButton()[0]][playerDatas
										.getToSelectedJButton()[1]];

							}

							// -------------------------------------------------------------
							// UPDATES BOARD
							// -------------------------------------------------------------

							setFigure(
									playerDatas.getToSelectedJButton()[0],
									playerDatas.getToSelectedJButton()[1],
									getFigure(
											playerDatas
													.getFromSelectedJButton()[0],
											playerDatas
													.getFromSelectedJButton()[1]));

							setFigure(playerDatas.getFromSelectedJButton()[0],
									playerDatas.getFromSelectedJButton()[1],
									null);

							getField(playerDatas.getFromSelectedJButton()[0],
									playerDatas.getFromSelectedJButton()[1])
									.setText(null);

							// -------------------------------------------------------------
							// DRAWS THE BOARD CAUS IT LOOKS MUCH BETTER
							// -------------------------------------------------------------

							clearBoard();

							// -------------------------------------------------------------
							// UNDOES THE TURN IF KING IS CHECKED
							// -------------------------------------------------------------

							if (kingCheck
									.check(playerDatas.getColorPlayerOnTurn(),
											figures)) {

								// -------------------------------------------------------------
								// INFORMS THE PLAYER
								// -------------------------------------------------------------

								JOptionPane.showMessageDialog(mainFrame,
										"Incorrect move!\nChecked!",
										"Incorrect move",
										JOptionPane.INFORMATION_MESSAGE);

								// -------------------------------------------------------------
								// UNDOES TURN
								// -------------------------------------------------------------

								setFigure(
										playerDatas.getFromSelectedJButton()[0],
										playerDatas.getFromSelectedJButton()[1],
										getFigure(
												playerDatas
														.getToSelectedJButton()[0],
												playerDatas
														.getToSelectedJButton()[1]));

								if (copiedFigure != null) {

									setFigure(
											playerDatas.getToSelectedJButton()[0],
											playerDatas.getToSelectedJButton()[1],
											copiedFigure);

								} else {

									setFigure(
											playerDatas.getToSelectedJButton()[0],
											playerDatas.getToSelectedJButton()[1],
											null);

									getField(
											playerDatas.getToSelectedJButton()[0],
											playerDatas.getToSelectedJButton()[1])
											.setText(null);

								}

								continue;

							}

						} else {

							// -------------------------------------------------------------
							// IF MOVE IS NOT CORRECT
							// -------------------------------------------------------------

							JOptionPane.showMessageDialog(mainFrame,
									"Incorrect move!", "Incorrect move",
									JOptionPane.INFORMATION_MESSAGE);
							continue;

						}

					} catch (Exception e) {

						JOptionPane.showMessageDialog(mainFrame,
								"Whoops, an error occured!\n" + e.getMessage()
										+ "\nThe program will be closed.",
								"Fatal Error", JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);

					}

				} else {

					// -------------------------------------------------------------
					// IF NO figure IS CHOOSEN
					// -------------------------------------------------------------

					JOptionPane.showMessageDialog(mainFrame,
							"No figure choosen!", "Incorrect move",
							JOptionPane.INFORMATION_MESSAGE);
					continue;

				}

			}

			// -------------------------------------------------------------
			// INFORMS THE OTHER PLAYER IF HE IS CHECKED NOW
			// -------------------------------------------------------------

			if (playerDatas.getOnTurn().equals(playerDatas.getNamePlayer1())
					&& kingCheck.check(Color.BLACK, figures)) {

				JOptionPane.showMessageDialog(mainFrame,
						playerDatas.getNamePlayer2() + " checked!", "Check",
						JOptionPane.INFORMATION_MESSAGE);

			} else if (playerDatas.getOnTurn().equals(
					playerDatas.getNamePlayer2())
					&& kingCheck.check(Color.WHITE, figures)) {

				JOptionPane.showMessageDialog(mainFrame,
						playerDatas.getNamePlayer1() + " checked!", "Check",
						JOptionPane.INFORMATION_MESSAGE);

			}

			// -------------------------------------------------------------
			// SETS CURRENT PLAYER TO SECOND POSITION IN QUEUE
			// UPDATES COLOR OF CURRENT PLAYER
			// -------------------------------------------------------------

			if (playerDatas.getOnTurn().equals(playerDatas.getNamePlayer1())) {

				playerDatas.setOnTurn(playerDatas.getNamePlayer2());

				playerDatas.setColorPlayerOnTurn(Color.BLACK);

			} else {

				playerDatas.setOnTurn(playerDatas.getNamePlayer1());

				playerDatas.setColorPlayerOnTurn(Color.WHITE);

			}

		}

	}

	/**
	 * Redraws every displayed field, redraws the figures and configures
	 * borders.
	 */
	public void clearBoard() {

		for (int y = 0; y < 8; y = y + 1) {

			for (int x = 0; x < 8; x = x + 1) {

				// -------------------------------------------------------------
				// DELETES RED BORDER (ALL BORDERS)
				// -------------------------------------------------------------

				getField(x, y).setBorder(null);

				// -------------------------------------------------------------
				// SETS UP BACKGROUND COLOR
				// -------------------------------------------------------------

				if (x % 2 == 0) {

					if (y % 2 == 0) {

						getField(x, y).setBackground(Color.BLACK);

					} else {

						getField(x, y).setBackground(Color.WHITE);

					}

				} else {

					if (y % 2 == 0) {

						getField(x, y).setBackground(Color.WHITE);

					} else {

						getField(x, y).setBackground(Color.BLACK);

					}

				}

				// -------------------------------------------------------------
				// REDRAWS THE FIELD WITH figure AND BORDER
				// -------------------------------------------------------------

				if (getFigure(x, y) != null) {

					drawfigure(x, y, getFigure(x, y));

				}

			}

		}

	}

	/**
	 * Does not reset PlayerDatas but chosen buttons (from and to).
	 */
	public void resetPlayerDatas() {

		playerDatas.setFromSelectedJButton("11.11");
		playerDatas.setToSelectedJButton("11.11");
		copiedFigure = null;

	}

	/**
	 * Sets the figures to their default location.
	 */
	public void defaultSetUp() {

		// -------------------------------------------------------------
		// PUTS KINGS ON BOARD
		// -------------------------------------------------------------

		setFigure(3, 0, createKing(Color.BLACK));

		setFigure(3, 7, createKing(Color.WHITE));

		// -------------------------------------------------------------
		// PUTS QUEENS ON BOARD
		// -------------------------------------------------------------

		setFigure(4, 0, createQueen(Color.BLACK));

		setFigure(4, 7, createQueen(Color.WHITE));

		// -------------------------------------------------------------
		// PUTS BISHOPS ON BOARD
		// -------------------------------------------------------------

		setFigure(2, 0, createBishop(Color.BLACK));
		setFigure(5, 0, createBishop(Color.BLACK));

		setFigure(2, 7, createBishop(Color.WHITE));
		setFigure(5, 7, createBishop(Color.WHITE));

		// -------------------------------------------------------------
		// PUTS KNIGHT ON BOARD
		// -------------------------------------------------------------

		setFigure(1, 0, createKnight(Color.BLACK));
		setFigure(6, 0, createKnight(Color.BLACK));

		setFigure(1, 7, createKnight(Color.WHITE));
		setFigure(6, 7, createKnight(Color.WHITE));

		// -------------------------------------------------------------
		// PUTS ROOKS ON BOARD
		// -------------------------------------------------------------

		setFigure(0, 0, createRook(Color.BLACK));
		setFigure(7, 0, createRook(Color.BLACK));

		setFigure(0, 7, createRook(Color.WHITE));
		setFigure(7, 7, createRook(Color.WHITE));

		// -------------------------------------------------------------
		// PUTS PAWNS ON BOARD
		// -------------------------------------------------------------

		for (int x = 0; x < 8; x = x + 1) {

			setFigure(x, 1, createPawn(Color.BLACK));

			setFigure(x, 6, createPawn(Color.WHITE));

		}

	}

	/**
	 * Draws the figures on the buttons (with unicode).
	 * 
	 * @param x
	 *            Buttons x-coordinate
	 * @param y
	 *            Buttons y-coordinate
	 * @param figure
	 *            Needs to know which figure to draw
	 */
	private void drawfigure(int x, int y, Figure figure) {

		String unicodeKingWhite = "\u2654";
		String unicodeQueenWhite = "\u2655";
		String unicodeBishopWhite = "\u2657";
		String unicodeKnightWhite = "\u2658";
		String unicodeRookWhite = "\u2656";
		String unicodePawnWhite = "\u2659";

		String unicodeKingBlack = "\u265A";
		String unicodeQueenBlack = "\u265B";
		String unicodeBishopBlack = "\u265D";
		String unicodeKnightBlack = "\u265E";
		String unicodeRookBlack = "\u265C";
		String unicodePawnBlack = "\u265F";

		// -------------------------------------------------------------
		// FOR BLACK figureS
		// -------------------------------------------------------------

		if (figure.getColor() == Color.BLACK) {

			getField(x, y).setForeground(Color.BLACK);

			if (getField(x, y).getBackground() == Color.BLACK) {

				getField(x, y).setBackground(Color.WHITE);
				getField(x, y).setBorder(new LineBorder(Color.BLACK, 10));

			}

			switch (String.valueOf(figure.getClass())) {
			case "class figures.figures.King":

				getField(x, y).setText(unicodeKingBlack);
				break;

			case "class figures.figures.Queen":

				getField(x, y).setText(unicodeQueenBlack);
				break;

			case "class figures.figures.Bishop":

				getField(x, y).setText(unicodeBishopBlack);
				break;

			case "class figures.figures.Knight":

				getField(x, y).setText(unicodeKnightBlack);
				break;

			case "class figures.figures.Rook":

				getField(x, y).setText(unicodeRookBlack);
				break;

			case "class figures.figures.Pawn":

				getField(x, y).setText(unicodePawnBlack);
				break;

			default:

				System.out.println("False figure");
				break;

			}

		}

		// -------------------------------------------------------------
		// FOR WHITE figureS
		// -------------------------------------------------------------

		if (figure.getColor() == Color.WHITE) {

			getField(x, y).setForeground(Color.WHITE);

			if (getField(x, y).getBackground() == Color.WHITE) {

				getField(x, y).setBackground(Color.BLACK);
				getField(x, y).setBorder(new LineBorder(Color.WHITE, 10));

			}

			switch (String.valueOf(figure.getClass())) {
			case "class figures.figures.King":

				getField(x, y).setText(unicodeKingWhite);
				break;

			case "class figures.figures.Queen":

				getField(x, y).setText(unicodeQueenWhite);
				break;

			case "class figures.figures.Bishop":

				getField(x, y).setText(unicodeBishopWhite);
				break;

			case "class figures.figures.Knight":

				getField(x, y).setText(unicodeKnightWhite);
				break;

			case "class figures.figures.Rook":

				getField(x, y).setText(unicodeRookWhite);
				break;

			case "class figures.figures.Pawn":

				getField(x, y).setText(unicodePawnWhite);
				break;

			default:

				System.out.println("False Figure");
				break;

			}

		}

	}

	// -------------------------------------------------------------
	// PRIVATE METHODS TO CREATE NEW FIGURES
	// -------------------------------------------------------------

	private King createKing(Color color) {

		King newKing = new King(color);
		return newKing;

	}

	private Queen createQueen(Color color) {

		Queen newQueen = new Queen(color);
		return newQueen;

	}

	private Bishop createBishop(Color color) {

		Bishop newBishop = new Bishop(color);
		return newBishop;

	}

	private Knight createKnight(Color color) {

		Knight newKnight = new Knight(color);
		return newKnight;

	}

	private Rook createRook(Color color) {

		Rook newRook = new Rook(color);
		return newRook;

	}

	private Pawn createPawn(Color color) {

		Pawn newPawn = new Pawn(color);
		return newPawn;

	}

}