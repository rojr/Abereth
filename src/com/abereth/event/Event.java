package com.abereth.event;

/**
 * Created by sanic on 12/11/2014.
 */
public abstract class Event<ParentObject> implements EventBase<ParentObject>
{
	private EventBase<ParentObject> completeFunction;
	private ParentObject object;
	public void init( ParentObject object )
	{
		this.object = object;
	}

	public abstract boolean isDone( ParentObject object );
	public abstract void onUpdate(int delta, ParentObject object);

	/**
	 * Used to set the onComplete event. NOT TO BE CALLED WHEN AN EVENT IS FINISHED
	 * THIS IS WHAT THE complete() method is for
	 * @param onComplete
	 */
	public void onComplete( EventBase<ParentObject> onComplete )
	{
		completeFunction = onComplete;
	}

	public void complete()
	{
		if( completeFunction != null )
		{
			completeFunction.complete( this.object );
		}
	}

	@Override
	public void complete(ParentObject object) {
		this.complete();
	}
}
