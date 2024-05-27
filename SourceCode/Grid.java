/**
 * This class implements the grid for the game Connect Four. It contains the token.
 */

/**
 * @author Michael
 *
 */



public class Grid {
	public final int numRows;  // 6
	public final int numCols;  // 7
	public Token[] grid;
	
	public Grid(int numR, int numC) {
		numRows = numR;
		numCols = numC;
		grid = new Token[numRows*numCols];  // Bottom left is (0, 0)
	}
	
	public int rowCol2Ind(int r, int c) {
		if(r>=numRows || c>=numCols) {
			System.out.println("Grid::rowCol2Ind: r or c out of range!");
			return -1;
		}
		return r*numCols + c;
	}
	
	public final Token getToken(int r, int c) {
		return grid[rowCol2Ind(r, c)];
	}
	
	public final char getTokenColor(int r, int c) {
		return getToken(r, c).getColor();
	}
	
	public int insertToken(int col, char color) {
		// Returns the row on which the current token is inserted.
		
		int ind = rowCol2Ind(numRows-1, col);
		if(grid[ind] != null) {
			// Column is full
			return -1;
		}
		// Find the next free row.
		// We start at numRows-2 since we know yet that the highest row is free.
		int r = numRows-2;
		for(; r>=0; r--) {
			if(grid[rowCol2Ind(r, col)]!=null) {
				// There is a Token in this row
				break;
			}
		}
		++r;
		grid[rowCol2Ind(r, col)] = new Token(color, 0, 0, -1);
		return r;
	}
	
	public boolean check4Win(char color) {
		if(check4row(color)) return true;
		if(check4col(color)) return true;
		if(check4diag(color)) return true;
		if(check4adiag(color)) return true;
		return false;
	}
	
	private boolean check4row(char color) {
		// Checks if there are 4 Token with the same color
		// next to each other in the same row.
		for(int r=0; r<numRows; ++r) {
			int index = rowCol2Ind(r, 0);
			for(int c=0; c<=numCols-4; ++c) {
				if(grid[index+c  ]==null ||
				   grid[index+c+1]==null ||
				   grid[index+c+2]==null ||
				   grid[index+c+3]==null)
				{
					continue;
				}
				if(grid[index+c  ].getColor()==color &&
				   grid[index+c+1].getColor()==color &&
				   grid[index+c+2].getColor()==color &&
				   grid[index+c+3].getColor()==color)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean check4col(char color) {
		// Checks if there are 4 Token with the same color
		// next to each other in the same column.
		for(int c=0; c<numCols; ++c) {
			for(int r=0; r<=numRows-4; ++r) {
				if(grid[rowCol2Ind(r  , c)]==null ||
				   grid[rowCol2Ind(r+1, c)]==null ||
				   grid[rowCol2Ind(r+2, c)]==null ||
				   grid[rowCol2Ind(r+3, c)]==null)
				{
					continue;
				}
				if(grid[rowCol2Ind(r  , c)].getColor()==color &&
				   grid[rowCol2Ind(r+1, c)].getColor()==color &&
				   grid[rowCol2Ind(r+2, c)].getColor()==color &&
				   grid[rowCol2Ind(r+3, c)].getColor()==color)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean check4diag(char color) {
		// Checks if there are 4 Token with the same color
		// next to each other diagonally.
		for(int c=0; c<=numCols-4; ++c) {
			for(int r=0; r<=numRows-4; ++r) {
				if(grid[rowCol2Ind(r  , c  )]==null ||
				   grid[rowCol2Ind(r+1, c+1)]==null ||
				   grid[rowCol2Ind(r+2, c+2)]==null ||
				   grid[rowCol2Ind(r+3, c+3)]==null)
				{
					continue;
				}
				if(grid[rowCol2Ind(r  , c  )].getColor()==color &&
				   grid[rowCol2Ind(r+1, c+1)].getColor()==color &&
				   grid[rowCol2Ind(r+2, c+2)].getColor()==color &&
				   grid[rowCol2Ind(r+3, c+3)].getColor()==color)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	private boolean check4adiag(char color) {
		// Checks if there are 4 Token with the same color
		// next to each other anti diagonally.
		for(int c=3; c<numCols; ++c) {
			for(int r=0; r<=numRows-4; ++r) {
				if(grid[rowCol2Ind(r  , c  )]==null ||
				   grid[rowCol2Ind(r+1, c-1)]==null ||
				   grid[rowCol2Ind(r+2, c-2)]==null ||
				   grid[rowCol2Ind(r+3, c-3)]==null)
				{
					continue;
				}
				if(grid[rowCol2Ind(r  , c  )].getColor()==color &&
				   grid[rowCol2Ind(r+1, c-1)].getColor()==color &&
				   grid[rowCol2Ind(r+2, c-2)].getColor()==color &&
				   grid[rowCol2Ind(r+3, c-3)].getColor()==color)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public void printGrid() {
		System.out.println();
		for(int i=0; i<numCols; ++i) {
			System.out.print("  "+i+" ");
		}
		System.out.println();
		for(int r=numRows-1; r>=0; --r) {
			System.out.print("| ");
			for(int c=0; c<numCols; ++c) {
				if(grid[rowCol2Ind(r, c)]==null) {
					System.out.print(" ");
				}
				else {
					System.out.print(grid[rowCol2Ind(r, c)].getColor());
				}
				System.out.print(" | ");
			}
			System.out.println();
		}
		for(int i=0; i<numCols; ++i) {
			System.out.print(" ---");
		}
		System.out.println();
	}

}
