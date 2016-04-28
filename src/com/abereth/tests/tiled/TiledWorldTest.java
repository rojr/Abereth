package com.abereth.tests.tiled;

import com.abereth.game.Game;
import com.abereth.view.TiledWorldView;
import com.abereth.world.TiledWorld;

public class TiledWorldTest extends TiledWorldView
{
    public static void main (String[] args)
    {
        Game g = new Game ( 500, 500 );
        g.addView ( new TiledWorldTest ( g ) );
        g.start ();
    }

    public TiledWorldTest ( Game game )
    {
        super ( game );
    }

    @Override
    public void update (int delta)
    {
        super.update ( delta );
    }
}
