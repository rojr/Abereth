package com.abereth.game;

import com.abereth.draw.Color;
import com.abereth.draw.Drawable;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by apex on 26/07/14.
 */
public class Draw {

	private Color lastColor;
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

	public void line(double x, double y, double x2, double y2)
	{
		glBegin(GL_LINES);
			glVertex2d(x,y);
			glVertex2d(x2, y2);
		glEnd();
	}

	public void line(double x, double y, double x2, double y2, int lineWidth)
	{
		glLineWidth(lineWidth);
		glBegin(GL_LINES);
			glVertex2d(x,y);
			glVertex2d(x2, y2);
		glEnd();
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

	public void square(double x, double y, double width, double height, int layer)
	{
		glBegin(GL_QUADS);
		glVertex3d( x, y, layer );
		glVertex3d( x + width, y, layer );
		glVertex3d( x + width, y + height, layer );
		glVertex3d( x, y + height, layer );
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

	public void render( Drawable d )
	{
		//If last color was the same as current one then there is no
		//need to re-bind it. Same will happen for textures.
		if(lastColor != d.getColor())
		{
			setColor( d.getColor() );
			lastColor = d.getColor();
		}
		glPushMatrix();
			//glRotatef((float)d.getRotation(),(float) (d.getDrawY() ), (float) (d.getDrawX()), 0f);
			glPushMatrix();
				glPushMatrix();
				glTranslated(d.getDrawX() + d.getDrawWidth() / 2, d.getDrawY() + d.getDrawHeight() / 2, 0);
				glRotatef((float) d.getRotation(), 0f, 0f, 1f);
				glTranslated(-d.getDrawX() - d.getDrawWidth() / 2, -d.getDrawY() - d.getDrawHeight() / 2, 0);
				glScalef(d.getScaleX(), d.getScaleY(), 0);
				d.render( this );
				glPopMatrix();
			glPopMatrix();
		glPopMatrix();
	}

}
