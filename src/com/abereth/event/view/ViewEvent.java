package com.abereth.event.view;

import com.abereth.event.Event;
import com.abereth.game.View;

/**
 * Created by sanic on 12/11/2014.
 */
public abstract class ViewEvent<ParentObject extends View> extends Event<ParentObject>
{
	@Override
	public abstract boolean isDone( ParentObject parentObject );

	@Override
	public abstract void OnUpdate( int delta, ParentObject parentObject );
}
