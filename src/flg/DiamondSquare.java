package flg;

import java.util.ArrayList;
import java.util.Random;

public class DiamondSquare {
	/**
	 * 
	 * @param map
	 * @param roughness > 0 && < 1 
	 */
	public static double[][] applyDiamondSquare(double[][] map, double roughness) {
		Random rand = new Random();
		
		for (int i = map.length / 2; i > 0; --i) {
			diamondStep(map, rand, roughness, i);
			squareStep(map, rand, roughness, i);
			
//			roughness /= 2;
		}
		
		return map;
	}
	
	private static void diamondStep(double[][] points, Random rand, double roughness, int length) {
		for (int i = 0; i < points.length; ++i) {
			for (int j = 0; j < points.length; ++j) {
				ArrayList<Double> parentValues = new ArrayList<Double>();
				
				if (i >= length / 2) {
					if (j >= length / 2) {
						parentValues.add(points[i - length / 2][j - length / 2]);
					}
					
					if (j + length / 2 < points.length) {
						parentValues.add(points[i - length / 2][j + length / 2]);
					}
				}
				
				if (i + length / 2 < points.length) {
					if (j >= length / 2) {
						parentValues.add(points[i + length / 2][j - length / 2]);
					}
					
					if (j + length / 2 < points.length) {
						parentValues.add(points[i + length / 2][j + length / 2]);
					}
				}
				
				points[i][j] = average(parentValues) + (rand.nextDouble() - 0.5d)*roughness;
			}
		}
	}
	
	private static void squareStep(double[][] map, Random rand, double roughness, int length) {
		
		for (int i=0;i<map.length;++i){
			for(int j=0; j<map.length;++j){
				ArrayList<Double> values = new ArrayList<Double>();
			
				if (i >= length) {
					if (j >= length) {
						values.add(map[i - length/2][j]);
					}
					
					if (j + length < map.length) {
						values.add(map[i][j + length/2]);
					}
				}
				
				if (i + length < map.length) {
					if (j >= length) {
						values.add(map[i][j - length/2]);
					}
					
					if (j + length < map.length) {
						values.add(map[i + length/2][j]);
					}
				}
				
				map[i][j] = average(values) + (rand.nextDouble() - 0.5d)*roughness;
			}
		}
	}
	
	private static double average(ArrayList<Double> l) {
		double sum = 0f;
		
		for (double i : l) {
			sum += i;
		}
		
		return sum / l.size();
	}
}
