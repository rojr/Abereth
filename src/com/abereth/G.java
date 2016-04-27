package com.abereth;

import com.abereth.draw.TextureLoader;

import java.util.ArrayList;

/**
 * Global variables; settings that apply to everything and everyone. Even you!
 */
public class G
{
	/**
	 * Width of the screen
	 */
	public static int WIDTH;
	/**
	 * Height of the screen
	 */
	public static int HEIGHT;

	public static String NAME;

	/**
	 * How many pixels does 1 unit in Dyn4j represent
	 */
	public static double DYN4J_PIXELS_TO_METERS = 6;

	public static final double NANO_TO_BASE = 1.0e9;
	public static String USER_DIR;
	/**
	 * Abereth resources path, this will link directly to Abereth/res folder no matter where
	 * the engine is being run from
	 */
	public static String ARP = "";

	public static final char characters[] = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
										'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U',
										'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h',
										'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u',
										'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
										'!', '?', '_', '-', ']', '[', '}', '{', '>', '=', '"', '\'', '+',
										'*', ':', ';', '.', ',', '(', ')', '&', '$', '%', '@', '#' };

	public static ArrayList<Integer> letterTexID = new ArrayList<Integer>();

	public static void loadCharacters()
	{
		for ( int i = 0; i < characters.length; i++ )
		{
			int tmp = i >= 26 ? i % 26 : i;
			letterTexID.add( TextureLoader.createTexture( "font/sprite1.png",
					tmp * 8, ( int ) ( 8 * Math.floor( i / 26 ) ), 8, 8 ) );
		}
	}

	public static int getCharacterTexture( char c )
	{
		if( letterTexID.size() ==  0 )
		{
			return 0;
		}

		for ( int i = 0; i < characters.length; i++ )
		{
			if( characters[ i ] == c ) return letterTexID.get( i );
		}
		return 0;
	}
}
