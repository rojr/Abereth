package com.abereth.input;

import org.lwjgl.opengl.Display;

/**
 * Created by Bobby on 21/09/2014.
 */
public class Mouse {

    public static boolean isLeftMouseDown()
    {
        return org.lwjgl.input.Mouse.isButtonDown( 0 );
    }

    public static boolean isRightMouseDown()
    {
        return org.lwjgl.input.Mouse.isButtonDown( 1 );
    }

    public static int getX()
    {
        return org.lwjgl.input.Mouse.getX();
    }

    public static int getY()
    {
        return Display.getHeight() - org.lwjgl.input.Mouse.getY();
    }
}
