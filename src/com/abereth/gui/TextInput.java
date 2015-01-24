package com.abereth.gui;

import com.abereth.game.Draw;
import com.abereth.input.Keyboard;

/**
 * Created by exfos on 24/01/15.
 */
public class TextInput extends GuiContainer
{

	private Text text;
	public TextInput( double x, double y, double width, double height )
	{
		super( x, y, width, height );
		text = new Text( x, y );
		add( text );
	}

	@Override
	public void Draw( Draw d )
	{
		super.Draw( d );
		if( this.isSelected )
		{
			String entered = Keyboard.getPressedCharacter();
			if( !entered.equals( "" ) )
			{
				text.set( text.getText() + entered );
			}
		}
	}
}
