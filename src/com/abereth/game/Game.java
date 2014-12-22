package com.abereth.game;

import com.abereth.G;
import com.abereth.draw.Color;
import com.abereth.event.game.GameEventManager;
import com.abereth.event.view.transitions.ViewTransition;
import com.abereth.input.Mouse;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by apex on 26/07/14.
 */

public class Game implements Runnable
{
	//Static
	public int fps;
	public int actualFps;
	static long lastFrame, lastFPS;
	static int delta;

	//Private
	private Draw draw;
	//Select the color you would like to clear from the game every Update
	//By default the color Black is cleared
	private Color clearColor;

	private int refreshRate;
	private ArrayList<View> viewList;

	private GameEventManager eventManager;

	/**
	 * Default constructor, creates a new game with 500 width and 500 height
	 */
	public Game()
	{
		this(500,500);
	}

	public Game( int width, int height )
	{
		this( "Game", width, height, new Draw() );
	}

	public Game( String name, int dimensionX, int dimensionY, Draw draw )
	{
		G.NAME = name;
		G.WIDTH = dimensionX;
		G.HEIGHT = dimensionY;
		this.draw = draw;
		this.clearColor = new Color( 0f, 0f, 0f, 0f );
		this.refreshRate = 60;
		this.viewList = new ArrayList<View>();
		this.eventManager = new GameEventManager( this );
	}

	public Draw getDraw()
	{
		return this.draw;
	}

	public Color getClearColor()
	{
		return this.clearColor;
	}

	public int getRefreshRate()
	{
		return refreshRate;
	}

	public void setDraw( Draw draw )
	{
		this.draw = draw;
	}

	/**
	 * Set the color you would like the screen to be set at every tick (refreshed to)
	 * Any unfilled area will be in this color
	 * @param color by default this color is black
	 */
	public void setClearColor( Color color )
	{
		this.clearColor = color;
	}

	/**
	 * Set the refresh rate of the game screen
	 * Currently 60 is maximum
	 * @param rate Frames per second
	 */
	public void setRefreshRate( int rate )
	{
		this.refreshRate = rate;
	}


