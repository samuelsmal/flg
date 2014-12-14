package flg;

import java.util.ArrayList;
import java.util.List;

import env3d.Env;
import env3d.advanced.EnvAdvanced;
import env3d.advanced.EnvTerrain;


public class Game
{

    public Game() {

    }

    int pow(int a, int b)
    {
        if (b == 0) {
            return 1;
        }
        if (b == 1) {
            return a;
        }
        if (b % 2 == 0) {
            return pow(a * a, b / 2); // even a=(a^2)^b/2
        }
        else {
            return a * pow(a * a, b / 2); // odd a=a*(a^2)^b/2
        }

    }

    public void play() {
        // The EnvAdvanced class is a subclass of Env. An object of the EnvAdvanced class
        // creates an environment without a default room.
        // It also allows more advanced features like reflections and shadows.
        Env env = new EnvAdvanced();


        int dim = pow(2, 6);
        double[][] map = new double[dim][dim];
        map[0][0] = map[0][dim - 1] = map[dim - 1][0] = map[dim - 1][dim - 1] = 221.0d;

        System.out.println(map[0][0]);
        map = DiamondSquare.applyDiamondSquare(map, 0.9d);
        System.out.println("diamond square completed");

        // Creates a height map of size 8x8. With a little hill in the middle.
        double heightMap[] = new double[dim * dim];

        int heightMapCounter = 0;
        for (double[] element : map) {
            for (int j = 0; j < map[0].length; ++j) {
                heightMap[heightMapCounter++] = element[j];
            }
        }

        // Create the terrain object based on the heightmap
        EnvTerrain terrain = new EnvTerrain(heightMap);
        // Terrain needs a texture
        // terrain.setTexture("textures/mud.gif");

        // Add the terrain object to the environemnt.
        env.addObject(terrain);

        // Exit when the escape key is pressed
        while (env.getKey() != 1) {
            env.advanceOneFrame();
        }
        env.exit();
    }

    public void play2(double[][] map) {
        // The EnvAdvanced class is a subclass of Env. An object of the EnvAdvanced class
        // creates an environment without a default room.
        // It also allows more advanced features like reflections and shadows.
        Env env = new EnvAdvanced();

        List<Double> heights = new ArrayList<Double>();

        for (double[] element : map) {
            for (double element2 : element) {
                heights.add(element2);
            }
        }

        // Creates a height map of size 8x8. With a little hill in the middle.
        double[] heightMap = new double[heights.size()];

        for (int i = 0; i < heights.size(); i++) {
            heightMap[i] = heights.get(i);
        }


        // Create the terrain object based on the heightmap
        EnvTerrain terrain = new EnvTerrain(heightMap);
        // Terrain needs a texture
//        terrain.setTexture("textures/mud.gif");

        // Add the terrain object to the environemnt.
        env.addObject(terrain);

        // Exit when the escape key is pressed
        while (env.getKey() != 1) {
            env.advanceOneFrame();
        }
        env.exit();
    }

    public static void main(String[] args) {
        double[][] map = new double[4][4];
        map[0][0] = map[0][3] = map[3][0] = map[3][3] = 12.0d;

        DiamondSquare.applyDiamondSquare(map, 0.4d);

//        (new Game()).play();
        (new Game()).play2(map);
    }
}
