package com.abereth.objects.dead;

import com.abereth.draw.Drawable;
import com.abereth.objects.Objects;

/**
 * Created by apex on 27/07/14.
 */
public abstract class Dead extends Drawable implements Objects
{

	private double x,y;
	public Dead(double x, double y)
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public void setX(double x)
	{
		this.x = x;
	}

	@Override
	public void setY(double y)
	{
		this.y = y;
	}

	@Override
	public double getX()
	{
		return x;
	}

	@Override
	public double getY()
	{
		return y;
	}
}
