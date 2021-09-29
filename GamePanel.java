import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GamePanel extends JPanel {
	private JButton[][] gameButts;
	private GameState gameState;

	private AIplayer ai;

	public GamePanel() {
		gameButts = new JButton[3][3];
		gameState = new GameState();

		ai = new AIplayer();

		setLayout(new GridLayout(3, 3));

		addButtons();
	}

	private void addButtons() {
		UserClickListener al = new UserClickListener();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				gameButts[i][j] = new JButton();
				gameButts[i][j].addActionListener(al);
				this.add(gameButts[i][j]);
			}
		}
	}

	private void disableButtons() {
		for (JButton[] jButtons : gameButts) {
			for (JButton jButton : jButtons) {
				jButton.setEnabled(false);
			}
		}
	}

	public void reset() {
		for (JButton[] jButtons : gameButts) {
			for (JButton jButton : jButtons) {
				jButton.setText("");
				jButton.setEnabled(true);
			}
		}

		gameState.reset();
	}

	public void aiPlay() {
		int[] nextMove = ai.nextMove(gameState.grid, gameState.turn);
		int r = nextMove[0], c = nextMove[1];
		gameButts[r][c].doClick();
	}

	private class UserClickListener implements ActionListener {
		
		private char winChar = GameState.WIN,
					 drawChar = GameState.DRAW;

		@Override
		public void actionPerformed(ActionEvent e) {
			JButton b = (JButton) e.getSource();
			char turn = gameState.turn;
			changeButton(b);
			setMove(b);

			char gameOver = GameState.isGameOver(gameState.grid);
			if (gameOver == winChar) {
				disableButtons();
				JOptionPane.showMessageDialog(null, turn + " WON");
				// Person won
			} else if (gameOver == drawChar) {
				disableButtons();
				JOptionPane.showMessageDialog(null, "IT IS A DRAW");
				// Person drew
			}
		}

		private void changeButton(JButton b) {
			b.setEnabled(false);
			b.setText(Character.toString(gameState.turn));
		}

		private void setMove(byte r, byte c) {
			gameState.setMove(r, c);
		}

		private void setMove(JButton b) {
			byte r = -1, c = -1;
			for(byte i = 0; i < gameState.grid.length; i++) {
				for(byte j = 0; j < gameState.grid[0].length; j++) {
					if (b == gameButts[i][j]) {
						r = i;
						c = j; 
						break;
					}
				}

				if (r != -1) break;
			}

			this.setMove(r, c);
		}
	}
}
