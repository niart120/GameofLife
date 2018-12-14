
public class CellField {
	private short[][] matrix;
	private short[][] bufferedMatrix;

	int size;
	public CellField(){
		size = 20;
		matrix = new short[size][size];
		bufferedMatrix = new short[size][size];

		setAtrandom();
		//resetMatrix();
	}

	public void applyRules() {
		buffering();
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[i].length;j++) {
				int c = countNeighbours(i,j);
				if(matrix[i][j]==1) {
					if(c!=2&&c!=3) {
						bufferedMatrix[i][j] = 0;
					}
				}else {
					if(c==3) {
						bufferedMatrix[i][j] = 1;
					}
				}
			}
		}
		loading();
	}

	private void resetMatrix() {
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[i].length;j++) {
				matrix[i][j] = 0;
			}
		}
	}

	private void setAtrandom() {
		for(int i=0;i<matrix.length;i++) {
			for(int j=0;j<matrix[i].length;j++) {
				if(Math.random()>0.7) {
					matrix[i][j] = 1;
				}else {
					matrix[i][j] = 0;
				}
			}
		}
	}

	private int countNeighbours(int x,int y) {
		int c = 0;
		for(int i=-1;i<2;i++) {
			for(int j=-1;j<2;j++) {
				int x_ = (size+x+i)%size;
				int y_ = (size+y+j)%size;
				c += matrix[x_][y_];
			}
		}
		c -= matrix[x][y];
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

	public short[][] getMatrix(){
		return matrix;
	}
	
	public void changeState(int i, int j) {
		if(matrix[i][j] == 1) {
			matrix[i][j] = 0;
		}else {
			matrix[i][j] = 1;
		}
	}
}
