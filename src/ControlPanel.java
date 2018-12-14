import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;

public class ControlPanel extends JPanel implements ActionListener{
	private GraphicsPanel gp;
	private CellField cf;

	private JButton player = new JButton("start");
	private JButton reset = new JButton("reset");
	
	private JSlider simSpeed = new JSlider(1,5,1);
	private Timer t;

	public ControlPanel(GraphicsPanel gpanel, CellField cfield) {
		this.gp = gpanel;
		this.cf = cfield;
		
		setLayout(new GridLayout(1,2));
		JPanel left = new JPanel();
		JPanel right = new JPanel();
		add(left);
		add(right);

		t = new Timer(100, cf);
		t.setActionCommand("timer");
		t.addActionListener(gp);

		player.addActionListener(this);
		player.setActionCommand("start");
		left.add(player);
		
		reset.addActionListener(cf);
		reset.addActionListener(gp);
		left.add(reset);
		
		left.add(simSpeed);
		
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
		}else if(cmd.equals("reset")) {
			t.stop();
			
		
		}else if(cmd.equals("timer")) {
			gp.repaint();
		}
	}
}
