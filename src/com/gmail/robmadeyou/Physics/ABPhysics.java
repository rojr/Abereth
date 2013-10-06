package com.gmail.robmadeyou.Physics;

import com.gmail.robmadeyou.Entity.ABEntity;
import com.gmail.robmadeyou.World.ABWorld;

/**
 * Created by Mrgadgetz
 * Date: 8/9/13
 * Time: 8:44 PM
 */
public class ABPhysics {

    public static final double GROUND_FRICTION_DELTA = 1.4F;

    public static final double AIR_FRICTION_DELTA = -.9D;

    public static final double MAX_MOVEMENT_SPEED = 10D;

    public static final double MAX_JUMP_SPEED = 12D;

    public static final double MOVEMENT_DELTA = .3D;

    public enum CollisionType {
        Top, Left, Right, Bottom, None
    }

    public static boolean CollidedWith(ABEntity a, ABEntity b) {
        return false;
    }

    public static CollisionType GetCollisionType(ABEntity a, ABEntity b) {
        return CollisionType.None;
    }

    public static boolean isWithinMaxThreshold(double value, double other) {
        return Math.abs(value - other) <= MAX_MOVEMENT_SPEED;
    }

    public static boolean intersectsWith(ABEntity other) {
        return false;
    }

    public static boolean isSolidAbove(ABEntity e) {
//        return CollidedWith(a, b)
//                && isWithinMaxThreshold(a.getX() + a.getHeight(), b.getY());

        int eX = (int) e.getX();
        int eY = (int) e.getY();
        float eW = e.getWidth();
        int bDimensions = ABWorld.BLOCK_SIZE();

        int startX = (int) Math.round(e.getX() / ABWorld.BLOCK_SIZE()) - 2;
        if (startX < 0) {
            startX = 0;
        }
        int durationX = startX + 4;
        while (durationX + startX >= ABWorld.WorldArrayWidth) {
            durationX--;
        }

        int startY = (int) Math.round(e.getY() / ABWorld.BLOCK_SIZE()) - 2;
        if (startY < 0) {
            startY = 0;
        }
        int durationY = startY + 4;
        while (durationY + startY >= ABWorld.WorldArrayHeight) {
            durationY--;
        }
        for (int sX = startX; sX < startX + durationX + 1; sX++) {
            for (int y = startY; y < startY + durationY; y++) {
                int x;
                if (sX >= 1) {
                    x = sX - 1;
                } else {
                    x = sX;
                }
                try{
                	int bX = ABWorld.blockList.getBlock(x, y).getX() * ABWorld.BLOCK_SIZE();
                	int bY = ABWorld.blockList.getBlock(x, y).getY() * ABWorld.BLOCK_SIZE();
                	for (int x2 = 0; x2 <= eW / 4; x2++) {
                    	boolean one = eX + (x2 * 4) >= bX
                            	&& eX + (x2 * 4) <= bX + bDimensions
                            	&& eY + 5 >= bY + bDimensions - 5
                            	&& eY - 5 <= bY + bDimensions + 5;
                            if (one) {
                        	if (ABWorld.blockList.getBlock(x, y).isSolid()) {
                            	e.setY(y * ABWorld.BLOCK_SIZE() + ABWorld.BLOCK_SIZE() + 5);
                            	return true;
                        	}
                    	}
                	}
                }catch(NullPointerException ex){}
            }
        }
        return false;
    }


    public static boolean isSolidUnder(ABEntity e) {
//        return CollidedWith(a, b)
//                && isWithinMaxThreshold(a.getY(), b.getY() + b.getHeight());
        int eX = (int) e.getX();
        int eY = (int) e.getY();
        float eW = e.getWidth();
        float eH = e.getHeight();

        int bDimensions = ABWorld.BLOCK_SIZE();
        /*
		 * Starting math to decide where the for loop should start from
		 * and end from, taking into consideration the array lengths so the
		 * engine no longer crashes when player is out of bounds
		 */
        int startX = (int) Math.round(e.getX() / ABWorld.BLOCK_SIZE()) - 2;
        if (startX < 0) {
            startX = 0;
        }
        int durationX = startX + 4;
        while (durationX + startX >= ABWorld.WorldArrayWidth) {
            durationX--;
        }

        int startY = (int) Math.round(e.getY() / ABWorld.BLOCK_SIZE()) - 2;
        if (startY < 0) {
            startY = 0;
        }
        int durationY = startY + 4;
        while (durationY + startY >= ABWorld.WorldArrayHeight) {
            durationY--;
        }
        for (int sX = startX; sX < startX + durationX + 1; sX++) {
            for (int y = startY; y < startY + durationY; y++) {
                int x;
                if (sX >= 1) {
                    x = sX - 1;
                } else {
                    x = sX;
                }
                //Returns null values if y is more than 816? weird
                try{
                int bX = ABWorld.blockList.getBlock(x, y).getX() * ABWorld.BLOCK_SIZE();
                int bY = ABWorld.blockList.getBlock(x, y).getY() * ABWorld.BLOCK_SIZE();
				/*
				 * For loop because in case the blocks are smaller than the players width, so the player doesn't fall
				 * through the blocks
				 */
                for (int x2 = 0; x2 < eW / 4; x2++) {
                    boolean one = eX + (x2 * 4) >= bX
                            && eX + (x2 * 4) <= bX + bDimensions
                            && eY + eH + 10 >= bY
                            && eY + eH <= bY + 7;

                    if (one) {
                        if (ABWorld.blockList.getBlock(x, y).isSolid()) {
                            e.setY(bY - 1 - eH);
                            e.doEffectFromBlock(ABWorld.blockList.getBlock(x, y).getType());
                            ABWorld.blockEffectX = x;
                            ABWorld.blockEffectY = y;
                            return true;
                        }
                    }

                }
                }catch(NullPointerException ex){}
            }
        }
        return false;
    }

