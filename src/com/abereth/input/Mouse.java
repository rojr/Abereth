package com.abereth.input;

/**
 * Created by Bobby on 21/09/2014.
 */
public class Mouse {

    public static boolean isLeftMousePressed()
    {
        return org.lwjgl.input.Mouse.isButtonDown( 0 );
    }
}
