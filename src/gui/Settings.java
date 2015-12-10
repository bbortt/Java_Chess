package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import dataBase.PlayerDatas;
import figures.Figure;

/**
 * This is the GUI which shows the settings. It implements different methods
 * like create the layout check the inputs and start a new game.
 * 
 * @author Timon Borter
 * 
 */
public class Settings extends JFrame {

	// -------------------------------------------------------------

	private static final long serialVersionUID = -2439118975423781454L;

	// -------------------------------------------------------------
	// LAOYUT (GRID IN GRID IN BORDER IN FRAME)
	// -------------------------------------------------------------

	JFrame firstBorderLayoutFrame = new JFrame();
	BorderLayout firstBorderLayout = new BorderLayout();

	JPanel secondGridLayoutPanel = new JPanel();
	GridLayout secondGridLayout = new GridLayout(2, 1);
	Border secondGridLayoutPanelBorder = BorderFactory.createEmptyBorder(130,
			170, 130, 170);

	JPanel thirdGridLayoutPanelTop = new JPanel();
	JPanel thirdGridLayoutPanelBottom = new JPanel();
	GridLayout thirdGridLayoutTop = new GridLayout(1, 3);
	GridLayout thirdGridLayoutBottom = new GridLayout(2, 1);

	JPanel fourthPanelTopFirst = new JPanel();
	JPanel fourthPanelTopSecond = new JPanel();
	JPanel fourthPanelTopThird = new JPanel();
	JPanel fourthPanelBottomTop = new JPanel();
	JPanel fourthPanelBottomBottom = new JPanel();
	GridLayout finalGridLayout = new GridLayout(2, 1);
	Border fourthPanelTopBorder = BorderFactory.createEmptyBorder(75, 50, 100,
			50);
	Border fourthPanelBottomBorder = BorderFactory.createEmptyBorder(10, 100,
			10, 100);

	// -------------------------------------------------------------
	// CONTENTS
	// -------------------------------------------------------------

	JLabel labelNamePlayer1 = new JLabel();
	JTextField fieldNamePlayer1 = new JTextField();
	JLabel labelNamePlayer2 = new JLabel();
	JTextField fieldNamePlayer2 = new JTextField();

	JLabel labelTimeSlider = new JLabel();
	JSlider timeSlider = new JSlider(JSlider.HORIZONTAL, 0, 10, 5);

	JButton startButton = new JButton("Start Game");

	ChessBoard chessBoard;

	// -------------------------------------------------------------
	// VARIABLES
	// -------------------------------------------------------------

	private PlayerDatas playerDatas;

	// -------------------------------------------------------------
	// PUBLIC GETTERS AND SETTERS OF CLASS
	// -------------------------------------------------------------

	/**
	 * Required to read the time sliders position
	 * 
	 * @return Time for each player
	 */
	public int getPositionTimeSlider() {

		return (timeSlider.getValue());

	}

	/**
	 * Required to read player one's name
	 * 
	 * @return Name player one
	 */
	public String getValueName1() {

		return (fieldNamePlayer1.getText());

	}

	/**
	 * Required to read player two's name
	 * 
	 * @return Name player two
	 */
	public String getValueName2() {

		return (fieldNamePlayer2.getText());

	}

	/**
	 * The GUI for the settings.
	 * 
	 * @param givenPlayerDatas
	 *            Requires PlayerDatas to save names and time
	 */
	public Settings(PlayerDatas givenPlayerDatas) {

		// -------------------------------------------------------------
		// SAVE THE NEW PLAYER DATAS (CREATED IN app.java)
		// -------------------------------------------------------------

		playerDatas = givenPlayerDatas;

		// -------------------------------------------------------------
		// CREATES THE FRAME
		// -------------------------------------------------------------

		createGUI();

		// -------------------------------------------------------------
		// ADDS CONTENTS TO FRAME
		// -------------------------------------------------------------

		fillSettings();

		// -------------------------------------------------------------
		// CENTERS FRAME
		// SETS FRAME VISIBLE
		// -------------------------------------------------------------

		firstBorderLayoutFrame.setLocationRelativeTo(null);
		firstBorderLayoutFrame.setVisible(true);

	}

