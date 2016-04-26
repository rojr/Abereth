package com.abereth.draw;

import com.abereth.G;
import de.matthiasmann.twl.utils.PNGDecoder;
import org.lwjgl.BufferUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.color.ColorSpace;
import java.awt.image.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;

import static org.lwjgl.opengl.GL11.*;

public class TextureLoader
{

	public static ArrayList<TexInfo> TextureInfo = new ArrayList<>();

	/**
	 * The table of textures that have been loaded in this loader
	 */
	private HashMap<String, Texture> table = new HashMap<String, Texture>();

	/**
	 * The colour model including alpha for the GL image
	 */
	private ColorModel glAlphaColorModel;

	/**
	 * The colour model for the GL image
	 */
	private ColorModel glColorModel;

	/**
	 * Scratch buffer for texture ID's
	 */
	private IntBuffer textureIDBuffer = BufferUtils.createIntBuffer( 1 );

	/**
	 * Create a new texture loader based on the game panel
	 */
	public TextureLoader()
	{
		glAlphaColorModel = new ComponentColorModel( ColorSpace.getInstance( ColorSpace.CS_sRGB ),
				new int[]{ 8, 8, 8, 8 },
				true,
				false,
				ComponentColorModel.TRANSLUCENT,
				DataBuffer.TYPE_BYTE );

		glColorModel = new ComponentColorModel( ColorSpace.getInstance( ColorSpace.CS_sRGB ),
				new int[]{ 8, 8, 8, 0 },
				false,
				false,
				ComponentColorModel.OPAQUE,
				DataBuffer.TYPE_BYTE );
	}

	/**
	 * Create a new texture ID
	 *
	 * @return A new texture ID
	 */
	private int createTextureID()
	{
		glGenTextures( textureIDBuffer );
		return textureIDBuffer.get( 0 );
	}

	/**
	 * Load a texture
	 *
	 * @param resourceName The location of the resource to load
	 * @return The loaded texture
	 * @throws IOException Indicates a failure to access the resource
	 */
	public Texture getTexture( String resourceName ) throws IOException
	{
		Texture tex = table.get( resourceName );

		if( tex != null )
		{
			return tex;
		}

		tex = getTexture( resourceName,
				GL_TEXTURE_2D, // target
				GL_RGBA,     // dst pixel format
				GL_LINEAR, // min filter (unused)
				GL_LINEAR );

		table.put( resourceName, tex );

		return tex;
	}

	/**
	 * Load a texture into OpenGL from a image reference on
	 * disk.
	 *
	 * @param resourceName   The location of the resource to load
	 * @param target         The GL target to load the texture against
	 * @param dstPixelFormat The pixel format of the screen
	 * @param minFilter      The minimising filter
	 * @param magFilter      The magnification filter
	 * @return The loaded texture
	 * @throws IOException Indicates a failure to access the resource
	 */
	public Texture getTexture( String resourceName,
	                           int target,
	                           int dstPixelFormat,
	                           int minFilter,
	                           int magFilter ) throws IOException
	{
		int srcPixelFormat;

		// create the texture ID for this texture
		int textureID = createTextureID();
		Texture texture = new Texture( target, textureID );

		// bind this texture
		glBindTexture( target, textureID );

		BufferedImage bufferedImage = loadImage( resourceName );
		texture.setWidth( bufferedImage.getWidth() );
		texture.setHeight( bufferedImage.getHeight() );

		if( bufferedImage.getColorModel().hasAlpha() )
		{
			srcPixelFormat = GL_RGBA;
		} else
		{
			srcPixelFormat = GL_RGB;
		}

		// convert that image into a byte buffer of texture data
		ByteBuffer textureBuffer = convertImageData( bufferedImage, texture );

		glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST );
		glTexParameteri( GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST );

		// produce a texture from the byte buffer
		glTexImage2D( target,
				0,
				dstPixelFormat,
				get2Fold( bufferedImage.getWidth() ),
				get2Fold( bufferedImage.getHeight() ),
				0,
				srcPixelFormat,
				GL_UNSIGNED_BYTE,
				textureBuffer );

		return texture;
	}

	/**
	 * Get the closest greater power of 2 to the fold number
	 *
	 * @param fold The target number
	 * @return The power of 2
	 */
	private static int get2Fold( int fold )
	{
		int ret = 2;
		while ( ret < fold )
		{
			ret *= 2;
		}
		return ret;
	}

	/**
	 * Convert the buffered image to a texture
	 *
	 * @param bufferedImage The image to convert to a texture
	 * @param texture       The texture to store the data into
	 * @return A buffer containing the data
	 */
	private ByteBuffer convertImageData( BufferedImage bufferedImage, Texture texture )
	{
		ByteBuffer imageBuffer;
		WritableRaster raster;
		BufferedImage texImage;

		int texWidth = 2;
		int texHeight = 2;

		// find the closest power of 2 for the width and height
		// of the produced texture
		while ( texWidth < bufferedImage.getWidth() )
		{
			texWidth *= 2;
		}
		while ( texHeight < bufferedImage.getHeight() )
		{
			texHeight *= 2;
		}

		texture.setTextureHeight( texHeight );
		texture.setTextureWidth( texWidth );

		// create a raster that can be used by OpenGL as a source
		// for a texture
		if( bufferedImage.getColorModel().hasAlpha() )
		{
			raster = Raster.createInterleavedRaster( DataBuffer.TYPE_BYTE, texWidth, texHeight, 4, null );
			texImage = new BufferedImage( glAlphaColorModel, raster, false, new Hashtable() );
		} else
		{
			raster = Raster.createInterleavedRaster( DataBuffer.TYPE_BYTE, texWidth, texHeight, 3, null );
			texImage = new BufferedImage( glColorModel, raster, false, new Hashtable() );
		}

		// copy the source image into the produced image
		Graphics g = texImage.getGraphics();
		g.setColor( new Color( 0f, 0f, 0f, 0f ) );
		g.fillRect( 0, 0, texWidth, texHeight );
		g.drawImage( bufferedImage, 0, 0, null );

		// build a byte buffer from the temporary image
		// that be used by OpenGL to produce a texture.
		byte[] data = ( ( DataBufferByte ) texImage.getRaster().getDataBuffer() ).getData();

		imageBuffer = ByteBuffer.allocateDirect( data.length );
		imageBuffer.order( ByteOrder.nativeOrder() );
		imageBuffer.put( data, 0, data.length );
		imageBuffer.flip();

		return imageBuffer;
	}

	/**
	 * Load a given resource as a buffered image
	 *
	 * @param ref The location of the resource to load
	 * @return The loaded buffered image
	 * @throws IOException Indicates a failure to find a resource
	 */
	private BufferedImage loadImage( String ref ) throws IOException
	{


		// due to an issue with ImageIO and mixed signed code
		// we are now using good oldfashioned ImageIcon to load
		// images and the paint it on top of a new BufferedImage
		Image img = new ImageIcon( ref ).getImage();
		BufferedImage bufferedImage = new BufferedImage( img.getWidth( null ), img.getHeight( null ), BufferedImage.TYPE_INT_ARGB );
		Graphics g = bufferedImage.getGraphics();
		g.drawImage( img, 0, 0, null );
		g.dispose();

		return bufferedImage;
	}

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
		TextureInfo.add( new TexInfo( G.ARP + location, startX, startY, width, height ) );
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