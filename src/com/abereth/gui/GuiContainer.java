package com.abereth.gui;

import com.abereth.draw.Drawable;
import com.abereth.game.Draw;

import java.util.ArrayList;

/**
 * Created by exfos on 23/01/15.
 * <p/>
 * Basically overriders every single method in Drawable
 * <p/>
 * this will make it update all the children while only having to call one method
 */
public class GuiContainer extends Gui implements Parent
{


	private ArrayList<Child> children = new ArrayList<Child>();

	public GuiContainer( double x, double y )
	{
		this( x, y, 0, 0 );
	}

	public GuiContainer( double x, double y, double width, double height )
	{
		super( x, y, width, height );
	}

	@Override
	public void setDrawX( double x )
	{
		double prevX = getDrawX();
		double diff = x - prevX;
		super.setDrawX( x );
		for ( Child c : children )
		{
			c.getOrigin().setDrawX( c.getOrigin().getDrawX() + diff );
		}
	}

	/**
	 * Only changes the x location of the container itself and not the children.
	 * <b>Only is changed if single is set to false</b>.
	 *
	 * @param x
	 * @param single if true changes all child objects. If false only changes parent
	 */
	public void setDrawX( double x, boolean single )
	{
		if( single )
		{
			setDrawX( x );
		} else
		{
			super.setDrawX( x );
		}
	}

	@Override
	public void setDrawY( double y )
	{
		double prevY = getDrawY();
		double diff = y - prevY;
		super.setDrawY( y );
		for ( Child c : children )
		{
			c.getOrigin().setDrawY( c.getOrigin().getDrawY() + diff );
		}
	}

	/**
	 * Only changes the y location of the container itself and not the children.
	 * <b>Only is changed if single is set to false</b>.
	 *
	 * @param y
	 * @param single if true changes all child objects. If false only changes parent
	 */
	public void setDrawY( double y, boolean single )
	{
		if( single )
		{
			setDrawY( y );
		} else
		{
			super.setDrawY( y );
		}
	}


	public ArrayList<Child> getChildren()
	{
		return children;
	}

	public void add( Child c )
	{
		c.setParent( this );
		c.getOrigin().setRotation( getRotation() );
		if( c.getOrigin().getDrawX() < getDrawX() )
		{
			c.getOrigin().setDrawX( getDrawX() );
		}
		if( c.getOrigin().getDrawY() < getDrawY() )
		{
			c.getOrigin().setDrawY( getDrawY() );
		}
		children.add( c );
	}

	@Override
	public void Draw( Draw d )
	{
		super.Draw( d );
		for ( Child c : children )
		{
			c.getOrigin().Draw( d );
		}
	}

	public Drawable getOrigin()
	{
		return this;
	}

	@Override
	public void setRotation( int rotation )
	{
		super.setRotation( rotation );
		for ( Child c : children )
		{
			c.getOrigin().setRotation( getRotation() );
		}
	}

	@Override
	public void setOpacity( float f )
	{
		super.setOpacity( f );
		for ( Child c : getChildren() )
		{
			c.getOrigin().setOpacity( f );
		}
	}

	public void setChildTexture( int tex )
	{
		for ( Child c : getChildren() )
		{
			c.getOrigin().setTexture( tex );
		}
	}
}
