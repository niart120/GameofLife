import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellField implements ActionListener{
	private boolean[][] matrix;
	private boolean[][] bufferedMatrix;

	int size;
	public CellField() {
		size = 40;
		matrix = new boolean[size][size];
		bufferedMatrix = new boolean[size][size];

		resetMatrix();
	}

	public void applyRules() {
		buffering();
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[i].length;j++) {
				int c = countNeighbours(i,j);
				if(matrix[i][j]) {
					bufferedMatrix[i][j] = c==2||c==3;
				}else {
					bufferedMatrix[i][j] = c==3;
				}
			}
		}
		loading();
	}

	private void resetMatrix() {
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[i].length;j++) {
				matrix[i][j] = false;
			}
		}
	}

	private void setAtrandom() {
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[i].length;j++) {
				matrix[i][j] = Math.random()>0.7;
			}
		}
	}

	private int countNeighbours(int x,int y) {
		int c = 0;
		for(int i=-1;i<2;i++) {
			for(int j=-1;j<2;j++) {
				int x_ = (size+x+i)%size;
				int y_ = (size+y+j)%size;
				if(matrix[x_][y_]) c++;
			}
		}
		if(matrix[x][y])c--;
		return c;
	}

	private void buffering() {
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[i].length;j++) {
				bufferedMatrix[i][j] = matrix[i][j];
			}
		}
	}

	private void loading() {
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[i].length;j++) {
				matrix[i][j] = bufferedMatrix[i][j];
			}
		}
	}

	public boolean[][] getMatrix(){
		return matrix;
	}
	
	public void changeState(int i, int j) {
		matrix[i][j] = !matrix[i][j];
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		String cmd = arg0.getActionCommand();
		if(cmd.equals("timer")) {
			applyRules();
			return;
		}
		if(cmd.equals("random")) {
			setAtrandom();
			return;
		}
		if(cmd.equals("clear")){
			resetMatrix();
			return;
		}
		
	}
	
	public void setSize(int t) {
		this.size = t;
		matrix = new boolean[size][size];
		bufferedMatrix = new boolean[size][size];
	}
	
}
