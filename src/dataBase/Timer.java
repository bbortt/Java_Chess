package dataBase;

import java.awt.event.WindowEvent;

import javax.swing.JFrame;
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
	private JFrame mainFrame;

	/**
	 * Starts new asynchronous thread which runs the timer. Sleeps for one
	 * second and counts timer -1. If time runs out, handles the event.
	 * 
	 * @param givenPlayerDatas
	 *            Requires the PlayerDatas to actualize the timer
	 * @param givenLabel
	 *            Requires the main-label for JOptionPane.showMessageDialog
	 * @param givenFrame
	 *            Requires the main-frame for JOptionPane.showMessageDialog
	 * */
	public Timer(final PlayerDatas givenPlayerDatas, JLabel givenJLabel,
			JFrame givenFrame) {

		playerDatas = givenPlayerDatas;
		label = givenJLabel;
		mainFrame = givenFrame;

		// -------------------------------------------------------------
		// IF TIME IS 0 --> NO TIME LIMIT
		// -------------------------------------------------------------

		if (playerDatas.getTimePlayer1() == 0
				&& playerDatas.getTimePlayer2() == 0) {

			label.setText("");
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

							JOptionPane.showMessageDialog(mainFrame,
									"Time run out!\n" + playerDatas.getOnTurn()
											+ " lost!", "Time run out",
									JOptionPane.INFORMATION_MESSAGE);

							mainFrame.dispatchEvent(new WindowEvent(mainFrame,
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

									label.setText("Remaining time: "
											+ playerDatas.getTimePlayer1()
											+ " seconds");

								} else {

									label.setText("Remaining time: "
											+ playerDatas.getTimePlayer2()
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