package com.abereth.gui;

import com.abereth.G;
import com.abereth.draw.Color;
import com.abereth.draw.TextureLoader;
import com.abereth.game.Draw;

/**
 * Created by apex on 10/03/14.
 */
public class Text extends GuiContainer implements Parent, Child
{

	public static String WHITE = "&!0",
			RED = "&!1",
			BLUE = "&!2",
			YELLOW = "&!3",
			GREEN = "&!4",
			CYAN = "&!5",
			BLACK = "&!6",
			ORANGE = "&!7",
			PINK = "&!8",
			VIOLET = "&!9";

	private double lastX, lastY;
	private int letterSize = 8;
	private String text;
	private char identifier = '&';
	private Parent parent;

	Color currentColor = Color.WHITE;

	public Text( double x, double y )
	{
		this( "", x, y );
	}

	public Text( String text, double x, double y )
	{
		this( text, x, y, 0, 0 );
	}

	public Text( String text, double x, double y, double width, double height )
	{
		super( x, y, width, height );
		lastX = getDrawX();
		lastY = getDrawY();
		set( text );
	}

	/**
	 * Sets size of the letters and spacing
	 *
	 * @param size size of each letter in pixels
	 */
	public void setSize( int size )
	{
		this.letterSize = size;
		set( text );
	}

	public String getText()
	{
		return text;
	}

	/**
	 * Default is 8
	 *
	 * @return letter size
	 */
	public int getSize()
	{
		return letterSize;
	}

	public void set( String text )
	{
		if( !( text.equalsIgnoreCase( this.text ) ) )
		{
			getChildren().clear();
			lastX = getDrawX();
			lastY = getDrawY();
			this.text = text;
			if( ( text.length() > 0 ) )
			{
				for ( int i = 0; i < text.toCharArray().length; i++ )
				{
					char c = text.toCharArray()[ i ];
					if( c == ' ' )
						lastX += letterSize;
					else if( c != '\n' )
					{
						if( c == identifier && i < text.toCharArray().length - 2 )
						{
							if( text.toCharArray()[ i + 1 ] == '!' )
							{
								char id = text.toCharArray()[ i + 2 ];
								switch ( id )
								{
									case '0':
										currentColor = Color.WHITE;
										break;
									case '1':
										currentColor = Color.RED;
										break;
									case '2':
										currentColor = Color.BLUE;
										break;
									case '3':
										currentColor = Color.YELLOW;
										break;
									case '4':
										currentColor = Color.GREEN;
										break;
									case '5':
										currentColor = Color.CYAN;
										break;
									case '6':
										currentColor = Color.BLACK;
										break;
									case '7':
										currentColor = Color.ORANGE;
										break;
									case '8':
										currentColor = Color.PINK;
										break;
									case '9':
										currentColor = Color.VIOLET;
										break;
								}
								i += 2;
								continue;
							} else if( text.toCharArray()[ i + 1 ] == '?' )
							{
								int toSkip = 0;
								try
								{
									if( Integer.parseInt( text.toCharArray()[ i + 2 ] + "" ) <= 9 )
									{
										if( Integer.parseInt( text.toCharArray()[ i + 3 ] + "" ) <= 9 )
										{
											letterSize = Integer.parseInt( text.toCharArray()[ i + 2 ] + "" ) * 10
													+ Integer.parseInt( text.toCharArray()[ i + 3 ] + "" );
											toSkip = 3;
										} else
										{
											letterSize = Integer.parseInt( text.toCharArray()[ i + 2 ] + "" );
											toSkip = 2;
										}
									}
								} catch ( Exception e )
								{
								}
								i += toSkip;
								continue;
							}
						}
						Letter l = new Letter( c, 0, 0, letterSize, letterSize );
						l.setColor( currentColor );
						add( l );
					} else
					{
						lastX = getDrawX();
						lastY += letterSize;
					}
				}
			}
		}
	}

	/**
	 * Do not use this method directly, it will only make things worse
	 *
	 * @param c
	 */
	@Override
	public void add( Child c )
	{
		if( c.getOrigin() instanceof Letter )
		{
			c.getOrigin().setDrawX( lastX );
			char currentC = ( ( Letter ) c.getOrigin() ).getLetter();
			boolean tmp = currentC == 'p' || currentC == 'q' || currentC == 'y' || currentC == 'g' || currentC == 'j';
			double add = tmp ? letterSize / 6 : 0;
			c.getOrigin().setDrawY( lastY + add );
			if( currentC == 'i' || currentC == 'l' )
			{
				lastX += letterSize / 2;
			} else
			{
				lastX += letterSize;
			}
			super.add( c );
		}
	}

	@Override
	public Parent getParent()
	{
		return parent;
	}

	@Override
	public void setParent( Parent p )
	{
		this.parent = p;
	}

	public static String size( int size )
	{
		return "&?" + size;
	}

	class Letter extends Gui implements Child
	{

		private Parent parent;
		private char c;

		public Letter( char c, double x, double y, double width, double height )
		{
			super( x, y, width, height );
			this.c = c;
			setTexture( G.getCharacterTexture( c ) );
		}

		public char getLetter()
		{
			return c;
		}

		public void setLetter( char c )
		{
			this.c = c;
			setTexture( G.getCharacterTexture( c ) );
		}

		public Parent getParent()
		{
			return parent;
		}

		public Letter getOrigin()
		{
			return this;
		}

		public void setParent( Parent p )
		{
			this.parent = p;
		}

		@Override
		public void Draw( Draw d )
		{
			d.BindTexture( getTexture() );
			TextureLoader.TexInfo info = TextureLoader.TextureInfo.get( getTexture() );
			d.square( this.getDrawX(), this.getDrawY(), this.getDrawWidth(), this.getDrawHeight(), info.getXPercent(), info.getYPercent(), info.getWidthPercent(), info.getHeightPercent() );
		}
	}

}
