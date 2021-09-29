import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridLayout;

import java.awt.event.ActionListener;

public class TopButtonsPanel extends JPanel {
	public JButton reset, aiPlay;

	public TopButtonsPanel(ActionListener al) {
		GridLayout gridLayout = new GridLayout(1, 2);
		setLayout(gridLayout);

		reset = new JButton("RESET GAME");
		aiPlay = new JButton("AI MOVE");

		reset.addActionListener(al);
		aiPlay.addActionListener(al);

		this.add(reset);
		this.add(aiPlay);
	}
}
