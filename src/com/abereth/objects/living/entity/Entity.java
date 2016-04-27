package com.abereth.objects.living.entity;

import com.abereth.game.Draw;
import com.abereth.objects.living.Living;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by apex on 27/07/14.
 */
public class Entity extends Living
{
	private float gravity;

	public Entity( double x, double y, double width, double height )
	{
		super(x,y, width, height);
		this.gravity = 0f;
	}

	public float getGravity()
	{
		return this.gravity;
	}

	@Override
	public void draw( Draw d )
	{
	}
}
