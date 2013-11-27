package com.age.helper;

import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glScalef;

import com.age.Screen;

public class Helper {
	public static float addScaling(float val){
		float ratioX = Screen.getWidth() / Screen.originalDimensionX;
		float ratioY = Screen.getHeight() / Screen.originalDimensionY;
		return val * (ratioX / ratioY);
	}
}
