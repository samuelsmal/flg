package flg;

import java.util.Random;

public class DSA {
	public static double[][] getMap(int size, double d) {
		Random rand = new Random();
		
		// each dimension must be of the form (x^2)+1 (eg 129, 257, 513,
		// 1025...)

		double[][] map = new double[size][size];
		
		map[0][0] = map[0][size - 1] = map[size - 1][0] = map[size - 1][size - 1] = d;
		
		
		double roughness = size / 2;
		int step = size - 1;
		while (step > 1) {
			// diamond
			for (int x = 0; x < size - 1; x += step) {
				for (int y = 0; y < size - 1; y += step) {
					int sx = x + (step >> 1);
					int sy = y + (step >> 1);
					int[][] points = { { x, y }, { x + step, y },
							{ x, y + step }, { x + step, y + step }, };
					computeAndSetNewValue(sx, sy, points, map, rand, roughness, size);
				}
			}
			// square
			for (int x = 0; x < size - 1; x += step) {
				for (int y = 0; y < size - 1; y += step) {
					int halfstep = step >> 1;
					int x1 = x + halfstep;
					int y1 = y;
					int x2 = x;
					int y2 = y + halfstep;
					int[][] points1 = { { x1 - halfstep, y1 },
							{ x1, y1 - halfstep }, { x1 + halfstep, y1 },
							{ x1, y1 + halfstep } };
					int[][] points2 = { { x2 - halfstep, y2 },
							{ x2, y2 - halfstep }, { x2 + halfstep, y2 },
							{ x2, y2 + halfstep } };
					computeAndSetNewValue(x1, y1, points1, map, rand, roughness, size);
					computeAndSetNewValue(x2, y2, points2, map, rand, roughness, size);
				}
			}
			roughness /= 2;
			step >>= 1;
		}
		
		return map;
	}

	private static void computeAndSetNewValue(int x, int y, int[][] points, double[][] map, Random rand, double roughness, int size) {
		double c = 0;
		for (int i = 0; i < 4; i++) {
			if (points[i][0] < 0) {
				points[i][0] += (size - 1);
			} else if (points[i][0] > size) {
				points[i][0] -= (size - 1);
			} else if (points[i][1] < 0) {
				points[i][1] += size - 1;
			} else if (points[i][1] > size) {
				points[i][1] -= size - 1;
			}
			c += map[points[i][0]][points[i][1]] / 4.;
		}
		c += (rand.nextDouble() * 2 * roughness) - roughness;

		setp(x, y, c, map);
		if (x == 0) {
			setp(size - 1, y, c, map);
		} else if (x == size - 1) {
			setp(0, y, c, map);
		} else if (y == 0) {
			setp(x, size - 1, c, map);
		} else if (y == size - 1) {
			setp(x, 0, c, map);
		}
	}

	// set a pixel value
	private static void setp(int x, int y, double c, double[][] map) {
		map[x][y] = c;
	}

}
