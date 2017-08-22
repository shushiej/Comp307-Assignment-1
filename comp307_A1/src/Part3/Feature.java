package Part3;

public class Feature {

	int[] row;
	int[] col;
	boolean[] sgn;
	
	public Feature(){
		this.row = new int[4];
		this.col = new int[4];
		this.sgn = new boolean[4];
		
		for(int i=0; i < 4; i++){
			row[i] = new java.util.Random().nextInt(10);
			col[i] = new java.util.Random().nextInt(10);
			sgn[i] = new java.util.Random().nextBoolean();
		}
	}
	public int value(boolean[][] image){
		int sum = 0;
		for(int i=0; i < 4; i++)
			if (image[row[i]] [col[i]]==sgn[i]){
				sum++;
			}
		return (sum>=3) ? 1:0;
	}
	public int[] getRow() {
		return row;
	}
	public void setRow(int[] row) {
		this.row = row;
	}
	public int[] getCol() {
		return col;
	}
	public void setCol(int[] col) {
		this.col = col;
	}
	public boolean[] getSgn() {
		return sgn;
	}
	public void setSgn(boolean[] sgn) {
		this.sgn = sgn;
	}
}
