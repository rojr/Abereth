package com.abereth.helpers;

/**
 * Created by exfos on 25/01/15.
 */
public class StringTools
{
	public static String insertEvery( int interval, String insert, String base )
	{
		StringBuilder str = new StringBuilder( );
		char[] charArray = base.toCharArray();
		for( double i = 0; i < charArray.length; i++ )
		{
			str.append( charArray[ ( int )i ] );
			if( i % interval == 0 && i != 0 )
			{
				str.append( insert );
			}
		}
		return str.toString();
	}
}
