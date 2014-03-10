package com.age.tests;

import com.age.Screen;
import com.age.graphics.ui.Image;
import com.age.graphics.ui.Text;

/**
 * Created by apex on 10/03/14.
 */
public class ExceptionTest {
    public static void main(String... args){
        Screen.create(500,500,"");

        Text t = new Text(20,20);
        t.add(new Image(20,20,20,20));

        while(!Screen.isCloseRequested()){
            Screen.update();



            Screen.refresh(60);
        }
    }
}
