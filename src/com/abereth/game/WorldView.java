package com.abereth.game;

import com.abereth.world.World;

/**
 * Created by r on 08/02/2016.
 */
public class WorldView extends View {

    private Thread worldThread;
    private World world;

    public WorldView(Game game) {
        super(game);
    }

    @Override
    public void onKill() {
        super.onKill();
        if( this.world != null )
        {
            this.worldThread.stop();
        }
    }

    @Override
    public void update(int delta) {

    }

    public void setWorld( World world )
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

}
