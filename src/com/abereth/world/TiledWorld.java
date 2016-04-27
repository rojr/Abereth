package com.abereth.world;

import com.abereth.world.tiles.Tile;

public class TiledWorld
{
    private Tile[][] tileList;
    private int width, height;

    public TiledWorld ( int width, int height )
    {
        super ();

        tileList = new Tile[ width ][ height ];

        this.buildWorld ();
    }

    public void buildWorld()
    {
        for( int i = 0; i < getWidth(); i++ )
        {
            for( int j = 0; j < getHeight(); j++ )
            {
                tileList[i][j] = new Tile ( i, j );
            }
        }
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }
}
