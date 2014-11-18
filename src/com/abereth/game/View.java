package com.abereth.game;

import com.abereth.draw.Color;
import com.abereth.draw.Drawable;
import com.abereth.event.view.ViewEvent;
import com.abereth.event.view.ViewEventManager;
import com.abereth.objects.living.Living;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by apex on 02/08/14.
 */
public abstract class View implements Comparable{

	private Game game;
	private boolean isPaused;
	private ArrayList<Drawable> drawList;
	private int layer;
	private ViewEventManager eventManager;

	/*
		View specific graphical option settings

		These will change every object that is currently being drawn within the view
		This means we can add some fancy transitions, like make all objects go up, or rotate?
	 */
	public Color VIEW_COLOR = Color.WHITE;
	public float VIEW_ROTATION_AMOUNT = 0;
	public int   VIEW_ROTATION_ORIGIN_X = 0;
	public int   VIEW_ROTATION_ORIGIN_Y = 0;
	public int   VIEW_X_OFFSET = 0;
	public int   VIEW_Y_OFFSET = 0;

	public View( Game game )
	{
		this.game = game;
		this.drawList = new ArrayList<Drawable>();
		this.layer = 1;
		this.eventManager = new ViewEventManager( this );
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
	 * Initialize will have the apropriate
	 */
	public void Initialize()
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
	public void kill()
	{
		getGame().detachView();
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
        drawList.add( objects );
        return objects;
	}

    public Drawable[] add( Drawable... objects )
    {
        Collections.addAll( drawList, objects );
        return objects;
    }

	public ViewEventManager getEventManager()
	{
		return this.eventManager;
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
			for ( int i = 0; i < drawList.size(); i++ )
			{
				if(d == drawList.get( i ) )
				{
					drawList.remove( i );
					break;
				}
			}
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

	/**
	 * Fades the view in a specific direction
	 * @param speed
	 * @param direction
	 */
	private View fade( final float speed, final boolean direction )
	{
		//Make sure we aren't referencing a new view
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
			public void OnUpdate ( int delta, View view )
			{
				//Just in case
				view.VIEW_COLOR = view.VIEW_COLOR.clone();
				if( direction )
				{
					view.VIEW_COLOR.setA( view.VIEW_COLOR.getA( ) + speed );
				}
				else
				{
					view.VIEW_COLOR.setA( view.VIEW_COLOR.getA() - speed );
				}
			}
		}, true );
		return this;
	}

	public void render( int delta )
	{
		eventManager.onUpdate( delta );
		for( Drawable d : drawList )
		{
			if(d instanceof Living)
			{
				((Living)d).onUpdate( delta );
			}
			getGame().getDraw().render( d, this );
            //TODO Seperate rendering and drawing...
			//TODO possibly statistics. render() will return boolean  and see how many items were drawn compared to total items?
		}
	}
}
