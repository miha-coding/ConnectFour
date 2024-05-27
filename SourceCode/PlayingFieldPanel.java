import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

/**
 * This class provides functionality to draw the grid and the inserted token.
 */

/**
 * @author Michael
 *
 */

public class PlayingFieldPanel extends JPanel {
	static final long serialVersionUID = 6412354675463245L;  // Java needs this value
	private int numRows;
	private int numCols;
	private int sizex;  // horizontal size of the playingField
	private int sizey;  // vertical size of the playingField
	private int sizeOuterCircle;  // min(sizex/numCols, sizey/numRows)
	private int rCircle;  // e.g. 0.9*sizeOuterCircle to have space between the cols.
	private int circleSpaceX; // horizontal space for each circle
	private int circleSpaceY; // vertical space for each circle
	private Token [] token;  // Every empty or occupied slot in the grid is a Token
	private Color colPl1;  // Color for player one.
	private Color colPl2;  // Color for player two.
	
	public PlayingFieldPanel(int numRows, int numCols, int sizex, int sizey) {
		this.numRows = numRows;
		this.numCols = numCols;
		this.sizex = sizex;
		this.sizey = sizey;
		
		circleSpaceX = sizex/numCols;
		circleSpaceY = sizey/numRows;
		sizeOuterCircle = Math.min(circleSpaceX, circleSpaceY);
		Double tmp = 0.8*sizeOuterCircle;
		rCircle =  tmp.intValue();
		
		token = new Token[numRows*numCols];
		initToken();  // Set all token to be white.
		
		// Maybe provide an menu entry to choose a color in a later version.
		colPl1 = Color.GREEN;
		colPl2 = Color.RED;
		
	}
	
	public int numRow2Ind(int row, int col) {
		return row*numCols + col;
	}
	
	public void initToken() {
		for(int r=0; r<numRows; ++r) {
			for(int c=0; c<numCols; ++c) {
				insertToken(r, c, -1);  // -1 -> slot is empty (Color: white)
			}
		}
	}
	
	public void insertToken(int row, int col, int playerNr) {
		// Calculate the position of the token in the grid, then
		// create and add a token object into the array.
		
		Double tmp = (col+0.0)*circleSpaceX + 15.0;
		int posx = tmp.intValue();
		tmp = (row+1.0)*circleSpaceY;
		int posy = sizey - tmp.intValue();
		token[numRow2Ind(row, col)] = new Token('0', posx, posy, playerNr);
	}
	
	public void updateToken(int row, int col, int playerNr) {
		// Update the playerNr (-> color).
		token[numRow2Ind(row, col)].setPlayerNr(playerNr);
	}
	
	public void reset() {
		// Set all playerNr to -1 again (-> color: white)
		for(int r=0; r<numRows; ++r) {
			for(int c=0; c<numCols; ++c) {
				token[numRow2Ind(r, c)].resetPlayerNr();
			}
		}
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		// Draw the grid
		g.setColor(Color.BLUE);
		g.fillRect(0, 0,  sizex,  sizey);
		
		// Draw the token.
		for(int i=0; i<token.length; ++i) {
			Token tok = token[i];
			if(tok.getPlayerNr() == 0) {
				g.setColor(colPl1);
			}
			else if(tok.getPlayerNr() == 1) {
				g.setColor(colPl2);
			}
			else {
				g.setColor(Color.WHITE);
			}
			g.fillOval(tok.x, tok.y, rCircle, rCircle);
		}
	}
	
	
}