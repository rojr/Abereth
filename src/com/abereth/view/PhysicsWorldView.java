package com.abereth.view;

import com.abereth.game.Game;
import com.abereth.world.PhysicsWorld;

/**
 * Created by r on 08/02/2016.
 */
public abstract class PhysicsWorldView extends View {

    private Thread worldThread;
    private PhysicsWorld world;

    public PhysicsWorldView (Game game) {
        super(game);
    }

    @Override
    public void onKill() {
        super.onKill();
        if( this.world != null )
        {
            this.world.stop();
        }
    }

    @Override
    public abstract void update(int delta);

    public void setWorld( PhysicsWorld world )
    {
        if( this.world != null )
        {
            this.world.stop();
        }

        this.world = world;

        world.setView( this );

        this.worldThread = new Thread( this.world );
        this.worldThread.start();
    }

    public Thread getWorldThread()
    {
        return this.worldThread;
    }

}
