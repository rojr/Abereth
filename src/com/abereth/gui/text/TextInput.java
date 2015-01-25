package com.abereth.gui.text;

import com.abereth.draw.Color;
import com.abereth.game.Draw;
import com.abereth.gui.GuiContainer;
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
	private boolean alwaysSelected;
	public TextInput( double x, double y, double width, double height )
	{
		super( x, y, width, height );
		text = new Text( x, y );
		add( text );
		this.caret = "I";
		this.caretPlaceholder = "";
		this.alwaysSelected = false;
	}

	public void setIsAlwaysSelected( boolean is )
	{
		this.alwaysSelected = is;
	}

	public Text getText()
	{
		return this.text;
	}

	@Override
	public void draw( Draw d )
	{
		d.square( getDrawX(), getDrawY(), getDrawWidth(), getDrawHeight() );
		if( this.isSelected || this.alwaysSelected )
		{
			drawLetters( d );

			String entered = Keyboard.getPressedCharacter();
			if( !entered.equals( "" ) )
			{
				handleInput( entered );
			}

		}
		super.draw( d );
	}

	public void handleInput( String input )
	{
		if( !input.equals( "bckspc" ) )
		{
			text.set( text.getText() + input );
		}
		else
		{
			if( text.getText().length() != 0 )
			{
				text.set( text.getText().substring( 0, text.getText().length() - 1 ) );
			}
		}
	}

	public void drawLetters( Draw d )
	{
		d.setColor( Color.RED );
		d.line( getDrawX(), getDrawY(), getDrawX() + getDrawWidth(), getDrawY() );
	}
}
