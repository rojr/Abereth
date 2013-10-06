package com.gmail.robmadeyou.Block;

import com.gmail.robmadeyou.Entity.ABEntity;

public interface ABBlock {
    int getID();

    public void consider();

    boolean isSolid();

    int getTexture();

    void setX(int x);

    void setY(int y);

    int getX();

    int getY();
    
    //boolean wasUpdated();

    void setTexture(int tex);

    void onUpdate();

    ABBlock getType();

    void draw();

    void doEffect(ABEntity e);

    void removeEffect(ABEntity e);

    public double getG_Score();

    public void setG_Score(Double score);

    public double get_hscore();

    public void setHscore(Double score);

    public ABBlock getParent();

    public void setParent(ABBlock block);

    public void visit();

    public boolean isVisited();

    public boolean isConsidered();

    public boolean isWalkable();
}
