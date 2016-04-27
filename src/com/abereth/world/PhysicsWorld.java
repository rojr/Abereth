package com.abereth.world;

import com.abereth.G;
import com.abereth.game.View;
import com.abereth.objects.living.Physical;
import org.lwjgl.Sys;

/**
 * Created by apex on 02/08/14.
 */
public class PhysicsWorld extends org.dyn4j.dynamics.World implements Runnable
{

	private long last;
	private View view;
	private boolean alive = true;
	public int fps;
	public int actualFps;
	static long lastFrame = 0, lastFPS = 0;
	static int delta;
	public PhysicsWorld ()
	{
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
		return this;
	}

	public PhysicsWorld add( Physical physical )
	{
		this.view.add( physical );
		this.addBody(physical.getBody());
		return this;
	}

	public PhysicsWorld remove( Physical physical )
	{
		this.view.remove( physical );
		this.removeBody(physical.getBody());
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

	@Override
	public void run()
	{
		System.out.println( "Physics thread starting" );
		long lastUpdated = System.currentTimeMillis();
		while( this.alive )
		{
			delta = getDelta();
			updateFPS();
			double rest = (double) 1/(120 / ( delta == 0 ? 1 : delta ) );

			double spent = ( double ) ( System.currentTimeMillis() - lastUpdated ) / 1000;
			if( rest < spent )
			{
				lastUpdated = System.currentTimeMillis();

				long time = System.nanoTime();
				long diff = time - this.last;
				this.last = time;
				double elapsedTime = (diff / G.NANO_TO_BASE);

				this.update( elapsedTime / rest );
			}
			else
			{
				try
				{
					Thread.sleep( 1 );
				}
				catch ( Exception ex ){}
			}
		}
		System.out.println( "Physics thread is dead :(" );
	}
}
