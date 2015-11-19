package figures.check;

import java.awt.Color;

import figures.Figure;

/**
 * This class checks whether a king is checked or not.
 * 
 * @author Timon Borter
 * 
 */
public class KingCheck {

	// -------------------------------------------------------------
	// VARIABLES
	// -------------------------------------------------------------

	private int xCoordinate;
	private int yCoordinate;
	private Color colorToCheck;
	private Figure[][] figures;

	// -------------------------------------------------------------
	// PUBLIC GETTERS AND SETTERS
	// -------------------------------------------------------------

	public int getxCoordinate() {

		return xCoordinate;

	}

	public void setxCoordinate(int xCoordinate) {

		this.xCoordinate = xCoordinate;

	}

	public int getyCoordinate() {

		return yCoordinate;

	}

	public void setyCoordinate(int yCoordinate) {

		this.yCoordinate = yCoordinate;

	}

	public Color getColorToCheck() {

		return colorToCheck;

	}

	public void setColorToCheck(Color colorToCheck) {

		this.colorToCheck = colorToCheck;

	}

	public Figure[][] getAllFigures() {

		return figures;

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
	 * Checks if someone's king is checked.
	 * 
	 * @param colorToCheck
	 *            The color of the player to check
	 * @param figures
	 *            Requires every figures position
	 * @return Returns true if the king is not checked
	 * */
	public boolean check(Color colorToCheck, Figure[][] figures) {

		// -------------------------------------------------------------
		// UPDATE THE PRIVATE VARIABLES
		// -------------------------------------------------------------

		setColorToCheck(colorToCheck);
		setAllFigures(figures);

		// -------------------------------------------------------------
		// DETECTS THE KING
		// -------------------------------------------------------------

		for (int y = 0; y < 8; y = y + 1) {

			for (int x = 0; x < 8; x = x + 1) {

				if (String.valueOf(getFigure(x, y)).contains(
						"Figures.Figures.King")
						&& getFigure(x, y).getColor() == getColorToCheck()) {

					setxCoordinate(x);
					setyCoordinate(y);

					x = 8;
					y = 8;

				}

			}

		}

		boolean checkValue = false;

		// -------------------------------------------------------------
		// SEARCHES VERTICAL FROM KING TO TOP
		// -------------------------------------------------------------

		int iterator = 1;

		while (getyCoordinate() - iterator >= 0) {

			if (getFigure(getxCoordinate(), getyCoordinate() - iterator) != null) {

				if (String
						.valueOf(
								getFigure(getxCoordinate(), getyCoordinate()
										- iterator)).contains(
								"Figures.Figures.Rook")
						&& getFigure(getxCoordinate(),
								getyCoordinate() - iterator).getColor() != getColorToCheck()) {

					checkValue = true;

				} else if (String
						.valueOf(
								getFigure(getxCoordinate(), getyCoordinate()
										- iterator)).contains(
								"Figures.Figures.Queen")
						&& getFigure(getxCoordinate(),
								getyCoordinate() - iterator).getColor() != getColorToCheck()) {

					checkValue = true;

				} else if (String
						.valueOf(
								getFigure(getxCoordinate(), getyCoordinate()
										- iterator)).contains(
								"Figures.Figures.King")
						&& getFigure(getxCoordinate(),
								getyCoordinate() - iterator).getColor() != getColorToCheck()
						&& iterator == 1) {

					checkValue = true;

				} else {

					// -------------------------------------------------------------
					// IF NOTHING MATCHES THE LOOP CAN BE BREAKEN
					// -------------------------------------------------------------

					break;

				}

			}

			iterator = iterator + 1;

		}

		// -------------------------------------------------------------
		// SEARCHES HORIZONTAL FROM KING TO RIGHT
		// -------------------------------------------------------------

		iterator = 1;

		while (getxCoordinate() + iterator <= 7) {

			if (getFigure(getxCoordinate() + iterator, getyCoordinate()) != null) {

				if (String
						.valueOf(
								getFigure(getxCoordinate() + iterator,
										getyCoordinate())).contains(
								"Figures.Figures.Rook")
						&& getFigure(getxCoordinate() + iterator,
								getyCoordinate()).getColor() != getColorToCheck()) {

					checkValue = true;

				} else if (String
						.valueOf(
								getFigure(getxCoordinate() + iterator,
										getyCoordinate())).contains(
								"Figures.Figures.Queen")
						&& getFigure(getxCoordinate() + iterator,
								getyCoordinate()).getColor() != getColorToCheck()) {

					checkValue = true;

				} else if (String
						.valueOf(
								getFigure(getxCoordinate() + iterator,
										getyCoordinate())).contains(
								"Figures.Figures.King")
						&& getFigure(getxCoordinate(),
								getyCoordinate() + iterator).getColor() != getColorToCheck()
						&& iterator == 1) {

					checkValue = true;

				} else {

					// -------------------------------------------------------------
					// IF NOTHING MATCHES THE LOOP CAN BE BREAKEN
					// -------------------------------------------------------------

					break;

				}

			}

			iterator = iterator + 1;

		}

		// -------------------------------------------------------------
		// SEARCHES VERTICAL FROM KING TO BOTTOM
		// -------------------------------------------------------------

		iterator = 1;

		while (getyCoordinate() + iterator <= 7) {

			if (getFigure(getxCoordinate(), getyCoordinate() + iterator) != null) {

				if (String
						.valueOf(
								getFigure(getxCoordinate(), getyCoordinate()
										+ iterator)).contains(
								"Figures.Figures.Rook")
						&& getFigure(getxCoordinate(),
								getyCoordinate() + iterator).getColor() != getColorToCheck()) {

					checkValue = true;

				} else if (String
						.valueOf(
								getFigure(getxCoordinate(), getyCoordinate()
										+ iterator)).contains(
								"Figures.Figures.Queen")
						&& getFigure(getxCoordinate(),
								getyCoordinate() + iterator).getColor() != getColorToCheck()) {

					checkValue = true;

				} else if (String
						.valueOf(
								getFigure(getxCoordinate(), getyCoordinate()
										+ iterator)).contains(
								"Figures.Figures.King")
						&& getFigure(getxCoordinate(),
								getyCoordinate() + iterator).getColor() != getColorToCheck()
						&& iterator == 1) {

					checkValue = true;

				} else {

					// -------------------------------------------------------------
					// IF NOTHING MATCHES THE LOOP CAN BE BREAKEN
					// -------------------------------------------------------------

					break;

				}

			}

			iterator = iterator + 1;

		}

		// -------------------------------------------------------------
		// SEARCHES HORIZONTAL FROM KING TO LEFT
		// -------------------------------------------------------------

		iterator = 1;

		while (getxCoordinate() - iterator >= 0) {

			if (getFigure(getxCoordinate() - iterator, getyCoordinate()) != null) {

				if (String
						.valueOf(
								getFigure(getxCoordinate() - iterator,
										getyCoordinate())).contains(
								"Figures.Figures.Rook")
						&& getFigure(getxCoordinate() - iterator,
								getyCoordinate()).getColor() != getColorToCheck()) {

					checkValue = true;

				} else if (String
						.valueOf(
								getFigure(getxCoordinate() - iterator,
										getyCoordinate())).contains(
								"Figures.Figures.Queen")
						&& getFigure(getxCoordinate() - iterator,
								getyCoordinate()).getColor() != getColorToCheck()) {

					checkValue = true;

				} else if (String
						.valueOf(
								getFigure(getxCoordinate() - iterator,
										getyCoordinate())).contains(
								"Figures.Figures.King")
						&& getFigure(getxCoordinate(),
								getyCoordinate() - iterator).getColor() != getColorToCheck()
						&& iterator == 1) {

					checkValue = true;

				} else {

					// -------------------------------------------------------------
					// IF NOTHING MATCHES THE LOOP CAN BE BREAKEN
					// -------------------------------------------------------------

					break;

				}

			}

			iterator = iterator + 1;

		}

		// -------------------------------------------------------------
		// CHECKS FROM KING TO BOTTOM RIGHT
		// SEARCHES FOR BISHOPS, QUEENS, KINGS OR PAWNS
		// -------------------------------------------------------------

		iterator = 1;

		while (getxCoordinate() + iterator <= 7
				&& getyCoordinate() + iterator <= 7) {

			if (getFigure(getxCoordinate() + iterator, getyCoordinate()
					+ iterator) != null) {

				if (String.valueOf(
						getFigure(getxCoordinate() + iterator, getyCoordinate()
								+ iterator)).contains("Figures.Figures.Bishop")
						&& getFigure(getxCoordinate() + iterator,
								getyCoordinate() + iterator).getColor() != getColorToCheck()) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(getxCoordinate() + iterator, getyCoordinate()
								+ iterator)).contains("Figures.Figures.Queen")
						&& getFigure(getxCoordinate() + iterator,
								getyCoordinate() + iterator).getColor() != getColorToCheck()) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(getxCoordinate() + iterator, getyCoordinate()
								+ iterator)).contains("Figures.Figures.Pawn")
						&& getFigure(getxCoordinate() + iterator,
								getyCoordinate() + iterator).getColor() == Color.WHITE
						&& getFigure(getxCoordinate() + iterator,
								getyCoordinate() + iterator).getColor() != getColorToCheck()
						&& iterator == 1) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(getxCoordinate() + iterator, getyCoordinate()
								+ iterator)).contains("Figures.Figures.King")
						&& getFigure(getxCoordinate() + iterator,
								getyCoordinate() + iterator).getColor() != getColorToCheck()
						&& iterator == 1) {

					checkValue = true;

				} else {

					// -------------------------------------------------------------
					// IF NOTHING MATCHES THE LOOP CAN BE BREAKEN
					// -------------------------------------------------------------

					break;

				}

			}

			iterator = iterator + 1;

		}

		// -------------------------------------------------------------
		// CHECKS FROM KING TO TOP LEFT
		// SEARCHES FOR BISHOPS, QUEENS, KINGS AND PAWNS
		// -------------------------------------------------------------

		iterator = 1;

		while (getxCoordinate() + iterator <= 7
				&& getyCoordinate() - iterator >= 0) {

			if (getFigure(getxCoordinate() + iterator, getyCoordinate()
					- iterator) != null) {

				if (String.valueOf(
						getFigure(getxCoordinate() + iterator, getyCoordinate()
								- iterator)).contains("Figures.Figures.Bishop")
						&& getFigure(getxCoordinate() + iterator,
								getyCoordinate() - iterator).getColor() != getColorToCheck()) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(getxCoordinate() + iterator, getyCoordinate()
								- iterator)).contains("Figures.Figures.Queen")
						&& getFigure(getxCoordinate() + iterator,
								getyCoordinate() - iterator).getColor() != getColorToCheck()) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(getxCoordinate() + iterator, getyCoordinate()
								- iterator)).contains("Figures.Figures.Pawn")
						&& getFigure(getxCoordinate() + iterator,
								getyCoordinate() - iterator).getColor() == Color.BLACK
						&& getFigure(getxCoordinate() + iterator,
								getyCoordinate() - iterator).getColor() != getColorToCheck()
						&& iterator == 1) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(getxCoordinate() + iterator, getyCoordinate()
								- iterator)).contains("Figures.Figures.King")
						&& getFigure(getxCoordinate() + iterator,
								getyCoordinate() - iterator).getColor() != getColorToCheck()
						&& iterator == 1) {

					checkValue = true;

				} else {

					// -------------------------------------------------------------
					// IF NOTHING MATCHES THE LOOP CAN BE BREAKEN
					// -------------------------------------------------------------

					break;

				}

			}

			iterator = iterator + 1;

		}

		// -------------------------------------------------------------
		// CHECKS FROM KING TO BOTTOM LEFT
		// SEARCHES FOR BISHOPS, QUEENS, KINGS AND PAWNS
		// -------------------------------------------------------------

		iterator = 1;

		while (getxCoordinate() - iterator >= 0
				&& getyCoordinate() + iterator <= 7) {

			if (getFigure(getxCoordinate() - iterator, getyCoordinate()
					+ iterator) != null) {

				if (String.valueOf(
						getFigure(getxCoordinate() - iterator, getyCoordinate()
								+ iterator)).contains("Figures.Figures.Bishop")
						&& getFigure(getxCoordinate() - iterator,
								getyCoordinate() + iterator).getColor() != getColorToCheck()) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(getxCoordinate() - iterator, getyCoordinate()
								+ iterator)).contains("Figures.Figures.Queen")
						&& getFigure(getxCoordinate() - iterator,
								getyCoordinate() + iterator).getColor() != getColorToCheck()) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(getxCoordinate() - iterator, getyCoordinate()
								+ iterator)).contains("Figures.Figures.Pawn")
						&& getFigure(getxCoordinate() - iterator,
								getyCoordinate() + iterator).getColor() == Color.WHITE
						&& getFigure(getxCoordinate() - iterator,
								getyCoordinate() + iterator).getColor() != getColorToCheck()
						&& iterator == 1) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(getxCoordinate() - iterator, getyCoordinate()
								+ iterator)).contains("Figures.Figures.King")
						&& getFigure(getxCoordinate() - iterator,
								getyCoordinate() + iterator).getColor() != getColorToCheck()
						&& iterator == 1) {

					checkValue = true;

				} else {

					// -------------------------------------------------------------
					// IF NOTHING MATCHES THE LOOP CAN BE BREAKEN
					// -------------------------------------------------------------

					break;

				}

			}

			iterator = iterator + 1;

		}

		// -------------------------------------------------------------
		// CHECKS FROM KING TO TOP LEFT
		// SEARCHES FOR BISHOPS, QUEENS, KINGS AND PAWNS
		// -------------------------------------------------------------

		iterator = 1;

		while (getxCoordinate() - iterator >= 0
				&& getyCoordinate() - iterator >= 0) {

			if (getFigure(getxCoordinate() - iterator, getyCoordinate()
					- iterator) != null) {

				if (String.valueOf(
						getFigure(getxCoordinate() - iterator, getyCoordinate()
								- iterator)).contains("Figures.Figures.Bishop")
						&& getFigure(getxCoordinate() - iterator,
								getyCoordinate() - iterator).getColor() != getColorToCheck()) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(getxCoordinate() - iterator, getyCoordinate()
								- iterator)).contains("Figures.Figures.Queen")
						&& getFigure(getxCoordinate() - iterator,
								getyCoordinate() - iterator).getColor() != getColorToCheck()) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(getxCoordinate() - iterator, getyCoordinate()
								- iterator)).contains("Figures.Figures.Pawn")
						&& getFigure(getxCoordinate() - iterator,
								getyCoordinate() - iterator).getColor() == Color.BLACK
						&& getFigure(getxCoordinate() - iterator,
								getyCoordinate() - iterator).getColor() != getColorToCheck()
						&& iterator == 1) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(getxCoordinate() - iterator, getyCoordinate()
								- iterator)).contains("Figures.Figures.King")
						&& getFigure(getxCoordinate() - iterator,
								getyCoordinate() - iterator).getColor() != getColorToCheck()
						&& iterator == 1) {

					checkValue = true;

				} else {

					// -------------------------------------------------------------
					// IF NOTHING MATCHES THE LOOP CAN BE BREAKEN
					// -------------------------------------------------------------

					break;

				}

			}

			iterator = iterator + 1;

		}

		// -------------------------------------------------------------
		// CHECKS FOR KNIGHTS
		// -------------------------------------------------------------

		if (getxCoordinate() + 2 <= 7 && getyCoordinate() + 1 <= 7) {

			if (getFigure(getxCoordinate() + 2, getyCoordinate() + 1) != null
					&& String.valueOf(
							getFigure(getxCoordinate() + 2,
									getyCoordinate() + 1)).contains(
							"Figures.Figures.Knight")
					&& getFigure(getxCoordinate() + 2, getyCoordinate() + 1)
							.getColor() != getColorToCheck()) {

				checkValue = true;

			}

		}

		if (getxCoordinate() + 2 <= 7 && getyCoordinate() - 1 >= 0) {

			if (getFigure(getxCoordinate() + 2, getyCoordinate() - 1) != null
					&& String.valueOf(
							getFigure(getxCoordinate() + 2,
									getyCoordinate() - 1)).contains(
							"Figures.Figures.Knight")
					&& getFigure(getxCoordinate() + 2, getyCoordinate() - 1)
							.getColor() != getColorToCheck()) {

				checkValue = true;

			}

		}

		if (getxCoordinate() - 2 >= 0 && getyCoordinate() + 1 <= 7) {

			if (getFigure(getxCoordinate() - 2, getyCoordinate() + 1) != null
					&& String.valueOf(
							getFigure(getxCoordinate() - 2,
									getyCoordinate() + 1)).contains(
							"Figures.Figures.Knight")
					&& getFigure(getxCoordinate() - 2, getyCoordinate() + 1)
							.getColor() != getColorToCheck()) {

				checkValue = true;

			}

		}

		if (getxCoordinate() - 2 >= 0 && getyCoordinate() - 1 >= 0) {

			if (getFigure(getxCoordinate() - 2, getyCoordinate() - 1) != null
					&& String.valueOf(
							getFigure(getxCoordinate() - 2,
									getyCoordinate() - 1)).contains(
							"Figures.Figures.Knight")
					&& getFigure(getxCoordinate() - 2, getyCoordinate() - 1)
							.getColor() != getColorToCheck()) {

				checkValue = true;

			}

		}

		if (getxCoordinate() + 1 <= 7 && getyCoordinate() + 2 <= 7) {

			if (getFigure(getxCoordinate() + 1, getyCoordinate() + 2) != null
					&& String.valueOf(
							getFigure(getxCoordinate() + 1,
									getyCoordinate() + 2)).contains(
							"Figures.Figures.Knight")
					&& getFigure(getxCoordinate() + 1, getyCoordinate() + 2)
							.getColor() != getColorToCheck()) {

				checkValue = true;

			}

		}

		if (getxCoordinate() + 1 <= 7 && getyCoordinate() - 2 >= 0) {

			if (getFigure(getxCoordinate() + 1, getyCoordinate() - 2) != null
					&& String.valueOf(
							getFigure(getxCoordinate() + 1,
									getyCoordinate() - 2)).contains(
							"Figures.Figures.Knight")
					&& getFigure(getxCoordinate() + 1, getyCoordinate() - 2)
							.getColor() != getColorToCheck()) {

				checkValue = true;

			}

		}

		if (getxCoordinate() - 1 >= 0 && getyCoordinate() + 2 <= 7) {

			if (getFigure(getxCoordinate() - 1, getyCoordinate() + 2) != null
					&& String.valueOf(
							getFigure(getxCoordinate() - 1,
									getyCoordinate() + 2)).contains(
							"Figures.Figures.Knight")
					&& getFigure(getxCoordinate() - 1, getyCoordinate() + 2)
							.getColor() != getColorToCheck()) {

				checkValue = true;

			}

		}

		if (getxCoordinate() - 1 >= 0 && getyCoordinate() - 2 >= 0) {

			if (getFigure(getxCoordinate() - 1, getyCoordinate() - 2) != null
					&& String.valueOf(
							getFigure(getxCoordinate() - 1,
									getyCoordinate() - 2)).contains(
							"Figures.Figures.Knight")
					&& getFigure(getxCoordinate() - 1, getyCoordinate() - 2)
							.getColor() != getColorToCheck()) {

				checkValue = true;

			}

		}

		return checkValue;

	}
}