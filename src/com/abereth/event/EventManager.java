package com.abereth.event;

import java.util.ArrayList;

/**
 *
 * @param <EventType>
 */
public class EventManager<EventType extends Event>
{

	private ArrayList<EventType> groupEventList = new ArrayList<>( );
	private ArrayList<EventType> sequentialEventList = new ArrayList<>( );

	public EventManager()
	{
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

	public void onUpdate( int delta )
	{
		sequentialEventList.get( 0 ).OnUpdate( delta );
		for( EventType event : groupEventList )
		{
			event.OnUpdate( delta );
		}
	}

}
