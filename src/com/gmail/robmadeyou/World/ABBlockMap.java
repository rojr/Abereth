package com.gmail.robmadeyou.world;

import com.gmail.robmadeyou.block.ABBlock;

/**
 * Created by Mrgadgetz
 * Date: 8/27/13
 * Time: 9:40 AM
 */
public class ABBlockMap {

    private ABBlock[][] blockList;

    private int width, height;

    public ABBlockMap(int width, int height) {
        this.width = width;
        this.height = height;
        blockList = new ABBlock[this.width][this.height];
    }

    public ABBlock getBlock(int x, int y) {
        if (withinBounds(x, y)) {
            return blockList[x][y];
        } else {
            return null;
        }
    }

    public void setBlock(int x, int y, ABBlock block) {
        if (withinBounds(x, y)) {
            blockList[x][y] = block;
        }
    }

    public void setBlock(ABBlock block) {
        int x = block.getX();
        int y = block.getY();
        if (withinBounds(x, y)) {
            blockList[x][y] = block;
        }
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public boolean withinBounds(int x, int y) {
        return (x >= 0 && x < width && y >= 0 && y < height);
    }

    public int getLength() {
        return blockList.length;
    }

    public int getMapHeight() {
        return blockList[0].length;
    }
}

