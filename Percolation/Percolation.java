public class Percolation {
	public boolean grid[][];
	private WeightedQuickUnionUF UF;
	private int size;
	private int length;
	
	public boolean[] topsites;
	public boolean[] bottomsites;
	// @throws java.lang.IllegalArgumentException if N < 1
	public Percolation(int N){
		if(N<1){throw new IllegalArgumentException();}
		
		UF = new WeightedQuickUnionUF(N*N + 2);
		length = N;
		size = N*N;

		grid = new boolean[N][N];
		for(int i=0;i<N;i++)
		{
			for(int j=0;j<N;j++)
			{
				grid[i][j] = false;
			}
		}
		
	} 	
	public void open(int i, int j){
		int newI = i-1;
		int newJ = j-1;
		if(!grid[newI][newJ]){
			grid[newI][newJ] = true;
			
			if(newI == 0){
				UF.union(size, point2Dto1D(newI, newJ));
				if(newJ == 0){
					if(grid[newI+1][newJ]){ UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI+1, newJ));}
					if(grid[newI][newJ+1]){ UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI, newJ+1));}
				}else if(newJ == length-1){
					if(grid[newI+1][newJ]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI+1, newJ));}
					if(grid[newI][newJ-1]){ UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI, newJ-1));}
				}else{
					if(grid[newI+1][newJ]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI+1, newJ));}
					if(grid[newI][newJ-1]){ UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI, newJ-1));}
					if(grid[newI][newJ+1]){ UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI, newJ+1));}
				}
			}else if(newI == length-1){
				UF.union(size+1, point2Dto1D(newI, newJ));
				if(newJ == 0){
					if(grid[newI-1][newJ]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI-1, newJ));}
					if(grid[newI][newJ+1]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI, newJ+1));}
				}else if(newJ == length-1){
					if(grid[newI-1][newJ]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI-1, newJ));}
					if(grid[newI][newJ-1]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI, newJ-1));}
				}else{
					if(grid[newI-1][newJ]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI-1, newJ));}
					if(grid[newI][newJ-1]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI, newJ-1));}
					if(grid[newI][newJ+1]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI, newJ+1));}
				}
			}else{
				if(newJ == length-1){
					if(grid[newI-1][newJ]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI-1, newJ));}
					if(grid[newI+1][newJ]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI+1, newJ));}
					if(grid[newI][newJ-1]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI, newJ-1));}
				}else if(newJ == 0){
					if(grid[newI-1][newJ]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI-1, newJ));}
					if(grid[newI+1][newJ]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI+1, newJ));}
					if(grid[newI][newJ+1]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI, newJ+1));}
				}else{
					if(grid[newI-1][newJ]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI-1, newJ));}
					if(grid[newI+1][newJ]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI+1, newJ));}
					if(grid[newI][newJ-1]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI, newJ-1));}
					if(grid[newI][newJ+1]){	UF.union(point2Dto1D(newI, newJ), point2Dto1D(newI, newJ+1));}
				}
			}
		}
	}
	public boolean isOpen(int i, int j){
		return grid[i-1][j-1];
	}
	public boolean isFull(int i, int j){
		return UF.connected(size, point2Dto1D(i-1, j-1));
	}
	public boolean percolates(){
		UF.
		return UF.connected(size, size+1);
	}
	public static void main(String[] args){
		//Percolation p = new Percolation(10);
	}
	private int point2Dto1D(int i, int j){
		return (i*length) + j; 
	}
}