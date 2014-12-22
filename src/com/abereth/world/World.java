package com.abereth.world;

import com.abereth.G;
import com.abereth.game.View;
import com.abereth.objects.living.Physical;
import org.dyn4j.geometry.Vector2;

/**
 * Created by apex on 02/08/14.
 */
public class World
{
	private org.dyn4j.dynamics.World physicalWorld;

	private long last;
	private View view;
	public World( )
	{
		this.physicalWorld = new org.dyn4j.dynamics.World( );
	}

	public void setView( View view )
	{
		this.view = view;
	}

	public View getView()
	{
		return view;
	}

	public org.dyn4j.dynamics.World getPhysicalWorld()
	{
		return this.physicalWorld;
	}

	public World add( Physical physical )
	{
		this.view.add( physical );
		this.physicalWorld.addBody( physical.getBody() );
		return this;
	}

	public World remove( Physical physical )
	{
		this.view.remove( physical );
		this.physicalWorld.removeBody( physical.getBody() );
		return this;
	}

	public World setGravity( Vector2 gravity )
	{
		this.physicalWorld.setGravity( gravity );
		return this;
	}

	public void Update()
	{
		long time = System.nanoTime();
		long diff = time - this.last;
		this.last = time;
		double elapsedTime = ( double ) diff / G.NANO_TO_BASE;

		this.physicalWorld.update( elapsedTime );
	}
}
