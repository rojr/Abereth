package com.abereth.world;

import com.abereth.view.TiledWorldView;
import com.abereth.world.tiles.Tile;

public class TiledWorld extends World
{
    private Tile[][] tileList;
    private int width, height;
    private TiledWorldView view;
    private WalkStyle walkStyle;

    public TiledWorld( TiledWorldView view )
    {
        this ( view, 100, 100 );
    }

    public TiledWorld ( TiledWorldView view, int width, int height )
    {
        super ();

        this.setUpWorld ( width, height );
        this.buildWorld ();
        this.walkStyle = WalkStyle.TILED;
    }

    public void setUpWorld( int width, int height )
    {
        this.width = width;
        this.height = height;

        tileList = new Tile[ width ][ height ];
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

    public WalkStyle getWalkStyle()
    {
        return this.walkStyle;
    }

    public TiledWorld setWalkStyle( WalkStyle style )
    {
        this.walkStyle = style;
        return this;
    }

    private enum WalkStyle
    {
        SMOOTH,
        TILED
    }
}
