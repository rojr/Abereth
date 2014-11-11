package com.abereth.tests;

import com.abereth.game.Game;
import com.abereth.game.View;
import com.abereth.input.Mouse;
import com.abereth.objects.living.entity.Entity;

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
		if(Mouse.isLeftMouseDown())
		{
			add(new Entity(Mouse.getX(), Mouse.getY(), 50, 50));
		}
	}
}
