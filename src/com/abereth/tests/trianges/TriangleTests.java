package com.abereth.tests.trianges;

import com.abereth.G;
import com.abereth.draw.Color;
import com.abereth.event.TimedEvent;
import com.abereth.game.Game;
import com.abereth.game.View;
import com.abereth.helpers.Vector2d;

/**
 * Created by sanic on 11/11/2014.
 */
public class TriangleTests extends View {

	public static void main(String[] args)
	{
		Game g = new Game( );
		g.addView( new TriangleTests( g ) );
		g.Start( );
	}

	private Double[][] coords = new Double[ 4 ][2];
	private int currentlyClicked = 0;

	public TriangleTests( Game game )
	{
		super( game );
		getGame().GetEventManager().CreateNewLogger( "FPS: ", 1000 );
		getEventManager().add( new TimedEvent<View>()
		{
			@Override
			public void eachInterval(int delta, View view)
			{
				System.out.println( "Drawlist: " + getDrawList().size() );
			}

			@Override
			public void init( View view )
			{
				super.init( view );
				setInterval(1000);
			}

			@Override
			public boolean isDone( View view )
			{
				return false;
			}
		}, true );
	}

	@Override
	public void update( int delta )
	{
		currentlyClicked = 0;
		Triangle triangle = new Triangle(
				new Vector2d( Math.random() * G.WIDTH, Math.random() * G.HEIGHT ),
				new Vector2d( Math.random() * G.WIDTH, Math.random() * G.HEIGHT ),
				new Vector2d( Math.random() * G.WIDTH, Math.random() * G.HEIGHT ));
		triangle.setColor( Color.random() );
		add( triangle );
	}
}
