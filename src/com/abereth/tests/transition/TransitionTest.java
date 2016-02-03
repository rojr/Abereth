package com.abereth.tests.transition;

import com.abereth.G;
import com.abereth.draw.Color;
import com.abereth.draw.shapes.Square;
import com.abereth.event.TimedEvent;
import com.abereth.event.view.transitions.FadeTransition;
import com.abereth.game.Game;
import com.abereth.game.View;
import com.abereth.input.Mouse;

/**
 * Created by sanic on 18/11/2014.
 */
public class TransitionTest
{

	public static void main( String[] args )
	{
		Game g = new Game( );
		g.addView( new ViewOne( g ) );
		g.Start();
	}

	public static class ViewOne extends View
	{
		public ViewOne( Game game )
		{
			super( game );
			Square square = new Square( 0, 0, G.WIDTH, G.HEIGHT );
			square.setColor( Color.YELLOW );
			add( square );
		}

		@Override
		public void initialize()
		{
			super.initialize();
		}

		@Override
		public void update( int delta )
		{
			if( Mouse.isLeftMouseClicked() )
			{
				getGame().ChangeView( new ViewTwo( getGame() ), new FadeTransition( 0.004f ) );
			}
		}
	}

	public static class ViewTwo extends View
	{
		public ViewTwo( Game game )
		{
			super( game );
			Square square = new Square( 0, 0, G.WIDTH, G.HEIGHT );
			square.setColor( Color.GREEN );
			add( square );
			getEventManager().add( new TimedEvent<View>()
			{
				@Override
				public void init( View view )
				{
					super.init( view );
					setInterval(1000);
				}

				@Override
				public void eachInterval(int delta, View view)
				{
					System.out.println( view.getGame().GetViews().size() );
				}

				@Override
				public boolean isDone( View view )
				{
					return false;
				}
			}, false );
		}

		@Override
		public void update( int delta )
		{
			if( Mouse.isLeftMouseClicked() )
			{
				getGame().ChangeView( new ViewOne( getGame() ), new FadeTransition( 0.004f ) );
			}
		}
	}
}
