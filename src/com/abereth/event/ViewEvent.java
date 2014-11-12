package com.abereth.event;

import com.abereth.game.View;

/**
 * Created by sanic on 12/11/2014.
 */
public interface ViewEvent extends Event
{
	void OnUpdate( View view );
}
