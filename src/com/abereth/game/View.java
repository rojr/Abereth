package com.abereth.game;

import com.abereth.G;
import com.abereth.draw.Color;
import com.abereth.draw.Drawable;
import com.abereth.draw.LayerHandler;
import com.abereth.event.view.ViewEvent;
import com.abereth.event.view.ViewEventManager;
import com.abereth.gui.Gui;
import com.abereth.input.Mouse;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by apex on 02/08/14.
 */
public abstract class View implements Comparable{

	private Game game;
	private boolean isPaused;
	private int layer;
	private ArrayList<Gui> guiList;
	private ArrayList<Camera> cameraList;
	private ViewEventManager eventManager;
	private LayerHandler drawList;

	/*
		View specific graphical option settings

		These will change every object that is currently being drawn within the view
		This means we can add some fancy transitions, like make all objects go up, or rotate?
	 */
	public Color VIEW_COLOR = Color.NONE;
	public float VIEW_ROTATION_AMOUNT = 0;
	public int   VIEW_ROTATION_ORIGIN_X = 0;
	public int   VIEW_ROTATION_ORIGIN_Y = 0;
	public int   VIEW_X_OFFSET = 0;
	public int   VIEW_Y_OFFSET = 0;

	public View( Game game )
	{
		this.game = game;
		this.drawList = new LayerHandler();
		this.guiList = new ArrayList<>(  );
		this.cameraList = new ArrayList<>(  );
		this.layer = 1;
		this.eventManager = new ViewEventManager<>( this );
		this.cameraList.add ( new Camera ( this, 0, 0, G.WIDTH, 200 ) );
		Camera c = new Camera ( this, 200, 200, 200, 200 );
		c.setScaleX ( 0.3f );
		c.setScaleY ( 0.3f );
		this.cameraList.add ( c );

		//Click handler
		//Specifically created to handle the clicked view when stuff is
		//overlapping
		getEventManager ().add( new ViewEvent()
		{
			@Override
			public boolean isDone( View view )
			{
				return false;
			}

			@Override
			public void onUpdate(int delta, View view)
			{
				if( Mouse.isLeftMouseClicked() )
				{
					int mx = Mouse.getX();
					int my = Mouse.getY();
					ArrayList<Gui> guiElements = new ArrayList<>( );
					for( Gui g : guiList )
					{
						if( g.getDrawX() <= mx && g.getDrawX() + g.getDrawWidth() >= mx && g.getDrawY() <= my && g.getDrawY() + g.getDrawHeight() >= my )
						{
							guiElements.add( g );
						}
					}
					if( guiElements.size() != 0 )
					{
						if( guiElements.size() == 1 )
						{
							Gui.setSelected( guiElements.get( 0 ) );
						}
						else
						{
							Collections.sort( guiElements );
							Gui.setSelected( guiElements.get( 0 ) );
						}
						return;
					}
					Gui.clearSelected();
				}
			}
		}, false );
	}

	public Game getGame()
	{
		return game;
	}

	/**
	 * View layering is important if you're
	 * interested in view overlaying. This lets you choose what will
	 * come in front and what will go behind.
	 * @return
	 */
	public int getLayer()
	{
		return layer;
	}

	public void setLayer( int layer )
	{
		this.layer = layer;
	}

	/**
	 * Use this method for anything other than setting the opacity of the view color
	 * @param c
	 */
	public void setColor( Color c )
	{
		Color color = c.clone();
		color.setA( VIEW_COLOR.getA() );
		VIEW_COLOR = color;
	}

	/**
	 * Manually pause the view
	 */
	public void pause()
	{
		this.isPaused = true;
		this.onPause();
	}

	/**
	 * Method called directly after view has been paused
	 */
	public void onPause(){}

	/**
	 * Extend this method to make sure they only get called upon creating the view
	 *
	 * The reason that we can't use the constructor is because the object hasn't yet been
	 * linked to the game yet, or the game hasn't yet started so we can't do anything until
	 * the game is started.
	 *
	 * initialize will only be executed upon game start, or
	 * the next available tick via an Event.
	 */
	public void initialize()
	{}

	/**
	 * If view was paused, calling this method will resume its state.
	 * If game wasn't paused nothing happens.
	 */
	public void resume()
	{
		this.isPaused = false;
		this.onResume();
	}

	/**
	 * Method called directly after view has been Resumed
	 */
	public void onResume(){}

	/**
	 * See if the view is paused or not.
	 * @return
	 */
	public boolean isPaused()
	{
		return isPaused;
	}

