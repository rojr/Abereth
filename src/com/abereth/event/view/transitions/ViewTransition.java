package com.abereth.event.view.transitions;

import com.abereth.event.view.ViewEvent;
import com.abereth.game.View;

/**
 * Created by sanic on 18/11/2014.
 */
public abstract class ViewTransition extends ViewEvent
{
	public boolean isTransitionFinished;
	public boolean first;

	public ViewTransition( boolean first )
	{
		super();
		this.first = first;
		this.isTransitionFinished = false;
	}

	public void init( View from, View to  )
	{
		super.init( from );
	}

	@Override
	public boolean isDone( View view )
	{
		return this.isTransitionFinished;
	}
}
