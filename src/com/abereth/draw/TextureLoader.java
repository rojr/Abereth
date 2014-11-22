package com.abereth.draw;

import de.matthiasmann.twl.utils.PNGDecoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class TextureLoader
{

	public static ArrayList<TexInfo> TextureInfo = new ArrayList<>();

	/**
	 *
	 * @param location
	 * @param startX
	 * @param startY
	 * @param width
	 * @param height
	 * @return
	 */
	public static int createTexture( String location, int startX, int startY, int width, int height )
	{
		for( int j = 0; j < TextureInfo.size(); j++ )
		{
			TexInfo i = TextureInfo.get( j );
			if( location.equalsIgnoreCase( i.getLocation() ) && startX == i.getX() && startY == i.getY() && width == i.getWidth() && height == i.getHeight() )
			{
				return j;
			}
		}
		//Because if the loop returned something, nothing under it would be run.
		TextureInfo.add( new TexInfo(location, startX, startY, width, height ) );
		return TextureInfo.size() - 1;
	}

	/**
	 * Textures are identified by there index in the ArrayList.
	 * Newly created textures are given an integer id of
	 * the arraylist.size - 1
	 *
	 * @param location
	 * @return
	 *          integer value representing the created texture
	 */
	public static int createTexture( String location )
	{
		return createTexture( location, 0, 0, 0, 0);
	}

	/**
	 *
	 * @param texID
	 * @return
	 *          the width of the texture with id texID
	 */
	public static int getTextureWidth( int texID )
	{
		return TextureInfo.get( texID ).getWidth();
	}

	/**
	 *
	 * @param texID
	 * @return
	 *          the height of the texture with id texID
	 */
	public static int getTextureHeight( int texID )
	{
		return TextureInfo.get( texID ).getHeight();
	}

	/**
	 * Attributes for texture objects
	 * width, heigth, location, x and y coordinates
	 */
	public static class TexInfo
	{
		private int x, y,width,height;
		private double xPercent, yPercent, widthPercent, heightPercent;
		private String location;
		private PNGDecoder decoder;
		private ByteBuffer buffer;
		private int texWidth, texHeight;
		public TexInfo( String location, int x, int y, int width, int height )
		{
			this.width = width;
			this.height = height;
			this.location = location;
			try
			{
				InputStream in = new FileInputStream( location );
				decoder = new PNGDecoder( in );
				buffer = ByteBuffer.allocateDirect( 4 * decoder.getWidth() * decoder.getHeight() );
				decoder.decode( buffer, decoder.getWidth() * 4, PNGDecoder.Format.RGBA );
				buffer.flip( );
				in.close();
			}
			catch ( IOException e )
			{
				e.printStackTrace();
			}

			glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST );
			glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST );

			this.texWidth = decoder.getWidth();
			this.texHeight = decoder.getHeight();
			this.x = x;
			this.y = y;
			if(width == 0 || height == 0)
			{
				this.xPercent = 0F;
				this.yPercent = 0F;
				this.heightPercent = 1F;
				this.widthPercent = 1F;
			}
			else
			{
				this.xPercent = ( double ) x / ( double ) texWidth;
				this.yPercent = ( double ) y / ( double ) texHeight;
				this.yPercent = ( double ) 1 - ( double ) yPercent;
				this.widthPercent = ( double ) width / ( double )texWidth;
				this.heightPercent = ( double ) height / ( double )texHeight;
			}
		}
		public int getX(){
			return x;
		}

		public PNGDecoder getDecoder()
		{
			return this.decoder;
		}

		public ByteBuffer getBuffer()
		{
			return this.buffer;
		}

		public int getY(){
			return y;
		}
		public int getWidth(){
			return width;
		}
		public int getHeight(){
			return height;
		}
		public double getXPercent(){
			return xPercent;
		}
		public double getYPercent(){
			return yPercent;
		}
		public double getWidthPercent(){
			return widthPercent;
		}
		public double getHeightPercent(){
			return heightPercent;
		}
		public String getLocation(){
			return location;
		}
	}
}