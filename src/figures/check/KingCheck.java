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
	 * Checks if someone's king is checked.
	 * 
	 * @param givenColorToCheck
	 *            The color of the player to check
	 * @param givenFigures
	 *            Requires every figures position
	 * @return Returns true if the king is not checked
	 */
	public boolean check(Color givenColorToCheck, Figure[][] givenFigures) {

		// -------------------------------------------------------------
		// UPDATE THE PRIVATE VARIABLES
		// -------------------------------------------------------------

		colorToCheck = givenColorToCheck;
		figures = givenFigures;

		// -------------------------------------------------------------
		// DETECTS THE KING
		// -------------------------------------------------------------

		for (int y = 0; y < 8; y = y + 1) {

			for (int x = 0; x < 8; x = x + 1) {

				if (String.valueOf(getFigure(x, y)).contains(
						"figures.figures.King")
						&& getFigure(x, y).getColor() == colorToCheck) {

					xCoordinate = x;
					yCoordinate = y;

					// -------------------------------------------------------------
					// BREAKS THE LOOP
					// -------------------------------------------------------------

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

		while (yCoordinate - iterator >= 0) {

			if (getFigure(xCoordinate, yCoordinate - iterator) != null) {

				if (String.valueOf(
						getFigure(xCoordinate, yCoordinate - iterator))
						.contains("figures.figures.Rook")
						&& getFigure(xCoordinate, yCoordinate - iterator)
								.getColor() != colorToCheck) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate, yCoordinate - iterator))
						.contains("figures.figures.Queen")
						&& getFigure(xCoordinate, yCoordinate - iterator)
								.getColor() != colorToCheck) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate, yCoordinate - iterator))
						.contains("figures.figures.King")
						&& getFigure(xCoordinate, yCoordinate - iterator)
								.getColor() != colorToCheck && iterator == 1) {

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

		while (xCoordinate + iterator <= 7) {

			if (getFigure(xCoordinate + iterator, yCoordinate) != null) {

				if (String.valueOf(
						getFigure(xCoordinate + iterator, yCoordinate))
						.contains("figures.figures.Rook")
						&& getFigure(xCoordinate + iterator, yCoordinate)
								.getColor() != colorToCheck) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate + iterator, yCoordinate))
						.contains("figures.figures.Queen")
						&& getFigure(xCoordinate + iterator, yCoordinate)
								.getColor() != colorToCheck) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate + iterator, yCoordinate))
						.contains("figures.figures.King")
						&& getFigure(xCoordinate, yCoordinate + iterator)
								.getColor() != colorToCheck && iterator == 1) {

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

		while (yCoordinate + iterator <= 7) {

			if (getFigure(xCoordinate, yCoordinate + iterator) != null) {

				if (String.valueOf(
						getFigure(xCoordinate, yCoordinate + iterator))
						.contains("figures.figures.Rook")
						&& getFigure(xCoordinate, yCoordinate + iterator)
								.getColor() != colorToCheck) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate, yCoordinate + iterator))
						.contains("figures.figures.Queen")
						&& getFigure(xCoordinate, yCoordinate + iterator)
								.getColor() != colorToCheck) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate, yCoordinate + iterator))
						.contains("figures.figures.King")
						&& getFigure(xCoordinate, yCoordinate + iterator)
								.getColor() != colorToCheck && iterator == 1) {

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

		while (xCoordinate - iterator >= 0) {

			if (getFigure(xCoordinate - iterator, yCoordinate) != null) {

				if (String.valueOf(
						getFigure(xCoordinate - iterator, yCoordinate))
						.contains("figures.figures.Rook")
						&& getFigure(xCoordinate - iterator, yCoordinate)
								.getColor() != colorToCheck) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate - iterator, yCoordinate))
						.contains("figures.figures.Queen")
						&& getFigure(xCoordinate - iterator, yCoordinate)
								.getColor() != colorToCheck) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate - iterator, yCoordinate))
						.contains("figures.figures.King")
						&& getFigure(xCoordinate, yCoordinate - iterator)
								.getColor() != colorToCheck && iterator == 1) {

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

		while (xCoordinate + iterator <= 7 && yCoordinate + iterator <= 7) {

			if (getFigure(xCoordinate + iterator, yCoordinate + iterator) != null) {

				if (String.valueOf(
						getFigure(xCoordinate + iterator, yCoordinate
								+ iterator)).contains("figures.figures.Bishop")
						&& getFigure(xCoordinate + iterator,
								yCoordinate + iterator).getColor() != colorToCheck) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate + iterator, yCoordinate
								+ iterator)).contains("figures.figures.Queen")
						&& getFigure(xCoordinate + iterator,
								yCoordinate + iterator).getColor() != colorToCheck) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate + iterator, yCoordinate
								+ iterator)).contains("figures.figures.Pawn")
						&& getFigure(xCoordinate + iterator,
								yCoordinate + iterator).getColor() == Color.WHITE
						&& getFigure(xCoordinate + iterator,
								yCoordinate + iterator).getColor() != colorToCheck
						&& iterator == 1) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate + iterator, yCoordinate
								+ iterator)).contains("figures.figures.King")
						&& getFigure(xCoordinate + iterator,
								yCoordinate + iterator).getColor() != colorToCheck
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

		while (xCoordinate + iterator <= 7 && yCoordinate - iterator >= 0) {

			if (getFigure(xCoordinate + iterator, yCoordinate - iterator) != null) {

				if (String.valueOf(
						getFigure(xCoordinate + iterator, yCoordinate
								- iterator)).contains("figures.figures.Bishop")
						&& getFigure(xCoordinate + iterator,
								yCoordinate - iterator).getColor() != colorToCheck) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate + iterator, yCoordinate
								- iterator)).contains("figures.figures.Queen")
						&& getFigure(xCoordinate + iterator,
								yCoordinate - iterator).getColor() != colorToCheck) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate + iterator, yCoordinate
								- iterator)).contains("figures.figures.Pawn")
						&& getFigure(xCoordinate + iterator,
								yCoordinate - iterator).getColor() == Color.BLACK
						&& getFigure(xCoordinate + iterator,
								yCoordinate - iterator).getColor() != colorToCheck
						&& iterator == 1) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate + iterator, yCoordinate
								- iterator)).contains("figures.figures.King")
						&& getFigure(xCoordinate + iterator,
								yCoordinate - iterator).getColor() != colorToCheck
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

		while (xCoordinate - iterator >= 0 && yCoordinate + iterator <= 7) {

			if (getFigure(xCoordinate - iterator, yCoordinate + iterator) != null) {

				if (String.valueOf(
						getFigure(xCoordinate - iterator, yCoordinate
								+ iterator)).contains("figures.figures.Bishop")
						&& getFigure(xCoordinate - iterator,
								yCoordinate + iterator).getColor() != colorToCheck) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate - iterator, yCoordinate
								+ iterator)).contains("figures.figures.Queen")
						&& getFigure(xCoordinate - iterator,
								yCoordinate + iterator).getColor() != colorToCheck) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate - iterator, yCoordinate
								+ iterator)).contains("figures.figures.Pawn")
						&& getFigure(xCoordinate - iterator,
								yCoordinate + iterator).getColor() == Color.WHITE
						&& getFigure(xCoordinate - iterator,
								yCoordinate + iterator).getColor() != colorToCheck
						&& iterator == 1) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate - iterator, yCoordinate
								+ iterator)).contains("figures.figures.King")
						&& getFigure(xCoordinate - iterator,
								yCoordinate + iterator).getColor() != colorToCheck
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

		while (xCoordinate - iterator >= 0 && yCoordinate - iterator >= 0) {

			if (getFigure(xCoordinate - iterator, yCoordinate - iterator) != null) {

				if (String.valueOf(
						getFigure(xCoordinate - iterator, yCoordinate
								- iterator)).contains("figures.figures.Bishop")
						&& getFigure(xCoordinate - iterator,
								yCoordinate - iterator).getColor() != colorToCheck) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate - iterator, yCoordinate
								- iterator)).contains("figures.figures.Queen")
						&& getFigure(xCoordinate - iterator,
								yCoordinate - iterator).getColor() != colorToCheck) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate - iterator, yCoordinate
								- iterator)).contains("figures.figures.Pawn")
						&& getFigure(xCoordinate - iterator,
								yCoordinate - iterator).getColor() == Color.BLACK
						&& getFigure(xCoordinate - iterator,
								yCoordinate - iterator).getColor() != colorToCheck
						&& iterator == 1) {

					checkValue = true;

				} else if (String.valueOf(
						getFigure(xCoordinate - iterator, yCoordinate
								- iterator)).contains("figures.figures.King")
						&& getFigure(xCoordinate - iterator,
								yCoordinate - iterator).getColor() != colorToCheck
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

		if (xCoordinate + 2 <= 7 && yCoordinate + 1 <= 7) {

			if (getFigure(xCoordinate + 2, yCoordinate + 1) != null
					&& String.valueOf(
							getFigure(xCoordinate + 2, yCoordinate + 1))
							.contains("figures.figures.Knight")
					&& getFigure(xCoordinate + 2, yCoordinate + 1).getColor() != colorToCheck) {

				checkValue = true;

			}

		}

		if (xCoordinate + 2 <= 7 && yCoordinate - 1 >= 0) {

			if (getFigure(xCoordinate + 2, yCoordinate - 1) != null
					&& String.valueOf(
							getFigure(xCoordinate + 2, yCoordinate - 1))
							.contains("figures.figures.Knight")
					&& getFigure(xCoordinate + 2, yCoordinate - 1).getColor() != colorToCheck) {

				checkValue = true;

			}

		}

		if (xCoordinate - 2 >= 0 && yCoordinate + 1 <= 7) {

			if (getFigure(xCoordinate - 2, yCoordinate + 1) != null
					&& String.valueOf(
							getFigure(xCoordinate - 2, yCoordinate + 1))
							.contains("figures.figures.Knight")
					&& getFigure(xCoordinate - 2, yCoordinate + 1).getColor() != colorToCheck) {

				checkValue = true;

			}

		}

		if (xCoordinate - 2 >= 0 && yCoordinate - 1 >= 0) {

			if (getFigure(xCoordinate - 2, yCoordinate - 1) != null
					&& String.valueOf(
							getFigure(xCoordinate - 2, yCoordinate - 1))
							.contains("figures.figures.Knight")
					&& getFigure(xCoordinate - 2, yCoordinate - 1).getColor() != colorToCheck) {

				checkValue = true;

			}

		}

		if (xCoordinate + 1 <= 7 && yCoordinate + 2 <= 7) {

			if (getFigure(xCoordinate + 1, yCoordinate + 2) != null
					&& String.valueOf(
							getFigure(xCoordinate + 1, yCoordinate + 2))
							.contains("figures.figures.Knight")
					&& getFigure(xCoordinate + 1, yCoordinate + 2).getColor() != colorToCheck) {

				checkValue = true;

			}

		}

		if (xCoordinate + 1 <= 7 && yCoordinate - 2 >= 0) {

			if (getFigure(xCoordinate + 1, yCoordinate - 2) != null
					&& String.valueOf(
							getFigure(xCoordinate + 1, yCoordinate - 2))
							.contains("figures.figures.Knight")
					&& getFigure(xCoordinate + 1, yCoordinate - 2).getColor() != colorToCheck) {

				checkValue = true;

			}

		}

		if (xCoordinate - 1 >= 0 && yCoordinate + 2 <= 7) {

			if (getFigure(xCoordinate - 1, yCoordinate + 2) != null
					&& String.valueOf(
							getFigure(xCoordinate - 1, yCoordinate + 2))
							.contains("figures.figures.Knight")
					&& getFigure(xCoordinate - 1, yCoordinate + 2).getColor() != colorToCheck) {

				checkValue = true;

			}

		}

		if (xCoordinate - 1 >= 0 && yCoordinate - 2 >= 0) {

			if (getFigure(xCoordinate - 1, yCoordinate - 2) != null
					&& String.valueOf(
							getFigure(xCoordinate - 1, yCoordinate - 2))
							.contains("figures.figures.Knight")
					&& getFigure(xCoordinate - 1, yCoordinate - 2).getColor() != colorToCheck) {

				checkValue = true;

			}

		}

		return checkValue;

	}
}