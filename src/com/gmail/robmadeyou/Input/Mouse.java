package com.gmail.robmadeyou.Input;

import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Gui.Interface;
import com.gmail.robmadeyou.Screen;

public class Mouse {

    public static boolean isLeftMouseButtonPressed() {
        if (leftMouseButtonPressed && !Engine.islmbpThisTick) {
            Engine.islmbpThisTick = true;
            return true;
        }
        return false;
    }

    public static boolean isRightMouseButtonPressed() {
        if (rightMouseButtonPressed && !Engine.isrmbpThisTick) {
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
    private static int y = Screen.getHeight() - org.lwjgl.input.Mouse.getY();

    public static boolean isOverGui = false;

    public static float getX() {
    	for(int i = 0; i < Engine.cameraList.size(); i++){
    		float sX = Math.round(Engine.cameraList.get(i).getCamX());
    		float sY = Math.round(Engine.cameraList.get(i).getCamY());
    		float sW = Engine.cameraList.get(i).getWidth();
    		float sH = Engine.cameraList.get(i).getHeight();
    		
    		if(x >= sX && x <= sX + sW && y >= sY && y <= sY + sH){
    			return x - sX;
    		}
    	}
        return x;
    }

    public static float getY() {
    	for(int i = 0; i < Engine.cameraList.size(); i++){
    		float sX = Engine.cameraList.get(i).getCamX();
    		float sY = Engine.cameraList.get(i).getCamY();
    		float sW = Engine.cameraList.get(i).getWidth();
    		float sH = Engine.cameraList.get(i).getHeight();
    		
    		if(x >= sX && x <= sX + sW && y >= sY && y <= sY + sH){
    			return y - sY;
    		}
    	}
        return y;
    }
    
    public static float getTranslatedX(){
    	for(int i = 0; i < Engine.cameraList.size(); i++){
    		float sX = Math.round(Engine.cameraList.get(i).getCamX());
    		float sY = Math.round(Engine.cameraList.get(i).getCamY());
    		float sW = Engine.cameraList.get(i).getWidth();
    		float sH = Engine.cameraList.get(i).getHeight();
    		
    		if(x >= sX && x <= sX + sW && y >= sY && y <= sY + sH){
    			return x - Engine.cameraList.get(i).getX() - sX;
    		}
    	}
    	return x;
    }
    public static float getTranslatedY(){
    	for(int i = 0; i < Engine.cameraList.size(); i++){
    		double sX = Engine.cameraList.get(i).getCamX();
    		double sY = Engine.cameraList.get(i).getCamY();
    		double sW = Engine.cameraList.get(i).getWidth();
    		double sH = Engine.cameraList.get(i).getHeight();
    		
    		if(x >= sX && x <= sX + sW && y >= sY && y <= sY + sH){
    			return (float) (y - Engine.cameraList.get(i).getY() - sY);
    		}
    	}
    	return y;
    }

    public static int getDX() {
        return org.lwjgl.input.Mouse.getDX();
    }

    public static int getDY() {
        return -org.lwjgl.input.Mouse.getDY();
    }

    public static void onUpdate() {
        isOverGui = false;
        for (int i = 0; i < Interface.visibleObjects.size(); i++) {
            if (Interface.visibleObjects.get(i).isMouseOver()) {
                isOverGui = true;
            }
        }

        x = (int) (org.lwjgl.input.Mouse.getX());
        y = (int) (com.gmail.robmadeyou.Screen.getHeight() - org.lwjgl.input.Mouse.getY());
        /*
		 * Here we set the two mouse buttons to be not pressed by default. So they aren't activated "accidentally"
		 */
        leftMouseButtonReleased = false;
        rightMouseButtonReleased = false;
		
		/*
		 * Checking if the right or left mouse buttons are pressed, then setting released variables to 
		 * true if buttons were pressed previous frame.
		 */
        if (leftMouseButtonDown && !org.lwjgl.input.Mouse.isButtonDown(0)) {
            leftMouseButtonReleased = true;
        }
        if (rightMouseButtonDown && !org.lwjgl.input.Mouse.isButtonDown(1)) {
            rightMouseButtonReleased = true;
        }

        leftMouseButtonPressed = false;
        rightMouseButtonPressed = false;

        leftMouseButtonDown = false;
        rightMouseButtonDown = false;

        while (org.lwjgl.input.Mouse.next()) {
            if (org.lwjgl.input.Mouse.getEventButtonState()) {
                if (org.lwjgl.input.Mouse.isButtonDown(0)) {
                    leftMouseButtonPressed = true;
                }
                if (org.lwjgl.input.Mouse.isButtonDown(1)) {
                    rightMouseButtonPressed = true;
                }
            }
        }
        if (org.lwjgl.input.Mouse.isButtonDown(0)) {
            leftMouseButtonDown = true;
        }
        if (org.lwjgl.input.Mouse.isButtonDown(1)) {
            rightMouseButtonDown = true;
        }
    }
}
