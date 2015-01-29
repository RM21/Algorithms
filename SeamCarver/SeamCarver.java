import java.awt.Color;

public class SeamCarver {
	private final int BORDER_ENERGY = 195075;
	private final double INFINITY = Double.MAX_VALUE;
	
	private boolean isVertical;
	private Picture picture;	
	private double[][] energyGrid;
	private double[][] distTo;
	private int[][] edgeTo;
	
	public SeamCarver(Picture picture){
		isVertical = true;
		this.picture = new Picture(picture);
		int height = picture.height();
		int width = picture.width();
		
		energyGrid = new double[height][width];
		edgeTo = new int[height][width];
		distTo = new double[height][width];
		
		for(int i=0;i<picture.height();i++){
			for(int j=0;j<picture.width();j++){
				energyGrid[i][j] = energy(j, i);
				edgeTo[i][j] = -1;
				distTo[i][j] = INFINITY;
			}
		}	
		calculatePaths();
	}
	public Picture picture(){
		return picture;
	}
	public int width(){
		return picture.width();
	}
	public int height(){
		return picture.height();
	}
	public double energy(int x, int y){
		if(x < 0 || x >= width() || y < 0 || y >= height()){
			throw new IndexOutOfBoundsException();
		}
		if(x == 0 || x == (width()-1) || y == 0 || y == (height()-1)){
			return BORDER_ENERGY;
		}
		return centralDifferenceX(x, y) + centralDifferenceY(x, y);
	}
	public int[] findHorizontalSeam(){
		int result[] = new int[picture.width()];
		if(isVertical)	transpose();

		
		return result;
	}
	public int[] findVerticalSeam(){
		int[] result = new int[picture.height()];
		if(!isVertical)	transpose();
		
		double min = INFINITY;
		int start = -1;
		for(int i=0;i < width();i++){
			if(distTo[height()-1][i] < min){
				min = distTo[height()-1][i];
				start = i;
			}
		}
		
		int index = 0;
		result[index] = start;
		for(int i=(height()-1);i>=1;i--){
			index++;
			result[index] = edgeTo[i][result[index-1]];
		}

		int i=0; 
		int j=result.length-1;
		while(i<j){
			int temp = result[i];
			result[i] = result[j];
			result[j] = temp;
			i++;
			j--;
		}
		
		return result;
	}
	public void removeHorizontalSeam(int[] seam){
		if(seam == null){
			throw new NullPointerException();
		}
		// remove horizontal seam from current picture
	}
	public void removeVerticalSeam(int[] seam){
		if(seam == null){
			throw new NullPointerException();
		}
		// remove vertical seam from current picture
	}
	private int centralDifferenceX(int x, int y){
		int xminus = x - 1;
		int xplus = x + 1;
		
		Color pixelLeft = picture.get(xminus, y);
		Color pixelRight = picture.get(xplus, y);
		
		int differenceRed = (int) Math.pow((Math.abs(pixelLeft.getRed() - pixelRight.getRed())), 2);
		int differenceGreen = (int) Math.pow((Math.abs(pixelLeft.getGreen() - pixelRight.getGreen())), 2);
		int differenceBlue = (int) Math.pow((Math.abs(pixelLeft.getBlue() - pixelRight.getBlue())), 2);
		
		return differenceRed + differenceGreen + differenceBlue;
	}
	private int centralDifferenceY(int x, int y){
		int yminus = y - 1;
		int yplus = y + 1;
		
		Color pixelUp = picture.get(x, yplus);
		Color pixelDown = picture.get(x, yminus);
		
		int differenceRed = (int) Math.pow((Math.abs(pixelUp.getRed() - pixelDown.getRed())), 2);
		int differenceGreen = (int) Math.pow((Math.abs(pixelUp.getGreen() - pixelDown.getGreen())), 2);
		int differenceBlue = (int) Math.pow((Math.abs(pixelUp.getBlue() - pixelDown.getBlue())), 2);
		
		return differenceRed + differenceGreen + differenceBlue;
	}
	private void transpose(){
		double[][] nGrid = new double[height()][width()];
		
		//double[] tempArray = new double
	}
	private void calculatePaths(){
		int width = width();
		int height = height();
		Queue<Integer> queue = new Queue<Integer>();
		
		for(int j=0;j<width;j++){
			queue.enqueue(j);
		}
		
		int row = 0;
		if(width == 1){
			for(int i=0;i<height();i++){
				edgeTo[i][0] = 0;
			}
		}
		if(height == 1){
			for(int i=0;i<height();i++){
				edgeTo[0][i] = 0;
			}
		}
		if(height != 1 && width != 1){
			while(!queue.isEmpty()){
				int current = queue.dequeue();
				double dist = energyGrid[row][current];
				if(row!=0){
					int parent;
					if(current == 0){
						if(distTo[row-1][current] < distTo[row-1][current+1]){
							parent = current;
						}else{
							parent = current + 1;
						}
					}else if(current == (width-1)){
						if(distTo[row-1][current] < distTo[row-1][current-1]){
							parent = current;
						}else{
							parent = current - 1;
						}
					}else{
						if((distTo[row-1][current] < distTo[row-1][current-1]) && (distTo[row-1][current] < distTo[row-1][current+1]) ){
							parent = current;
						}else{
							if(distTo[row-1][current+1] < distTo[row-1][current-1]){
								parent = current + 1;
							}else{
								parent = current - 1;
							}
						}
					}
					distTo[row][current] = distTo[row-1][parent] + dist;
					edgeTo[row][current] = parent;
				}else{
					distTo[row][current] = dist;
				}
				if(row<(height-1)){
					queue.enqueue(current);
				}
				if(current == (width -1)){
					row++;
				}
			}
		}
	}
	/*
	private int shortestPath(int start){
		int result = 0;
		distTo[0] = 0;
		
		Queue<Integer> queue = new Queue<Integer>();
		queue.enqueue(start);
		while(!queue.isEmpty()){
			int pixel = queue.dequeue();
		}
		
		for(int i=0;i<picture.height();i++){
			for(int j=0;j<picture.width();j++){
				marked[i][j] = false;
				distTo[i][j] = INFINITY;
			}
		}
		return result;
	}*/
	private double distTo(int i, int j){
		return distTo[j][i];
	}
	private double edgeTo(int i, int j){
		return edgeTo[j][i];
	}
	public static void main(String[] args){
		SeamCarver sc = new SeamCarver(new Picture("10x12.png"));
		for (int j = 0; j < sc.height(); j++)
        {
            for (int i = 0; i < sc.width(); i++)
                System.out.printf("%9.0f ", sc.distTo(i, j));

            System.out.println();
        }
		
		for (int j = 0; j < sc.height(); j++)
        {
            for (int i = 0; i < sc.width(); i++)
                System.out.printf("%9.0f ", sc.edgeTo(i, j));

            System.out.println();
        }
		int[] result = sc.findVerticalSeam();
		for(int i=0; i< result.length; i++){
			System.out.println( result[i]);
		}
	}
}