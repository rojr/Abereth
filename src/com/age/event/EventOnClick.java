package com.age.event;

import com.age.logic.input.Mouse;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by apex on 06/03/14.
 */
public abstract class EventOnClick extends Event{
    public EventOnClick(){

    }

    public Vector2f getMouseLocation(){
        return new Vector2f(Mouse.getX(), Mouse.getY());
    }

    public Vector2f getTranslatedMouseLocation(){
        return new Vector2f(Mouse.getTranslatedX(), Mouse.getTranslatedY());
    }
}
