package com.abereth.view;

import com.abereth.game.Game;
import com.abereth.world.TiledWorld;

/**
 * Created by r on 27/04/16.
 */
public class TiledWorldView extends View
{
    private TiledWorld world;

    public TiledWorldView ( Game game, TiledWorld world )
    {
        super ( game );

        this.world = world;
    }

    public TiledWorld getWorld()
    {
        return this.world;
    }

    @Override
    public void update (int delta)
    {

    }
}
