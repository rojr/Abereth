package com.age.world;

import com.age.exceptions.NameExists;
import com.age.graphics.effects.TextureLoader;

import java.util.ArrayList;

/**
 * Created by apex on 04/02/14.
 */
public class TileType {


    public static ArrayList<TileType> tileList = new ArrayList<TileType>();

    public static TileType AIR   = new TileType(TextureLoader.createTexture("res/tiles/Sky.png"), false, "AIR"),
                           BRICK = new TileType(TextureLoader.createTexture("res/tiles/Stone.png"), true, "BRICK");

    int texture;
    boolean solid;
    String name;
    public TileType(int texture, boolean solid, String name){
        this.texture = texture;
        this.solid = solid;
        this.name = name;
    }

    public int getTexture(){
        return this.texture;
    }
    public boolean isSolid(){
        return this.solid;
    }
    public String getName() { return this.name; }

    public static TileType add(TileType tile) throws NameExists{
        for(TileType t : tileList){
            if(t.getName().equalsIgnoreCase(tile.getName())){
                throw new NameExists();
            }
        }
        tileList.add(tile);
        return tile;
    }
}
