package com.gmail.robmadeyou.Physics;

import com.gmail.robmadeyou.Block.ABBlock;
import com.gmail.robmadeyou.Block.BlockAir;
import com.gmail.robmadeyou.Entity.ABEntity;
import com.gmail.robmadeyou.World.ABWorld;
import org.lwjgl.util.vector.Vector2f;

import java.util.ArrayList;

/**
 * Created by Mrgadgetz
 * Date: 8/10/13
 * Time: 1:11 PM
 */
public class ABSATCollisionDetection {

    public static ArrayList<ABBlock> getNonEffectingBlocks() {

        ArrayList<ABBlock> effectingBlocks = new ArrayList<ABBlock>();

        for (int i = 0; i < ABWorld.WorldArrayWidth; i++) {
            for (int j = 0; j < ABWorld.WorldArrayHeight; j++) {
                if (!(ABWorld.blockList.getBlock(i, j) instanceof BlockAir)) {
                    effectingBlocks.add(ABWorld.blockList.getBlock(i, j));
                    System.out.println("Collected " + ABWorld.blockList.getBlock(i, j).toString());
                }
            }
        }
        System.out.println("collected all blocks blocklist");
        return effectingBlocks;
    }

    public static ArrayList<Vector2f> getEntityAxes(ABEntity entity) {

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


