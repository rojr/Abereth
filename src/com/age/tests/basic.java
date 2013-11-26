package com.age.tests;

import com.age.Age;
import com.age.Screen;
import com.age.graphics.Camera;
import com.age.graphics.effects.Color;
import com.age.graphics.render.Collector;
import com.age.graphics.render.shapes.Box;

import static org.lwjgl.opengl.GL11.glScalef;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glPopMatrix;


public class basic {

	public static void main(String[] args) {
		Screen.create(200, 200, "noname");
		
		Box box = new Box(20, 20, 50, 50);
		box.setColor(Color.Blue);
		Camera camera = new Camera(0, 0, 400, 400);
		Age.cameraList.add(camera);
		while(!Screen.isCloseRequested()){
			Screen.update();
			float ratioX = Screen.getWidth() / Screen.originalDimensionX;
			float ratioY = Screen.getHeight() / Screen.originalDimensionY;
						glPushMatrix();
			if(Collector.add(box)){
				System.out.println("aaa");
			}
			glScalef(ratioX, ratioY, 0);
			camera.onUpdate();
			glPopMatrix();
						
			
			Screen.refresh(60);
		}
	}

}
