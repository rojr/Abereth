package com.gmail.robmadeyou.block;

import com.gmail.robmadeyou.draw.ABCollector;
import com.gmail.robmadeyou.draw.ABCollector.DrawParameters;
import com.gmail.robmadeyou.effects.ABColor;
import com.gmail.robmadeyou.effects.ABTextures;
import com.gmail.robmadeyou.entity.ABEntity;
import com.gmail.robmadeyou.world.ABWorld;

public class BlockAir implements ABBlock, Comparable<ABBlock> {

    private int x, y;
    private int id = 0;
    private boolean isSolid = false;
    private boolean isWalkable = true;
    private int texture;
    private ABColor color;

    public double g_score = Double.MAX_VALUE;
    public double h_score = Double.MAX_VALUE;
    private ABBlock parent = null;
    private boolean isConsidered;
    private boolean isVisited = false;

    public BlockAir(int x, int y) {
        this.x = x;
        this.y = y;
        this.texture = ABTextures.Block_Sky;
        this.color = ABColor.White;
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
    	DrawParameters p = new DrawParameters("box", x * ABWorld.BLOCK_SIZE(), y * ABWorld.BLOCK_SIZE(), ABWorld.BLOCK_SIZE(), ABWorld.BLOCK_SIZE());
    		p.setTextureID(texture);
    		p.setColor(color);
    		p.setLayer(0);
    		p.setUseTranslate(true);
        ABCollector.add(p);
    }

    public void doEffect(ABEntity e) {
        //Air gives no effect. How sad :(
    }

    public void removeEffect(ABEntity e) {
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
    public ABBlock getParent() {
        return parent;
    }

    @Override
    public void setParent(ABBlock block) {
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

    public ABBlock getType() {
        return this;
    }

    @Override
    public String toString() {
        return "BlockAir:" + "\nCoordinates: (" + this.x + ", " + this.y + ")";
    }

    @Override
    public int compareTo(ABBlock o) {
        if (g_score + h_score < o.getG_Score() + o.get_hscore()) {
            return -1;
        }
        if (g_score + h_score > o.getG_Score() + o.get_hscore()) {
            return 1;
        }
        return 0;
    }
}
