package com.gmail.robmadeyou.Interfaces;

import com.gmail.robmadeyou.Block.Block;
import com.gmail.robmadeyou.Entity.Entity;

/**
 * Created by Mrgadgetz
 * Date: 8/9/13
 * Time: 11:48 PM
 */
public interface BaseActor {
    public boolean isNear(Entity other);
    public void doEffectFromBlock(Block type);
    public void removeEffectFromBlock(Block type);
    public void onUpdate(int delta);
    public void draw();
}
