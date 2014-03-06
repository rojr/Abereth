package com.age.event;

import com.age.graphics.Drawable;

/**
 * Created by apex on 06/03/14.
 */
public abstract class EventDrawable extends Event{
    public EventDrawable(){}

    public abstract void event(Drawable d);
}
