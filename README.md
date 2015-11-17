# Java_Chess

This application is a chess, written in java. It's free to use and share it. Commercial use is restricted.
<br><br>Note: This is a technical README! If you want to know the chess rules, have a look at this: https://en.wikipedia.org/wiki/Rules_of_chess

---------------------------------
<h2>The Main() class app.java</h2>
```Java
public static void main(String[] args) {

	// -------------------------------------------------------------
	
	settings = new Settings(playerDatas);
	
	// -------------------------------------------------------------

}
```
It just calles an instanze of Settings.java. This is the first GUI class where the users have to input their names and chose a maximal game time. No more variables or instanzes are created or saved in this class. Settings.java handles the start of a new game.
<h2>Settings.java</h2>
```Java
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
```
The layout looks quite difficult, but is very easy once you've seen it. There are two JTextFields (not in the layout) where the users can input their name. Further there is a JSlider which represents the timer. As you chose 0 as time, you will have an endless game (at least until someone got checked).
<h2>ChessBoard.java</h2>
```Java
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
```
Also here, we have the two names of the players in the top right an left corners and the timer right in the middle. In a square there are 8x8 buttons, each with an event handler. By clicking on them you select a coordinate and move a figure in a two dimensional array. Of course this only happens if the move was validated first. On the bottom of the window we have three  buttons: A button for the castlings, a button to suggest a draw and one to surrender.

<h2>Package figures.check</h2>
This package contains the classes which validate moves. By calling each classes check() method, they return true if the move is valid, or false if not. Basically they check for the right color, the right range, the right direction and the right aim. Also it is checked if nothing is in between the figure and the aim. If the king is checked and you dont change this with your move, the move() method returns false too.
<br>If this method returns false, the move will be undone in Game.java in method aTurn(). This is also the main computer class which handles the full game, once Settings.java was closed.

<h2>KingCheck.java</h2>
```Java
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
```
This class is there to check, wether the king of current player is checked. First of all it has to detect, where the king stands. This happens in a doubled for loop. Then it searches in every direction away from the king for an enemies figure. He is not checked if: The end of the game board is reached or a figure of the own color stands in between the other.

<br><br><strong>This is just a short introduction! For more informations have a look into the Java Documentation.</strong>

---------------------------------
All rights preserverd @ bbortt!
Contact me: timon.borter@post.ch

---------------------------------
