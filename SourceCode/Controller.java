/**
 * This class handles the communication between the model (game)
 * and the GUI (mainwindow).
 */

/**
 * @author Michael
 *
 */
public class Controller {

	private MainWindow mainwin;
	private Game game;
	private boolean gameOver;
	
	public Controller(int numRows, int numCols) {
		mainwin = new MainWindow(this, numRows, numCols);
		game = new Game(numRows, numCols, 'x', 'o');
		gameOver = true;
		startGame();
	}
	
	public void startGame() {
		game.initGame();
		mainwin.resetPlayingField();  // Remove tokens from grid.
		mainwin.nextPlayer(game.getCurrentPlayer());  // Set label text.
		gameOver=false;
	}
	
	public void insertToken(int col) {
		if(gameOver) return;
		
		int row = game.insertToken(col);
		if(row==-1){  // Column is full.
			return;
		}
		mainwin.insertToken(row, col, game.getCurrentPlayer());
		if(game.check4Win()) {
			gameOver = true;
			mainwin.playerWin(game.getCurrentPlayer());
			return;
		}
		nextPlayer();
		
	}
	
	public void nextPlayer() {
		game.nextPlayer();
		mainwin.nextPlayer(game.getCurrentPlayer());
	}
}
