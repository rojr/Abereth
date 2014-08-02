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

		View v = new View(g) {
			@Override
			public void update(int delta) {

				getGame().getDraw().square(Mouse.getX(), Display.getHeight() - Mouse.getY(), 20, 20);
				if(Mouse.isButtonDown(0))
				{
					View v2 = new View(getGame())
					{
						@Override
						public void update(int delta) {
							getGame().getDraw().setColor(Color.random());
							getGame().getDraw().square(Mouse.getX(), Display.getHeight() - Mouse.getY() - 60, 20, 20);
						}
					};
					getGame().changeView(v2);
				}
			}
		};

		g.addView(v);
		g.start();
	}
}
