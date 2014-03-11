package com.age.tests;

import com.age.Screen;
import com.age.graphics.effects.Emitter;
import com.age.logic.input.Mouse;

/**
 * Created by apex on 11/03/14.
 */
public class EmitterTest {
    public static void main(String... args){
        Screen.create(500,500,"");

        Emitter em =(Emitter) new Emitter(40,40,40,40,0.5).toEngine();
        while(!Screen.isCloseRequested()){
            Screen.update();


            Screen.refresh(60);
        }
    }
}
