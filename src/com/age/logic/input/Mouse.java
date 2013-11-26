package com.age.logic.input;

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
	
	
	public static void onUpdate() {
		
		
		
		
		
		
	}
}
