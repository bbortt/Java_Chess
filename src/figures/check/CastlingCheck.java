package figures.check;

import javax.swing.JButton;
import javax.swing.JOptionPane;

import main.Game;
import dataBase.PlayerDatas;
import figures.Figure;
import gui.ChessBoard;

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
	private KingCheck kingCheck;

	// -------------------------------------------------------------
	// PUBLIC GETTERS AND SETTERS
	// -------------------------------------------------------------

	public PlayerDatas getPlayerDatas() {

		return this.playerDatas;

	}

	public void setPlayerDatas(PlayerDatas playerDatas) {

		this.playerDatas = playerDatas;

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

	public Game getGame() {

		return this.game;

	}

	public void setGame(Game game) {

		this.game = game;

	}

	public KingCheck getKingCheck() {

		return this.kingCheck;

	}

	public void setKingCheck(KingCheck kingCheck) {

		this.kingCheck = kingCheck;

	}

	/**
	 * Sets game to the local variable and creates KingCheck.
	 * 
	 * @param game
	 *            Requires an instance of the game
	 */
	public CastlingCheck(Game game) {

		setGame(game);
		setPlayerDatas(getGame().getPlayerDatas());
		setAllFields(getGame().getAllFields());
		setAllFigures(getGame().getAllFigures());
		setKingCheck(new KingCheck());

	}

	/**
	 * Checks if a left castling could be done and might does it.
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

						getGame().clearBoard();

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

							getGame().clearBoard();

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

						getGame().clearBoard();

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

							getGame().clearBoard();

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
	 * Checks if a right castling could be done and might does it.
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

						getGame().clearBoard();

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

							getGame().clearBoard();

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

						getGame().clearBoard();

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

							getGame().clearBoard();

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

}
