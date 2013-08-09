package com.gmail.robmadeyou.Block;

import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.Textures;
import com.gmail.robmadeyou.Entity.Entity;
import com.gmail.robmadeyou.World.World;

public class BlockAir implements Block {

	private int x, y;
	private int id = 0;
	private boolean isSolid = false;
	private int texture;
	private Color color;
	public BlockAir(int x, int y){
		this.x = x;
		this.y = y;
		this.texture = Textures.Block_Sky;
		this.color = Color.White;
	}
	
	public int getID() {
		return id;
	}

	public boolean isSolid() {
		return isSolid;
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

	public void onUpdate() {
		draw();
	}
	public void draw() {
		Collector.add(new DrawParameters("box", x * World.BLOCK_SIZE(), y * World.BLOCK_SIZE(), World.BLOCK_SIZE(), World.BLOCK_SIZE(), texture, color, 0, true));
	}
	public void doEffect(Entity e){
		//Air gives no effect. How sad :(
	}
	public void removeEffect(Entity e){
		//Air gives no effect!
	}

	public int getTexture() {
		return texture;
	}
	
	public void setTexture(int tex) {
		this.texture = tex;
	}
	
	public Block getType() {
		return this;
	}
}
