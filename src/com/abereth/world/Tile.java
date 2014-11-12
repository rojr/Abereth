package com.abereth.world;

import com.abereth.draw.Drawable;
import com.abereth.game.Draw;

/**
 * Created by apex on 02/08/14.
 *
 * Going to default to 8PX per tile for now and see how it goes
 *
 * This means that we are going to have (8 * 32,768) * 2 = 524288px to play with; Which is fairly decent for a single Map
 * bear in mind that Maps aren't supposed to be exactly the biggest things in the world; so... there's that.
 */
public class Tile extends Drawable {

    public static final byte size = 8;
    public Tile( short x, short y )
    {
        super( x * size, y * size, size, size);
    }

    @Override
    public void draw(Draw d) {
        
    }
}
