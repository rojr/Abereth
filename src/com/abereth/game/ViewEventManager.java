package com.abereth.game;

import com.abereth.event.EventManager;
import com.abereth.event.ViewEvent;

/**
 * Created by jeremiah on 12/11/2014.
 */
public class ViewEventManager extends EventManager<ViewEvent>
{

	private View view;
	public ViewEventManager ( View view )
	{
		super( );
		this.view = view;
	}

	public View getView()
	{
		return this.view;
	}

	@Override
	public void onUpdate ( int delta )
	{
		if( getSequentialEventList().size() >= 1 )
		{
			ViewEvent current = getSequentialEventList().get( 0 );
			if( current.isDone( view ) )
			{
				remove( current );
				onUpdate( delta );
				return;
			}
			else
			{
				current.OnUpdate( delta, view );
			}
		}

		for( ViewEvent event : getGroupEventList( ) )
		{
			if( event.isDone( view ) )
			{
				remove( event );
				break;
			}
			event .OnUpdate( delta, this.view );
		}
	}
}
