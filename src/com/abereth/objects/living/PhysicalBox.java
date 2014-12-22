package com.abereth.objects.living;

import com.abereth.G;
import com.abereth.game.Draw;
import org.dyn4j.dynamics.Body;
import org.dyn4j.dynamics.BodyFixture;
import org.dyn4j.geometry.Polygon;
import org.dyn4j.geometry.Rectangle;
import org.dyn4j.geometry.Vector2;

import static org.lwjgl.opengl.GL11.*;

/**
 * Created by sanic on 12/12/2014.
 */
public class PhysicalBox extends Physical
{
	private Body rectangle;

	public PhysicalBox( double x, double y, double width, double height )
	{
		super( );
		this.rectangle = new Body();
		Rectangle rect = new Rectangle( width / G.DYN4J_PIXELS_TO_METERS, height / G.DYN4J_PIXELS_TO_METERS );
		this.rectangle.addFixture( new BodyFixture( rect ) );
		this.rectangle.setMass();
		this.rectangle.translate( x / G.DYN4J_PIXELS_TO_METERS, y / G.DYN4J_PIXELS_TO_METERS );
		setBody( this.rectangle );
	}

	@Override
	public void DrawShape( Draw d )
	{
		Polygon p = ( Polygon ) getBody().getFixture( 0 ).getShape();
		glBegin( GL_POLYGON );
		for( Vector2 v : p.getVertices() )
		{
			glVertex2d( v.x, -v.y );
		}
		glEnd();
	}
}
