package com.abereth.objects.living;

import com.abereth.draw.Drawable;

/**
 * Created by apex on 27/07/14.
 */
public abstract class Living extends Drawable
{

	public Living( double x, double y, double width, double height )
	{
		super(x,y,width,height);
	}

	public abstract void onUpdate();
}
