package com.age.tests;

import com.age.Age;
import com.age.Screen;
import com.age.graphics.effects.Color;
import com.age.graphics.effects.TextureLoader;
import com.age.graphics.render.shapes.Box;
import com.age.logic.input.Keyboard;
import com.age.logic.input.Keyboard.Key;

import javax.xml.soap.Text;


public class basic {

	public static void main(String... args) {


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
        box2.setOpacity(0.2f);
        box3.setTexture(TextureLoader.createTexture("res/player/player1.png"));
        box.setTexture(TextureLoader.createTexture("res/player/player1.png", 20, 20, 5, 5));
        box2.setTexture(TextureLoader.createTexture("res/player/player1.png", 20,20,5,5));
		while(!Screen.isCloseRequested()){
            Screen.update();
            box.setRotation(box.getRotation() - 1);
            if(Keyboard.isKeyPressed(Key.A)){
                Age.cameraMain.setTarget(box);
            }else if(Keyboard.isKeyPressed(Key.S)){
                Age.cameraMain.setTarget(box2);
            }else if(Keyboard.isKeyDown(Key.D)){
                box2.setDrawX(box2.getDrawX() - 2);
            }
            if(Keyboard.isKeyPressed(Key.Space)){
                Age.remove(box);
            }
			box.setLayer(2);
			Screen.refresh(60);
		}
	}
}
