package com.abereth.gui;

import com.abereth.draw.Drawable;
import com.abereth.game.Draw;

import java.util.ArrayList;

/**
 * Created by jeremiah on 22/11/2014.
 */
public class Gui extends Drawable implements Comparable
{
	protected boolean isSelected;
	public static Gui selectedElement = null;
	public Gui ( )
	{
		super( );
		this.isSelected = false;
	}

	public Gui( double x, double y, double width, double height )
	{
		super(x, y, width, height );
	}

	@Override
	public void draw( Draw d )
	{}

	public static void setSelected( Gui gui )
	{
		gui.isSelected = true;
		selectedElement = gui;
	}

	public static void clearSelected()
	{
		if( selectedElement != null )
		{
			selectedElement.isSelected = false;
			selectedElement = null;
		}
	}

	@Override
	public int compareTo( Object o )
	{
		Gui obj = ( Gui ) o;
		return obj.getLayer() - getLayer();
	}
}
