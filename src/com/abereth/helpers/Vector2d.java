package com.abereth.helpers;

import org.lwjgl.util.vector.Vector2f;

/**
 * Created by Bobby on 18/09/2014.
 */
public class Vector2d {

    private double x, y;
    public Vector2d( double x, double y )
    {
        this.x = x;
        this.y = y;
    }

    public double getX()
    {
        return this.x;
    }

    public double getY()
    {
        return this.y;
    }

    public void setX( double x )
    {
        this.x = x;
    }

    public void setY( double y )
    {
        this.y = y;
    }

    public void set( double x, double y )
    {
        setX( x );
        setY( y );
    }

}
