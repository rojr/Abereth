package com.gmail.robmadeyou;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import static org.lwjgl.opengl.GL11.glClear;
import static org.lwjgl.opengl.GL11.GL_COLOR_BUFFER_BIT;

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
	
	private static long lastFrame;
	private static long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
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
	 * Multi-Player as one person might have a very fast computer and the other might have a slow one, the slow one
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
	public static int getWidth(){
		return Display.getWidth();
	}
	public static int getHeight(){
		return Display.getHeight();
	}
	public static void createScreen(int dimensionX, int dimensionY, String name){
		try
		{	
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
		 */
		catch(LWJGLException e){
			e.printStackTrace();
			System.out.println("Error. Unable to create a Display");
		}
	}
	/*
	 * This method will be called from the Main class in the game.
	 * This method will update everything to do with the screen.
	 * 
	 * It will be synchronised at the rate of the int rate is set to be at
	 */
	public static void screenUpdate(int rate){
		//This will clear the screen before drawing it again.
		glClear(GL_COLOR_BUFFER_BIT);
		
		
		Display.sync(rate);
		Display.update();
	}
}
