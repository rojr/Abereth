package com.age.tests;

import org.lwjgl.opengl.Display;

import com.age.Screen;

public class basic {

	public static void main(String[] args) {
		Screen.create(200, 200, "noname");
		while(!Screen.isCloseRequested()){
			Screen.update();
			
			Display.sync(60);
		}
	}

}
