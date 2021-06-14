package it.unibo.tw.web.examples;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.Base64;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;




public class MatrixUtils extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	@Override
	public void init(ServletConfig config) throws ServletException{
		super.init(config);
	
	}
	
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// recupero il tipo di categoria cercata dai parametri della richiesta

		//prendi la stringa JSON dal parametro
		String JSONStr = request.getParameter("JSON");

		//istanza GSON
		Gson g = new Gson();
		
		//converti la stringa JSON in un oggetto vero e proprio
		int[][] JSONObj = g.fromJson(JSONStr, int[][].class);
		
		printMatrix(JSONObj);
		
		
		
		
		
		//System.out.println(JSONObj +"");
		
		//rispondiamo con un JSON, quindi settiamo il relativo MIME TYPE
		response.setContentType("application/json");
		
		//rispondiamo ad esempio con un array di elementi, con un solo elemento all'interno
		PrintWriter out = response.getWriter();
		out.write(g.toJson(JSONObj));
	}
	
	
	
	
	
	
	
/*
 * gnerazione di 4 thread per il calcolo delle sottomatrici
 */
public void calculate(char operation, String fileName) throws IOException {
		
	int[][][] mats = readMatrix(fileName);
	int[][] matA = mats[0];
	int[][] matB = mats[1];
	int[][] resMat = new int [matA.length][matA[0].length];
	
	int midX = matA.length/2;
	int midY = matA[0].length/2;
	int endX = matA.length;
	int endY = matA[0].length;
	
	MatrixSlaveCalculator[] threads = new MatrixSlaveCalculator[4];
	
	threads[0] = new MatrixSlaveCalculator(resMat, matA, matB, 		0, 		midX, 		0, 		midY, 		operation);
	threads[1] = new MatrixSlaveCalculator(resMat, matA, matB, 		0, 		midX, 		midY, 		endY, 		operation);
	threads[2] = new MatrixSlaveCalculator(resMat, matA, matB, 		midX, 		endX, 		0,		 midY, 		operation);
	threads[3] = new MatrixSlaveCalculator(resMat, matA, matB, 		midX, 		endX, 		midY,		 endY, 		operation);
	
	for(MatrixSlaveCalculator thread : threads) {
		thread.start();
	}
	for(MatrixSlaveCalculator thread : threads) {
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//printMatrix(resMat);
	
}
	
	
	
	
	
	
	
	
	
	/*
	 * Copies the source matrix into the destination one (witch has to be allocated)
	 * the source matrix needs to be smaller than or equal to the size of the destination one
	 * @ param dest destination matrix
	 * @ param source source matrix
	 * @ param fromx X offset from where to put the first element of the matrix to copy (inclusive)
	 * @ param fromy Y offset from where to put the first element of the matrix to copy (inclusive)
	 * */
	private void putFragment(int[][] dest, int[][] source, int fromx, int fromy) {
		for(int x=0; x<source.length; x++) {
			for(int y=0; y<source[0].length; y++) {
				dest[fromx+x][fromy+y] = source[x][y];
			}
		}
	}

	/*
	 * get a portion of a matrix, starting from (fromx, fromy) inclusive ad reaching (tox, toy) exclusive
	 */
	private int[][] sliceMatrix(int[][] matrix, int xfrom, int xto, int yfrom, int yto){
		int res[][] = new int[xto-xfrom][yto-yfrom];
		for(int x=xfrom; x<xto; x++) {
			for(int y=yfrom; y<yto; y++) {
				//System.out.println("x: " + (x+xfrom) + " Y: " + (y+yfrom));
				res[x-xfrom][y-yfrom] = matrix[x][y];
			}
		}
		//printMatrix(res);
		return res;
	}
			
			
	public static void printMatrix(int[][] mat) {
		for(int y=0; y<mat[0].length; y++) {
			for(int x=0; x<mat.length; x++) {
				System.out.print(mat[x][y] + " ");
			}
			System.out.println();
		}
	}

	/*
	* reads two matricies from a file. first matrix is in res[0], second one is in res[1]
	* file format should be:
	* 1 2 3 4
	* 5 6 7 8
	* 0 0 0 0
	* 1 1 1 1
	* so theese are two 2x4 matricies (no extra newline between them!)
	*/
	private int[][][] readMatrix(String fname) throws IOException {
		File f = new File(fname);
		BufferedReader r = new BufferedReader(new FileReader(f));
		int rowCount = 0;
		int colCount = 0;
		String s;
		while((s = r.readLine()) != null) {
			rowCount++;
			colCount = s.split(" ").length;
		}
		int[][] matA = new int[colCount][rowCount/2];
		int[][] matB = new int[colCount][rowCount/2];
		r.close();
		r = new BufferedReader(new FileReader(f));
		rowCount = 0;
		while((s = r.readLine()) != null) {
			colCount = 0;
			for(String sr : s.split(" ")) {
				matA[colCount][rowCount] = Integer.parseInt(sr);
				colCount++;
			}
			rowCount++;
			if(rowCount == matA[0].length) break;
		}
		rowCount = 0;
		while((s = r.readLine()) != null) {
			colCount = 0;
			for(String sr : s.split(" ")) {
				matB[colCount][rowCount] = Integer.parseInt(sr);
				colCount++;
			}
			rowCount++;
		}
		//printMatrix(matA);
		//printMatrix(matB);
		int [][][] res = new int[2][0][0];
		res[0] = matA;
		res[1] = matB;
		return res;
	}




}