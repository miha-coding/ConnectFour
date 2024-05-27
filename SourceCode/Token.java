/**
 * This class implements token for the game Connect Four. We use letters instead of colors.
 */

/**
 * @author Michael
 *
 */
public class Token {
	private char color;  // Color (here letter)
	
	// GUI
	public final int x;
	public final int y;
	private int playerNr;
	
	
	public Token(char col, int x, int y, int playerNr) {
		color = col;
		this.x = x;
		this.y = y;
		this.playerNr = playerNr;
	}
	
	public void resetPlayerNr() {
		playerNr = -1;
	}
	
	public boolean setPlayerNr(int plNr) {
		if(plNr==0 || plNr==1) {
			playerNr = plNr;
			return true;
		}
		return false;
	}
	
	public final int getPlayerNr() {
		return playerNr;
	}
	
	public char getColor() { return color; }
	
}
