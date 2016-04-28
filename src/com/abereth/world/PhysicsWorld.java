package com.abereth.world;

import com.abereth.G;
import com.abereth.view.View;
import com.abereth.objects.living.Physical;
import org.lwjgl.Sys;

public class PhysicsWorld extends World
{

	private long last;
	private View view;
	private boolean alive = true;
	public int fps;
	public int actualFps;
	static long lastFrame = 0, lastFPS = 0;
	static int delta;
	private org.dyn4j.dynamics.World dyn4jWorld;
	public PhysicsWorld ()
	{
		this.dyn4jWorld = new org.dyn4j.dynamics.World();
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
		return dyn4jWorld;
	}

	public PhysicsWorld add( Physical physical )
	{
		this.view.add( physical );
		getPhysicalWorld().addBody ( physical.getBody () );
		return this;
	}

	public PhysicsWorld remove( Physical physical )
	{
		this.view.remove( physical );
		getPhysicalWorld().removeBody ( physical.getBody () );
		return this;
	}

	public long getTime()
	{
		return ( Sys.getTime() * 1000 ) / Sys.getTimerResolution();
	}

	public boolean isAlive()
	{
		return alive;
	}

	public void stop()
	{
		this.alive = false;
	}

	private void updateFPS()
	{
		if ( getTime() - lastFPS > 1000 )
		{
			actualFps = fps;
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}

	public int getFPS( )
	{
		return this.actualFps;
	}

	private int getDelta()
	{
		long currentTime = getTime();
		int delta = ( int ) ( currentTime - lastFrame );
		lastFrame = getTime();
		return delta;
	}

	private long lastUpdated = System.currentTimeMillis();

	@Override
	public void tick ()
	{
		delta = getDelta();
		updateFPS();
		double rest = (double) 1 / ( 120 );

		double spent = ( double ) ( System.currentTimeMillis() - lastUpdated ) / 1000;
		if( rest < spent )
		{
			lastUpdated = System.currentTimeMillis();

			long time = System.nanoTime();
			long diff = time - this.last;
			this.last = time;
			double elapsedTime = (diff / G.NANO_TO_BASE);

			getPhysicalWorld().update ( elapsedTime / rest );
		}
		else
		{
			try
			{
				Thread.sleep ( 1 );
			} catch ( Exception ex )
			{
			}
		}
	}
}
