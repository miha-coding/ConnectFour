import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.GridBagConstraints;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * This class implements the main Window of the GUI Application.
 */

/**
 * @author Michael
 *
 */
public class MainWindow{
	private int numCols;
	private int numRows;
	
	// Menu
	private JMenuBar menuBar;
	private JMenu menu;
	private JMenuItem newGame;
	
	// Widgets
	private JFrame frame;
	private JPanel panel;
	private PlayingFieldPanel playingField;
	private JLabel currentPlayerLab;
	private JButton [] colButtons;
	
	private Controller control;  // Buttons "call" a method from controller
	
	public MainWindow(Controller c, int numRows, int numCols) {  // TODO: window size
		control = c;
		this.numRows = numRows;
		this.numCols = numCols;
		
		frame = new JFrame("Connected Four");
		
		initMenu();
		initWindow();
	}
	
	public void newGame() {
		// Menu entry
		control.startGame();
		playingField.repaint();
	}
	
	public void initMenu() {
		menuBar = new JMenuBar();
		menu = new JMenu("Game");
		newGame = new JMenuItem("New Game");
		newGame.addActionListener(e -> this.newGame());
		
		menuBar.add(menu);
		menu.add(newGame);
		
		frame.setJMenuBar(menuBar);
	}
	
	private void initWindow() {
		
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(700, 500);
		
		panel = new JPanel(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();  // Constraints for the layout.
				
		currentPlayerLab = new javax.swing.JLabel("Spieler 1 ist an der Reihe.");
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = numCols;
		panel.add(currentPlayerLab, c);
		
		colButtons = new javax.swing.JButton[numCols];
		c.gridy = 1;  						// All buttons in row 1.
		c.gridwidth = 1;  					// Reset gridwidth to 1.
		c.ipadx = 20;  						// padding in x direction makes the buttons broader.
		c.insets = new Insets(10,10,0,10);  // Space around the buttons
		
		for(int i=0; i<numCols; ++i) {
			JButton button = new JButton(""+i);
			button.addActionListener(new ButtonListener(control, i));
			
			colButtons[i] = button;
			c.gridx = i;
			panel.add(colButtons[i], c);
		}
		
		playingField = new PlayingFieldPanel(numRows, numCols, 550, 300);
		c.gridx = 0;
		c.gridy = 2;
		c.gridwidth = numCols;
		c.gridheight = 1;
		c.ipadx = 550;
		c.ipady = 300;
		playingField.setSize(550, 300);
		panel.add(playingField, c);
		
		frame.add(panel);
		frame.setVisible(true);
	}
	
	public void insertToken(int row, int col, int playerNr) {
		playingField.updateToken(row, col, playerNr);
		playingField.repaint();
	}
	
	public void nextPlayer(int playerNr) {
		currentPlayerLab.setText("Spieler "+playerNr+" ist an der Reihe");
	}
	
	public void resetPlayingField() {
		playingField.reset();
	}
	
	public void playerWin(int playerNr) {
		currentPlayerLab.setText("Glückwunsch! Spieler "+playerNr+" hat gewonnen!");
	}
	
	// Action Listener for the button. It saves the button column and
	// hand this value to the controller.
	class ButtonListener implements ActionListener{
		private int column;
		private Controller controller;
		public ButtonListener(Controller control, int col) {
			controller = control;
			column = col;
		}
		
		public void actionPerformed(ActionEvent e) {
			controller.insertToken(column);
		}
	}
	
	
}
		
		

