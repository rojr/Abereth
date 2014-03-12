package com.age.tests;

import com.age.Screen;
import com.age.graphics.effects.Color;
import com.age.graphics.ui.Container;
import com.age.graphics.ui.Image;
import com.age.graphics.ui.Text;
import com.age.helper.Child;
import com.age.logic.input.Keyboard;
import com.age.logic.input.Mouse;

/**
 * Created by apex on 06/03/14.
 */
public class ContainerTest {
    public static void main(String... args){
        Screen.create(500,500,"");


        Container c = new Container(20,20,100,100).toEngine();
        Image i = new Image(40, 4, 200, 20);
        i.setColor(Color.AQUA);
        c.add(i);
        while(!Screen.isCloseRequested()){
            Screen.update();
            c.setRotation(c.getRotation() + 1);

            c.setRotationX(Mouse.getTranslatedX());
            c.setRotationY(Mouse.getTranslatedY());

            Screen.refresh(60);
        }
    }
}
