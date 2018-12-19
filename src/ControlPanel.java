import java.awt.GridLayout;
import java.awt.Label;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.Timer;


public class ControlPanel extends JPanel implements ActionListener, ChangeListener{
	private GraphicsPanel gp;
	private CellField cf;

	private JButton player = new JButton("start");
	private JButton random = new JButton("random");
	private JButton clear = new JButton("clear");
	
	private JSlider simSpeed = new JSlider(1,5,1);
	private JSlider scale = new JSlider(20,80,40);
	
	private JButton aliveColor = new JButton("alive cell");
	private JButton deadColor = new JButton("dead cell");
	
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
		
		random.addActionListener(cf);
		random.addActionListener(gp);
		random.addActionListener(this);
		left.add(random);
		
		clear.addActionListener(cf);
		clear.addActionListener(gp);
		left.add(clear);
		
		left.add(new Label("GameSpeed"));
		
		simSpeed.setMajorTickSpacing(1);
		simSpeed.setPaintLabels(true);
		simSpeed.setPaintTicks(true);
		simSpeed.addChangeListener(this);
		
		left.add(simSpeed);
		
		left.add(new Label("scale"));
		
		scale.setMajorTickSpacing(20);
		scale.setSnapToTicks(true);
		scale.setPaintLabels(true);
		scale.setPaintTicks(true);
		scale.addChangeListener(this);
		
		left.add(scale);
		
		left.add(new Label("Cell Color"));
			
		
		
		aliveColor.setActionCommand("ac");
		aliveColor.addActionListener(gp);
		left.add(aliveColor);
		
		deadColor.setActionCommand("dc");
		deadColor.addActionListener(gp);
		left.add(deadColor);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String cmd = e.getActionCommand();
		if(cmd.equals("start")) {
			t.start();
			compEnabled(false);
			player.setText("stop");
			player.setActionCommand("stop");
		}else if(cmd.equals("stop")) {
			t.stop();
			compEnabled(true);
			player.setText("start");
			player.setActionCommand("start");
		}else if(cmd.equals("clear")) {
			t.stop();	
			compEnabled(true);
			player.setText("start");
			player.setActionCommand("start");
		}else if(cmd.equals("random")) {
			t.stop();
			compEnabled(true);
			player.setText("start");
			player.setActionCommand("start");
		}
	}
	
	public void compEnabled(boolean enabled) {
		scale.setEnabled(enabled);
	}
	
	public void stateChanged(ChangeEvent e) {
		if (simSpeed.getValueIsAdjusting()) {
			t.setDelay(250/simSpeed.getValue());
		}
		
		if(scale.getValueIsAdjusting()) {
			cf.setSize(scale.getValue());
			gp.updateGPMatrix();
			gp.setCells();
			gp.repaint();
		}
		
		
	}
}
