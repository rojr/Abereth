package com.abereth.game;

import com.abereth.G;
import com.abereth.draw.Drawable;
import com.abereth.input.Mouse;
import com.abereth.view.View;

import static org.lwjgl.opengl.GL11.*;

public class Camera
{
    private View view;
    private int x, y, width, height;
    private float offsetX, offsetY;
    private boolean clickable;
    private float scaleX, scaleY;

    public Camera ( View view, int x, int y, int width, int height )
    {
        super ();

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.clickable = true;

        this.offsetX = 0f;
        this.offsetY = 0f;

        this.scaleX = width / ( float ) G.WIDTH ;
        this.scaleY = height / (float ) G.HEIGHT;

        this.view = view;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public int getMouseX()
    {
        return ( int ) ( ( Mouse.getX () - x ) / scaleX ) ;
    }

    public int getMouseY()
    {
        return ( int ) ( ( Mouse.getY () - y ) / scaleY );
    }

    public float getScaleX()
    {
        return this.scaleX;
    }

    public float getScaleY()
    {
        return this.scaleY;
    }

    public float getOffsetX()
    {
        return this.offsetX;
    }

    public float getOffsetY()
    {
        return this.offsetY;
    }

    public View getView()
    {
        return this.view;
    }

    public boolean isClickable()
    {
        return this.clickable;
    }

    public boolean isDrawableWithinBounds( Drawable d )
    {
        double oX = this.getOffsetX ();
        double oY = this.getOffsetY ();

        double dX = d.getDrawX ();
        double dY = d.getDrawY ();

        double dW = d.getDrawWidth ();
        double dH = d.getDrawHeight ();

        return true;
               /* (
                    ( dX >= oX && dX < oX + getWidth () && dY >= oY && dY < oY + getHeight () ) &&
                    ( dX + dW >= oX && dX + dW < oX + getWidth () && dY + dH >= oY && dY + dH < oY + getHeight () )
                );*/
    }

    public Camera setScaleX( float scale )
    {
        this.scaleX = scale;
        return this;
    }

    public Camera setScaleY( float scale )
    {
        this.scaleY = scale;
        return this;
    }

    public void render( boolean update )
    {
        glPushMatrix ();
        {
            glTranslated ( this.x, this.y, 0 );
            glScalef ( this.getScaleX (), this.getScaleY (), 1 );
            getView ().getDrawList ().render ( this, Game.delta, update );
        }
        glPopMatrix ();
    }
}
