package flg;

import env3d.advanced.EnvAdvanced;
import env3d.advanced.EnvTerrain;
import env3d.Env;
 
public class Game
{
    public Game() {
         
    }
    
    int pow (int a, int b)
    {
        if ( b == 0)        return 1;
        if ( b == 1)        return a;
        if ( b % 2 == 0)    return     pow ( a * a, b/2); //even a=(a^2)^b/2
        else                return a * pow ( a * a, b/2); //odd  a=a*(a^2)^b/2

    }
     
    public void play() {        
        // The EnvAdvanced class is a subclass of Env.  An object of the EnvAdvanced class
        // creates an environment without a default room.  
        // It also allows more advanced features like reflections and shadows.
        Env env = new EnvAdvanced();
        
        
        int dim = pow(2, 6);
        double[][] map = new double[dim][dim];
        map[0][0] = map[0][dim - 1] = map[dim - 1][0] = map[dim -1 ][dim -1] = 221.0d;
       
        System.out.println(map[0][0]);
        map = DiamondSquare.applyDiamondSquare(map, 0.9d);
        System.out.println("diamond square completed");
         
        // Creates a height map of size 8x8.  With a little hill in the middle.
        double heightMap [] = new double[dim * dim];

        int heightMapCounter = 0;
        for (int i = 0; i < map.length; ++i) {
        	for (int j = 0; j < map[0].length; ++j) {
        		heightMap[heightMapCounter++] = map[i][j];
        	}
        }
         
        // Create the terrain object based on the heightmap                       
        EnvTerrain terrain = new EnvTerrain(heightMap);
        // Terrain needs a texture
        //terrain.setTexture("textures/mud.gif");
         
        // Add the terrain object to the environemnt.
        env.addObject(terrain);
         
        // Exit when the escape key is pressed
        while (env.getKey() != 1) {
            env.advanceOneFrame();
        }
        env.exit();
    }
     
    public static void main(String [] args) {
        (new Game()).play();
    }
}
