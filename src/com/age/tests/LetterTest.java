package com.age.tests;

import com.age.Screen;
import com.age.graphics.effects.Color;
import com.age.graphics.ui.Image;
import com.age.graphics.ui.Text;
import com.age.helper.Child;
import com.age.logic.input.Keyboard;
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

        Text text =(Text) new Text("Welcome\nthis is something to\n think about", 150,200).toEngine();
        text.setSize(16);


        while(!Screen.isCloseRequested()){
            Screen.update();


            if(Keyboard.isKeyDown(Keyboard.Key.Space)){
                for(Child c : text.getChildren()){
                    c.getOrigin().setRotation(c.getOrigin().getRotation() + ( (int) (Math.random() * 10 )));
                }
            }else{
                for(Child c : text.getChildren()){
                    if(c.getOrigin().getRotation() <= 0 || true){
                        if(c.getOrigin().getRotation() == 0){
                            c.getOrigin().getColor().fade(Color.GREEN);
                        }else{
                            c.getOrigin().setColor((Color.random(new Color[]{Color.BLUE, Color.RED})));
                        }
                        c.getOrigin().setRotation((int)(c.getOrigin().getRotation() * (0.99 - Math.random() / 10)));
                    }else{
                        c.getOrigin().setRotation(c.getOrigin().getRotation()- 1);
                    }
                }
            }

            t.setRotation(t.getRotation() + 1);
            Screen.refresh(60);
        }
    }
}
