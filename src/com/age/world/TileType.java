package com.age.world;

import com.age.graphics.effects.TextureLoader;

/**
 * Created by apex on 04/02/14.
 */
public enum TileType {

    AIR(TextureLoader.createTexture("res/tiles/Sky.png"), false, "AIR"),
    BRICK(TextureLoader.createTexture("res/tiles/Stone.png"), true, "BRICK");

    int texture;
    boolean solid;
    String name;
    TileType(int texture, boolean solid, String name){
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
}
