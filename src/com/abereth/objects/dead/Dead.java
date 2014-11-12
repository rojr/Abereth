package com.abereth.objects.dead;

import com.abereth.draw.Drawable;

/**
 * Created by apex on 27/07/14.
 */
public abstract class Dead extends Drawable
{

	private double x,y;
	public Dead(double x, double y)
	{
		this.x = x;
		this.y = y;
	}
}
