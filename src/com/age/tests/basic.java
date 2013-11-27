package com.age.tests;


import com.age.Age;
import com.age.Screen;
import com.age.graphics.Camera;
import com.age.graphics.effects.Color;
import com.age.graphics.render.Collector;
import com.age.graphics.render.shapes.Box;
import com.age.helper.Helper;
import com.age.logic.input.Keyboard;
import com.age.logic.input.Keyboard.Key;


public class basic {

	public static void main(String[] args) {
		Screen.create(200, 200, "noname");
		
		Box box = new Box(20, 20, 50, 50);
		box.setColor(Color.Yellow);
		Camera camera = new Camera(0, 0, 400, 400);
		Age.cameraList.add(camera);
		while(!Screen.isCloseRequested()){
			Screen.update();
			if(Keyboard.isKeyDown(Key.A)){
				camera.addTranslateX(1);
			}
			box.setRotation(box.getRotation() + 1);
			Collector.add(box);
			
			camera.onUpdate();
			
			Screen.refresh(60);
		}
	}

}
