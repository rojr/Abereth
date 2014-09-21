package com.abereth.objects.living.entity;

import com.abereth.draw.Color;
import com.abereth.game.Draw;
import com.abereth.objects.living.Living;
import org.lwjgl.util.vector.Vector2f;

/**
 * Created by apex on 27/07/14.
 */
public class Entity extends Living {


	private Vector2f direction;
	private double weight;
	public Entity(double x, double y, double width, double height)
	{
		super(x,y, width, height);
		direction = new Vector2f();
		this.weight = 1;
	}

	public Vector2f getDirection()
	{
		return direction;
	}

	public double getWeight()
	{
		return weight;
	}

	public void setDirection(Vector2f direction)
	{
		this.direction = direction;
	}

	public void setWeight(double weight)
	{
		this.weight = weight;
	}

	@Override
	public void onUpdate() {
	}

	@Override
	public void draw( Draw draw )
	{
        draw.setColor(Color.RED);
        draw.line(getDrawX(), getDrawY(), getDrawX() + getDrawWidth(), getDrawY());
        draw.setColor(Color.WHEAT);
		draw.square(getDrawX(), getDrawY(), getDrawWidth(), getDrawHeight());
	}
}
