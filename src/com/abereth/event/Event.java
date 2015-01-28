package com.abereth.event;

/**
 * Created by sanic on 12/11/2014.
 */
public abstract class Event<ParentObject>
{
	private EventComplete<ParentObject> completeFunction;
	private ParentObject object;
	public void init( ParentObject object )
	{
		this.object = object;
	}

	public abstract boolean isDone( ParentObject object );
	public abstract void OnUpdate( int delta, ParentObject object );

	/**
	 * Used to set the onComplete event. NOT TO BE CALLED WHEN AN EVENT IS FINISHED
	 * THIS IS WHAT THE complete() method is for
	 * @param onComplete
	 */
	public void onComplete( EventComplete<ParentObject> onComplete )
	{
		completeFunction = onComplete;
	}

	public void complete()
	{
		if( completeFunction != null )
		{
			completeFunction.onDone(  this.object );
		}
	}

}
