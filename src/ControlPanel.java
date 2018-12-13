import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.Timer;

public class ControlPanel extends JPanel implements ActionListener{
	private GraphicsPanel gp;
	private CellField cf;

	private JButton player = new JButton("start");
	private Timer t;

	public ControlPanel(GraphicsPanel gpanel, CellField cfield) {
		this.gp = gpanel;
		this.cf = cfield;

		t = new Timer(100, this);
		t.setActionCommand("timer");
		setEventButton(player,"start");
	}

	private void setEventButton(JButton btn, String cmd) {
		btn.addActionListener(this);
		btn.setActionCommand(cmd);
		add(btn);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("start")) {
			t.start();
			player.setText("stop");
			player.setActionCommand("stop");
		}else if(cmd.equals("stop")) {
			t.stop();
			player.setText("start");
			player.setActionCommand("start");
		}else if(cmd.equals("timer")) {
			cf.applyRules();
			gp.repaint();
		}
	}
}
