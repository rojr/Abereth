package com.gmail.robmadeyou.tests;

import com.gmail.robmadeyou.Layer;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Gui.Button;
import com.gmail.robmadeyou.Gui.Interface;
import com.gmail.robmadeyou.Screen.GameType;

public class GuiTest {
	public static void main(String args[]){
		Screen.create(500, 500, "Demo", GameType.CUSTOM, false);
		
		Interface.add(new Button(40, 40));
		
		while(!Screen.isAskedToClose()){
			Screen.update(60);
			
			
			
			
			
			Screen.refresh();
		}
	}
}
