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

        Text text =(Text) new Text("&!0We&!1lcome\nthis &!9is so&!4met&!6hing &!3to\n think ab&!0out", 150,200).toEngine();
        text.setSize(16);


        /*

        In this test press spacebar to see the text rotate at a random speed and then go back to the original location.
        String manipulation similar to Regular Expressions is also implemented here and will be tested.

         */

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
                            c.getOrigin().getColor().fade(Color.WHITE);
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
