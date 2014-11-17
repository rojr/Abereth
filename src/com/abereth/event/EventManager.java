package com.abereth.event;

import java.util.ArrayList;

/**
 *
 * @param <EventType>
 */
public class EventManager<EventType extends Event, ParentObject>
{

	private ArrayList<EventType> groupEventList = new ArrayList<>( );
	private ArrayList<EventType> sequentialEventList = new ArrayList<>( );

	private ParentObject object;
	public EventManager( ParentObject object )
	{
		this.object = object;
	}

	public EventManager add( EventType event, boolean sequential )
	{
		if( sequential )
		{
			sequentialEventList.add( event );
		}
		else
		{
			groupEventList.add( event );
		}
		return this;
	}

	public EventManager remove( EventType event )
	{
		if( !groupEventList.remove( event ) )
		{
			sequentialEventList.remove( event );
		}
		return this;
	}

	public ArrayList<EventType> getGroupEventList ( )
	{
		return this.groupEventList;
	}

	public ArrayList<EventType> getSequentialEventList( )
	{
		return this.sequentialEventList;
	}

	public ParentObject getObject()
	{
		return this.object;
	}

	public void onUpdate( int delta )
	{
		if( getSequentialEventList().size() >= 1 )
		{
			EventType current = getSequentialEventList().get( 0 );
			if( current.isDone( this.object ) )
			{
				remove( current );
				onUpdate( delta );
				return;
			}
			else
			{
				current.OnUpdate( delta, this.object );
			}
		}

		for( EventType event : getGroupEventList( ) )
		{
			if( event.isDone( this.object ) )
			{
				remove( event );
				onUpdate( delta );
				return;
			}
			event.OnUpdate( delta, this.object );
		}
	}

}
