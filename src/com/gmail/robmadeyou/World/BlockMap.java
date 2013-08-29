package com.gmail.robmadeyou.World;

import com.gmail.robmadeyou.Block.Block;

/**
 * Created by Mrgadgetz
 * Date: 8/27/13
 * Time: 9:40 AM
 */
public class BlockMap {

    private Block[][] blockList;

    private int width, height;

    public BlockMap(int width, int height) {
        this.width = width;
        this.height = height;
        blockList = new Block[this.width][this.height];
    }

    public Block getBlock(int x, int y) {
        if (withinBounds(x, y)) {
            return blockList[x][y];
        } else {
            return null;
        }
    }

    public void setBlock(int x, int y, Block block) {
        if (withinBounds(x, y)) {
            blockList[x][y] = block;
        }
    }

    public void setBlock(Block block) {
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

