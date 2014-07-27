package com.abereth.tests;

import com.abereth.Game;
import com.abereth.draw.Color;
import com.abereth.draw.Draw;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 * Created by apex on 27/07/14.
 */
public class Test {
	public static void main(String... args)
	{
		Draw d = new Draw();
		Game.create(500, 500, "Community");
		int size = 20;
		boolean up = false;
		while(!Display.isCloseRequested())
		{
			Game.update();
			d.setColor(Color.random());

			d.square(Mouse.getX(), Display.getHeight() - Mouse.getY(), size, 40);
			Game.refresh(60);
			System.out.println(Game.actualFps);
		}
	}
}
