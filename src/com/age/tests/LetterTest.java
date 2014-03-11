package com.age.tests;

import com.age.Screen;
import com.age.graphics.effects.Color;
import com.age.graphics.ui.Image;
import com.age.graphics.ui.Text;
import org.lwjgl.Sys;

/**
 * Created by apex on 10/03/14.
 */
public class LetterTest {
    public static void main(String... args){
        Screen.create(500,500,"aaaa");

        Text t = (Text) new Text("",20,20,0,0).toEngine();
        t.setColor(Color.RED);

        t.setRotationY(Screen.getWidth() / 2);
        t.setRotationX(Screen.getHeight() / 2);

        while(!Screen.isCloseRequested()){
            Screen.update();
            t.setRotation(t.getRotation() + 1);
            Screen.refresh(60);
        }
    }
}
