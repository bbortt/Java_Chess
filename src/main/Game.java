package main;

import java.awt.Color;

import javax.swing.JButton;
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
import gui.ChessBoard;

/**
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

	private KingCheck kingCheck = new KingCheck();

	// -------------------------------------------------------------
	// PUBLIC GETTERS AND SETTERS
	// -------------------------------------------------------------

	public PlayerDatas getPlayerDatas() {

		return this.playerDatas;

	}

	public void setPlayerDatas(PlayerDatas playerDatas) {

		this.playerDatas = playerDatas;

	}

	public JLabel getLabelNamePlayer1() {

		return labelNamePlayer1;

	}

	public void setLabelNamePlayer1(JLabel labelNamePlayer1) {

		this.labelNamePlayer1 = labelNamePlayer1;

	}

	public JLabel getLabelTimeTillEnd() {

		return labelTimeTillEnd;

	}

	public void setLabelTimeTillEnd(JLabel labelTimeTillEnd) {

		this.labelTimeTillEnd = labelTimeTillEnd;

	}

	public JLabel getLabelNamePlayer2() {

		return labelNamePlayer2;

	}

	public void setLabelNamePlayer2(JLabel labelNamePlayer2) {

		this.labelNamePlayer2 = labelNamePlayer2;

	}

	public Figure getCopiedFigure() {

		return copiedFigure;

	}

	public void setCopiedFigure(Figure copiedFigure) {

		this.copiedFigure = copiedFigure;

	}

	public JButton[][] getAllFields() {

		return this.fields;

	}

	public void setAllFields(JButton[][] fields) {

		this.fields = fields;

	}

	public JButton getField(int x, int y) {

		return this.fields[x][y];

	}

	public void setField(int x, int y, JButton field) {

		this.fields[x][y] = field;

	}

	public Figure[][] getAllFigures() {

		return this.figures;

	}

	public void setAllFigures(Figure[][] figures) {

		this.figures = figures;

	}

	public Figure getFigure(int x, int y) {

		return this.figures[x][y];

	}

	public void setFigure(int x, int y, Figure figure) {

		this.figures[x][y] = figure;

	}

	/**
	 * Game() - Starts a new game, this is not the GUI
	 * 
	 * @param playerDatas
	 *            Requires PlayerDatas to check the steps
	 * @param fields
	 *            Requires fields to actualize the GUI in ChessBoard.java
	 * @param labelNamePlayer1
	 *            Requires label to show or hide
	 * @param labelTimeTillEnd
	 *            Requires label to create the timer (Timer.java)
	 * @param labelNamePlayer2
	 *            Requires label to show or hide
	 */
	public Game(PlayerDatas playerDatas, JButton[][] fields,
			JLabel labelNamePlayer1, JLabel labelTimeTillEnd,
			JLabel labelNamePlayer2) {

		// -------------------------------------------------------------
		// SYNCHRONIZES LOCAL VARIABLES WITH ATTRIBUTES
		// -------------------------------------------------------------

		setPlayerDatas(playerDatas);
		setAllFields(fields);
		setLabelNamePlayer1(labelNamePlayer1);
		setLabelTimeTillEnd(labelTimeTillEnd);
		setLabelNamePlayer2(labelNamePlayer2);

		// -------------------------------------------------------------
		// CREATES VIRTUAL CHESS BOARD
		// -------------------------------------------------------------

		setAllFigures(new Figure[8][8]);

	}

	/**
	 * theTimer() - Starts a timer for both players in an asynchronous task
	 */
	public void theTimer() {

		timer = new Timer(playerDatas, getLabelTimeTillEnd());

	}

	/**
	 * aTurn() - This is a complete turn of a player - once everything was set
	 * up, the whole game runs in this one function
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

			if (getPlayerDatas().getOnTurn().equals(
					getPlayerDatas().getNamePlayer1())) {

				getLabelNamePlayer1().show();
				getLabelNamePlayer2().hide();

			} else {

				getLabelNamePlayer2().show();
				getLabelNamePlayer1().hide();

			}

			// -------------------------------------------------------------
			// WAITS TILL PLAYER HAS BOTH BUTTONS CHOOSEN
			// -------------------------------------------------------------

			while ((getPlayerDatas().getFromSelectedJButton()[0] == 10 || getPlayerDatas()
					.getToSelectedJButton()[0] == 10)
					&& (getPlayerDatas().getFromSelectedJButton()[0] != 20 && getPlayerDatas()
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

			if (getPlayerDatas().getFromSelectedJButton()[0] != 20
					&& getPlayerDatas().getToSelectedJButton()[0] != 20) {

				// -------------------------------------------------------------
				// CHECKS FOR CORRECT MOVE
				// - CHECKS IF figure IS CHOOSEN
				// - CALLS THE MOVE METHOD (RETURNS TRUE IF MOVE IS OK)
				// - CHECKS IF SOMEONES KING IS CHECKED
				// -------------------------------------------------------------

				if (getFigure(getPlayerDatas().getFromSelectedJButton()[0],
						getPlayerDatas().getFromSelectedJButton()[1]) != null) {

					try {

						if (getFigure(
								getPlayerDatas().getFromSelectedJButton()[0],
								getPlayerDatas().getFromSelectedJButton()[1])
								.move(getPlayerDatas().getFromSelectedJButton()[0],
										getPlayerDatas()
												.getFromSelectedJButton()[1],
										getPlayerDatas().getToSelectedJButton()[0],
										getPlayerDatas().getToSelectedJButton()[1],
										playerDatas, figures)) {

							// -------------------------------------------------------------
							// AMOUNTS THE TURN COUNTER
							// -------------------------------------------------------------

							getFigure(
									getPlayerDatas().getFromSelectedJButton()[0],
									getPlayerDatas().getFromSelectedJButton()[1])
									.setTurnCounter(
											getFigure(
													getPlayerDatas()
															.getFromSelectedJButton()[0],
													getPlayerDatas()
															.getFromSelectedJButton()[1])
													.getTurnCounter() + 1);

							if (getFigure(getPlayerDatas()
									.getToSelectedJButton()[0],
									getPlayerDatas().getToSelectedJButton()[1]) != null) {

								// -------------------------------------------------------------
								// CALLS THE KILL METHOD
								// -------------------------------------------------------------

								getFigure(
										getPlayerDatas().getToSelectedJButton()[0],
										getPlayerDatas().getToSelectedJButton()[1])
										.kill(getPlayerDatas().getOnTurn());

								// -------------------------------------------------------------
								// SAVES THE KILLED figure
								// -------------------------------------------------------------

								setCopiedFigure(getFigure(
										getPlayerDatas().getToSelectedJButton()[0],
										getPlayerDatas().getToSelectedJButton()[1]));

							}

							// -------------------------------------------------------------
							// UPDATES BOARD
							// -------------------------------------------------------------

							setFigure(
									getPlayerDatas().getToSelectedJButton()[0],
									getPlayerDatas().getToSelectedJButton()[1],
									getFigure(
											getPlayerDatas()
													.getFromSelectedJButton()[0],
											getPlayerDatas()
													.getFromSelectedJButton()[1]));

							setFigure(
									getPlayerDatas().getFromSelectedJButton()[0],
									getPlayerDatas().getFromSelectedJButton()[1],
									null);

							getField(
									getPlayerDatas().getFromSelectedJButton()[0],
									getPlayerDatas().getFromSelectedJButton()[1])
									.setText(null);

							// -------------------------------------------------------------
							// DRAWS THE BOARD CAUS IT LOOKS MUCH BETTER
							// -------------------------------------------------------------

							clearBoard();

							// -------------------------------------------------------------
							// UNDOES THE TURN IF KING IS CHECKED
							// -------------------------------------------------------------

							if (kingCheck.check(getPlayerDatas()
									.getColorPlayerOnTurn(), getAllFigures())) {

								// -------------------------------------------------------------
								// INFORMS THE PLAYER
								// -------------------------------------------------------------

								JOptionPane.showMessageDialog(
										ChessBoard.getMainFrame(),
										"Incorrect move!\nCheck!",
										"Incorrect move",
										JOptionPane.INFORMATION_MESSAGE);

								// -------------------------------------------------------------
								// UNDOES TURN
								// -------------------------------------------------------------

								setFigure(
										getPlayerDatas()
												.getFromSelectedJButton()[0],
										getPlayerDatas()
												.getFromSelectedJButton()[1],
										getFigure(
												getPlayerDatas()
														.getToSelectedJButton()[0],
												getPlayerDatas()
														.getToSelectedJButton()[1]));

								if (getCopiedFigure() != null) {

									setFigure(getPlayerDatas()
											.getToSelectedJButton()[0],
											getPlayerDatas()
													.getToSelectedJButton()[1],
											getCopiedFigure());

								} else {

									setFigure(getPlayerDatas()
											.getToSelectedJButton()[0],
											getPlayerDatas()
													.getToSelectedJButton()[1],
											null);

									getField(
											getPlayerDatas()
													.getToSelectedJButton()[0],
											getPlayerDatas()
													.getToSelectedJButton()[1])
											.setText(null);

								}

								continue;

							}

						} else {

							// -------------------------------------------------------------
							// IF MOVE IS NOT CORRECT
							// -------------------------------------------------------------

							JOptionPane.showMessageDialog(
									ChessBoard.getMainFrame(),
									"Incorrect move!", "Incorrect move",
									JOptionPane.INFORMATION_MESSAGE);
							continue;

						}

					} catch (Exception e) {

						JOptionPane.showMessageDialog(
								ChessBoard.getMainFrame(),
								"Whoops, an error occured!\n" + e.getMessage()
										+ "\nThe program will be closed.",
								"Fatal Error", JOptionPane.INFORMATION_MESSAGE);
						System.exit(0);

					}

				} else {

					// -------------------------------------------------------------
					// IF NO figure IS CHOOSEN
					// -------------------------------------------------------------

					JOptionPane.showMessageDialog(ChessBoard.getMainFrame(),
							"No figure choosen!", "Incorrect move",
							JOptionPane.INFORMATION_MESSAGE);
					continue;

				}

			}

			// -------------------------------------------------------------
			// INFORMS THE OTHER PLAYER IF HE IS CHECKED NOW
			// -------------------------------------------------------------

			if (getPlayerDatas().getOnTurn().equals(
					getPlayerDatas().getNamePlayer1())
					&& kingCheck.check(Color.BLACK, getAllFigures())) {

				JOptionPane.showMessageDialog(ChessBoard.getMainFrame(),
						getPlayerDatas().getNamePlayer2() + " checked!",
						"Check", JOptionPane.INFORMATION_MESSAGE);

			} else if (getPlayerDatas().getOnTurn().equals(
					getPlayerDatas().getNamePlayer2())
					&& kingCheck.check(Color.WHITE, getAllFigures())) {

				JOptionPane.showMessageDialog(ChessBoard.getMainFrame(),
						getPlayerDatas().getNamePlayer1() + " checked!",
						"Check", JOptionPane.INFORMATION_MESSAGE);

			}

			// -------------------------------------------------------------
			// SETS CURRENT PLAYER TO SECOND POSITION IN QUEUE
			// UPDATES COLOR OF CURRENT PLAYER
			// -------------------------------------------------------------

			if (getPlayerDatas().getOnTurn().equals(
					getPlayerDatas().getNamePlayer1())) {

				getPlayerDatas().setOnTurn(getPlayerDatas().getNamePlayer2());

				getPlayerDatas().setColorPlayerOnTurn(Color.BLACK);

			} else {

				getPlayerDatas().setOnTurn(getPlayerDatas().getNamePlayer1());

				getPlayerDatas().setColorPlayerOnTurn(Color.WHITE);

			}

		}

	}

	/**
	 * leftCastling() - Checks if a left castling could be done and might does
	 * it
	 */
	public void leftCastling() {

		// -------------------------------------------------------------
		// KING MUSTNT BE CHECKED
		// -------------------------------------------------------------

		if (!kingCheck.check(getPlayerDatas().getColorPlayerOnTurn(),
				getAllFigures())) {

			// -------------------------------------------------------------
			// FOR PLAYER 1
			// -------------------------------------------------------------

			if (getPlayerDatas().getOnTurn() == getPlayerDatas()
					.getNamePlayer1()) {

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
							.getColor() == getPlayerDatas()
							.getColorPlayerOnTurn())
							&& (getFigure(3, 7).getTurnCounter() == 0
									&& String.valueOf(getFigure(3, 7))
											.contains("figures.figures.King") && getFigure(
									3, 7).getColor() == getPlayerDatas()
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

						clearBoard();

						// -------------------------------------------------------------
						// UNDOES TURN IF KING IS CHECKED AFTER CASTLING
						// -------------------------------------------------------------

						if (kingCheck.check(getPlayerDatas()
								.getColorPlayerOnTurn(), getAllFigures())) {

							// -------------------------------------------------------------
							// INFORMS PLAYER
							// -------------------------------------------------------------

							JOptionPane.showMessageDialog(
									ChessBoard.getMainFrame(),
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

							clearBoard();

						} else {

							// -------------------------------------------------------------
							// REQUIRED TO CONTINUE
							// -------------------------------------------------------------

							getPlayerDatas().setFromSelectedJButton("21.21");
							getPlayerDatas().setToSelectedJButton("21.21");

						}

					} else {

						JOptionPane.showMessageDialog(
								ChessBoard.getMainFrame(), "Incorrect move!",
								"Incorrect move",
								JOptionPane.INFORMATION_MESSAGE);

					}

				} else {

					JOptionPane.showMessageDialog(ChessBoard.getMainFrame(),
							"Incorrect move!", "Incorrect move",
							JOptionPane.INFORMATION_MESSAGE);

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
							.getColor() == getPlayerDatas()
							.getColorPlayerOnTurn())
							&& (getFigure(3, 0).getTurnCounter() == 0
									&& String.valueOf(getFigure(3, 0))
											.contains("figures.figures.King") && getFigure(
									3, 0).getColor() == getPlayerDatas()
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

						clearBoard();

						// -------------------------------------------------------------
						// UNDOES TURN IF KING IS CHECKED AFTER CASTLING
						// -------------------------------------------------------------

						if (kingCheck.check(getPlayerDatas()
								.getColorPlayerOnTurn(), getAllFigures())) {

							// -------------------------------------------------------------
							// INFORMS PLAYER
							// -------------------------------------------------------------

							JOptionPane.showMessageDialog(
									ChessBoard.getMainFrame(),
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

							clearBoard();

						} else {

							// -------------------------------------------------------------
							// REQUIRED TO CONTINUE
							// -------------------------------------------------------------

							getPlayerDatas().setFromSelectedJButton("21.21");
							getPlayerDatas().setToSelectedJButton("21.21");

						}

					} else {

						JOptionPane.showMessageDialog(
								ChessBoard.getMainFrame(), "Incorrect move!",
								"Incorrect move",
								JOptionPane.INFORMATION_MESSAGE);

					}

				} else {

					JOptionPane.showMessageDialog(ChessBoard.getMainFrame(),
							"Incorrect move!", "Incorrect move",
							JOptionPane.INFORMATION_MESSAGE);

				}

			}

		} else {

			JOptionPane.showMessageDialog(ChessBoard.getMainFrame(),
					"Castling not possible.\n" + getPlayerDatas().getOnTurn()
							+ " checked!", "Check",
					JOptionPane.INFORMATION_MESSAGE);

		}

	}

	/**
	 * rightCastling() - Checks if a right castling could be done and might does
	 * it
	 */
	public void rightCastling() {

		// -------------------------------------------------------------
		// KING MUSTNT BE CHECKED
		// -------------------------------------------------------------

		if (!kingCheck.check(getPlayerDatas().getColorPlayerOnTurn(),
				getAllFigures())) {

			// -------------------------------------------------------------
			// FOR PLAYER 1
			// -------------------------------------------------------------

			if (getPlayerDatas().getOnTurn() == getPlayerDatas()
					.getNamePlayer1()) {

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
							.getColor() == getPlayerDatas()
							.getColorPlayerOnTurn())
							&& (getFigure(3, 7).getTurnCounter() == 0
									&& String.valueOf(getFigure(3, 7))
											.contains("figures.figures.King") && getFigure(
									3, 7).getColor() == getPlayerDatas()
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

						clearBoard();

						// -------------------------------------------------------------
						// UNDOES TURN IF KING IS CHECKED AFTER CASTLING
						// -------------------------------------------------------------

						if (kingCheck.check(getPlayerDatas()
								.getColorPlayerOnTurn(), getAllFigures())) {

							JOptionPane.showMessageDialog(
									ChessBoard.getMainFrame(),
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

							clearBoard();

						} else {

							// -------------------------------------------------------------
							// REQUIRED TO CONTINUE
							// -------------------------------------------------------------

							getPlayerDatas().setFromSelectedJButton("21.21");
							getPlayerDatas().setToSelectedJButton("21.21");

						}

					} else {

						JOptionPane.showMessageDialog(
								ChessBoard.getMainFrame(), "Incorrect move!",
								"Incorrect move",
								JOptionPane.INFORMATION_MESSAGE);

					}

				} else {

					JOptionPane.showMessageDialog(ChessBoard.getMainFrame(),
							"Incorrect move!", "Incorrect move",
							JOptionPane.INFORMATION_MESSAGE);

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
							.getColor() == getPlayerDatas()
							.getColorPlayerOnTurn())
							&& (getFigure(3, 0).getTurnCounter() == 0
									&& String.valueOf(getFigure(3, 0))
											.contains("figures.figures.King") && getFigure(
									3, 0).getColor() == getPlayerDatas()
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

						clearBoard();

						// -------------------------------------------------------------
						// UNDOES TURN IF KING IS CHECKED AFTER CASTLING
						// -------------------------------------------------------------

						if (kingCheck.check(getPlayerDatas()
								.getColorPlayerOnTurn(), getAllFigures())) {

							// -------------------------------------------------------------
							// INFORMS THE PLAYER
							// -------------------------------------------------------------

							JOptionPane.showMessageDialog(
									ChessBoard.getMainFrame(),
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

							clearBoard();

						} else {

							// -------------------------------------------------------------
							// REQUIRED TO CONTINUE
							// -------------------------------------------------------------

							getPlayerDatas().setFromSelectedJButton("21.21");
							getPlayerDatas().setToSelectedJButton("21.21");

						}

					} else {

						JOptionPane.showMessageDialog(
								ChessBoard.getMainFrame(), "Incorrect move!",
								"Incorrect move",
								JOptionPane.INFORMATION_MESSAGE);

					}

				} else {

					JOptionPane.showMessageDialog(ChessBoard.getMainFrame(),
							"Incorrect move!", "Incorrect move",
							JOptionPane.INFORMATION_MESSAGE);

				}

			}

		} else {

			JOptionPane.showMessageDialog(ChessBoard.getMainFrame(),
					"Castling not possible.\n" + getPlayerDatas().getOnTurn()
							+ " is checked!", "Check",
					JOptionPane.INFORMATION_MESSAGE);

		}

	}

	/**
	 * clearBoard() - Redraws every displayed field, redraws the figures and
	 * configures borders
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
	 * resetPlayerDatas() - Does not reset PlayerDatas but chosen buttons (from
	 * and to)
	 */
	public void resetPlayerDatas() {

		getPlayerDatas().setFromSelectedJButton("11.11");
		getPlayerDatas().setToSelectedJButton("11.11");
		setCopiedFigure(null);

	}

	/**
	 * defaultSetUp() - Sets the figures on their default location
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
	 * drawfigure() - Draws the figures on the buttons (with unicode)
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