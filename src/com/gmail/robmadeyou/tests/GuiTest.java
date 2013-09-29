package com.gmail.robmadeyou.tests;

import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Layer;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Gui.Button;
import com.gmail.robmadeyou.Gui.Interface;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.World.Camera;

public class GuiTest {
	public static void main(String args[]){
		Screen.create(500, 500, "Demo", GameType.CUSTOM, false);
		
		Interface.add(new Button(40, 40));
		
		Camera cam = Engine.addNewCamera(new Camera(0, 0, 0, 0, Screen.getWidth(), Screen.getHeight()));
		
		while(!Screen.isAskedToClose()){
			Screen.update(60);
			
			
			
			
			
			Screen.refresh();
		}
	}
}
