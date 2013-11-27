package com.age.tests;


import com.age.Screen;
import com.age.graphics.effects.Color;
import com.age.graphics.render.Collector;
import com.age.graphics.render.shapes.Box;
import com.age.logic.input.Keyboard;
import com.age.logic.input.Mouse;
import com.age.logic.input.Keyboard.Key;


public class basic {

	public static void main(String[] args) {
		Screen.create(200, 200, "noname");
		
		Box box = new Box(20, 20, 50, 50);
		box.setColor(Color.Yellow);
		while(!Screen.isCloseRequested()){
			Screen.update();
			box.setRotation(box.getRotation() + 35);
			box.setLocationByCenter(Mouse.getX(), Mouse.getY());
			Collector.add(box);
			
			Screen.refresh(60);
		}
	}

}
