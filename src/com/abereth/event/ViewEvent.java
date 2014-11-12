package com.abereth.event;

import com.abereth.game.View;

/**
 * Created by sanic on 12/11/2014.
 */
public abstract class ViewEvent extends Event
{
	@Deprecated
	public void OnUpdate ( int delta )
	{
	}

	@Deprecated
	public boolean isDone ( )
	{
		return false;
	}

	public abstract boolean isDone ( View view );

	public abstract void OnUpdate ( int delta, View view );
}
