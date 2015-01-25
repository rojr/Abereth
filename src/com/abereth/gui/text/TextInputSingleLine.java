package com.abereth.gui.text;

/**
 * Created by exfos on 25/01/15.
 */
public class TextInputSingleLine extends TextInput
{
	public TextInputSingleLine( double x, double y, double width, double height )
	{
		super( x, y, width, height );
	}

	@Override
	public void handleInput( String input )
	{
		if( !input.equals( "\n" ) )
		{
			super.handleInput( input );
		}
	}
}
