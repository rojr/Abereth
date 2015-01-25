package com.abereth.draw.shapes;

import com.abereth.draw.Drawable;
import com.abereth.game.Draw;

/**
 * Created by sanic on 18/11/2014.
 */
public class Square extends Drawable
{
	public Square( int x, int y, int width, int height )
	{
		super( x, y, width, height );
	}

	@Override
	public void draw( Draw d )
	{
		d.square( getDrawX(), getDrawY(), getDrawWidth(), getDrawHeight() );
	}
}
