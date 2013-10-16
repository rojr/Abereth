package com.gmail.robmadeyou.block;

import com.gmail.robmadeyou.draw.ABCollector;
import com.gmail.robmadeyou.draw.ABCollector.DrawParameters;
import com.gmail.robmadeyou.effects.ABColor;
import com.gmail.robmadeyou.effects.ABTextures;
import com.gmail.robmadeyou.entity.ABEntity;
import com.gmail.robmadeyou.world.ABWorld;

public class BlockStone implements ABBlock, Comparable<ABBlock> {

    private double g_score = Double.MAX_VALUE;
    private double h_score = Double.MAX_VALUE;
    public ABBlock parent;
    private int x, y;
    private int id = 1;
    private boolean isSolid = true;
    private boolean isWalkable = false;
    private int texture = ABTextures.Block_Stone;
    private ABColor color;
    protected boolean isConsidered = false;
    protected boolean isVisited = false;


    public BlockStone(int x, int y) {
        this.x = x;
        this.y = y;
        this.color = ABColor.White;
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

    public ABBlock getType() {
        return this;
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
        //Stone does no effect
    }

    public void removeEffect(ABEntity e) {
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
    public ABBlock getParent() {
        return this.parent;
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
    public boolean isWalkable() {
        return isWalkable;
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
