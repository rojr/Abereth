package com.age.tests;

import com.age.Age;
import com.age.Screen;
import com.age.graphics.effects.Color;
import com.age.graphics.effects.TextureLoader;
import com.age.graphics.render.Collector;
import com.age.graphics.render.shapes.Box;
import com.age.graphics.ui.Text;
import com.age.graphics.ui.display.Image;
import com.age.logic.input.Keyboard;
import com.age.logic.input.Keyboard.Key;
import com.age.logic.input.Mouse;


public class basic {

	public static void main(String[] args) {
		Screen.create(600, 200, "noname");
		
		Box box = (Box) Age.add(new Box(20, 20, 50, 50));
		Box box2 = (Box) Age.add(new Box(40, 50, 20, 50));
		Box box3 = (Box) Age.add(new Box(0, 0, 50,50));
		box3.setLayer(4);
		
		box3.setColor(Color.Red);
		box.setColor(Color.Yellow);
		box.setUseTranslate(true);
		box2.setUseTranslate(true);
		
		box.setUseBounds(true);
		box.setBoundsX(0);
		box.setBoundsY(0);
		box.setBoundsWidth(80);
		box.setBoundsHeight(50);
		
		Image img = (Image) Age.add(new Image(100, 40, 60, 70));
		img.text("Homeless man was here once");
		img.setColor(Color.Green);
		Text t = (Text) Age.add(new Text("ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "", 300, 20));
		Text t2 = (Text)Age.add(new Text("abcdefghijklmnopqrstuvwxyz" + "", 300, 50));
		box2.setTexture(TextureLoader.createTexture("flame.png"));
		Text fps = (Text) Age.add(new Text("31241683"+"edqwdqwd", 100, 100));
		int rot = 0, rot2 = 0, rot3 = 0, rot4 = 0;
		fps.setUseTranslate(true);
		while(!Screen.isCloseRequested()){
			Screen.update();
			fps.setText(Screen.actualFps+"");
			fps.setLocation(Mouse.getTranslatedX() + 20, Mouse.getTranslatedY());
			t.setRotation((int)( 360 * Math.random()));
			box.setRotation(box.getRotation() - 1);
			if(Keyboard.isKeyPressed(Key.A)){
				Age.cameraMain.setTarget(box);
			}else if(Keyboard.isKeyPressed(Key.S)){
				Age.cameraMain.setTarget(box2);
			}else if(Keyboard.isKeyDown(Key.D)){
				box2.setDrawX(box2.getDrawX() - 2);
			}	
			img.setDrawX(Mouse.getTranslatedX());
			box.setLayer(2);
			Screen.refresh(60);
		}
	}
}
