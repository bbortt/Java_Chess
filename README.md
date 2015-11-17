# Java_Chess

This application is a chess, written in java. It's free to use and share it. Commercial use is restricted.
<br><br>Note: This is a technical README! If you want to know the ches rules, have a look at this: https://en.wikipedia.org/wiki/Rules_of_chess

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

<br><br><strong>This is just a short introduction! For more informations have a look into the Java Documentation.</strong>

---------------------------------
All rights preserverd @ bbortt!
Contact me: timon.borter@post.ch

---------------------------------
