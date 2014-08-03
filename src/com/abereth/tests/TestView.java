package com.abereth.tests;

import com.abereth.draw.Color;
import com.abereth.game.Game;
import com.abereth.game.View;
import com.abereth.objects.living.entity.Entity;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;

/**
 * Created by apex on 03/08/14.
 */
public class TestView extends View
{
	private Entity ent;
	public TestView( Game game )
	{
		super( game );
	}

	@Override
	public void update(int delta) {
		if(Mouse.isButtonDown(0))
		{
			add(new Entity(Mouse.getX(), Display.getHeight() - Mouse.getY(), 50, 50));
		}
	}
}
