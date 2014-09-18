package com.abereth.objects.living;

import com.abereth.draw.Drawable;
import com.abereth.objects.MappedObject;
import com.abereth.world.Map;
import com.abereth.world.Tile;

/**
 * Created by apex on 27/07/14.
 */
public abstract class Living extends Drawable implements MappedObject
{
    private Map map;
	public Living( Map map, double x, double y, double width, double height )
	{
		super(x,y,width,height);
        this.map = map;
	}

	public abstract void onUpdate();

    @Override
    public short getCX()
    {
        return ( short ) Math.floor( getDrawX() / Tile.size );
    }

    @Override
    public short getCY()
    {
        return ( short ) Math.floor( getDrawY() / Tile.size );
    }

    @Override
    public short setCX( short cX ) {
        setDrawX( cX * Tile.size );
        return getCX();
    }

    @Override
    public short setCY(short cY) {
        setDrawY( cY * Tile.size );
        return getCY();
    }

    @Override
    public Map getMap()
    {
        return this.map;
    }

}
