package com.age.tests;

import com.age.Age;
import com.age.Screen;
import com.age.graphics.Drawable;
import com.age.graphics.ui.Text;
import com.age.logic.input.Mouse;

public class TextTests {
	public static void main (String args[]){
		Screen.create(600, 400, "TextTest");
		
		Text text = (Text) Age.add(new Text("Sup brah?", 50, 50));
		
		while(!Screen.isCloseRequested()){
			Screen.update();
				for(Drawable d : text.getList()){
					
				}
				text.setLocation(Mouse.getTranslatedX(), Mouse.getTranslatedY());
				text.setRotation(text.getList().get(4).getRotation() + 15, 4);
			Screen.refresh(60);
		}
	}
}
