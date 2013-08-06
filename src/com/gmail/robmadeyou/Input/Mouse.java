package com.gmail.robmadeyou.Input;

import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Gui.Interface;

public class Mouse {
	
	public static boolean isLeftMouseButtonPressed(){
		if(leftMouseButtonPressed && !Engine.islmbpThisTick){
			Engine.islmbpThisTick = true;
			return true;
		}
		return false;
	}
	public static boolean isRightMouseButtonPressed(){
		if(rightMouseButtonPressed && !Engine.isrmbpThisTick){
			Engine.isrmbpThisTick = true;
			return true;
		}
		return false;
	}
	
	public static boolean isLeftMouseButtonReleased = false;
	public static boolean isRightMouseButtonReleased = false;
	
	public static boolean isLeftMouseButtonDown = false;
	public static boolean isRightMouseButtonDown = false;
	
	
	
	public static boolean leftMouseButtonPressed = false;
	public static boolean rightMouseButtonPressed = false;
	public static boolean leftMouseButtonReleased = false;
	public static boolean rightMouseButtonReleased = false;
	public static boolean leftMouseButtonDown = false;
	public static boolean rightMouseButtonDown = false;
	
	
	
	private static int x = org.lwjgl.input.Mouse.getX();
	private static int y = com.gmail.robmadeyou.Screen.getHeight() - org.lwjgl.input.Mouse.getY();
	
	public static boolean isOverGui = false;
	
	public static int getX(){
		return x;
	}
	public static int getY(){
		return y;
	}
	public static void onUpdate(){
		isOverGui = false;
		for(int i = 0; i < Interface.visibleObjects.size(); i++){
			if(Interface.visibleObjects.get(i).isMouseOver()){
				isOverGui = true;
			}
		}
		
		x = (int) (org.lwjgl.input.Mouse.getX() -Screen.translate_x);
		y = (int) (com.gmail.robmadeyou.Screen.getHeight() - org.lwjgl.input.Mouse.getY() - Screen.translate_y);
		/*
		 * Here we set the two mouse buttons to be not pressed by default. So they aren't activated "accidentally"
		 */
		leftMouseButtonReleased = false;
		rightMouseButtonReleased = false;
		
		/*
		 * Checking if the right or left mouse buttons are pressed, then setting released variables to true if buttons were 
		 * pressed previous frame.
		 */
		if(leftMouseButtonDown && !org.lwjgl.input.Mouse.isButtonDown(0)){
			leftMouseButtonReleased = true;
		}
		if(rightMouseButtonDown && !org.lwjgl.input.Mouse.isButtonDown(1)){
			rightMouseButtonReleased = true;
		}
		
		leftMouseButtonPressed = false;
		rightMouseButtonPressed = false;
		
		leftMouseButtonDown = false;
		rightMouseButtonDown = false;
		
		while(org.lwjgl.input.Mouse.next()){
			if(org.lwjgl.input.Mouse.getEventButtonState()){
				if(org.lwjgl.input.Mouse.isButtonDown(0)){
					leftMouseButtonPressed = true;
				}
				if(org.lwjgl.input.Mouse.isButtonDown(1)){
					rightMouseButtonPressed = true;
				}
			}
		}
		if(org.lwjgl.input.Mouse.isButtonDown(0)){
			leftMouseButtonDown = true;
		}
		if(org.lwjgl.input.Mouse.isButtonDown(1)){
			rightMouseButtonDown = true;
		}
	}
}
