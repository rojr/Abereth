package com.abereth.event.game;

import com.abereth.event.EventManager;
import com.abereth.game.Game;

/**
 * Created by sanic on 17/11/2014.
 */
public class GameEventManager extends EventManager<GameEvent, Game>
{
	public GameEventManager( Game game )
	{
		super( game );
	}
}
