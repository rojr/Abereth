package com.gmail.robmadeyou.Draw;

import java.util.ArrayList;

import com.gmail.robmadeyou.Layer;
import com.gmail.robmadeyou.Effects.Color;

public class Collector {
	
	static ArrayList<DrawParameters> drawArraySorted = new ArrayList<DrawParameters>();
	static ArrayList<DrawParameters> drawArrayUnsorted = new ArrayList<DrawParameters>();
	
	public static void add(DrawParameters p){
		drawArrayUnsorted.add(p);
	}
	public static void organize(){
		int currentLayer = 0;
		while(drawArrayUnsorted.size() != drawArraySorted.size()){
			for(int i = 0; i < drawArrayUnsorted.size(); i++){
				if(drawArrayUnsorted.get(i).getLayer() == currentLayer){
					drawArraySorted.add(drawArrayUnsorted.get(i));
				}
			}
			currentLayer++;
			if(currentLayer > Layer.layers){break;}
		}
	}
	public static void clear(){
		drawArraySorted.clear();
		drawArrayUnsorted.clear();
	}
	
	public static class DrawParameters{
		
		private double x,y,w,h;
		private int texID;
		private int layerID;
		private Color color;
		private String type;
		private boolean useTranslate;
		private float opacity;
		public DrawParameters(String type,double x, double y, double width, double height, 
				int texID, Color color, int layerID){
			this.type = type;
			this.x = x;
			this.y = y;
			this.w = width;
			this.h = height;
			this.texID = texID;
			this.color = color;
			this.layerID = layerID;
			this.useTranslate = false;
			this.opacity = 1f;
		}
		public DrawParameters(String type,double x, double y, double width, double height,
				int texID, Color color, int layerID, boolean useTranslate){
			this.type = type;
			this.x = x;
			this.y = y;
			this.w = width;
			this.h = height;
			this.texID = texID;
			this.color = color;
			this.layerID = layerID;
			this.useTranslate = useTranslate;
			this.opacity = 1f;
		}
		public DrawParameters(String type,double x, double y, double width, double height,
				int texID, Color color, float opacity, int layerID, boolean useTranslate){
			this.type = type;
			this.x = x;
			this.y = y;
			this.w = width;
			this.h = height;
			this.texID = texID;
			this.color = color;
			this.layerID = layerID;
			this.useTranslate = useTranslate;
			this.opacity = opacity;
		}
		public DrawParameters(String type,double x, double y, double width, double height,
				int texID, Color color, float opacity, int layerID){
			this.type = type;
			this.x = x;
			this.y = y;
			this.w = width;
			this.h = height;
			this.texID = texID;
			this.color = color;
			this.layerID = layerID;
			this.opacity = opacity;
		}
		
		public String getType(){
			return type;
		}
		public int getLayer(){
			return layerID;
		}
		public double getX(){
			return x;
		}
		public double getY(){
			return y;
		}
		public double getWidth(){
			return w;
		}
		public double getHeight(){
			return h;
		}
		public Color getColor(){
			return color;
		}
		public int getTextureID(){
			return texID;
		}
		public boolean useTranslate(){
			return useTranslate;
		}
		public float getOpacity(){
			return opacity;
		}
	}
}
