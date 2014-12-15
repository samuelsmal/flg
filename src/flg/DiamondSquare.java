package flg;

import java.util.ArrayList;
import java.util.Random;

public class DiamondSquare {
	/**
	 * 
	 * @param points
	 * @param roughness
	 */
	public static void applyDiamondSquare(double[][] points, double roughness) {
		applyDiamondSquare(points, roughness, points.length);
	}
	
	
	public static void applyDiamondSquare(double[][] points, double roughness, int steps) {
		Random rand = new Random();
		
		for (int sideLength = points.length - 1, j = 0; sideLength >= 2 && j < steps; sideLength /= 2, ++j) {
			diamondStep(points, rand, roughness, sideLength);
			squareStep(points, rand, roughness, sideLength);
			
			roughness /= 2f;
		}
	}
	
	private static void diamondStep(double[][] points, Random rand, double roughness, int sideLength) {
		for (int i = 0; i < points.length - 1; i += sideLength) {
			for (int j = 0; j < points.length - 1; j += sideLength) {
				double avg = points[i][j] + //top left
					      points[i + sideLength][j] +//top right
					      points[i][j+sideLength] + //lower left
					      points[i+sideLength][j+sideLength];//lower right
				avg /= 4.0f;
							
				points[i + sideLength / 2][j + sideLength / 2] = avg + ((rand.nextDouble() * 2 * roughness) - roughness);
			}
		}
	}
	
	private static void squareStep(double[][] points, Random rand, double roughness, int sideLength) {
		for (int i = 0; i < points.length - 1; i += sideLength){
			for(int j = 0; j < points.length - 1; j += sideLength){
				double avg = 
				        points[(i - sideLength / 2 + points.length -1)%(points.length - 1)][j] + //left of center
				        points[(i + sideLength / 2                   )%(points.length - 1)][j] + //right of center
				        points[i][(j + sideLength / 2                    )%(points.length - 1)] + //below center
				        points[i][(j - sideLength / 2 + points.length - 1)%(points.length - 1)]; //above center
				      avg /= 4.0;
				
				double newValue = avg + ((rand.nextDouble() * 2 * roughness) - roughness);
				points[i][j] = newValue;
				
				if (i == 0) points[points.length -1][j] = newValue;
				if (j == 0) points[i][points.length -1] = newValue;
			}
		}
	}
	
	private static double average(ArrayList<Double> l) {
		double sum = 0f;
		
		for (Double i : l) {
			sum += i;
		}
		
		return sum / l.size();
	}
}
