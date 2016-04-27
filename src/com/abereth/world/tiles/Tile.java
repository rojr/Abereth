package com.abereth.world.tiles;

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

    public Tile( int x, int y )
    {
        super( x, y , 16, 16);
    }

    public Tile( int x, int y, int width, int height )
    {
        super( x, y, width, height );
        this.setUseTranslate ( true );
    }

    @Override
    public void draw( Draw d ) {
        d.square ( getDrawX (), getDrawY (), getDrawWidth (), getDrawHeight () );
    }
}
