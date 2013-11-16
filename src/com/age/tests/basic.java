package com.age.tests;

import com.age.Screen;

import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glVertex2d;
import static org.lwjgl.opengl.GL11.GL_QUADS;

import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glPopMatrix;


public class basic {

	public static void main(String[] args) {
		Screen.create(200, 200, "noname");
		while(!Screen.isCloseRequested()){
			Screen.update();
			float ratioX = Screen.getWidth() / Screen.originalDimensionX;
			float ratioY = Screen.getHeight() / Screen.originalDimensionY;
						glPushMatrix();
			glScalef(ratioX, ratioY, 0);
			
			glBegin(GL_QUADS);
				glVertex2d(25, 25);
				glVertex2d(25, 50);
				glVertex2d(50, 50);
				glVertex2d(50, 25);
			glEnd();
			glPopMatrix();
			
			Screen.refresh(60);
		}
	}

}