	private void updateFPS()
	{
		if ( getTime() - lastFPS > 1000 )
		{
			actualFps = fps;
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public long getTime()
	{
		return ( Sys.getTime() * 1000 ) / Sys.getTimerResolution();
	}

	private int getDelta()
	{
		long currentTime = getTime();
		int delta = ( int ) ( currentTime - lastFrame );
		lastFrame = getTime();
		return delta;
	}

	public int getFPS()
	{
		return actualFps;
	}

	public void create()
	{
		create( G.WIDTH, G.HEIGHT, G.NAME );
	}

	private void create( int dimensionX, int dimensionY, String title )
	{
		try {
			Display.setDisplayMode(new DisplayMode(dimensionX, dimensionY));
			Display.setTitle(title);
			Display.create();
			Display.setResizable(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		glEnable(GL_TEXTURE_2D);
		glEnable(GL_BLEND);
		glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);
		glClearColor(0.0F, 0.0F, 0.0F, 0.0F);
		glViewport(0, 0, dimensionX, dimensionY);
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		glOrtho(0, dimensionX, dimensionY, 0, 1, -1);
		glMatrixMode(GL_MODELVIEW);
		lastFPS = getTime();

		//Display.setVSyncEnabled(true);

		G.WIDTH = dimensionX;
		G.HEIGHT = dimensionY;
	}

	/**
	 * Add views to the game, reason for multiple views is
	 * because of view layering, for example a shop screen over
	 * the game screen as it simply renders in the background.
	 * @param view
	 */
	public void addView( View... view )
	{
		Collections.addAll(viewList, view);
		Collections.sort(viewList);
		for( View v : view )
		{
			v.Initialize();
		}
	}

	/**
	 * Remove view(s) from the game
	 * @param view
	 */
	public void detachView( View... view )
	{
		ArrayList<View> tmp = new ArrayList<>();
		Collections.addAll( tmp, view );
		if( !Collections.disjoint( viewList, tmp ) )
		{
			for (View v : view )
			{
				for ( int i = 0; i < viewList.size(); i++ )
				{
					if( v == viewList.get( i ) )
					{
						v.onKill();
						viewList.remove( i );
						break;
					}
				}
			}
		}
		Collections.sort( viewList );
	}

	public ArrayList<View> GetViews()
	{
		return this.viewList;
	}

	public GameEventManager GetEventManager()
	{
		return this.eventManager;
	}

	/**
	 * Removes all current views and
	 * adds a completely new one
	 *
	 * Be careful if you're working with multiple views not to delete
	 * one
	 */
	public void ChangeView( View view )
	{
		viewList.clear( );
		addView( view );
	}

	public void ChangeView( View view, ViewTransition transition )
	{
		transition.init( viewList.get( 0 ), view );
	}

	public Thread Start( )
	{
		Thread running = new Thread( this );
		running.start();
		return running;
	}

	/**
	 * First image must be 16x16 and second image must be 32 x 32
	 *
	 * @param icon1 Left hand corner image
	 * @param icon2 Bottom taskbar image
	 */
	public void setTaskBarIcon( String icon1, String icon2 )
	{
		try
		{
			ByteBuffer[] list = new ByteBuffer[ 2 ];
			list[ 0 ] = loadIcon( icon1, 16, 16 );
			list[ 1 ] = loadIcon( icon2, 32, 32 );
			Display.setIcon( list );

		}catch ( IOException ex )
		{
			System.out.println( "Loading image failed..." );
		}
	}

	public ByteBuffer loadIcon( String filename, int width, int height ) throws IOException
	{
		BufferedImage image = ImageIO.read(new File(filename)); // load image

		// convert image to byte array
		byte[] imageBytes = new byte[ width * height * 4 ];
		for ( int i = 0; i < height; i++ )
		{
			for ( int j = 0; j < width; j++ )
			{
				int pixel = image.getRGB( j, i );
				for ( int k = 0; k < 3; k++ ) // red, green, blue
				{
					imageBytes[ ( i * 16 + j ) * 4 + k ] = ( byte ) ( ( ( pixel >> ( 2 - k ) * 8 ) ) & 255 );
				}
				imageBytes[ ( i * 16 + j ) * 4 + 3 ] = ( byte ) ( ( ( pixel >> ( 3 ) * 8 ) ) & 255 ); // alpha
			}
		}
		return ByteBuffer.wrap(imageBytes);
	}


	public void Update ( ){
		delta = getDelta();
		updateFPS();
		glClearColor(clearColor.getR(), clearColor.getG(), clearColor.getB(), clearColor.getA());
		Display.update();
		glClear(GL_COLOR_BUFFER_BIT);
	}

	public void refresh(int rate){
		Display.sync(rate);
	}

	@Override
	public void run()
	{
		create();
		System.out.println( "Running from: " + System.getProperty( "user.dir" ) );
		while( !Display.isCloseRequested( ) )
		{
			Update( );
			eventManager.onUpdate( delta );
			int i = 0;
			try
			{
				for ( ; i < viewList.size(); i++ )
				{
					if ( !viewList.get( i ).isPaused() )
					{
						viewList.get( i ).update( delta );
						viewList.get( i ).render( delta );
					}
				}
			} catch ( ConcurrentModificationException ex )
			{ ex.printStackTrace(); }
			refresh(refreshRate);
			//TODO add after tick and before tick methods so that they can be added to and removed from later
			Mouse.isLeftMouseDown();
			Mouse.isRightMouseDown();
			Mouse.wasLeftMouseClickedThisTick = false;
			Mouse.wasRightMouseClickedThisTick = false;
		}
		for( View view : viewList )
		{
			view.kill();
		}
	}
}
