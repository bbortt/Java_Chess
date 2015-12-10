package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import main.Game;
import dataBase.PlayerDatas;
import figures.Figure;
import figures.check.CastlingCheck;

/**
 * This is the GUI which shows the game board. It implements different methods
 * like create the game board with buttons and event handlers.
 * 
 * @author Timon Borter
 * 
 */
public class ChessBoard extends JFrame {

	// -------------------------------------------------------------

	private static final long serialVersionUID = 1508838705655373228L;

	// -------------------------------------------------------------
	// LAOYUT (GRID IN BORDER IN FRAME)
	// -------------------------------------------------------------

	private static JFrame firstBorderLayoutFrame = new JFrame();
	private BorderLayout firstBorderLayout = new BorderLayout();

	private JPanel secondGridLayoutPanelNorth = new JPanel();
	private GridLayout secondGridLayoutNorth = new GridLayout(1, 3);
	Border secondGridLayoutNorthBorder = BorderFactory.createEmptyBorder(40,
			75, 40, 75);

	private JPanel secondGridLayoutPanelWest = new JPanel();
	private GridLayout secondGridLayoutWest = new GridLayout(1, 1);
	Border secondGridLayoutWestBorder = BorderFactory.createEmptyBorder(20, 20,
			20, 20);

	private JPanel secondGridLayoutPanelSouth = new JPanel();
	private GridLayout secondGridLayoutSouth = new GridLayout(3, 1);
	Border secondGridLayoutSouthBorder = BorderFactory.createEmptyBorder(20,
			340, 20, 340);

	private JPanel secondGridLayoutPanelEast = new JPanel();
	private GridLayout secondGridLayoutEast = new GridLayout(1, 1);
	Border secondGridLayoutEastBorder = BorderFactory.createEmptyBorder(20, 20,
			20, 20);

	private JPanel secondGridLayoutPanelCenter = new JPanel();
	private GridLayout secondGridLayoutCenter = new GridLayout(8, 8);
	Border secondGridLayoutCenterBorder = BorderFactory.createEmptyBorder(0,
			180, 0, 180);

	// -------------------------------------------------------------
	// CONTENTS
	// -------------------------------------------------------------

	JLabel labelNamePlayer1 = new JLabel();
	JLabel labelTimeTillEnd = new JLabel();
	JLabel labelNamePlayer2 = new JLabel();

	JButton castlingButton = new JButton();
	JButton drawButton = new JButton();
	JButton surrenderButton = new JButton();

	// -------------------------------------------------------------
	// VARIABLES
	// -------------------------------------------------------------

	private JButton[][] fields = new JButton[8][8];
	private PlayerDatas playerDatas;
	private Game game;
	private CastlingCheck castlingCheck;

	// -------------------------------------------------------------
	// REQUIRED GETTERS AND SETTERS
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
	 * Required to get the main instance of the game
	 * 
	 * @return The main Game
	 */
	public Game getGame() {

		return this.game;

	}

	/**
	 * Required to set the main instance of the game
	 * 
	 * @param game
	 *            The main Game
	 */
	public void setGame(Game game) {

		this.game = game;

	}

	/**
	 * The GUI for the match.
	 * 
	 * @param givenPlayerDatas
	 *            Requires PlayerDatas to display names and time
	 */
	public ChessBoard(PlayerDatas givenPlayerDatas, Figure[][] givenFigures) {

		// -------------------------------------------------------------
		// SAVE THE NEW PLAYER DATAS (CREATED IN app.java)
		// -------------------------------------------------------------

		playerDatas = givenPlayerDatas;

		// -------------------------------------------------------------
		// CREATES THE FRAME
		// -------------------------------------------------------------

		createGUI();

		// -------------------------------------------------------------
		// ADDS CONTENTS
		// -------------------------------------------------------------

		fillChessBoard();

		// -------------------------------------------------------------
		// CENTERS FRAM
		// SETS FRAME VISIBLE
		// -------------------------------------------------------------

		firstBorderLayoutFrame.setLocationRelativeTo(null);
		firstBorderLayoutFrame.setVisible(true);

		// -------------------------------------------------------------
		// STARTS GAME CLASS
		// -------------------------------------------------------------

		game = new Game(playerDatas, fields, givenFigures, labelNamePlayer1,
				labelTimeTillEnd, labelNamePlayer2, firstBorderLayoutFrame);

		// -------------------------------------------------------------
		// CASTS CASTLINGCHECK
		// -------------------------------------------------------------

		castlingCheck = new CastlingCheck(game, playerDatas, fields,
				givenFigures, firstBorderLayoutFrame);

	}

