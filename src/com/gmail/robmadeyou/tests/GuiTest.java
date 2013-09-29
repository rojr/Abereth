package com.gmail.robmadeyou.tests;


import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Layer;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Gui.Button;
import com.gmail.robmadeyou.Gui.Interface;
import com.gmail.robmadeyou.Gui.Text;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;
import com.gmail.robmadeyou.Input.Mouse;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.World.Camera;

public class GuiTest {
	public static void main(String args[]){
		Screen.create(500, 500, "Demo", GameType.CUSTOM, false);
		
		Button b = (Button) Interface.add(new Button(40, 40));	
		
		Engine.addNewCamera(new Camera(0, 0, 0, 0, Screen.getWidth() / 2, Screen.getHeight()));
		Engine.addNewCamera(new Camera(Screen.getWidth() / 2, 0, 0, 0, Screen.getWidth() / 2, Screen.getHeight()));
		b.setUseTranslate(false);
		System.out.println(b.getX());
		int bLayer = b.getLayer();
		Layer.addLayer(2);
		while(!Screen.isAskedToClose()){
			Screen.update(60);
			Text.drawString("x: " + Mouse.getX(), Mouse.getTranslatedX() + 10, Mouse.getTranslatedY(), 1, 1, 1, Color.Red, true, false);
			Text.drawString("y: " + Mouse.getY(), Mouse.getTranslatedX() + 10, Mouse.getTranslatedY() + 20, 1, 1, 1, Color.Red, true, false);
			Text.drawString("fps: " + Screen.actualFps, Mouse.getTranslatedX() + 10, Mouse.getTranslatedY() + 30, 1, 1, 1, Color.Red, true, false);
			
			if(Keyboard.isKeyPressed(Key.A)){
				if(b.getLayer() == bLayer){
					b.setLayer(2);
				}else{
					b.setLayer(bLayer);
				}
			}
			
			Screen.refresh();
		}
		Screen.destroy();
	}
}
