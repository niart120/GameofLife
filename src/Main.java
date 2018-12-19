import java.awt.GridLayout;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] arg) {
		JFrame fr = new JFrame();
		CellField cf = new CellField();
		GraphicsPanel gp = new GraphicsPanel(cf);
		ControlPanel cp = new ControlPanel(gp,cf);

		fr.setTitle("Game of Life");
		fr.setSize(486, 992);
		fr.setLocation(300, 100);
		fr.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		fr.setResizable(false);

		fr.setLayout(new GridLayout(2,1));

		fr.add(gp);
		fr.add(cp);

		fr.setVisible(true);
	}
}
