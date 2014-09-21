package com.abereth.game;

import com.abereth.G;
import com.abereth.draw.Color;
import com.abereth.draw.Drawable;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by apex on 26/07/14.
 */

public class Game {
	//Static
	public int fps;
	public int actualFps;
	static long lastFrame, lastFPS;
	static int delta;

	//Private
	private Draw draw;
	//Select the color you would like to clear from the game every update
	//By default the color Black is cleared
	private Color clearColor;

	private int refreshRate;
	private ArrayList<View> viewList;

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
		create( dimensionX, dimensionY, name);
		this.draw = draw;
		this.clearColor = new Color( 0f, 0f, 0f, 0f );
		this.refreshRate = 60;
		this.viewList = new ArrayList<View>();
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


	private void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			actualFps = fps;
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public long getTime() {
		return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}

	private int getDelta() {
		long currentTime = getTime();
		int delta = (int) (currentTime - lastFrame);
		lastFrame = getTime();
		return delta;
	}

	public int getFPS()
	{
		return actualFps;
	}

	private void create(int dimensionX, int dimensionY, String title){
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

		Display.setVSyncEnabled(true);

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
	}

	/**
	 * Remove view(s) from the game
	 * @param view
	 */
	public void detachView( View... view )
	{
		ArrayList<View> tmp = new ArrayList<View>();
		Collections.addAll(tmp, view);
		if(!Collections.disjoint(viewList, tmp))
		{
			for (View v : view )
			{
				for ( int i = 0; i < viewList.size(); i++ )
				{
					if(v == viewList.get( i ) )
					{
						viewList.remove( i );
						break;
					}
				}
			}
		}
		Collections.sort(viewList);
	}

	/**
	 * Removes all current views and
	 * adds a completely new one
	 *
	 * Be careful if you're working with multiple views not to delete
	 * one
	 */
	public void changeView( View view )
	{
		viewList.clear();
		viewList.add(view);
	}

	public void start( )
	{
		while( !Display.isCloseRequested( ) )
		{
			update( );
			for ( View v : viewList )
			{
				if ( !v.isPaused() )
				{
					v.update( delta );
					v.render( delta );
				}
			}
			refresh(refreshRate);
		}
	}

	public void update(){
		delta = getDelta();
		updateFPS();
		glClearColor(clearColor.getR(), clearColor.getG(), clearColor.getB(), clearColor.getA());
		Display.update();
		glClear(GL_COLOR_BUFFER_BIT);
	}

	public void refresh(int rate){
		Display.sync(rate);
	}

}
