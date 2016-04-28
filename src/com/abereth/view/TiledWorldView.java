package com.abereth.view;

import com.abereth.game.Game;
import com.abereth.world.TiledWorld;

/**
 * Created by r on 27/04/16.
 */
public class TiledWorldView extends WorldView
{
    private TiledWorld world;

    public TiledWorldView ( Game game )
    {
        super ( game );

        this.world = new TiledWorld ( this );
    }

    public TiledWorld getWorld()
    {
        return this.world;
    }

    @Override
    public void update (int delta)
    {
        super.update ( delta );
    }
}
