package com.abereth;

import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;

/**
 * Created by apex on 26/07/14.
 */

public class Game {

	public Game(String name, int dimensionX, int dimensionY)
	{
		create(dimensionX, dimensionY, name);
	}

	public static int fps;
	public static int actualFps;
	static long lastFrame, lastFPS;
	static int delta;
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

	private static int getDelta() {
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		return delta;
	}

	public static void create(int dimensionX, int dimensionY, String title){
		try {
			Display.setDisplayMode(new DisplayMode(dimensionX, dimensionY));
			Display.setTitle(title);
			Display.create();
			Display.setResizable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
		glViewport(0, 0, dimensionX, dimensionY);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, dimensionX, dimensionY, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		lastFPS = getTime();

		Display.setVSyncEnabled(true);
	}

	public static void update(){
		delta = getDelta();
		updateFPS();
		glClearColor(0.0F, 0.0F, 0.0F, 1f);
		Display.update();
		glClear(GL_COLOR_BUFFER_BIT);
	}

	public static void refresh(int rate){
		Display.sync(rate);
	}

}
