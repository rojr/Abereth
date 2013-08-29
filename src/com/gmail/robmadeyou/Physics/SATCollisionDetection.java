package com.gmail.robmadeyou.Physics;

import com.gmail.robmadeyou.Block.Block;
import com.gmail.robmadeyou.Block.BlockAir;
import com.gmail.robmadeyou.Entity.Entity;
import com.gmail.robmadeyou.World.World;
import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;

/**
 * Created by Mrgadgetz
 * Date: 8/10/13
 * Time: 1:11 PM
 */
public class SATCollisionDetection {

    public static ArrayList<Block> getNonEffectingBlocks() {

        ArrayList<Block> effectingBlocks = new ArrayList<Block>();

        for (int i = 0; i < World.WorldArrayWidth; i++) {
            for (int j = 0; j < World.WorldArrayHeight; j++) {
                if (!(World.blockList.getBlock(i, j) instanceof BlockAir)) {
                    effectingBlocks.add(World.blockList.getBlock(i, j));
                    System.out.println("Collected " + World.blockList.getBlock(i, j).toString());
                }
            }
        }
        System.out.println("collected all blocks blocklist");
        return effectingBlocks;
    }

    public static ArrayList<Vector2f> getEntityAxes(Entity entity) {

        ArrayList<Vector2f> axis = new ArrayList<Vector2f>();

        axis.add(new Vector2f((float) entity.getWidth() / 2, (float) entity.getHeight() / 2));
        axis.add(new Vector2f((float) entity.getWidth(), (float) entity.getHeight()));
        axis.add(new Vector2f((float) entity.getWidth(), (float) entity.getY()));
        axis.add(new Vector2f((float) entity.getX(), (float) entity.getY()));
        axis.add(new Vector2f((float) entity.getX(), (float) entity.getHeight()));


        return axis;
    }

//    public ArrayList<Vector2f> getBlockAxis(Block block) {
//
//        ArrayList<Vector2f> axis = new ArrayList<Vector2f>();
//
//        axis.add(new Vector2f( (float) block.getWidth()/2,(float) entity.getHeight()/2));
//        axis.add(new Vector2f( (float) entity.getWidth(),(float) entity.getHeight()));
//        axis.add(new Vector2f( (float) entity.getWidth(),(float) entity.getY()));
//        axis.add(new Vector2f( (float) entity.getX(),(float) entity.getY()));
//        axis.add(new Vector2f( (float) entity.getX(),(float) entity.getHeight()));
//
//
//        return axis;
//    }
}


