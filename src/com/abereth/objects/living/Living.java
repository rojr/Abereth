package com.abereth.objects.living;

import com.abereth.draw.Drawable;
import com.abereth.objects.Objects;

/**
 * Created by apex on 27/07/14.
 */
public abstract class Living extends Drawable implements Objects
{

	private double x, y, width, height;
	public Living( double x, double y, double width, double height )
	{
		this.x = x;
		this.y = y;
	}

	@Override
	public void setX( double x )
	{
		this.x = x;
	}

	@Override
	public void setY( double y )
	{
		this.y = y;
	}

	public void setWidth( double width )
	{
		this.width = width;
	}

	public void setHeight( double height )
	{
		this.height = height;
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

	public double getWidth()
	{
		return width;
	}

	public double getHeight()
	{
		return height;
	}

	public abstract void onUpdate();
}
