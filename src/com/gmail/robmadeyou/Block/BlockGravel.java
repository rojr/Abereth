package com.gmail.robmadeyou.Block;

import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.Textures;
import com.gmail.robmadeyou.Entity.Entity;
import com.gmail.robmadeyou.World.World;

public class BlockGravel implements Block, Comparable<Block> {

    private int x, y;
    private int id = 0;
    private boolean isSolid = false;
    private boolean isWalkable = true;
    private int texture;
    private Color color;

    public double g_score = Double.MAX_VALUE;
    public double h_score = Double.MAX_VALUE;
    private Block parent = null;
    private boolean isConsidered;
    private boolean isVisited = false;

    public BlockGravel(int x, int y) {
        this.x = x;
        this.y = y;
        this.texture = Textures.Block_Gravel;
        this.color = Color.White;
    }

    public int getID() {
        return id;
    }


    public boolean isSolid() {
        return isSolid;
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

    public void onUpdate() {
        draw();
    }

    public void draw() {
        Collector.add(new DrawParameters("box", x * World.BLOCK_SIZE(), y * World.BLOCK_SIZE(), World.BLOCK_SIZE(), World.BLOCK_SIZE(), texture, color, 0, true));
    }

    public void doEffect(Entity e) {
        //Air gives no effect. How sad :(
    }

    public void removeEffect(Entity e) {
        //Air gives no effect!
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
        return parent;
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
    public void consider() {
        isConsidered = true;

    }

    @Override
    public boolean isWalkable() {
        return isWalkable;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public int getTexture() {
        return texture;
    }

    public void setTexture(int tex) {
        this.texture = tex;
    }

    public Block getType() {
        return this;
    }

    @Override
    public String toString() {
        return "BlockGravel:" + "\nCoordinates: (" + this.x + ", " + this.y + ")";
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
