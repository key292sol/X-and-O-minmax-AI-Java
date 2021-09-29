import javax.swing.JFrame;

import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainFrame extends JFrame {
	public TopButtonsPanel topPanel;
	public GamePanel gp;

	public MainFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		TopPanelAL al = new TopPanelAL();
		topPanel = new TopButtonsPanel(al);
		gp = new GamePanel();

		GridBagLayout gbl = new GridBagLayout();
		this.setLayout(gbl);

		GridBagConstraints gbc = new GridBagConstraints();
		gbc.fill = GridBagConstraints.BOTH;
		gbc.weightx = 1;

		gbc.gridx = 0;

		gbc.gridy = 0;
		gbc.weighty = 0.2;
		this.add(topPanel, gbc);

		gbc.gridy = 1;
		gbc.weighty = 1;
		this.add(gp, gbc);

		setSize(500, 500);
		setVisible(true);
		setLocationRelativeTo(null);
	}

	private class TopPanelAL implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			Object src = e.getSource();

			if (src == topPanel.reset) {
				gp.reset();
			} else if (src == topPanel.aiPlay) {
				gp.aiPlay();
			}
		}
	}
}
