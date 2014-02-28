package com.age.logic.entity;

import com.age.graphics.effects.Animation;
import com.age.graphics.effects.TextureLoader;
import com.age.logic.input.Keyboard;

/**
 * Created by apex on 14/02/14.
 */
public class Player extends Entity {
    private Animation skeleton = new Animation(TextureLoader.createTexture("res/animations/skeleton/1.png"),5);
    public Player(double x, double y){
        super(x,y,40,80);
        skeleton.add(TextureLoader.createTexture("res/animations/skeleton/2.png"))
        .add(TextureLoader.createTexture("res/animations/skeleton/3.png"))
        .add(TextureLoader.createTexture("res/animations/skeleton/4.png"));
    }

    @Override
    public void update(double delta){
        super.update(delta);
        if(Keyboard.isKeyDown(Keyboard.Key.D)){
            moveRight();
            setInvertsY(false);
            setTexture(skeleton.getCurrentTexture());
        }else if(Keyboard.isKeyDown(Keyboard.Key.A)){

            setInvertsY(true);
            setTexture(skeleton.getCurrentTexture());
            moveLeft();
        }else{
            setTexture(skeleton.get(0));
        }
        if(Keyboard.isKeyPressed(Keyboard.Key.Space)){
            jump(-4);
        }
    }
}
