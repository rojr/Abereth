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

        while(!Screen.isCloseRequested()){
            Screen.update();
            t.set(Sys.getTime()+"");
            t.setRotation(t.getRotation()+32);
            Screen.refresh(60);
        }
    }
}
