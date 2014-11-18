package com.abereth.tests.trianges;

import com.abereth.game.Game;
import com.abereth.game.View;
import com.abereth.helpers.Vector2d;
import com.abereth.input.Mouse;

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
	}

	@Override
	public void update( int delta )
	{
		if( Mouse.isLeftMouseClicked() )
		{
			if( currentlyClicked != 4 )
			{
				System.out.println( currentlyClicked );
				coords[ currentlyClicked ][0] = ( double ) Mouse.getX();
				coords[ currentlyClicked ][1] = ( double ) Mouse.getY();

				if( currentlyClicked != 3 )
				{
					add( new Point( Mouse.getX(), Mouse.getY() ) );
				}

				if( currentlyClicked >= 1 )
				{
					if( currentlyClicked == 3 )
					{
						add( new Line(
								coords[ 2 ][0], coords[ 2 ][1],
								coords[ 0 ][0], coords[ 0 ][1]
						) );
					}
					else
					{
						add( new Line(
								coords[ currentlyClicked ][0], coords[ currentlyClicked ][1],
								coords[ currentlyClicked - 1 ][0], coords[ currentlyClicked - 1 ][1]
						) );
					}
				}
				currentlyClicked++;
			}
			else
			{
				System.out.println( "Done" );
				currentlyClicked = 0;
				add( new Triangle(
						new Vector2d( coords[ 0 ][ 0 ], coords[ 0 ][ 1 ] ),
						new Vector2d( coords[ 1 ][ 0 ], coords[ 1 ][ 1 ] ),
						new Vector2d( coords[ 2 ][ 0 ], coords[ 2 ][ 1 ] )
				) );
			}
		}
	}
}
