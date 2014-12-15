package flg;

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
        env.setResolution(1200, 600, 0);
        env.enableLighting();


        int dim = pow(2, 6);
        double[][] map = Map.hillCenterMap(dim, 0d, 10d);

        System.out.println(map[0][0]);
        map = DiamondSquare.applyDiamondSquare(map, 7.5d);
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
        terrain.setTexture("textures/moss.png");

        // Add the terrain object to the environemnt.
        env.addObject(terrain);

        // Exit when the escape key is pressed
        while (env.getKey() != 1) {
            env.advanceOneFrame();
        }
        env.exit();
    }

    public void play_imagebased(String file) {
        Env env = new EnvAdvanced();
        env.enableLighting();

        // Use an image to provide height data, this image is 256x256
        EnvTerrain terrain = new EnvTerrain(file);
        // Terrain needs a texture
        terrain.setTexture("textures/moss.png");

        // Add the terrain object to the environemnt.
        env.addObject(terrain);

        // Inspect the entire terrain from far away.
        env.setCameraXYZ(128, 250, 400);
        env.setCameraPitch(-20);

        // Exit when the escape key is pressed
        while (env.getKey() != 1) {
            env.advanceOneFrame();
        }
        env.exit();
    }

    public static void main(String[] args) {
        (new Game()).play_imagebased("termaps/processing_termap1.png");
    }
}
