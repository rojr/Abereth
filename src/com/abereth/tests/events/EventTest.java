package com.abereth.tests.events;

import com.abereth.G;
import com.abereth.draw.Color;
import com.abereth.event.TimedEvent;
import com.abereth.event.view.ViewEvent;
import com.abereth.game.Game;
import com.abereth.game.View;
import com.abereth.input.Keyboard;
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
		g.Start( );
	}

	public EventTest ( Game game )
	{
		super( game );
		getEventManager().add( new ViewEvent()
		{
			@Override
			public boolean isDone( View view )
			{
				return Mouse.isLeftMouseClicked();
			}

			@Override
			public void onUpdate(int delta, View view)
			{
				if( Math.random() >= 0.9 )
				{
					view.add( new Point( ( int ) ( Math.random() * G.WIDTH ), ( int ) ( Math.random() * G.HEIGHT ) ) );
				}
			}
		}, false );
	}

	@Override
	public void initialize()
	{
		super.initialize();

		getGame().GetEventManager().CreateNewLogger( "FPS: ", 1000 );
		getEventManager().add( new TimedEvent<View>( )
		{

			@Override
			public void init ( View view )
			{
				super.init( view );
				setInterval(1000);
			}

			@Override
			public void eachInterval(int delta, View view)
			{
				view.setColor( Color.random() );
			}

			@Override
			public boolean isDone ( View view )
			{
				return false;
			}
		}, false );
	}

	@Override
	public void update ( int delta )
	{
		if( Keyboard.isKeyPressed( Keyboard.Key.A ) )
		{
			fadeOut( 0.04f );
		}
		else if( Keyboard.isKeyPressed( Keyboard.Key.S ) )
		{
			fadeIn( 0.04f );
		}

		VIEW_X_OFFSET += Mouse.getDX();
		VIEW_Y_OFFSET -= Mouse.getDY();

		if( Mouse.isRightMouseClicked( ) ){
			getEventManager().add( new ViewEvent( )
			{
				@Override
				public boolean isDone ( View view )
				{
					return Mouse.isLeftMouseClicked( );
				}

				@Override
				public void onUpdate(int delta, View view)
				{
					if ( Math.random( ) >= 0.9 )
					{
						view.add( new Point( ( int ) ( Math.random( ) * G.WIDTH ), ( int ) ( Math.random( ) * G.HEIGHT ) ) );
					}
				}
			}, false );
		}
	}
}
