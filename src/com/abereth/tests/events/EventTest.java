package com.abereth.tests.events;

import com.abereth.G;
import com.abereth.event.view.ViewEvent;
import com.abereth.game.Game;
import com.abereth.game.View;
import com.abereth.input.Mouse;
import com.abereth.tests.trianges.Point;

/**
 * Created by jeremiah on 12/11/2014.
 */
public class EventTest extends View
{
	public static void main ( String[] args )
	{
		Game g = new Game( 1024, 512 );
		g.addView( new EventTest( g ) );
		g.start();
	}

	public EventTest ( Game game )
	{
		super( game );
		getEventManager().add( new ViewEvent()
		{
			@Override
			public boolean isDone ( View view )
			{
				return Mouse.isLeftMouseClicked( );
			}

			@Override
			public void OnUpdate ( int delta, View view )
			{
				if( Math.random() >= 0.9 )
				{
					view.add( new Point( (int) (Math.random() * G.WIDTH), (int)(Math.random() * G.HEIGHT ) ) );
				}
			}
		}, false );
	}

	@Override
	public void update ( int delta )
	{
		System.out.println( getGame().actualFps );
		if( Mouse.isRightMouseClicked() )
		{
			getEventManager().add(  new ViewEvent( )
			{
				@Override
				public boolean isDone ( View view )
				{
					return Mouse.isLeftMouseClicked( );
				}

				@Override
				public void OnUpdate ( int delta, View view )
				{
					if( Math.random() >= 0.9 )
					{
						view.add( new Point( (int) (Math.random() * G.WIDTH), (int)(Math.random() * G.HEIGHT ) ) );
					}
				}
			}, false );
		}
	}
}
