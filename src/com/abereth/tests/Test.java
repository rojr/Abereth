package com.abereth.tests;

import com.abereth.game.Game;

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
