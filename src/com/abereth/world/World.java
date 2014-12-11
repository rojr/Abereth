package com.abereth.world;

import com.abereth.game.View;
import com.abereth.objects.living.entity.Entity;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by apex on 02/08/14.
 */
public class World
{
	private View view;
	private int x, y;
	private ArrayList<Entity> entityList;
	private org.dyn4j.dynamics.World PhysicsWorld;
	public World( View view, int x, int y )
	{
		this.view = view;
		this.x = x;
		this.y = y;
		this.entityList = new ArrayList<Entity>();
	}

	/**
	 *
	 * @param entity
	 */
	public void addEnitity( Entity... entity )
	{
		Collections.addAll( entityList, entity);
	}

	/**
	 * Remove entity(es) from the world list. This will no longer
	 * Update the entities and allow them to interact with the world.
	 * @param entity
	 */
	public void removeEntity( Entity... entity )
	{
		for ( Entity e : entity )
		{
			for ( int i = 0; i < entityList.size(); i++ )
			{
				if( e == entityList.get( i ) )
				{
					entityList.remove( i );
					break;
				}
			}
		}
	}
}
