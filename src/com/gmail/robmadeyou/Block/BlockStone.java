package com.gmail.robmadeyou.Block;

import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.Textures;
import com.gmail.robmadeyou.Entity.Entity;
import com.gmail.robmadeyou.World.World;

public class BlockStone implements Block, Comparable<Block> {

    private double g_score = Double.MAX_VALUE;
    private double h_score = Double.MAX_VALUE;
    public Block parent;
    private int x, y;
    private int id = 1;
    private boolean isSolid = true;
    private boolean isWalkable = false;
    private int texture = Textures.Block_Stone;
    private Color color;
    protected boolean isConsidered = false;
    protected boolean isVisited = false;


    public BlockStone(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = Color.White;
    }

    public void consider() {
        isConsidered = true;
    }

    public int getID() {
        return id;
    }

    public boolean isSolid() {
        return isSolid;
    }

    public int getTexture() {
        return texture;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setTexture(int tex) {
        this.texture = tex;
    }

    public void onUpdate() {
        draw();
    }

    public Block getType() {
        return this;
    }

    public void draw() {
        Collector.add(new DrawParameters("box", x * World.BLOCK_SIZE(), y * World.BLOCK_SIZE(), World.BLOCK_SIZE(), World.BLOCK_SIZE(), texture, color, 0, true));
    }

    public void doEffect(Entity e) {
        //Stone does no effect
    }

    public void removeEffect(Entity e) {
        //Stone does no effect
    }

    @Override
    public double getG_Score() {
        return g_score;
    }

    @Override
    public void setG_Score(Double score) {
        this.g_score = score;
    }

    @Override
    public double get_hscore() {
        return this.h_score;
    }

    @Override
    public void setHscore(Double score) {
        this.h_score = score;
    }

    @Override
    public Block getParent() {
        return this.parent;
    }

    @Override
    public void setParent(Block block) {
        this.parent = block;
    }

    @Override
    public void visit() {
        isVisited = true;
    }

    @Override
    public boolean isVisited() {
        return isVisited;
    }

    @Override
    public boolean isConsidered() {
        return isConsidered;
    }

    @Override
    public boolean isWalkable() {
        return isWalkable;
    }

    @Override
    public int compareTo(Block o) {
        if (g_score + h_score < o.getG_Score() + o.get_hscore()) {
            return -1;
        }
        if (g_score + h_score > o.getG_Score() + o.get_hscore()) {
            return 1;
        }
        return 0;
    }
}
