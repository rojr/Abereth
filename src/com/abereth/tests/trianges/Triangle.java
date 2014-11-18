package com.abereth.tests.trianges;

import com.abereth.draw.Drawable;
import com.abereth.game.Draw;
import com.abereth.helpers.Vector2d;

/**
 * Created by sanic on 11/11/2014.
 */
public class Triangle extends Drawable
{
	private Vector2d a,b,c;
	public Triangle( Vector2d a, Vector2d b, Vector2d c )
	{
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}

	@Override
	public void Draw( Draw d )
	{
		d.triangle( a, b, c );
	}
}
