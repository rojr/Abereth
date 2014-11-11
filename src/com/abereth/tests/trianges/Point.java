package com.abereth.tests.trianges;

import com.abereth.draw.Drawable;
import com.abereth.game.Draw;

/**
 * Created by sanic on 11/11/2014.
 */
public class Point extends Drawable
{
	private int x, y;
	public Point( int x, int y )
	{
		super();

		this.x = x;
		this.y = y;
	}

	@Override
	public void draw( Draw d )
	{
		d.dot( x, y );
	}
}
