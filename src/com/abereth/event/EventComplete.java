package com.abereth.event;

/**
 * Created by exfos on 28/01/15.
 */
public abstract interface EventComplete<P>
{
	void onDone( P object );
}