	/**
	 * Kills the current View, deleting it from the game and detaching anything
	 * necessary
	 */
	public final void kill()
	{
		System.out.println( "View Killed" );
		getGame().detachView();
		onKill();
	}

	/**
	 * Method is called when ever object is deleted from the game
	 */
	public void onKill()
	{}

	/**
	 * Adding objects to the View rendering list.
	 * This is where all the logic for items is going to happen.
	 * The items will Update behind the scenes, and the views will be in charge
	 * of making sure that they are getting rendered.
	 *
	 * The reason that objects are stored in views is that when we switch views, the objects
	 * don't stay in the same place. Instead the screens are changed and a completely new
	 * set of objects take place, this is convenient for different locations, e.g. Main menu, Actual player game,
	 * and character selection. This keeps things separately and makes sure the program doesn't have to run through
	 * every single item to render a screen with maybe only two or three.
	 * @param objects
	 */
	public Drawable add( Drawable objects )
	{
		objects.setView( this );
		drawList.add( objects );
		if( objects instanceof Gui )
		{
			guiList.add( ( Gui ) objects );
		}
		return objects;
	}

	public Drawable[] add( Drawable... objects )
	{
		for( Drawable d : objects )
		{
			add( d );
		}
		return objects;
	}

	public Camera add( Camera camera )
	{
		cameraList.add ( camera );
		return camera;
	}

	public ViewEventManager getEventManager()
	{
		return this.eventManager;
	}

	public LayerHandler getDrawList()
	{
		return this.drawList;
	}

	/**
	 * O(n)2
	 *
	 * Searches for objects to remove from the DrawList.
	 * @param objects
	 */
	public void remove( Drawable... objects )
	{
		for (Drawable d : objects )
		{
			drawList.remove( d );
			if( d instanceof Gui )
			{
				guiList.remove( d );
			}
		}
	}

	public void remove( Camera... cameras )
	{
		for (Camera c : cameras )
		{
			cameraList.remove( c );
		}
	}

	@Override
	public int compareTo(Object o) {
		return this.getLayer() - ( ( View ) o ).getLayer();
	}

	public abstract void update( int delta );

	public View fadeOut( float speed )
	{
		return fade( speed, false );
	}

	public View fadeIn( float speed )
	{
		return fade( speed, true );
	}

	public int getMouseX()
	{
		Camera c = this.getClickedCamera ();
		if( c != null )
		{
			return this.getClickedCamera().getMouseX ();
		}
		return Mouse.getX ();
	}

	public int getMouseY()
	{
		Camera c = this.getClickedCamera ();
		if( c != null )
		{
			return this.getClickedCamera().getMouseY ();
		}
		return Mouse.getY ();
	}

	/**
	 * Returns the camera that is currently focused
	 * @return Camera
	 */
	private Camera getClickedCamera()
	{
		for( Camera c : this.cameraList )
		{
			int mx = Mouse.getX ();
			int my = Mouse.getY ();

			if( mx >= c.getX() && mx < c.getX () + c.getWidth () && my >= c.getY () && my < c.getHeight () && c.isClickable() )
			{
				return c;
			}
		}
		return null;
	}

	/**
	 * Fades the view in a specific direction
	 * @param speed
	 * @param direction
	 */
	private View fade( final float speed, final boolean direction )
	{
		//Make sure we aren't referencing a new color
		this.VIEW_COLOR = this.VIEW_COLOR.clone();
		this.eventManager.add( new ViewEvent( )
		{
			@Override
			public boolean isDone ( View view )
			{
				if( direction && view.VIEW_COLOR.getA() >= 1 )
				{
					view.VIEW_COLOR.setA( 1f );
					return true;
				}
				else if( !direction && view.VIEW_COLOR.getA() <= 0 )
				{
					view.VIEW_COLOR.setA( 0f );
					return true;
				}
				return false;
			}

			@Override
			public void onUpdate(int delta, View view)
			{
				//Just in case
				view.VIEW_COLOR = view.VIEW_COLOR.clone();
				if( direction )
				{
					view.VIEW_COLOR.setA( view.VIEW_COLOR.getA( ) + ( speed * delta ) );
				}
				else
				{
					view.VIEW_COLOR.setA( view.VIEW_COLOR.getA() - ( speed * delta ) );
				}
			}
		}, true );
		return this;
	}

	public void render( int delta )
	{
		eventManager.onUpdate( delta );
		for( Camera c : this.cameraList )
		{
			c.render ( ( c == this.cameraList.get ( 0 ) ) );
		}
	}
}
