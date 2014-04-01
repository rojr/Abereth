package com.age;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import com.age.graphics.Camera;
import com.age.graphics.render.Collector;
import org.newdawn.slick.tests.CanvasContainerTest;

import java.awt.*;

import static org.lwjgl.opengl.GL11.*;

public class Screen {

	public static boolean detailsActive = false;
	static boolean worldCreated = false;
	private static long lastFrame;
	private static long lastFPS;
	private static int fps = 0;
	public static int actualFps = 0;
	public static int delta = getDelta();
	public static boolean applet = false;
	
	public static float originalDimensionX = 0;
	public static float originalDimensionY = 0;

	public static int getWidth(){
		return Display.getWidth();
	}
	public static int getHeight(){
		return Display.getHeight();
	}
	
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

	public static boolean isCloseRequested(){
		return Display.isCloseRequested();
	}
	
	public static void create(int dimensionX, int dimensionY, String title){
		originalDimensionX = dimensionX;
		originalDimensionY = dimensionY;
		try {
			Display.setDisplayMode(new DisplayMode(dimensionX, dimensionY));
			Display.setTitle(title);
            Display.create();
            Display.setResizable(false);
            Canvas c = new Canvas();
		} catch (Exception e) {
			e.printStackTrace();
		}
		lastFPS = getTime();
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
	    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
		glViewport(0, 0, dimensionX, dimensionY);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, dimensionX, dimensionY, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		Age.init();
		
		Display.setVSyncEnabled(true);
	}
	
	public static void update(){
		
		glClearColor(0.0F, 0.0F, 0.0F, 1f);
		Display.update();
		updateFPS();
		delta = getDelta();
		if(Display.wasResized()) fixDimensions();
		glClear(GL_COLOR_BUFFER_BIT);
		Age.onUpdate();
	}
	
	public static void refresh(int rate){
		//Update all cameras
		for(Camera c : Age.cameraList){
			c.onUpdate();
		}
		Display.sync(rate);
		for(int i = 0; i < Collector.drawArray.size(); i++){
			Collector.drawArray.get(i).clear();
		}
	}
	
	static void fixDimensions(){
        try{
            Display.setDisplayConfiguration((float)Math.random(),(float)Math.random(),(float) Math.random());

        }catch(Exception e){

        }
        glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
		glViewport(200, 0, getWidth(), getHeight());
		//glMatrixMode(GL_PROJECTION);
		//glLoadIdentity();
		//glOrtho(0, getWidth(), getHeight(), 0, 1, -1);
		//glMatrixMode(GL_MODELVIEW);
        //Age.cameraMain.setTranslateX(Age.cameraMain.getTranslateX() / Age.ratioX());
        //Age.cameraMain.setTranslateY(Age.cameraMain.getTranslateY() / Age.ratioY());
        Age.cameraMain.setWidth(getWidth());
        Age.cameraMain.setHeight(getHeight());
	}
}