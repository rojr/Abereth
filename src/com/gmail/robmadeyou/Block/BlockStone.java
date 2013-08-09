package com.gmail.robmadeyou.Block;

import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.Textures;
import com.gmail.robmadeyou.Entity.Entity;
import com.gmail.robmadeyou.World.World;

public class BlockStone implements Block {

	private int x, y;
	private int id = 1;
	private boolean isSolid = true;
	private int texture = Textures.Block_Stone;
	private Color color;
	public BlockStone(int x, int y){
		this.x = x;
		this.y = y;
		this.color = Color.White;
	}
	
	public int getID() {
		return id;
	}

	public boolean isSolid() {
		return isSolid;
	}

	public int getTexture() {
		return texture;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setTexture(int tex) {
		this.texture = tex;
	}

	public void onUpdate() {
		draw();
	}
	public Block getType(){
		return this;
	}
	public void draw() {
		Collector.add(new DrawParameters("box", x * World.BLOCK_SIZE(), y * World.BLOCK_SIZE(), World.BLOCK_SIZE(), World.BLOCK_SIZE(), texture, color, 0, true));
	}
	
	public void doEffect(Entity e){
		//Stone does no effect
	}
	public void removeEffect(Entity e){
		//Stone does no effect
	}
}
