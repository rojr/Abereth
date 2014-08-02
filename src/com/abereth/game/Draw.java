package com.abereth.game;

import com.abereth.draw.Color;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by apex on 26/07/14.
 */
public class Draw {

	public Draw()
	{

	}

	public void setColor(Color c)
	{
		setColor(c.getR(), c.getG(), c.getB(), c.getA());
	}

	public void setColor(float r, float g, float b, float a)
	{
		glColor4f(r,g,b,a);
	}

	public void square(double x, double y, double width, double height)
	{
		glBegin(GL_QUADS);
			glVertex2d( x, y );
			glVertex2d( x + width, y );
			glVertex2d( x + width, y + height );
			glVertex2d( x, y + height );
		glEnd();
	}

	public void texturedSquare(double x, double y, double width, double height)
	{
		//TODO add texture class;
		glBegin(GL_QUADS);
			glVertex2d( x, y );
			glVertex2d( x + width, y );
			glVertex2d( x + width, y + height );
			glVertex2d( x, y + height );
		glEnd();
	}

}
