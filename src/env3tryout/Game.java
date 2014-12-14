package env3tryout;

import env3d.advanced.EnvAdvanced;
import env3d.advanced.EnvTerrain;
import env3d.Env;
 
public class Game
{
    public Game() {
         
    }
     
    public void play() {        
        // The EnvAdvanced class is a subclass of Env.  An object of the EnvAdvanced class
        // creates an environment without a default room.  
        // It also allows more advanced features like reflections and shadows.
        Env env = new EnvAdvanced();
         
        // Creates a height map of size 8x8.  With a little hill in the middle.
        double heightMap [] = { 0,0,0,0,0,0,0,0,
                                0,0,0,0,0,0,0,0,
                                0,0,0,0,0,0,0,0,
                                0,0,0,3,3,0,0,0,
                                0,0,0,3,3,0,0,0,
                                0,0,0,0,0,0,0,0,
                                0,0,0,0,0,0,0,0,
                                0,0,0,0,0,0,0,0};
         
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
