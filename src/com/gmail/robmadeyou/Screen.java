package com.gmail.robmadeyou;

import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Render;
import com.gmail.robmadeyou.Effects.Textures;
import com.gmail.robmadeyou.Gui.Interface;
import com.gmail.robmadeyou.Gui.Text;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;
import com.gmail.robmadeyou.Input.Mouse;
import com.gmail.robmadeyou.World.World;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.newdawn.slick.SlickException;

import static org.lwjgl.opengl.GL11.*;

/*
 * So all this stuff is made by Robmadeyou.
 * 
 * I'm okay with you using any of this. I've deliberately commented on the code to hopefully
 * show you what I tried doing and in hopes of you being able to learn something from the code.
 * I have used the LWJGL library for openGL rendering and such. I also used Slick for texture loading
 * and sound. This way the coding of the game is going to be a bit easier than having to code it all
 * yourself and just wasting hours trying to figure out how things are done.
 * 
 * I build this for CoderDojo in Armagh. Jamie Kelly is somewhat responsible for all of this happening ((https://github.com/jamiekelly))
 * as he is the one suggested that something like this would be nice if was made.
 */

public class Screen {

    public static String engineName = "Rob\'s Engine: ";
    public static String version = "0.1";

    public static boolean detailsActive = false;
    private static boolean worldCreated = false;
    private static long lastFrame;
    private static long lastFPS;
    private static int fps = 0;
    public static int actualFps = 0;
    public static int delta = getDelta();

    private static void updateFPS() {
        if (getTime() - lastFPS > 1000) {
            actualFps = fps;
            fps = 0;
            lastFPS += 1000;
        }
        fps++;
    }

    private static long getTime() {
        return (Sys.getTime() * 1000) / Sys.getTimerResolution();
    }

    private static boolean vSync = false;

    public static void toggleVSync() {
        vSync = !vSync;
    }

    public static void changeGameMode(GameType type) {
        TypeOfGame = type;
    }

    /*
     * Delta time is the time taken between each frame
     *
     * Delta time is used for animations and movement to add a
     * frame independent movement.
     *
     * For example instead of adding + 5 every frame and if the user is using a
     * quite fast end PC it will move faster than for someone with a lower end PC, where
     * as frame independent movement would add the same amount each time to make the movement
     * seem fluid for both low end and high end computers. This is very useful when doing things through
     * Multi-PlayerData as one person might have a very fast computer and the other might have a slow one, the slow one
     * is at a disadvantage as they can't move as fast
     */
    private static int getDelta() {
        long currentTime = getTime();
        int delta = (int) (currentTime - lastFrame);
        lastFrame = getTime();
        return delta;
    }

    /*
     * These two methods simply return the screen dimensions;
     */
    public static int getWidth() {
        return Display.getWidth();
    }

    public static int getHeight() {
        return Display.getHeight();
    }

    public static boolean isAskedToClose() {
        return Display.isCloseRequested();
    }

    public static GameType TypeOfGame = GameType.SIDE_SCROLLER;

    public static double translate_x = 0;
    public static double translate_y = 0;

    public static int WorldTileSize = 32;

    public static int WorldWidth = 0;
    public static int WorldHeight = 0;
    /*
     * Object ID is created for rendering purposes
     * Increases performance by a lot. For example for emitters:
     *
     * Instead of recieving every
     */
    private static int renderObjectID = 0;

    public static void create(int dimensionX, int dimensionY, String name, GameType typeOfGame, boolean Minimalistic) {
        long startTimer = getTime();
        TypeOfGame = typeOfGame;
        try {
            /*
			 * Here the Display that we are going to be using is created with the 
			 * dimensions called in the parameters when the method was called
			 */
            Display.setDisplayMode(new DisplayMode(dimensionX, dimensionY));
			/*
			 * Sets the title of the Display to be what ever the parameter in the method
			 */
            Display.setTitle(name);
            Display.create();
            Display.setResizable(false);

        }
		/*
		 * Catching an exception in case a Display has already been created or some other error.
		 */ catch (LWJGLException e) {
            e.printStackTrace();
            System.out.println("Error. Unable to create a Display");
        }
        lastFPS = getTime();
        if (!Minimalistic) {
            glEnable(GL_TEXTURE_2D);
            glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

            glViewport(0, 0, dimensionX, dimensionY);
            glMatrixMode(GL_PROJECTION);
            glLoadIdentity();
            glOrtho(0, dimensionX, dimensionY, 0, 1, -1);
            glMatrixMode(GL_MODELVIEW);
        }
        Text.loadTextures();
        System.out.println(engineName + "Font set up");
        Textures.setUpTextures();
        System.out.println(engineName + "Textures set up: " + Textures.texSetUp);

        long endTimer = getTime() - startTimer;
        double finishTime = endTimer / 1000;
        System.out.println(engineName + "v" + version + " Loaded in: " + finishTime + " seconds");
    }

    public static void setWorldDimensionsInBlocks(int numOfBlocksOnXAxis, int numOfBlocksOnYAxis) {
        WorldWidth = numOfBlocksOnXAxis;
        WorldHeight = numOfBlocksOnYAxis;
    }

    public static void setUpWorld() {
		
		/*
		 * Initialising WorldWidth, and Height as before that
		 * the screen wasn't created and we couldn't actually initialise the variables :S
		 */
        if (WorldWidth == 0 && WorldHeight == 0) {
            WorldWidth = Math.round(getWidth() / WorldTileSize) + 1;
            WorldHeight = Math.round(getHeight() / WorldTileSize);
        }
        if (WorldWidth < Math.round(getWidth() / WorldTileSize) + 1) {
            WorldWidth = Math.round(getWidth() / WorldTileSize + 1);
            System.out.println(engineName + "WorldWidth TOO LOW. default to: " + WorldWidth);
        }
        if (WorldHeight < Math.round(getHeight() / WorldTileSize)) {
            WorldHeight = Math.round(getHeight() / WorldTileSize);
            System.out.println(engineName + "WorldHeight TOO LOW. default to: " + WorldHeight);
        }
        World.setWorldDimensions(WorldWidth, WorldHeight);
        try {
            World.setArrayListClear();
        } catch (SlickException e) {
            e.printStackTrace();
        }
        System.out.println(engineName + "World loaded");
        worldCreated = true;
    }

    public static void setWorldBlockSizeInPixels(int size) {
        WorldTileSize = size;
    }
	/*
	 * This method will be called from the Main class in the game.
	 * This method will update everything to do with the screen.
	 * 
	 * It will be synchronised at the rate of the int rate is set to be at
	 */

    public static void update(int rate) {
        //This will clear the screen before drawing it again.
        glClear(GL_COLOR_BUFFER_BIT);
        updateFPS();
        delta = getDelta();
        Engine.islmbpThisTick = false;
        Engine.isrmbpThisTick = false;

        Interface.onUpdate();

        Mouse.onUpdate();

        if (worldCreated) {
            World.onUpdate();
        }
        Engine.update(delta);
        Interface.onUpdate();

        if (Keyboard.isKeyPressed(Key.Grave)) {
            detailsActive = !detailsActive;
        }
        Display.sync(60);
    }

    public static void refresh() {
        Collector.organize();
        Render.renderAll();
        Collector.clear();
        Display.update();
    }

    public static void destroy() {
        System.out.println(engineName + "Shutting down");
        Display.destroy();
        System.exit(0);
    }

    public static int registerObject() {
        renderObjectID++;
        return renderObjectID;
    }

    public enum GameType {
        SIDE_SCROLLER,
        RPG_STYLE,
        CUSTOM;

        GameType() {
        }
    }
}
