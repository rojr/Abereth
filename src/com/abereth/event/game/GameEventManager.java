package com.abereth.event.game;

import com.abereth.event.Event;
import com.abereth.event.EventManager;
import com.abereth.game.Game;

/**
 * Created by sanic on 17/11/2014.
 */
public class GameEventManager extends EventManager<Event, Game>
{
	public GameEventManager( Game game )
	{
		super( game );
	}

	public void CreateNewLogger( final String prefix, final long delay )
	{
		add( new LoggerEvent()
		{
			@Override
			public void init ( Game game )
			{
				super.init( game );
				setInterval(delay);
			}

			@Override
			public void eachInterval(int delta, Game game)
			{
				System.out.println( prefix + game.actualFps );
			}

			@Override
			public boolean isDone ( Game game )
			{
				return false;
			}
		}, false);
	}

}
