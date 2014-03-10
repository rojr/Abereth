package com.age.tests;

import com.age.Screen;
import com.age.graphics.effects.Color;
import com.age.graphics.ui.Image;
import com.age.graphics.ui.Text;

/**
 * Created by apex on 10/03/14.
 */
public class LetterTest {
    public static void main(String... args){
        Screen.create(500,500,"aaaa");

        Text t = (Text) new Text("ABCDEFGHIJKLMNOPQRSTUVWQYZ\nabcdefghijklmnopqrstuvwxyz\n1234567890",20,20,20,20).toEngine();
        t.setColor(Color.RED);

        while(!Screen.isCloseRequested()){
            Screen.update();
            if(t.getRotationY() >= 40)
                t.setRotation(t.getRotation()+3);
            else{
                t.setRotation(t.getRotation()-3);
            }

            Screen.refresh(60);
        }
    }
}