    public static boolean isSolidLeft(ABEntity e) {
//        return CollidedWith(a, b)
//                && isWithinMaxThreshold(a.getX() + a.getWidth(), b.getX());
        int eX = (int) e.getX();
        int eY = (int) e.getY();
        int bDimensions = ABWorld.BLOCK_SIZE();

        int startX = (int) Math.round(e.getX() / ABWorld.BLOCK_SIZE()) - 2;
        if (startX < 0) {
            startX = 0;
        }
        int durationX = startX + 4;
        while (durationX + startX >= ABWorld.WorldArrayWidth) {
            durationX--;
        }

        int startY = (int) Math.round(e.getY() / ABWorld.BLOCK_SIZE()) - 2;
        if (startY < 0) {
            startY = 0;
        }
        int durationY = startY + 4;
        while (durationY + startY >= ABWorld.WorldArrayHeight) {
            durationY--;
        }
        for (int sX = startX; sX < startX + durationX + 1; sX++) {
            for (int y = startY; y < startY + durationY; y++) {
                int x;
                if (sX >= 1) {
                    x = sX - 1;
                } else {
                    x = sX;
                }
                try{
                	int bX = ABWorld.blockList.getBlock(x, y).getX() * ABWorld.BLOCK_SIZE();
                	int bY = ABWorld.blockList.getBlock(x, y).getY() * ABWorld.BLOCK_SIZE();

                	for (int y2 = 0; y2 <= e.getHeight() / 4; y2++) {
                		boolean one = eX <= bX + bDimensions + 3
                				&& eX >= bX + bDimensions - 3
                				&& eY - 1 + (4 * y2) >= bY
                				&& eY - 1 + (4 * y2) <= bY + bDimensions;
                		if (one) {
                			if (ABWorld.blockList.getBlock(x, y).isSolid()) {
                				e.setX(x * ABWorld.BLOCK_SIZE() + ABWorld.BLOCK_SIZE() + 2);
                				return true;
                			}
                		}
                	}
                }catch(NullPointerException ex){}
            }
        }
        return false;
    }

    public static boolean isSolidRight(ABEntity e) {
//        return CollidedWith(a, b)
//                && isWithinMaxThreshold(a.getX(), b.getX() + b.getWidth());
        int eX = (int) e.getX();
        int eY = (int) e.getY();
        float eW = e.getWidth();
        int bDimensions = ABWorld.BLOCK_SIZE();

        int startX = (int) Math.round(e.getX() / ABWorld.BLOCK_SIZE()) - 2;
        if (startX < 0) {
            startX = 0;
        }
        int durationX = startX + 4;
        while (durationX + startX >= ABWorld.WorldArrayWidth) {
            durationX--;
        }

        int startY = (int) Math.round(e.getY() / ABWorld.BLOCK_SIZE()) - 2;
        if (startY < 0) {
            startY = 0;
        }
        int durationY = startY + 4;
        while (durationY + startY >= ABWorld.WorldArrayHeight) {
            durationY--;
        }
        for (int sX = startX; sX < startX + durationX + 1; sX++) {
            for (int y = startY; y < startY + durationY; y++) {
                int x;
                if (sX >= 1) {
                    x = sX - 1;
                } else {
                    x = sX;
                }
                try{
                	int bX = ABWorld.blockList.getBlock(x, y).getX() * ABWorld.BLOCK_SIZE();
                	int bY = ABWorld.blockList.getBlock(x, y).getY() * ABWorld.BLOCK_SIZE();

                	for (int y2 = 0; y2 <= e.getHeight() / 4; y2++) {
                		boolean one = eX + eW <= bX + 3
                				&& eX + eW >= bX - 3
                				&& eY - 1 + (4 * y2) >= bY
                				&& eY - 1 + (4 * y2) <= bY + bDimensions;
                		if (one) {
                			if (ABWorld.blockList.getBlock(x, y).isSolid()) {
                				return true;
                			}
                		}
                	}
                }catch(NullPointerException ex){}
            }
        }
        return false;
    }
}
