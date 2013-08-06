package com.gmail.robmadeyou.Block;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2d;

import org.newdawn.slick.opengl.Texture;

import com.gmail.robmadeyou.Screen;
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
		Collector.add(new DrawParameters("box", x * World.BLOCK_SIZE(), y * World.BLOCK_SIZE(), World.BLOCK_SIZE(), World.BLOCK_SIZE(), texture, color, 1, true));
	}
	
	public void doEffect(Entity e){
		//Stone does no effect
	}
	public void removeEffect(Entity e){
		//Stone does no effect
	}
}