	/**
	 * Creates the GUI's layout.
	 */
	private void createGUI() {

		// -------------------------------------------------------------
		// CREATES FRAME WITH BORDER LAYOUT
		// ADDS PANEL IN CENTER
		// SIZE: 1000 X 8000
		// -------------------------------------------------------------
		firstBorderLayoutFrame.setResizable(false);
		firstBorderLayoutFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		firstBorderLayoutFrame.setLayout(firstBorderLayout);
		firstBorderLayoutFrame.setSize(new Dimension(1000, 800));
		firstBorderLayoutFrame.add(secondGridLayoutPanel, BorderLayout.CENTER);

		// -------------------------------------------------------------
		// CREATES GRID LAYOUT (2 ROWS, 1 COLUMN)
		// ADDS 2 PANELS, 1 IN EACH ROW
		// IN CENTER OF BORDER LAYOUT
		// -------------------------------------------------------------

		secondGridLayoutPanel.setBorder(secondGridLayoutPanelBorder);
		secondGridLayoutPanel.setLayout(secondGridLayout);
		secondGridLayoutPanel.add(thirdGridLayoutPanelTop);
		secondGridLayoutPanel.add(thirdGridLayoutPanelBottom);

		// -------------------------------------------------------------
		// CREATES GRID LAYOUT (1 ROW, 3 COLUMNS)
		// ADDS 3 PANELS, 1 IN EACH COLUMN
		// IN TOP OF GRID LAYOUT
		// -------------------------------------------------------------

		thirdGridLayoutPanelTop.setLayout(thirdGridLayoutTop);
		thirdGridLayoutPanelTop.add(fourthPanelTopFirst);
		thirdGridLayoutPanelTop.add(fourthPanelTopSecond);
		thirdGridLayoutPanelTop.add(fourthPanelTopThird);

		// -------------------------------------------------------------
		// CREATES GRID LAYOUT (2 ROWS, 1 COLUMN)
		// ADDS 2 PANELS, 1 IN EACH ROW
		// IN BOTTOM OF GRID LAYOUT
		// -------------------------------------------------------------

		thirdGridLayoutPanelBottom.setLayout(thirdGridLayoutBottom);
		thirdGridLayoutPanelBottom.add(fourthPanelBottomTop);
		thirdGridLayoutPanelBottom.add(fourthPanelBottomBottom);

		// -------------------------------------------------------------
		// CREATES GRID LAYOUT (2 ROWS, 1 COLUMN)
		// LABEL AND TEXTFIELD GONNA BE INSIDE ON TOP
		// ADDS JPANEL ON BOTTOM
		// -------------------------------------------------------------

		fourthPanelTopFirst.setBorder(fourthPanelTopBorder);
		fourthPanelTopFirst.setLayout(finalGridLayout);
		fourthPanelTopThird.setBorder(fourthPanelTopBorder);
		fourthPanelTopThird.setLayout(finalGridLayout);
		fourthPanelBottomTop.setBorder(fourthPanelBottomBorder);
		fourthPanelBottomTop.setLayout(finalGridLayout);

	}

	/**
	 * Fills the layout with contents.
	 */
	private void fillSettings() {

		// -------------------------------------------------------------
		// COLORS AND BACKGROUNDS
		// -------------------------------------------------------------

		// -------------------------------------------------------------
		// SETS THE NAMEBOXES ON TOP OF THE WINDOWS
		// -------------------------------------------------------------

		labelNamePlayer1.setText("Name Player 1: ");
		labelNamePlayer1.setHorizontalAlignment(SwingConstants.CENTER);
		fourthPanelTopFirst.add(labelNamePlayer1);
		fourthPanelTopFirst.add(fieldNamePlayer1);

		labelNamePlayer2.setText("Name Player 2: ");
		labelNamePlayer2.setHorizontalAlignment(SwingConstants.CENTER);
		fourthPanelTopThird.add(labelNamePlayer2);
		fourthPanelTopThird.add(fieldNamePlayer2);

		// -------------------------------------------------------------
		// CREATES TIME SLIDER (0 = NO TIME LIMIT)
		// -------------------------------------------------------------

		labelTimeSlider.setText("Set Timer: ");
		labelTimeSlider.setHorizontalAlignment(SwingConstants.CENTER);
		fourthPanelBottomTop.add(labelTimeSlider);

		timeSlider.setMajorTickSpacing(1);
		timeSlider.setPaintTicks(true);
		timeSlider.setPaintLabels(true);
		timeSlider.setName("Time-Slider");
		fourthPanelBottomTop.add(timeSlider);

		// -------------------------------------------------------------
		// CREATES START BUTTON
		// -------------------------------------------------------------

		startButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				checkStart();

			}

		});

		fourthPanelBottomBottom.add(startButton);

	}

	/**
	 * Checks if every input is done, safes the data and starts the game then.
	 */
	private void checkStart() {

		if (!getValueName1().equals("") && !getValueName2().equals("")) {

			playerDatas.setNamePlayer1(getValueName1());
			playerDatas.setNamePlayer2(getValueName2());
			playerDatas.setTimePlayer1(getPositionTimeSlider() * 60);
			playerDatas.setTimePlayer2(getPositionTimeSlider() * 60);
			playerDatas.setOnTurn(playerDatas.getNamePlayer1());
			playerDatas.setColorPlayerOnTurn(Color.WHITE);

			int response = JOptionPane
					.showConfirmDialog(
							firstBorderLayoutFrame,
							"Choose the figure to move with a left click. With right click, choose where to move."
									+ "\nIncorrect moves are not accepted!",
							"Hint", JOptionPane.YES_NO_OPTION,
							JOptionPane.QUESTION_MESSAGE);

			if (response == JOptionPane.YES_OPTION) {

				Figure[][] figures = new Figure[8][8];

				chessBoard = new ChessBoard(playerDatas, figures);
				chessBoard.getGame().theTimer();
				chessBoard.getGame().defaultSetUp();
				new Thread(new Runnable() {

					@Override
					public void run() {

						chessBoard.getGame().aTurn();

					}

				}).start();

			}

			firstBorderLayoutFrame.dispatchEvent(new WindowEvent(
					firstBorderLayoutFrame, WindowEvent.WINDOW_CLOSING));

		} else {

			JOptionPane.showMessageDialog(firstBorderLayoutFrame,
					"Not every input is correct!", "Error",
					JOptionPane.ERROR_MESSAGE);

		}

	}

}