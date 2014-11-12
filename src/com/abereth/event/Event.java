package com.abereth.event;


/**
 * Created by sanic on 12/11/2014.
 */
public abstract class Event
{
	public abstract boolean isDone();
	public abstract void OnUpdate( int delta );
}
