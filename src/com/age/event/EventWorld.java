package com.age.event;

import com.age.world.World;

/**
 * Created by apex on 06/03/14.
 */
public abstract class EventWorld extends Event{
    public EventWorld(){}

    @Override
    public abstract void event(Object world);
}
