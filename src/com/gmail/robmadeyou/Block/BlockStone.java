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
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.Textures;
import com.gmail.robmadeyou.World.World;

public class BlockStone implements Block {

	private int x, y;
	private int id = 1;
	private boolean isSolid = true;
	private Texture texture = Textures.test;
	public BlockStone(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	public int getID() {
		return id;
	}

	public boolean isSolid() {
		return isSolid;
	}

	public Texture getTexture() {
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

	public void setTexture(Texture tex) {
		this.texture = tex;
	}

	public void onUpdate() {
		draw();
	}

	public void draw() {
		if(texture != null){
			texture.bind();
		}
		glPushMatrix();
		glTranslated(Screen.translate_x, Screen.translate_y, 0);
		Color.White.bind();
		glBegin(GL_QUADS);
			glTexCoord2d(0, 0);
			glVertex2d(x * World.BLOCK_SIZE() , y * World.BLOCK_SIZE());
			glTexCoord2d(1, 0);
			glVertex2d(x * World.BLOCK_SIZE() + World.BLOCK_SIZE(), y * World.BLOCK_SIZE());
			glTexCoord2d(1, 1);
			glVertex2d(x * World.BLOCK_SIZE() + World.BLOCK_SIZE(), y * World.BLOCK_SIZE() + World.BLOCK_SIZE());
			glTexCoord2d(0, 1);
			glVertex2d(x * World.BLOCK_SIZE() , y * World.BLOCK_SIZE() + World.BLOCK_SIZE());
		glEnd();
	
		glPopMatrix();
	}

}
