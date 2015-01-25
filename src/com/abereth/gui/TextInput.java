package com.abereth.gui;

import com.abereth.draw.Color;
import com.abereth.game.Draw;
import com.abereth.input.Keyboard;

/**
 * Created by exfos on 24/01/15.
 */
public class TextInput extends GuiContainer
{

	private Text text;
	private String caret;
	private String caretPlaceholder;
	private int caretTimeout;
	public TextInput( double x, double y, double width, double height )
	{
		super( x, y, width, height );
		text = new Text( x, y );
		add( text );
		this.caret = "I";
		this.caretPlaceholder = "";
	}

	public Text getText()
	{
		return this.text;
	}

	@Override
	public void draw( Draw d )
	{
		d.square( getDrawX(), getDrawY(), getDrawWidth(), getDrawHeight() );
		if( this.isSelected )
		{
			d.setColor( Color.RED );
			d.line( getDrawX(), getDrawY(), getDrawX() + getDrawWidth(), getDrawY() );

			String entered = Keyboard.getPressedCharacter();
			if( !entered.equals( "" ) )
			{
				if( !entered.equals( "bckspc" ) )
				{
					text.set( text.getText() + entered );
				}
				else
				{
					text.set( text.getText().substring( 0, text.getText().length() - 1 ) );
				}
			}
		}
		d.setColor( Color.GREEN );
		super.draw( d );
	}
}
