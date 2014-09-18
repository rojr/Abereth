package com.abereth.world;

import com.abereth.game.View;
import com.abereth.objects.MappedObject;
import com.abereth.objects.living.entity.Entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Vector;

/**
 * Created by Bobby on 18/09/2014.
 *
 * This class creates a new map.
 *
 * Now maps are different to World. World consists of maps, that a player
 * can move to and from; they can travel between them and so on.
 *
 * Where as Worlds contain general information about maps and how they are linked
 * and what happens where.
 *
 * This way we can build maps from a View screen or even externally and load them when the game is done
 * either from Code or special software created to make worlds / maps.
 *
 * Maps how ever have a size limit; we are working with shorts; so min size being 0 and max 32,767(including)
 * how ever this doesn't mean that we are going to be rendering this many frames; it really only means that the arrays won't get
 * populated to be that big.
 */
public class Map {

    private World world;
    private int width, height;
    private Tile[][] map;
    //This means that objects can't be on top of each other; as in a tile can always only be occupied by a single object
    private MappedObject[][] objects;
    /**
     * Creates a new map.
     * @param world World that the map belongs to
     * @param width The width of the map
     * @param height The height of the map
     */
    public Map( World world, short width, short height )
    {
        this.world = world;
        this.width = width;
        this.height = height;
        //Here we make it so that 0,0 is always at the center
        //So player can create tiles at -25,-2 and still be fine with it
        //but as soon as they go past Short.MAX_VALUE it will error out because
        //shorts are too small
        this.map = new Tile[ Short.MAX_VALUE + width ][ Short.MAX_VALUE + height ];
        this.objects = new MappedObject[ Short.MAX_VALUE + width ][ Short.MAX_VALUE + height ];
    }
}
