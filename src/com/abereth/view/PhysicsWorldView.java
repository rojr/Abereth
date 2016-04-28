package com.abereth.view;

import com.abereth.game.Game;
import com.abereth.world.PhysicsWorld;

/**
 * Created by r on 08/02/2016.
 */
public abstract class PhysicsWorldView extends WorldView {

    private Thread worldThread;
    private PhysicsWorld world;

    public PhysicsWorldView (Game game) {
        super(game);
    }

    @Override
    public void onKill() {
        super.onKill();
    }

    @Override
    public void update( int delta )
    {
        super.update ( delta );
        world.tick();
    }

    public void setWorld( PhysicsWorld world )
    {
        if( this.world != null )
        {
            this.world.stop();
        }

        this.world = world;

        world.setView( this );
    }

}
