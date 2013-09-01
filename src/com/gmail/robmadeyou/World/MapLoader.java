package com.gmail.robmadeyou.World;

import com.gmail.robmadeyou.Block.Block;
import com.gmail.robmadeyou.Block.BlockAir;
import com.gmail.robmadeyou.Block.BlockGravel;
import com.gmail.robmadeyou.Block.BlockStone;
import org.newdawn.slick.Color;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Created by Mrgadgetz
 * Date: 8/28/13
 * Time: 8:42 PM
 */
public class MapLoader {

    public static String MAP_STRING = "res/World/maptest.png";

    public static enum BLOCK_TYPE {
        WATER, ROCK, GRASS, SAND, DIRT,;
    }

    public static BlockMap generateMap(String image) throws SlickException {

        BlockMap returnValue;

        Image img = new Image(image);
        int width = img.getWidth();
        int height = img.getHeight();
        returnValue = new BlockMap(width, height);
        //System.out.println(returnValue.getLength() + "\n" + returnValue.getHeight() + "\n");

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {

                Color color = img.getColor(x, y);
                BLOCK_TYPE type = getBlockIDFromColor(color.getRedByte(), color.
                        getGreenByte(), color.getBlueByte());

                if (type != null) {
                    Block tile = getBlockType(x, y, type);
                    returnValue.setBlock(x, y, tile);
                } else {
                    returnValue.setBlock(x, y, new BlockAir(x, y));
                }

            }
        }
        img.destroy();
        img = null;


        return returnValue;
    }

    private static Block getBlockType(int x, int y, BLOCK_TYPE type) {
        switch (type) {
            case WATER:
                return new BlockAir(x, y);
            case ROCK:
                return new BlockStone(x, y);
            case GRASS:
                new BlockAir(x, y);
            case SAND:
                return new BlockAir(x, y);
            case DIRT:
                return new BlockGravel(x, y);
            default:
                return null;
        }

    }

    private static BLOCK_TYPE getBlockIDFromColor(int r, int g, int b) {

        if (r == 0 && g == 0 && b == 255) { // pure blue
            return BLOCK_TYPE.WATER;
        } else if (r == 128 && g == 128 && b == 128) { // pure grey
            return BLOCK_TYPE.ROCK;
        } else if (r == 0 && g == 255 && b == 0) { // pure green
            return BLOCK_TYPE.GRASS;
        } else if (r == 255 && g == 255 && b == 0) { // pure yellow
            return BLOCK_TYPE.SAND;
        } else if (r == 255 && g == 255 && b == 255) { // pure white
            return BLOCK_TYPE.DIRT;
        } else {
            return null;
        }
    }
}
