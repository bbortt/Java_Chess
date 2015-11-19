package dataBase;

import gui.ChessBoard;

import java.awt.event.WindowEvent;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

/**
 * This class runs in an asynchronous task which sleeps for one second and then
 * counts -1 to the PlayerDatas.getTimer() for each player. It also handles the
 * event of out ran time.
 * 
 * @author Timon Borter
 * 
 */
public class Timer {

	// -------------------------------------------------------------
	// VARIABLES
	// -------------------------------------------------------------

	private PlayerDatas playerDatas;
	private JLabel label;

	// -------------------------------------------------------------
	// PUBLIC GETTERS AND SETTERS
	// -------------------------------------------------------------

	public PlayerDatas getPlayerDatas() {
		return this.playerDatas;

	}

	public void setPlayerDatas(PlayerDatas playerDatas) {
		this.playerDatas = playerDatas;

	}

	public JLabel getLabel() {
		return label;

	}

	public void setLabel(JLabel label) {
		this.label = label;

	}

	/**
	 * Starts new asynchronous thread which runs the timer. Sleeps for one
	 * second and counts timer -1. If time runs out, handles the event.
	 * 
	 * @param playerDatas
	 *            Requires the PlayerDatas to actualize the timer
	 * @param label
	 *            Requires the main-label for JOptionPane.showMessageDialog
	 * */
	public Timer(final PlayerDatas playerDatas, JLabel label) {

		setPlayerDatas(playerDatas);
		setLabel(label);

		// -------------------------------------------------------------
		// IF TIME IS 0 --> NO TIME LIMIT
		// -------------------------------------------------------------

		if (getPlayerDatas().getTimePlayer1() == 0
				&& getPlayerDatas().getTimePlayer2() == 0) {

			getLabel().setText("");
			Thread.currentThread().interrupt();

		} else {

			// -------------------------------------------------------------
			// OPENS TIMER IN NEW THREAD
			// -------------------------------------------------------------

			new Thread(new Runnable() {

				// -------------------------------------------------------------
				// ACTION IF THREAD STARTS RUNNING
				// -------------------------------------------------------------

				@Override
				public void run() {

					// -------------------------------------------------------------
					// ENDLESS (TILL CLOSE)
					// -------------------------------------------------------------

					while (true) {

						// -------------------------------------------------------------
						// CHECKS IF SOMEONE RUNNED OUT OF TIME
						// -------------------------------------------------------------

						if (playerDatas.getTimePlayer1() <= 0
								|| playerDatas.getTimePlayer2() <= 0) {

							JOptionPane.showMessageDialog(
									ChessBoard.getMainFrame(),
									"Time run out!\n"
											+ getPlayerDatas().getOnTurn()
											+ " lost!", "Time run out",
									JOptionPane.INFORMATION_MESSAGE);

							ChessBoard.getMainFrame().dispatchEvent(
									new WindowEvent(ChessBoard.getMainFrame(),
											WindowEvent.WINDOW_CLOSING));

						}

						// -------------------------------------------------------------
						// REFRESHS THE TIME
						// -------------------------------------------------------------

						if (playerDatas.getOnTurn().equals(
								playerDatas.getNamePlayer1())) {

							playerDatas.setTimePlayer1(playerDatas
									.getTimePlayer1() - 1);

						} else {

							playerDatas.setTimePlayer2(playerDatas
									.getTimePlayer2() - 1);

						}

						// -------------------------------------------------------------
						// ACTION IN LABEL
						// HAS TO UPDATE THREAD WITHOUT INTERRUPT
						// -------------------------------------------------------------

						SwingUtilities.invokeLater(new Runnable() {

							@Override
							public void run() {

								// -------------------------------------------------------------
								// REFRESHS LABEL
								// -------------------------------------------------------------

								if (playerDatas.getOnTurn().equals(
										playerDatas.getNamePlayer1())) {

									getLabel().setText(
											"Remaining time: "
													+ playerDatas
															.getTimePlayer1()
													+ " seconds");

								} else {

									getLabel().setText(
											"Remaining time: "
													+ playerDatas
															.getTimePlayer2()
													+ " seconds");

								}

							}

						});

						// -------------------------------------------------------------
						// SLEEPS THREAD FOR 1 SECOND (EVERY SECOND)
						// -------------------------------------------------------------

						try {

							Thread.sleep(999);

						} catch (Exception e) {

							System.err.println(e.getMessage());

						}

					}

				}

			}).start();

		}

	}

}