	/**
	 * Creates the GUI's layout.
	 */
	private void createGUI() {
		// -------------------------------------------------------------
		// CREATES FRAME
		// ADDS 3 PANELS IN NORTH, CENTER AND SOUTH
		// SIZE: 1000 X 8000
		// -------------------------------------------------------------

		firstBorderLayoutFrame.setResizable(false);
		firstBorderLayoutFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		firstBorderLayoutFrame.setLayout(firstBorderLayout);
		firstBorderLayoutFrame.setSize(new Dimension(1000, 800));
		firstBorderLayoutFrame.add(secondGridLayoutPanelNorth,
				BorderLayout.NORTH);
		firstBorderLayoutFrame
				.add(secondGridLayoutPanelEast, BorderLayout.EAST);
		firstBorderLayoutFrame
				.add(secondGridLayoutPanelWest, BorderLayout.WEST);
		firstBorderLayoutFrame.add(secondGridLayoutPanelCenter,
				BorderLayout.CENTER);
		firstBorderLayoutFrame.add(secondGridLayoutPanelSouth,
				BorderLayout.SOUTH);

		// -------------------------------------------------------------
		// CREATES GRID LAYOUT (1 ROW, 3 COLUMNS)
		// IN NORTH OF BORDER LAYOUT
		// -------------------------------------------------------------

		secondGridLayoutPanelNorth.setPreferredSize(new Dimension(800, 130));
		secondGridLayoutPanelNorth.setBorder(secondGridLayoutNorthBorder);
		secondGridLayoutPanelNorth.setLayout(secondGridLayoutNorth);

		// -------------------------------------------------------------
		// CREATES GRID LAYOUT (1 ROW, 1 COLUMN(
		// IN EAST OF BORDER LAYOUT
		// -------------------------------------------------------------

		secondGridLayoutPanelEast.setPreferredSize(new Dimension(40, 640));
		secondGridLayoutPanelEast.setBorder(secondGridLayoutEastBorder);
		secondGridLayoutPanelEast.setLayout(secondGridLayoutEast);

		// -------------------------------------------------------------
		// CREATES GRID LAYOUT (2 ROWS, 1 COLUMN)
		// IN SOUTH OF BORDER LAYOUT
		// -------------------------------------------------------------

		secondGridLayoutPanelSouth.setPreferredSize(new Dimension(800, 130));
		secondGridLayoutPanelSouth.setBorder(secondGridLayoutSouthBorder);
		secondGridLayoutPanelSouth.setLayout(secondGridLayoutSouth);

		// -------------------------------------------------------------
		// CREATES GRID LAYOUT (1 ROW, 1 COLUMN)
		// IN WEST OF BORDER LAYOUT
		// -------------------------------------------------------------

		secondGridLayoutPanelWest.setPreferredSize(new Dimension(40, 640));
		secondGridLayoutPanelWest.setBorder(secondGridLayoutWestBorder);
		secondGridLayoutPanelWest.setLayout(secondGridLayoutWest);

		// -------------------------------------------------------------
		// CREATES GRID LAYOUT (8 ROWS, 8 COLUMN)
		// IN CENTER OF BORDER LAYOUT
		// -------------------------------------------------------------

		secondGridLayoutPanelCenter.setBorder(secondGridLayoutCenterBorder);
		secondGridLayoutPanelCenter.setLayout(secondGridLayoutCenter);

	}

