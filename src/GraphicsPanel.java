import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class GraphicsPanel extends JPanel implements MouseListener, MouseMotionListener, ActionListener{
	private CellField cf;
	private short[][] gpMatrix;
	private Rectangle[][] cells;
	
	private int mouseX, mouseY;

	public GraphicsPanel(CellField cellfield) {
		this.cf = cellfield;
		updateGPMatrix();
		setCells();
		addMouseMotionListener(this);
		addMouseListener(this);
	}

	public void paintComponent(Graphics g) {
		Graphics2D g2 = (Graphics2D)g;
		paintCells(g2);
		
		if(mouseX!=-1) {
			g2.setColor(Color.RED);
			g2.draw(cells[mouseX][mouseY]);
		}
		g2.setColor(Color.DARK_GRAY);		
		g2.drawRect(0, 0, getWidth(), getHeight()-2);
	}
	
	private void paintCells(Graphics2D g2) {
		gpMatrix = cf.getMatrix();
		for(int i=0;i<cells.length;i++) {
			for(int j=0;j<cells[i].length;j++) {
				if(gpMatrix[i][j]==1) {
					g2.setColor(Color.white);
				}else {
					g2.setColor(Color.black);
				}
				g2.fill(cells[i][j]);
				
			}
		}
	}
	
	public void updateGPMatrix() {
		gpMatrix = cf.getMatrix();
	}
	
	public void setCells() {
		cells = new Rectangle[cf.size][cf.size];
		double length = 400/cf.size;
		for(int i=0;i<cells.length;i++) {
			for(int j=0;j<cells[i].length;j++) {
				int lengthX = (int) (length*(i+1)-length*i);
				int lengthY = (int) (length*(j+1)-length*j);
				cells[i][j] = new Rectangle((int)(length*i),(int)(length*j),lengthX,lengthY);
			}
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		for(int i=0;i<cells.length;i++) {
			for(int j=0;j<cells[i].length;j++) {
				if(cells[i][j].contains(e.getPoint())){
					mouseX = i;
					mouseY = j;
					repaint();
					return;
				}
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		cf.changeState(mouseX, mouseY);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
		mouseX = -1;
		mouseY = -1;
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		repaint();
	}
}
