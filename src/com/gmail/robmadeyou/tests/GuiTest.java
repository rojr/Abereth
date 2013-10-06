package com.gmail.robmadeyou.tests;


import com.gmail.robmadeyou.Abereth;
import com.gmail.robmadeyou.ABLayer;
import com.gmail.robmadeyou.ABScreen;
import com.gmail.robmadeyou.Effects.ABColor;
import com.gmail.robmadeyou.Gui.ABButton;
import com.gmail.robmadeyou.Gui.ABUI;
import com.gmail.robmadeyou.Gui.ABText;
import com.gmail.robmadeyou.Input.ABKeyboard;
import com.gmail.robmadeyou.Input.ABKeyboard.ABKey;
import com.gmail.robmadeyou.Input.ABMouse;
import com.gmail.robmadeyou.ABScreen.GameType;
import com.gmail.robmadeyou.World.ABCamera;

public class GuiTest {
	public static void main(String args[]){
		ABScreen.create(500, 500, "Demo", GameType.CUSTOM, false);
		
		ABButton b = (ABButton) ABUI.add(new ABButton(40, 40));	
		
		Abereth.addNewCamera(new ABCamera(0, 0, 0, 0, ABScreen.getWidth() / 2, ABScreen.getHeight()));
		Abereth.addNewCamera(new ABCamera(ABScreen.getWidth() / 2, 0, 0, 0, ABScreen.getWidth() / 2, ABScreen.getHeight()));
		b.setUseTranslate(false);
		System.out.println(b.getX());
		int bLayer = b.getLayer();
		ABLayer.addLayer(2);
		while(!ABScreen.isAskedToClose()){
			ABScreen.update(60);
			ABText.drawString("x: " + ABMouse.getX(), ABMouse.getTranslatedX() + 10, ABMouse.getTranslatedY(), 1, 1, 1, ABColor.Red, true, false);
			ABText.drawString("y: " + ABMouse.getY(), ABMouse.getTranslatedX() + 10, ABMouse.getTranslatedY() + 20, 1, 1, 1, ABColor.Red, true, false);
			ABText.drawString("fps: " + ABScreen.actualFps, ABMouse.getTranslatedX() + 10, ABMouse.getTranslatedY() + 30, 1, 1, 1, ABColor.Red, true, false);
			
			if(ABKeyboard.isKeyPressed(ABKey.A)){
				if(b.getLayer() == bLayer){
					b.setLayer(2);
				}else{
					b.setLayer(bLayer);
				}
			}
			
			ABScreen.refresh();
		}
		ABScreen.destroy();
	}
}
