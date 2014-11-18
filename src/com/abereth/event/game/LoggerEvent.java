package com.abereth.event.game;

import com.abereth.event.TimedEvent;
import com.abereth.game.Game;

/**
 * Created by jeremiah on 17/11/2014.
 */
public abstract class LoggerEvent extends TimedEvent<Game>
{
	public LoggerEvent ( )
	{
		super( );
		SetInterval( 1000 );
	}
}
