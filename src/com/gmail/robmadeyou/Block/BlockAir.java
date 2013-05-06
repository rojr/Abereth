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
import com.gmail.robmadeyou.World.World;

public class BlockAir implements Block {

	private int x, y;
	private int id = 0;
	private boolean isSolid = false;
	private Texture texture;
	public BlockAir(int x, int y){
		this.x = x;
		this.y = y;
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
		glPushMatrix();
		glTranslated(Screen.translate_x, Screen.translate_y, 0);
		Color.Blue.bind();
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

	public Texture getTexture() {
		return texture;
	}
	
	public void setTexture(Texture tex) {
		this.texture = tex;
	}
}
