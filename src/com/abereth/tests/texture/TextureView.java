package com.abereth.tests.texture;

import com.abereth.G;
import com.abereth.draw.TextureLoader;
import com.abereth.draw.shapes.Square;
import com.abereth.game.Game;
import com.abereth.view.View;

/**
 * Created by jeremiah on 22/11/2014.
 */
public class TextureView extends View
{

	public static void main ( String[] args )
	{
		Game g = new Game( 1024, 512 );
		g.addView( new TextureView( g ) );
		g.start ();
	}

	private Square square;
	public TextureView ( Game game )
	{
		super( game );
		square = new Square( 0, 0, G.WIDTH, G.HEIGHT );
		square.setTexture( TextureLoader.createTexture( "Abereth/res/tiles/Stone.png" ) );
		add( square );
	}

	@Override
	public void update ( int delta )
	{
		square.setRotation( square.getRotation() + 1 );
	}
}
