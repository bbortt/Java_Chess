package figures.check;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import main.Game;
import dataBase.PlayerDatas;
import figures.Figure;

/**
 * This class handles the castlings. They are done or undone right here.
 * 
 * @author Timon Borter
 * 
 */
public class CastlingCheck {

	// -------------------------------------------------------------
	// VARIABLES
	// -------------------------------------------------------------

	private PlayerDatas playerDatas;

	private JButton[][] fields;
	private Figure[][] figures;

	private Game game;

	private JFrame mainFrame;

	private KingCheck kingCheck;

	// -------------------------------------------------------------
	// REQUIRED GETTER AND SETTER
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
	 * Sets the local variables and creates KingCheck.
	 * 
	 * @param givenGame
	 *            Requires an instance of the game
	 * @param givenPlayerDatas
	 *            Requires PlayerDatas to check colors
	 * @param givenFields
	 *            Requires fields to actualize the GUI
	 * @param givenFigues
	 *            Requires actual figures positions
	 * @param givenMainFrame
	 *            Requires JFrame for JOptionPane.showMessageDialog()
	 */
	public CastlingCheck(Game givenGame, PlayerDatas givenPlayerDatas,
			JButton[][] givenFields, Figure[][] givenFigures,
			JFrame givenMainFrame) {

		game = givenGame;
		playerDatas = givenPlayerDatas;
		fields = givenFields;
		figures = givenFigures;
		mainFrame = givenMainFrame;
		kingCheck = new KingCheck();

	}

	/**
	 * Checks if a left castling could be done and might does it.
	 */
	public void leftCastling() {

		// -------------------------------------------------------------
		// KING MUSTNT BE CHECKED
		// -------------------------------------------------------------

		if (!kingCheck.check(playerDatas.getColorPlayerOnTurn(), figures)) {

			// -------------------------------------------------------------
			// FOR PLAYER 1
			// -------------------------------------------------------------

			if (playerDatas.getOnTurn() == playerDatas.getNamePlayer1()) {

				// -------------------------------------------------------------
				// CHECKS FOR figureS
				// -------------------------------------------------------------

				if (getFigure(0, 7) != null && getFigure(1, 7) == null
						&& getFigure(2, 7) == null && getFigure(3, 7) != null) {

					// -------------------------------------------------------------
					// CHECKS ROOK AND KING
					// -------------------------------------------------------------

					if ((getFigure(0, 7).getTurnCounter() == 0
							&& String.valueOf(getFigure(0, 7)).contains(
									"figures.figures.Rook") && getFigure(0, 7)
							.getColor() == playerDatas.getColorPlayerOnTurn())
							&& (getFigure(3, 7).getTurnCounter() == 0
									&& String.valueOf(getFigure(3, 7))
											.contains("figures.figures.King") && getFigure(
									3, 7).getColor() == playerDatas
									.getColorPlayerOnTurn())) {

						// -------------------------------------------------------------
						// DOES THE CASTLING
						// -------------------------------------------------------------

						setFigure(1, 7, getFigure(3, 7));
						setFigure(3, 7, null);
						getField(3, 7).setText(null);
						setFigure(2, 7, getFigure(0, 7));
						setFigure(0, 7, null);
						getField(0, 7).setText(null);

						// -------------------------------------------------------------
						// REDRAWS THE BOARD
						// -------------------------------------------------------------

						game.clearBoard();

						// -------------------------------------------------------------
						// UNDOES TURN IF KING IS CHECKED AFTER CASTLING
						// -------------------------------------------------------------

						if (kingCheck.check(playerDatas.getColorPlayerOnTurn(),
								figures)) {

							// -------------------------------------------------------------
							// INFORMS PLAYER
							// -------------------------------------------------------------

							JOptionPane.showMessageDialog(mainFrame,
									"Incorrect move.\nCheck!",
									"Incorrect move",
									JOptionPane.INFORMATION_MESSAGE);

							// -------------------------------------------------------------
							// UNDOES TURN
							// -------------------------------------------------------------

							setFigure(0, 7, getFigure(2, 7));
							setFigure(2, 7, null);
							getField(2, 7).setText(null);
							setFigure(3, 7, getFigure(1, 7));
							setFigure(1, 7, null);
							getField(1, 7).setText(null);

							// -------------------------------------------------------------
							// REDRAWS BOARD
							// -------------------------------------------------------------

							game.clearBoard();

						} else {

							// -------------------------------------------------------------
							// REQUIRED TO CONTINUE
							// -------------------------------------------------------------

							playerDatas.setFromSelectedJButton("21.21");
							playerDatas.setToSelectedJButton("21.21");

						}

					} else {

						JOptionPane.showMessageDialog(mainFrame,
								"Incorrect move!", "Incorrect move",
								JOptionPane.INFORMATION_MESSAGE);

					}

				} else {

					JOptionPane.showMessageDialog(mainFrame, "Incorrect move!",
							"Incorrect move", JOptionPane.INFORMATION_MESSAGE);

				}

			}

			// -------------------------------------------------------------
			// FOR PLAYER 2
			// -------------------------------------------------------------

			else {

				// -------------------------------------------------------------
				// CHECKS FOR figureS
				// -------------------------------------------------------------

				if (getFigure(0, 0) != null && getFigure(1, 0) == null
						&& getFigure(2, 0) == null && getFigure(3, 0) != null) {

					// -------------------------------------------------------------
					// CHECKS FOR ROOK AND KING
					// -------------------------------------------------------------

					if ((getFigure(0, 0).getTurnCounter() == 0
							&& String.valueOf(getFigure(0, 0)).contains(
									"figures.figures.Rook") && getFigure(0, 0)
							.getColor() == playerDatas.getColorPlayerOnTurn())
							&& (getFigure(3, 0).getTurnCounter() == 0
									&& String.valueOf(getFigure(3, 0))
											.contains("figures.figures.King") && getFigure(
									3, 0).getColor() == playerDatas
									.getColorPlayerOnTurn())) {

						// -------------------------------------------------------------
						// DOES THE CASTLING
						// -------------------------------------------------------------

						setFigure(1, 0, getFigure(3, 0));
						setFigure(3, 0, null);
						getField(3, 0).setText(null);
						setFigure(2, 0, getFigure(0, 0));
						setFigure(0, 0, null);
						getField(0, 0).setText(null);

						// -------------------------------------------------------------
						// REDRAWS THE BOARD
						// -------------------------------------------------------------

						game.clearBoard();

						// -------------------------------------------------------------
						// UNDOES TURN IF KING IS CHECKED AFTER CASTLING
						// -------------------------------------------------------------

						if (kingCheck.check(playerDatas.getColorPlayerOnTurn(),
								figures)) {

							// -------------------------------------------------------------
							// INFORMS PLAYER
							// -------------------------------------------------------------

							JOptionPane.showMessageDialog(mainFrame,
									"Incorrect move.\nCheck!",
									"Incorrect move",
									JOptionPane.INFORMATION_MESSAGE);

							// -------------------------------------------------------------
							// UNDOES TURN
							// -------------------------------------------------------------

							setFigure(0, 0, getFigure(2, 0));
							setFigure(2, 0, null);
							getField(2, 0).setText(null);
							setFigure(3, 0, getFigure(1, 0));
							setFigure(1, 0, null);
							getField(1, 0).setText(null);

							// -------------------------------------------------------------
							// REDRAWS BOARD
							// -------------------------------------------------------------

							game.clearBoard();

						} else {

							// -------------------------------------------------------------
							// REQUIRED TO CONTINUE
							// -------------------------------------------------------------

							playerDatas.setFromSelectedJButton("21.21");
							playerDatas.setToSelectedJButton("21.21");

						}

					} else {

						JOptionPane.showMessageDialog(mainFrame,
								"Incorrect move!", "Incorrect move",
								JOptionPane.INFORMATION_MESSAGE);

					}

				} else {

					JOptionPane.showMessageDialog(mainFrame, "Incorrect move!",
							"Incorrect move", JOptionPane.INFORMATION_MESSAGE);

				}

			}

		} else {

			JOptionPane.showMessageDialog(mainFrame, "Castling not possible.\n"
					+ playerDatas.getOnTurn() + " checked!", "Check",
					JOptionPane.INFORMATION_MESSAGE);

		}

	}

