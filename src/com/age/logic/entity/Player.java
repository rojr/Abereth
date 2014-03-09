package com.age.logic.entity;

import com.age.graphics.effects.Animation;
import com.age.graphics.effects.TextureLoader;
import com.age.logic.input.Keyboard;

/**
 * Created by apex on 14/02/14.
 */
public class Player extends Entity {
    public Player(double x, double y){
        this(x, y, 40, 80);

    }
    public Player(double x, double y, double width, double height){
        super(x,y,width,height);
    }

    @Override
    public void update(double delta){
        super.update(delta);
    }
}
