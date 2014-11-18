package com.abereth.input;

import org.lwjgl.opengl.Display;

/**
 * Created by Bobby on 21/09/2014.
 */
public class Mouse {

	protected static boolean wasLeftMouseReleased = true;
	protected static boolean wasRightMouseReleased = true;

	public static boolean wasLeftMouseClickedThisTick = false;
	public static boolean wasRightMouseClickedThisTick = false;

	/**
	 * Only triggers once per click, so that means event will only activate once,
	 * and will reset after mouse has been released and then only clicked again
	 *
	 * @return
	 */
	public static boolean isLeftMouseClicked()
	{
		//WasLeftMouseClickedThisTick is so that if this method is called more than once this tick
		//that means it doesn't just auto lock to false :)
		//Same process with the right mouse button
		if( wasLeftMouseReleased && isLeftMouseDown() || wasLeftMouseClickedThisTick )
		{
			wasLeftMouseReleased = false;
			wasLeftMouseClickedThisTick = true;
			return true;
		}
		return false;
	}

	/**
	 * Only triggers once per click, so that means event will only activate once,
	 * and will reset after mouse has been released and then only clicked again
	 * @return
	 */
	public static boolean isRightMouseClicked()
	{
		if( wasRightMouseReleased && isRightMouseDown() || wasRightMouseClickedThisTick )
		{
			wasRightMouseReleased = false;
			wasRightMouseClickedThisTick = true;
			return true;
		}
		return false;
	}

	public static boolean isLeftMouseDown()
	{
		boolean isMouseDown = org.lwjgl.input.Mouse.isButtonDown( 0 );
		if( isMouseDown )
		{
			wasLeftMouseReleased = false;
		}
		else
		{
			if( !wasLeftMouseReleased )
			{
				wasLeftMouseReleased = true;
			}
		}
		return isMouseDown;
	}

	public static boolean isRightMouseDown()
	{
		boolean isMouseDown = org.lwjgl.input.Mouse.isButtonDown( 1 );
		if( isMouseDown )
		{
			wasRightMouseReleased = false;
		}
		else
		{
			if( !wasRightMouseReleased )
			{
				wasRightMouseReleased = true;
			}
		}
		return isMouseDown;
	}

	public static int getDX()
	{
		return org.lwjgl.input.Mouse.getDX();
	}

	public static int getDY()
	{
		return org.lwjgl.input.Mouse.getDY();
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
