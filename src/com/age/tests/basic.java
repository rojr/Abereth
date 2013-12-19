package com.age.tests;

import com.age.Age;
import com.age.Screen;
import com.age.graphics.effects.Color;
import com.age.graphics.effects.TextureLoader;
import com.age.graphics.render.Collector;
import com.age.graphics.render.shapes.Box;
import com.age.graphics.ui.Text;
import com.age.logic.input.Keyboard;
import com.age.logic.input.Keyboard.Key;
import com.age.logic.input.Mouse;


public class basic {

	public static void main(String[] args) {
		Screen.create(600, 200, "noname");
		
		Box box = new Box(20, 20, 50, 50);
		Box box2 = new Box(40, 50, 50, 50);
		Box box3 = new Box(0, 0, 50,50);
		
		box3.setColor(Color.Red);
		box.setColor(Color.Yellow);
		box.setUseTranslate(true);
		box2.setUseTranslate(true);
		
		Text t = new Text("ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "", 300, 20);
		Text t2 = new Text("abcdefghijklmnopqrstuvwxyz" + "", 300, 50);
		box2.setTexture(TextureLoader.createTexture("flame.png"));
		Text fps = new Text("31241683"+"edqwdqwd", 100, 100);
		while(!Screen.isCloseRequested()){
			Screen.update();
			fps.setText(Screen.actualFps+"");
			fps.setLocation(Mouse.getX() + 20, Mouse.getY());
			
			fps.render();
			t.render();
			t2.render();
			t.setRotation(90, 4);
			box.setRotation(box.getRotation() - 1);
			if(Keyboard.isKeyPressed(Key.A)){
				Age.cameraMain.setTarget(box);
			}else if(Keyboard.isKeyPressed(Key.S)){
				Age.cameraMain.setTarget(box2);
			}else if(Keyboard.isKeyDown(Key.D)){
				box2.setDrawX(box2.getDrawX() - 2);
			}
			Collector.add(box);
			box2.render();
			box.setLayer(2);
			box3.render();
			Screen.refresh(60);
		}
	}

}
