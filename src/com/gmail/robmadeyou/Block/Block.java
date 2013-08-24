package com.gmail.robmadeyou.Block;

import com.gmail.robmadeyou.Entity.Entity;

public interface Block {
	int getID();
    public void consider();
	boolean isSolid();
	int getTexture();
	void setX(int x);
	void setY(int y);
	int getX();
	int getY();
	void setTexture(int tex);
	void onUpdate();
	Block getType();
	void draw();
	void doEffect(Entity e);
	void removeEffect(Entity e);
    public double getG_Score();
    public void setG_Score(Double score);
    public double get_hscore();
    public void setHscore(Double score);
    public Block getParent();
    public void setParent(Block block);
    public void visit();
    public boolean isVisited();
    public boolean isConsidered();
    public boolean isWalkable();
}
