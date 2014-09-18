package com.abereth.world;

import com.abereth.draw.Drawable;
import com.abereth.game.Draw;
import com.abereth.objects.MappedObject;

/**
 * Created by apex on 02/08/14.
 *
 * Going to default to 8PX per tile for now and see how it goes
 *
 * This means that we are going to have (8 * 32,768) * 2 = 524288px to play with; Which is fairly decent for a single Map
 * bear in mind that Maps aren't supposed to be exactly the biggest things in the world; so... there's that.
 */
public class Tile extends Drawable implements MappedObject {

    public static final byte size = 8;
    private Map map;
    public Tile( Map map, short x, short y )
    {
        super( x * size, y * size, size, size);
        this.map = map;
    }

	@Override
	public void draw(Draw d)
    {
        d.square( getDrawX(), getDrawY(), getDrawWidth(), getDrawHeight() );
	}

    @Override
    public short getCX()
    {
        return ( short ) Math.floor(getDrawX() / size);
    }

    @Override
    public short getCY()
    {
        return ( short ) Math.floor( getDrawY() / size );
    }

    @Override
    public short setCX(short cX)
    {
        setDrawX( cX * size );
        return getCX();
    }

    @Override
    public short setCY( short cY )
    {
        setDrawY( cY * size );
        return getCY();
    }

    public Map getMap()
    {
        return this.map;
    }
}
