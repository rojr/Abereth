package com.age.graphics;

import java.util.ArrayList;

/**
 * Created by apex on 29/01/14.
 */
public abstract class DrawableGroup extends Drawable{
    private ArrayList<Drawable> list = new ArrayList<Drawable>();
    public DrawableGroup(double x, double y){
        super(x,y);
    }

    public abstract void draw();
}
