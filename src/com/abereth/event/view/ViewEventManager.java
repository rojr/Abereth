package com.abereth.event.view;

import com.abereth.event.EventManager;
import com.abereth.game.View;

/**
 * Created by jeremiah on 12/11/2014.
 */
public class ViewEventManager extends EventManager<ViewEvent, View>
{
	public ViewEventManager( View view )
	{
		super( view );
	}
}
