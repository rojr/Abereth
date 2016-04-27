package com.abereth.game;

import com.abereth.G;
import com.abereth.draw.Color;
import com.abereth.draw.Drawable;
import com.abereth.draw.TextureLoader;
import com.abereth.helpers.Vector2d;
import org.dyn4j.geometry.Vector2;
import org.lwjgl.util.vector.Vector2f;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by apex on 26/07/14.
 */
public class Draw {

	private Color lastColor;
	private int lastTexture;
	private TextureLoader textureLoader;
	private com.abereth.draw.Texture t = null;


	public Draw()
	{
		textureLoader = new TextureLoader();

	}

	public void setColor(Color c)
	{
		if( c.getA() == 1f )
		{
			setColor( c.getR(), c.getG(), c.getB() );
		}
		else
		{
			setColor(c.getR(), c.getG(), c.getB(), c.getA());
		}
	}

	public void setColor( float r, float g, float b )
	{
		glColor3f( r, g, b );
	}

	public void setColor(float r, float g, float b, float a)
	{
		glColor4f(r,g,b,a);
	}

    public void dot(double x, double y)
    {
        glBegin(GL_POINTS);
            glVertex2d(x,y);
        glEnd();
    }

    public void dot(double x, double y, int size)
    {
        glPointSize(size);
        dot(x, y);
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

	public void square( double x, double y, double width, double height )
	{
		this.square( x, y, width, height, 0, 0, 1, 1 );
	}

	public void square( double x, double y, double width, double height, double xPercent, double yPercent, double wPercent, double hPercent )
	{
		glBegin( GL_QUADS );
			glTexCoord2d( xPercent, yPercent );
			glVertex2d( x, y );
			glTexCoord2d( xPercent + wPercent, yPercent );
			glVertex2d( x + width, y );
			glTexCoord2d( xPercent + wPercent, yPercent + hPercent );
			glVertex2d( x + width, y + height );
			glTexCoord2d( xPercent, yPercent + hPercent );
			glVertex2d( x, y + height );
		glEnd();
	}

	public void square( Vector2... vectors )
	{
		if( vectors.length != 4 )
		{
			return;
		}

		glBegin( GL_QUADS );
			glTexCoord2d( 0, 0 );
			glVertex2d( vectors[0].x, vectors[0].y );
			glTexCoord2d( 1, 0 );
			glVertex2d( vectors[1].x, vectors[1].y );
			glTexCoord2d( 1, 1 );
			glVertex2d( vectors[2].x, vectors[2].y );
			glTexCoord2d( 0, 1 );
			glVertex2d( vectors[3].x, vectors[3].y );
		glEnd();
	}

    /**
     *
     * Triangles are great. Always have 3 points
     *
     * Points can be all in random order; how ever you want, of course though;
     * they have to be in specific order if you want to make custom shapes or
     * different kinds of triangles
     *
     * @param a Location 1
     * @param b Location 211:5
     * @param c Location 3
     */
    public void triangle( Vector2f a, Vector2f b, Vector2f c )
    {
        glBegin( GL_TRIANGLES );
            glVertex2f( a.getX(), a.getY() );
            glVertex2f( b.getX(), b.getY() );
            glVertex2f( c.getX(), c.getY() );
        glEnd();
    }

    /**
     *
     * Triangles are great. Always have 3 points
     *
     * Points can be all in random order; how ever you want, of course though;
     * they have to be in specific order if you want to make custom shapes or
     * different kinds of triangles
     *
     * @param a Location 1
     * @param b Location 2
     * @param c Location 3
     */
    public void triangle( Vector2d a, Vector2d b, Vector2d c )
    {
        glBegin( GL_TRIANGLES );
            glVertex2d( a.getX(), a.getY() );
            glVertex2d( b.getX(), b.getY() );
            glVertex2d( c.getX(), c.getY() );
        glEnd();
    }

	public void bindTexture(int id)
	{
		if( id == -1 )
		{
			id = TextureLoader.TEXTURE_EMPTY;
		}

		try
		{
			//TODO make this more dynamic so stuff gets saved in memory rather than hanging about here
			this.t = textureLoader.getTexture( TextureLoader.TextureInfo.get( id ).getLocation() );
		}
		catch ( Exception ex )
		{
			ex.printStackTrace();
		}

		if( t != null )
		{
			t.bind();
		}

	}

    /**
     *
     * @param d
     */
	public void render( Drawable d, Camera camera )
	{
		if ( !camera.isDrawableWithinBounds ( d ) )
		{
			return;
		}


		View view = camera.getView ();
		//If last color was the same as current one then there is no
		//need to re-bind it. Same will happen for textures.
		if ( lastColor != d.getColor () )
		{
			//setColor( d.getColor() );
			lastColor = d.getColor ();
		}

		if ( d.getTexture () != -1 )
		{
			bindTexture ( d.getTexture () );
		} else
		{
			bindTexture ( TextureLoader.TEXTURE_EMPTY );
		}

		if( d.isUseTranslate() )
		{
			glPushMatrix();
			glTranslatef(camera.getOffsetX (), camera.getOffsetY (), 0);
		}
		glPushMatrix ();
		{
			glPushMatrix ();
			{
				glPushMatrix ();
				{

					glTranslated ( d.getDrawX () + d.getDrawWidth () / 2, d.getDrawY () + d.getDrawHeight () / 2, 0 );
					glRotatef ( ( float ) d.getRotation (), 0f, 0f, 1f );
					glTranslated ( -d.getDrawX () - d.getDrawWidth () / 2, -d.getDrawY () - d.getDrawHeight () / 2, 0 );
					glScalef ( d.getScaleX (), d.getScaleY (), 1 );
					glPushMatrix ();
					{
						if ( view.VIEW_COLOR.getR () != 2f && view.VIEW_COLOR.getG () != 2f && view.VIEW_COLOR.getB () != 2f )
						{
							setColor ( view.VIEW_COLOR );
						} else
						{
							setColor ( d.getColor ().getR (), d.getColor ().getG (), d.getColor ().getB (), view.VIEW_COLOR.getA () );
						}
						glPushMatrix ();
						{
							glTranslated ( view.VIEW_ROTATION_ORIGIN_X + G.WIDTH / 2, view.VIEW_ROTATION_ORIGIN_Y + G.HEIGHT / 2, 0 );
							glRotatef ( view.VIEW_ROTATION_AMOUNT, 0f, 0f, 1f );
							glTranslated ( -view.VIEW_ROTATION_ORIGIN_X - G.WIDTH / 2, -view.VIEW_ROTATION_ORIGIN_Y - G.HEIGHT / 2, 0 );
							glPushMatrix ();
							{
								glTranslatef ( view.VIEW_X_OFFSET, view.VIEW_Y_OFFSET, 0 );
								d.render ( this );
							}
							glPopMatrix ();
						}
						glPopMatrix ();
					}
					glPopMatrix ();
				}
				glPopMatrix ();
			}
			glPopMatrix ();
		}
		glPopMatrix ();
		if(d.isUseTranslate()){
			glPopMatrix();
		}
	}

}
