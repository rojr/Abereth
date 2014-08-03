package com.abereth.game;

import com.abereth.draw.Drawable;
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

	public View( Game game )
	{
		this.game = game;
		this.drawList = new ArrayList<Drawable>();
		this.layer = 1;
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
		this.onKill();
	}

	/**
	 * Kills the current View, deleting it from the game and detaching anything
	 * necessary
	 */
	public void onKill()
	{
		getGame().detachView();
	}

	/**
	 * Adding objects to the View rendering list.
	 * This is where all the logic for items is going to happen.
	 * The items will update behind the scenes, and the views will be in charge
	 * of making sure that they are getting rendered.
	 *
	 * The reason that objects are stored in views is that when we switch views, the objects
	 * don't stay in the same place. Instead the screens are changed and a completely new
	 * set of objects take place, this is convenient for different locations, e.g. Main menu, Actual player game,
	 * and character selection. This keeps things separately and makes sure the program doesn't have to run through
	 * every single item to render a screen with maybe only two or three.
	 * @param objects
	 */
	public void add( Drawable... objects )
	{
		Collections.addAll(drawList, objects);
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

	public void render()
	{
		for( Drawable d : drawList )
		{
			if(d instanceof Living)
			{
				((Living)d).onUpdate();
			}
			getGame().getDraw().render( d );
			//TODO possibly statistics. render() will return boolean  and see how many items were drawn compared to total items?
		}
	}
}
