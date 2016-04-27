package com.abereth.objects.living;

import com.abereth.G;
import com.abereth.draw.Drawable;
import com.abereth.game.Draw;
import org.dyn4j.dynamics.Body;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by sanic on 12/12/2014.
 */
public abstract class Physical extends Drawable
{
	private Body body;

	public Physical( )
	{
		super();
	}

	public void setBody( Body b )
	{
		this.body = b;
	}

	public Body getBody()
	{
		return this.body;
	}

	public abstract void drawShape (Draw d);

	@Override
	public double getDrawX()
	{
		return getBody().getTransform().getTranslationX() * G.DYN4J_PIXELS_TO_METERS;
	}

	@Override
	public double getDrawY()
	{
		return getBody().getTransform().getTranslationY() * G.DYN4J_PIXELS_TO_METERS;
	}

	@Override
	public void draw( Draw d )
	{
		glPushMatrix();
		{
			glScaled( G.DYN4J_PIXELS_TO_METERS, -G.DYN4J_PIXELS_TO_METERS, G.DYN4J_PIXELS_TO_METERS );
			glTranslated( getBody().getTransform().getTranslationX(), -getBody().getTransform().getTranslationY(), 0.0 );
			glRotated( -Math.toDegrees( getBody().getTransform().getRotation() ), 0.0, 0.0, 1.0 );
			this.drawShape ( d );
		}
		glPopMatrix();
	}

}