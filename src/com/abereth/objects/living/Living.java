package com.abereth.objects.living;

import com.abereth.draw.Drawable;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by apex on 27/07/14.
 */
public abstract class Living extends Drawable
{
	private Vector2f direction;
	public Living( double x, double y, double width, double height )
	{
		super(x,y,width,height);
		direction = new Vector2f( 0, 0 );
	}

	public Vector2f getDirection()
	{
		return direction;
	}

	public void setDirection( float x, float y )
	{
		this.direction = new Vector2f( x, y );
	}

	public void setDirection( double x, double y )
	{
		setDirection( ( float ) x, ( float ) y );
	}

	public void setDirectionX( float x )
	{
		this.direction = new Vector2f( x, getDirection().getY() );
	}

	public void setDirectionX( double x )
	{
		setDirectionX( (float) x );
	}

	public void setDirectionY( float y )
	{
		this.direction = new Vector2f( getDirection().getX(), y );
	}

	public void setDirectionY( double y )
	{
		setDirectionY( ( float ) y );
	}

	public void onUpdate( int delta )
	{
		//Applies velocity
		setDrawY( getDrawY() + ( getDirection().getY() * delta ) * 0.05 );
		setDrawX( getDrawX() + ( getDirection().getX() * delta ) * 0.05 );
	}

}
