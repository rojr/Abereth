package com.gmail.robmadeyou.Interfaces;

import com.gmail.robmadeyou.Block.ABBlock;
import com.gmail.robmadeyou.Entity.ABEntity;

/**
 * Created by Mrgadgetz
 * Date: 8/9/13
 * Time: 11:48 PM
 */
public interface ABBaseActor {
    public boolean isNear(ABEntity other);

    public void doEffectFromBlock(ABBlock type);

    public void removeEffectFromBlock(ABBlock type);

    public void onUpdate(int delta);

    public void draw();
}
