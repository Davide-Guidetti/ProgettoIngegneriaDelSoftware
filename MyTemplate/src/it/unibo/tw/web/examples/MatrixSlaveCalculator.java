package it.unibo.tw.web.examples;

public class MatrixSlaveCalculator extends Thread {
	
	int[][] resMat;
	int[][] matA;
	int[][] matB;
	int fromX;
	int toX;
	int fromY;
	int toY;
	char operation;
	
	public MatrixSlaveCalculator(int[][] resMat, int[][] matA, int[][] matB, int fromX, int toX, int fromY, int toY, char operation) {
		super();
		this.resMat = resMat;
		this.matA = matA;
		this.matB = matB;
		this.fromX = fromX;
		this.toX = toX;
		this.fromY = fromY;
		this.toY = toY;
		this.operation = operation;
	}
	
	public void run() {
		for(int y=fromY; y<toY; y++) {
			for(int x=fromX; x<toX; x++) {
				
				if(operation == 's') {
					resMat[x][y] = matA[x][y] + matB[x][y];
				}else if(operation == 'd'){
					resMat[x][y] = matA[x][y] - matB[x][y];
				}
				
			}
		}
	}
	
}
