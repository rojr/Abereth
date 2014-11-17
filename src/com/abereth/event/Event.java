package com.abereth.event;

/**
 * Created by sanic on 12/11/2014.
 */
public abstract class Event<ParentObject>
{
	public void init( ParentObject object )
	{}

	public abstract boolean isDone( ParentObject object );
	public abstract void OnUpdate( int delta, ParentObject object );
}
