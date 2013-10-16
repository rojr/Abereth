package com.gmail.robmadeyou.interfaces;

import com.gmail.robmadeyou.block.ABBlock;
import com.gmail.robmadeyou.entity.ABEntity;

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