	/**
	 * Checks if a right castling could be done and might does it.
	 */
	public void rightCastling() {

		// -------------------------------------------------------------
		// KING MUSTNT BE CHECKED
		// -------------------------------------------------------------

		if (!kingCheck.check(playerDatas.getColorPlayerOnTurn(), figures)) {

			// -------------------------------------------------------------
			// FOR PLAYER 1
			// -------------------------------------------------------------

			if (playerDatas.getOnTurn() == playerDatas.getNamePlayer1()) {

				// -------------------------------------------------------------
				// CHECKS FOR figureS
				// -------------------------------------------------------------

				if (getFigure(3, 7) != null && getFigure(4, 7) == null
						&& getFigure(5, 7) == null && getFigure(6, 7) == null
						&& getFigure(7, 7) != null) {

					// -------------------------------------------------------------
					// CHECKS FOR ROOK AND KING
					// -------------------------------------------------------------

					if ((getFigure(7, 7).getTurnCounter() == 0
							&& String.valueOf(getFigure(7, 7)).contains(
									"figures.figures.Rook") && getFigure(7, 7)
							.getColor() == playerDatas.getColorPlayerOnTurn())
							&& (getFigure(3, 7).getTurnCounter() == 0
									&& String.valueOf(getFigure(3, 7))
											.contains("figures.figures.King") && getFigure(
									3, 7).getColor() == playerDatas
									.getColorPlayerOnTurn())) {

						// -------------------------------------------------------------
						// DOES THE CASTLING
						// -------------------------------------------------------------

						setFigure(5, 7, getFigure(7, 7));
						setFigure(7, 7, null);
						getField(7, 7).setText(null);
						setFigure(6, 7, getFigure(3, 7));
						setFigure(3, 7, null);
						getField(3, 7).setText(null);

						// -------------------------------------------------------------
						// REDRAWS THE BOARD
						// -------------------------------------------------------------

						game.clearBoard();

						// -------------------------------------------------------------
						// UNDOES TURN IF KING IS CHECKED AFTER CASTLING
						// -------------------------------------------------------------

						if (kingCheck.check(playerDatas.getColorPlayerOnTurn(),
								figures)) {

							JOptionPane.showMessageDialog(mainFrame,
									"Incorrect move.\nCheck!",
									"Incorrect move",
									JOptionPane.INFORMATION_MESSAGE);

							// -------------------------------------------------------------
							// UNDOES CASTLING
							// -------------------------------------------------------------

							setFigure(7, 7, getFigure(5, 7));
							setFigure(5, 7, null);
							getField(5, 7).setText(null);
							setFigure(3, 7, getFigure(6, 7));
							setFigure(6, 7, null);
							getField(6, 7).setText(null);

							// -------------------------------------------------------------
							// REDRAWS BOARD
							// -------------------------------------------------------------

							game.clearBoard();

						} else {

							// -------------------------------------------------------------
							// REQUIRED TO CONTINUE
							// -------------------------------------------------------------

							playerDatas.setFromSelectedJButton("21.21");
							playerDatas.setToSelectedJButton("21.21");

						}

					} else {

						JOptionPane.showMessageDialog(mainFrame,
								"Incorrect move!", "Incorrect move",
								JOptionPane.INFORMATION_MESSAGE);

					}

				} else {

					JOptionPane.showMessageDialog(mainFrame, "Incorrect move!",
							"Incorrect move", JOptionPane.INFORMATION_MESSAGE);

				}

			}

			// -------------------------------------------------------------
			// FOR PLAYER 2
			// -------------------------------------------------------------

			else {

				// -------------------------------------------------------------
				// CHECKS FOR figureS
				// -------------------------------------------------------------

				if (getFigure(3, 0) != null && getFigure(4, 0) == null
						&& getFigure(5, 0) == null && getFigure(6, 0) == null
						&& getFigure(7, 0) != null) {

					// -------------------------------------------------------------
					// CHECKS FOR ROOK AND KING
					// -------------------------------------------------------------

					if ((getFigure(7, 0).getTurnCounter() == 0
							&& String.valueOf(getFigure(7, 0)).contains(
									"figures.figures.Rook") && getFigure(7, 0)
							.getColor() == playerDatas.getColorPlayerOnTurn())
							&& (getFigure(3, 0).getTurnCounter() == 0
									&& String.valueOf(getFigure(3, 0))
											.contains("figures.figures.King") && getFigure(
									3, 0).getColor() == playerDatas
									.getColorPlayerOnTurn())) {

						// -------------------------------------------------------------
						// DOES THE CASTLING
						// -------------------------------------------------------------

						setFigure(5, 0, getFigure(7, 0));
						setFigure(7, 0, null);
						getField(7, 0).setText(null);
						setFigure(6, 0, getFigure(3, 0));
						setFigure(3, 0, null);
						getField(3, 0).setText(null);

						// -------------------------------------------------------------
						// REDRAWS THE BOARD
						// -------------------------------------------------------------

						game.clearBoard();

						// -------------------------------------------------------------
						// UNDOES TURN IF KING IS CHECKED AFTER CASTLING
						// -------------------------------------------------------------

						if (kingCheck.check(playerDatas.getColorPlayerOnTurn(),
								figures)) {

							// -------------------------------------------------------------
							// INFORMS THE PLAYER
							// -------------------------------------------------------------

							JOptionPane.showMessageDialog(mainFrame,
									"Incorrect move.\nCheck!",
									"Incorrect move",
									JOptionPane.INFORMATION_MESSAGE);

							// -------------------------------------------------------------
							// UNDOES THE CASTLING
							// -------------------------------------------------------------

							setFigure(7, 0, getFigure(5, 0));
							setFigure(5, 0, null);
							getField(5, 0).setText(null);
							setFigure(3, 0, getFigure(6, 0));
							setFigure(6, 0, null);
							getField(6, 0).setText(null);

							// -------------------------------------------------------------
							// REDRAWS THE BOARD
							// -------------------------------------------------------------

							game.clearBoard();

						} else {

							// -------------------------------------------------------------
							// REQUIRED TO CONTINUE
							// -------------------------------------------------------------

							playerDatas.setFromSelectedJButton("21.21");
							playerDatas.setToSelectedJButton("21.21");

						}

					} else {

						JOptionPane.showMessageDialog(mainFrame,
								"Incorrect move!", "Incorrect move",
								JOptionPane.INFORMATION_MESSAGE);

					}

				} else {

					JOptionPane.showMessageDialog(mainFrame, "Incorrect move!",
							"Incorrect move", JOptionPane.INFORMATION_MESSAGE);

				}

			}

		} else {

			JOptionPane.showMessageDialog(mainFrame, "Castling not possible.\n"
					+ playerDatas.getOnTurn() + " is checked!", "Check",
					JOptionPane.INFORMATION_MESSAGE);

		}

	}

}
