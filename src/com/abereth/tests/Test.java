package com.abereth.tests;

import com.abereth.game.Game;
import com.abereth.draw.Color;
import com.abereth.game.Draw;
import com.abereth.game.View;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 * Created by apex on 27/07/14.
 */
public class Test {
	public static void main(String... args)
	{
		Game g = new Game();

		g.addView(new TestView(g));
		g.start();
	}
}
