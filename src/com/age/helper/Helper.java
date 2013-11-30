package com.age.helper;

import com.age.Screen;

public class Helper {
	public static float addScaling(float val){
		float ratioX = Screen.getWidth() / Screen.originalDimensionX;
		float ratioY = Screen.getHeight() / Screen.originalDimensionY;
		return val * (ratioX / ratioY);
	}
}
