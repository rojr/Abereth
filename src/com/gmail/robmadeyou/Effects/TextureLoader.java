package com.gmail.robmadeyou.Effects;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;
import java.util.ArrayList;

public class TextureLoader{
	public static ArrayList<TexInfo> TextureInfo = new ArrayList<TexInfo>();
	
	public static int createTexture(String location, int startX, int startY, int width, int height){
		TextureInfo.add(new TexInfo(location, startX, startY, width, height));
		return TextureInfo.size() - 1;
	}
	
	public static int createTexture(String location){
		TextureInfo.add(new TexInfo(location, 0, 0, 0, 0));
		return TextureInfo.size() - 1;
	}
	
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
				this.yPercent = (double) 1 -  yPercent;
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
	}
}
