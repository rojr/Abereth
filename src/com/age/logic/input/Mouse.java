package com.age.logic.input;

import com.age.Screen;

public class Mouse {

	private static boolean hasLeftBeenPressed = false;
	private static boolean hasRightBeenPressed = false;
	
	public static boolean isLeftMouseButtonDown(){
		hasLeftBeenPressed = true;
		return org.lwjgl.input.Mouse.isButtonDown(0);
	}
	public static boolean isRightMouseButtonDown(){
		hasRightBeenPressed = true;
		return org.lwjgl.input.Mouse.isButtonDown(1);
	}
	
	public static int getX(){
		float ratioX = Screen.getWidth() / Screen.originalDimensionX;
		
		return (int) (org.lwjgl.input.Mouse.getX() / ratioX);
	}
	public static int getY(){
		float ratioY = Screen.getHeight() / Screen.originalDimensionY;
		return (int) ((Screen.getHeight() - org.lwjgl.input.Mouse.getY()) / ratioY);
	}
	
	
	public static void onUpdate() {
		
		
		
		
		
		
	}
}