	/**
	 * Fills the layout with contents.
	 */
	public void fillChessBoard() {

		// -------------------------------------------------------------
		// COLORS AND BACKGROUNDS
		// -------------------------------------------------------------

		// -------------------------------------------------------------
		// CREATES CHESS FIELDS AS BUTTONS
		// 8 X 8 BUTTONS IN AN ARRAY (X, Y)
		// ADDS NEW BUTTONS TO GUI
		// -------------------------------------------------------------

		Color color;

		for (int y = 0; y < 8; y = y + 1) {

			for (int x = 0; x < 8; x = x + 1) {

				// -------------------------------------------------------------
				// SETS UP BACKGROUND COLORS OF FIELDS
				// -------------------------------------------------------------

				if (y % 2 == 0) {

					if (x % 2 == 0) {

						color = Color.BLACK;

					} else {

						color = Color.WHITE;

					}

				} else {

					if (x % 2 == 0) {

						color = Color.WHITE;

					} else {

						color = Color.BLACK;

					}

				}

				setField(
						x,
						y,
						createNewButton(
								Integer.toString(x + 1) + "."
										+ Integer.toString(y + 1), color));
				secondGridLayoutPanelCenter.add(fields[x][y]);

			}

		}

		// -------------------------------------------------------------
		// ADDS NAMES OF THE TWO PLAYERS
		// -------------------------------------------------------------

		labelNamePlayer1.setText(playerDatas.getNamePlayer1());
		labelNamePlayer1.setHorizontalAlignment(SwingConstants.LEFT);
		secondGridLayoutPanelNorth.add(labelNamePlayer1);

		labelTimeTillEnd.setHorizontalAlignment(SwingConstants.CENTER);
		secondGridLayoutPanelNorth.add(labelTimeTillEnd);

		labelNamePlayer2.setText(playerDatas.getNamePlayer2());
		labelNamePlayer2.setHorizontalAlignment(SwingConstants.RIGHT);
		secondGridLayoutPanelNorth.add(labelNamePlayer2);

		// -------------------------------------------------------------
		// ADDS castling BUTTON
		// -------------------------------------------------------------

		castlingButton.setText("Castling");
		castlingButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int response = JOptionPane
						.showConfirmDialog(
								firstBorderLayoutFrame,
								"Do a castling.\n\"YES\" for the left one, \"NO\" for the right one!",
								"Castling", JOptionPane.YES_NO_OPTION,
								JOptionPane.QUESTION_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {

					castlingCheck.leftCastling();

				} else {

					castlingCheck.rightCastling();

				}

			}

		});

		secondGridLayoutPanelSouth.add(castlingButton);

		// -------------------------------------------------------------
		// ADDS draw BUTTON
		// -------------------------------------------------------------

		drawButton.setText("Suggest a draw");
		drawButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				int response = JOptionPane.showConfirmDialog(
						firstBorderLayoutFrame, playerDatas.getOnTurn()
								+ " suggests a draw.\nAccept?", "Draw",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

				if (response == JOptionPane.YES_OPTION) {

					JOptionPane.showMessageDialog(firstBorderLayoutFrame,
							"The game ends in a draw.", "Draw",
							JOptionPane.INFORMATION_MESSAGE);

					firstBorderLayoutFrame
							.dispatchEvent(new WindowEvent(
									firstBorderLayoutFrame,
									WindowEvent.WINDOW_CLOSING));

				}

			}

		});

		secondGridLayoutPanelSouth.add(drawButton);

		// -------------------------------------------------------------
		// ADDS SURRENDER BUTTON
		// -------------------------------------------------------------

		surrenderButton.setText("Surrender");
		surrenderButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				JOptionPane.showMessageDialog(firstBorderLayoutFrame,
						playerDatas.getOnTurn() + " did surrender!",
						"Surrendered", JOptionPane.INFORMATION_MESSAGE);

				firstBorderLayoutFrame.dispatchEvent(new WindowEvent(
						firstBorderLayoutFrame, WindowEvent.WINDOW_CLOSING));

			}

		});

		secondGridLayoutPanelSouth.add(surrenderButton);

	}

	/**
	 * Creates a new button with MouseListener().
	 * 
	 * @param givenName
	 *            Requires the fields name, constructed out of his coordinates
	 * @param givenColor
	 *            Requires the fields color
	 * @return Returns a complete JButton with MouseListener()
	 */
	private JButton createNewButton(String givenName, Color givenColor) {
		final JButton button = new JButton();
		button.setFont(new Font("Serif", Font.BOLD, 30));
		button.setName(givenName);
		button.setBackground(givenColor);
		button.setFocusPainted(false);
		button.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {

				// TODO Auto-generated method stub

			}

			@Override
			public void mousePressed(MouseEvent e) {

				// TODO Auto-generated method stub

			}

			@Override
			public void mouseExited(MouseEvent e) {

				// TODO Auto-generated method stub

			}

			@Override
			public void mouseEntered(MouseEvent e) {

				// TODO Auto-generated method stub

			}

			@Override
			public void mouseClicked(MouseEvent e) {

				// -------------------------------------------------------------
				// EVENT TO HAPPEN IF LEFT CLICK
				// -------------------------------------------------------------

				if (SwingUtilities.isLeftMouseButton(e)) {

					// -------------------------------------------------------------
					// PRINTS SOME INFO FOR THE PROGRAMMER
					// UPDATES PLAYERDATAS
					// REMOVES OLD BORDERS
					// CREATES NEW BORDER
					// -------------------------------------------------------------

					playerDatas.setFromSelectedJButton(button.getName());

					game.clearBoard();

					button.setBorder(new LineBorder(Color.RED, 2));

				} else {

					// -------------------------------------------------------------
					// EVENT TO HAPPEN IF RIGHT CLICK
					// -------------------------------------------------------------

					if (SwingUtilities.isRightMouseButton(e)) {

						// -------------------------------------------------------------
						// UPDATES PLAYERDATAS
						// REMOVES OLD BORDERS
						// CREATES NEW BORDER
						// -------------------------------------------------------------

						playerDatas.setToSelectedJButton(button.getName());

						game.clearBoard();

						button.setBorder(new LineBorder(Color.RED, 2));

					}

				}

			}

		});

		return button;

	}

}