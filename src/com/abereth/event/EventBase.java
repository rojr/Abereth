package com.abereth.event;

/**
 * Created by exfos on 28/01/15.
 */
public interface EventBase<P>
{
	void complete( P object );
}
