package com.abereth.view;

import com.abereth.draw.Drawable;
import com.abereth.game.Game;
import com.abereth.objects.living.Living;

import java.util.ArrayList;

public class WorldView extends View
{
    public ArrayList<Living> livingList;
    public WorldView ( Game game )
    {
        super ( game );

        this.livingList = new ArrayList<> (  );
    }

    @Override
    public Drawable add (Drawable objects)
    {
        if( objects instanceof Living )
        {
            livingList.add ( (Living) objects );
        }
        return super.add ( objects );
    }

    @Override
    public void remove (Drawable... objects)
    {
        for( Drawable d : objects )
        {
            if( d instanceof Living )
            {
                livingList.remove ( d );
            }
        }
        super.remove ( objects );
    }

    @Override
    public void update (int delta)
    {
        for( Living l : this.livingList )
        {
            l.onUpdate ( delta );
        }
    }
}
