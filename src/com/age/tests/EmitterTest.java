package com.age.tests;

import com.age.Age;
import com.age.Game;
import com.age.View;
import com.age.event.Event;
import com.age.graphics.effects.Color;
import com.age.graphics.effects.Emitter;
import com.age.helper.Random;
import com.age.logic.input.Keyboard;

/**
 * Created by apex on 11/03/14.
 */
public class EmitterTest extends View{
    Emitter em;
    public EmitterTest(){
        super("EmitterTest");
    }

    @Override
    public void dispose() {

    }

    @Override
    public void init() {
        em =(Emitter) new Emitter(40,40,40,40,0.5).toEngine();
        em.setOnDotCreate(new Event() {
            @Override
            public void event(Object e) {
                Emitter.Dot dot =(Emitter.Dot) e;
                dot.setTexture(Random.choose(Age.letterTexID));
                dot.setColor(Color.random(new Color[]{Color.RED, Color.BLUE}));
                dot.setOpacity((float)Math.random());
            }
        });
    }

    @Override
    public void update() {
        if(Keyboard.isKeyPressed(Keyboard.Key.Space)){
            Age.remove(em);
        }
        if(Keyboard.isKeyPressed(Keyboard.Key.B)){
            Age.add(em);
        }
    }

    public static void main(String... args){
        Game g = new Game("EmitterTest", 500,500);
        g.start(new EmitterTest());
    }
}
