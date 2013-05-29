package com.gmail.robmadeyou.Block;

import org.newdawn.slick.opengl.Texture;

import com.gmail.robmadeyou.Entity.Entity;

public interface Block {
	int getID();
	boolean isSolid();
	Texture getTexture();
	void setX(int x); 
	void setY(int y);
	int getX();
	int getY();
	void setTexture(Texture tex);
	void onUpdate();
	Block getType();
	void draw();
	void doEffect(Entity e);
	void removeEffect(Entity e);
}
