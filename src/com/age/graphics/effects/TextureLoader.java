package com.age.graphics.effects;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class TextureLoader{

	public static ArrayList<TexInfo> TextureInfo = new ArrayList<TexInfo>();

    /**
     *
     * @param location
     * @param startX
     * @param startY
     * @param width
     * @param height
     * @return
     */
	public static int createTexture(String location, int startX, int startY, int width, int height){
        for(int j = 0; j < TextureInfo.size(); j++){
            TexInfo i = TextureInfo.get(j);
            if(location.equalsIgnoreCase(i.getLocation()) && startX == i.getX() && startY == i.getY() && width == i.getWidth() && height == i.getHeight() && false){
                return j;
            }
        }
        //Because if the loop returned something, nothing under it would be run.
        TextureInfo.add(new TexInfo(location, startX, startY, width, height));
		return TextureInfo.size() - 1;
    }

    /**
     * Textures are identified by there index in the ArrayList.
     * Newly created textures are given an integer id of
     * the arraylist.size - 1
     *
     * @param location
     * @return
     *          integer value representing the created texture
     */
	public static int createTexture(String location){
		//TextureInfo.add(new TexInfo(location, 0, 0, 0, 0));
        createTexture(location,0,0,0,0);
		return TextureInfo.size() - 1;
	}

    /**
     *
     * @param texID
     * @return
     *          the width of the texture with id texID
     */
	public static int getTextureWidth(int texID){
		return TextureInfo.get(texID).getWidth();
	}

    /**
     *
     * @param texID
     * @return
     *          the height of the texture with id texID
     */
	public static int getTextureHeight(int texID){
		return TextureInfo.get(texID).getHeight();
	}

    /**
     * Attributes for texture objects
     * width, heigth, location, x and y coordinates
     */
	public static class TexInfo{
		private int x, y,width,height;
		private double xPercent, yPercent, widthPercent, heightPercent;
		private String location;
		private Texture tex;
		private int texWidth, texHeight;
		public TexInfo(String location, int x, int y, int width, int height){
			this.width = width;
			this.height = height;
			this.location = location;
			try{
				this.tex = org.newdawn.slick.opengl.TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream(location), true);
			} catch (IOException e) {e.printStackTrace();}
			//TODO Even thought this works... I should re-write how textures are loaded and implement
			//png decoders, jpg decoders and so on, to be able to load many different file formats, but that later.
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			this.texWidth = tex.getImageWidth();
			this.texHeight = tex.getImageHeight();
			this.x = x;
			this.y = y;
			if(width == 0 || height == 0){
				this.xPercent = 0F;
				this.yPercent = 0F;
				this.heightPercent = 1F;
				this.widthPercent = 1F;
			}else{
				this.xPercent = (double) x / (double) texWidth;
				this.yPercent = (double) y / (double) texHeight;
				this.yPercent = (double) 1 - (double) yPercent;
				this.widthPercent = (double) width / (double)texWidth;
				this.heightPercent = (double) height / (double)texHeight;
			}
		}
		public int getX(){
			return x;
		}
		public int getY(){
			return y;
		}
		public int getWidth(){
			return width;
		}
		public int getHeight(){
			return height;
		}
		public double getXPercent(){
			return xPercent;
		}
		public double getYPercent(){
			return yPercent;
		}
		public double getWidthPercent(){
			return widthPercent;
		}
		public double getHeightPercent(){
			return heightPercent;
		}
		public Texture getTexture(){
			return tex;
		}
		public String getLocation(){
			return location;
		}
	}
}
