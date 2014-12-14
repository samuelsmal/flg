package flg;


import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;

import env3d.Env;
import env3d.advanced.EnvAdvanced;
import env3d.advanced.EnvParticle;
import env3d.advanced.EnvSkyRoom;
import env3d.advanced.EnvTerrain;
import env3d.advanced.EnvWater;


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


        int dim = pow(2, 8);
        double[][] map = new double[dim][dim];
        map[0][0] = map[0][dim - 1] = map[dim - 1][0] = map[dim - 1][dim - 1] = 1.4d;

        System.out.println(map[0][0]);
        map = DiamondSquare.applyDiamondSquare(map, 21.4d);
        System.out.println("diamond square completed");

        // Creates a height map of size 8x8. With a little hill in the middle.
        double heightMap[] = new double[dim * dim];

        int heightMapCounter = 0;
        for (double[] element : map) {
            for (int j = 0; j < map[0].length; ++j) {
                heightMap[heightMapCounter++] = element[j];
            }
        }
        
        String root = "/home/samuel/Documents/Studium/05_HS14/03_Einführung-in-die-Computergestützte-Naturwissenschaften-2/flg";
        
        EnvSkyRoom skyroom = new EnvSkyRoom(root + "/textures/skybox/interstellar/");
        
        // EnvSkyRoom is a room, so we use env.setRoom() to set make it visible in our
        // environment.
        env.setRoom(skyroom);

        EnvWater water = new EnvWater();
        // Make the water plain a 10x10 square
        water.setScale(dim);
        water.setRotateZ(-Math.PI / 2d);
          
        // Add the wataer object into our environment.
        env.addObject(water);
                  
        // Create the terrain object based on the heightmap
        EnvTerrain terrain = new EnvTerrain(heightMap);
        // Terrain needs a texture
        terrain.setTexture(root + "/textures/doty_sad.png");
        
        terrain.setScale(1, 0.2, 1);
        
        // Add the terrain object to the environemnt.
        env.addObject(terrain);
        
     // Create a particle system with 10000 particles
        EnvParticle particle = new EnvParticle(10000);
 
        // emit in the negative y direction
        particle.getJmeParticleSystem().getParticleInfluencer().setInitialVelocity(new Vector3f(0, -5, 0));
         
        // size of each particle
        particle.getJmeParticleSystem().setStartSize(0.05f);
        particle.getJmeParticleSystem().setEndSize(0.01f);
        // how long does each particle last       
        particle.getJmeParticleSystem().setLowLife(100);
        particle.getJmeParticleSystem().setHighLife(10000);
        // The start and end color, color is (r, g, b, alpha). 
        // alpha = 1 means visible, alpha = 0 means transparent
        particle.getJmeParticleSystem().setStartColor(new ColorRGBA(1, 1, 1, 1));
        particle.getJmeParticleSystem().setEndColor(new ColorRGBA(1, 1, 1, 0.8f));
 
        // Sets the texture to be a snowfake
        particle.setTexture("textures/particle/snowflake.png");       
        // put the particle system much higher than the camera
        particle.setY(50);
          
        // add the particle to the environment.
        // The default center point is 0, 0, 0.
        env.addObject(particle);

        // Exit when the escape key is pressed
        while (env.getKey() != 1) {
        	// Get the height of the camera
            double height = terrain.getHeight(env.getCameraX(), env.getCameraZ());
 
            // Make the y value a little higher than the terrain, to create the
            // illusion that we are "walking" on the surface.
            env.setCameraXYZ(env.getCameraX(), height+1, env.getCameraZ());
            
            env.advanceOneFrame();
        }
        env.exit();
    }

    public static void main(String[] args) {
        (new Game()).play();
    }
}
