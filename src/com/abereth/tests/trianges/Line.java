package com.abereth.tests.trianges;

import com.abereth.draw.Drawable;
import com.abereth.game.Draw;

/**
 * Created by sanic on 11/11/2014.
 */
public class Line extends Drawable
{
	private double x, y, x2, y2;
	public Line( double x, double y, double x2, double y2 )
	{
		super();

		this.x = x;
		this.x2 = x2;
		this.y = y;
		this.y2 = y2;
	}

	@Override
	public void draw( Draw d )
	{
		d.line( x, y, x2, y2 );
	}
}
