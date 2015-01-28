package com.abereth.event.view;

import com.abereth.event.Event;
import com.abereth.event.EventManager;
import com.abereth.game.View;

/**
 * Created by jeremiah on 12/11/2014.
 */
public class ViewEventManager<V extends View> extends EventManager<Event<V>, V>
{
	public ViewEventManager( V view )
	{
		super( view );
	}
}
