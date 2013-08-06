package com.gmail.robmadeyou.Block;

import com.gmail.robmadeyou.Entity.Entity;

public interface Block {
	int getID();
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
}
