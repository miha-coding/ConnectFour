/**
 * This class implements the game logic.
 * It also contains the main function.
 * With the boolean value GUI can be decided if the game is started with
 * or without a GUI. 
 */

/**
 * @author Michael
 *
 */

import java.util.Scanner;

public class Game {
	
	private int numRows;
	private int numCols;
	private char[] playerCols; // Color of player
	private Grid grid;  // Playing grid
	private int curPlayer;
	private boolean win;  // Has a player won the game
	
	
	public static void main(String[] args) {
		
		// GUI == true  -> GUI Application
		// GUI == false -> Command Line Application
		boolean GUI = true;
		
		final int numRows = 6;
		final int numCols = 7;
		
		if(!GUI) {
			Game game = new Game(numRows, numCols, 'x', 'o');
			game.startGame();
		}
		else {
			new Controller(numRows, numCols);
		}
	}
	
	
	public Game(int numR, int numC, char colPl1, char colPl2) {
		numRows = numR;
		numCols = numC;
		playerCols = new char[2];
		playerCols[0] = colPl1;
		playerCols[1] = colPl2;
		curPlayer = 1; 
		grid = new Grid(numRows, numCols);
	}
	
	public void nextPlayer() {
		// There are two players: Player 0 and Player 1.
		curPlayer = (curPlayer+1) % 2;
	}
	
	public int getColFromUser() {
		// User input in which column the user want to insert the token.
		
		System.out.println("In welche Spalte moechten Sie ihr Chip einwerfen?");
		System.out.print("Ihre Eingabe (Spalte 0 bis "+(numCols-1)+"): ");
		
		int num = -1;
		
		@SuppressWarnings("resource")
		Scanner scan = new Scanner(System.in);  // Never close System.in!
		while(num < 0 || num >= numCols) {
			try {
				String buffer = scan.nextLine();
				num = Integer.valueOf(buffer);
			} catch(Exception e) {
				System.out.println("Fehlerhafte Eingabe! Erlaubt sind ganze"+
						" Zahlen in einem bestimmten Zahlenbereich");
				scan.reset();
				num = -1;
			}
			
		}
			
		return num;
	}
	
	public void initGame() {
		grid = new Grid(numRows, numCols);
		win = false;
	}
	
	public void startGame() {
		// Starts the game in the CLI.
		initGame();
		while(!win) {
			nextPlayer();
			win = playAStep(curPlayer);
		}
		grid.printGrid();
		System.out.println("Spieler "+curPlayer+ " gewinnt!");
	}
	
	public int insertToken(int col) {
		return grid.insertToken(col, playerCols[curPlayer]);
	}
	
	public boolean check4Win() {
		return grid.check4Win(playerCols[curPlayer]);
	}
	
	public boolean playAStep(int playerNr) {
		// Plays a step in the CLI.
		
		System.out.println("\n\nSpieler "+playerNr+ " ("+playerCols[playerNr]+") ist an der Reihe: ");
		grid.printGrid();  // Show current grid.
		int col = getColFromUser();
		while(insertToken(col)==-1) {
			System.out.println("Die gewählte Spalte ist voll. "+
							   "Bitte wählen Sie eine andere Spalte.");
			col = getColFromUser();
		}
		if(check4Win()) return true;
		return false;
	}
	
	public int getCurrentPlayer() {
		return curPlayer;
	}

}
