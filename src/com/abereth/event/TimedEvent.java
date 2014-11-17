package com.abereth.event;

/**
 * Created by sanic on 17/11/2014.
 */
public abstract class TimedEvent<ParentObject> extends Event<ParentObject>
{
	private long interval;
	private long lastExecutionTime;

	/**
	 * Timed event. Interval is default to 10, so basically almost every tick the interval is going to be updated.
	 * Make sure you use SetInterval() in the init method, or somewhere else for that sakes if you want to control the speed
	 * of execution
	 */
	public TimedEvent( )
	{
		this.interval = 10;
		this.lastExecutionTime = 0;
	}

	public long GetInterval()
	{
		return interval;
	}

	public void SetInterval( long interval )
	{
		this.interval = interval;
	}

	@Override
	public void OnUpdate( int delta, ParentObject parentObject )
	{
		long currentTime = System.currentTimeMillis();
		if( currentTime - this.lastExecutionTime > this.interval )
		{
			this.lastExecutionTime = currentTime;
			EachInterval( delta, parentObject );
		}
	}

	public abstract void EachInterval( int delta, ParentObject parentObject );

}
