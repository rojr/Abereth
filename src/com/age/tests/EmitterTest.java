package com.age.tests;

import com.age.Age;
import com.age.Screen;
import com.age.event.EventEmitterOnDotCreate;
import com.age.graphics.effects.Color;
import com.age.graphics.effects.Emitter;
import com.age.helper.Random;
import com.age.logic.input.Keyboard;

/**
 * Created by apex on 11/03/14.
 */
public class EmitterTest {
    public static void main(String... args){
        Screen.create(500,500,"");

        Emitter em =(Emitter) new Emitter(40,40,40,40,0.5).toEngine();
        em.setOnDotCreate(new EventEmitterOnDotCreate() {
            @Override
            public void event(Object e) {
                Emitter.Dot dot =(Emitter.Dot) e;
                dot.setTexture(Random.choose(Age.letterTexID));
                dot.setColor(Color.random(new Color[]{Color.RED, Color.BLUE}));
                dot.setOpacity((float)Math.random());
            }
        });
        while(!Screen.isCloseRequested()){
            Screen.update();

            //em.setRandomChildTexture(Age.letterTexID);
            if(Keyboard.isKeyPressed(Keyboard.Key.Space)){
                Age.remove(em);
            }
            if(Keyboard.isKeyPressed(Keyboard.Key.B)){
                Age.add(em);
            }
            Screen.refresh(60);
        }
    }
}
