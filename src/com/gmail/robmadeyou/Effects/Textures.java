package com.gmail.robmadeyou.Effects;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Textures {
	
	public static boolean texSetUp = false;
	
	public static Texture none;
	public static Texture test;
	public static Texture player_default;
	public static Texture Block_Sky;
	public static Texture Block_Stone;
	
	static ArrayList<Tex> tex = new ArrayList<Tex>();
	
	public static void setUpTextures(){
		
		try {
			none = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/none.png"), true);
			
			test = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/powerUp/PaddleFreeze.png"), true);
			Block_Sky = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/World/Sky.png"), true);
			Block_Stone = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/World/Stone.png"), true);
			
			texSetUp = true;
		} catch (IOException e) {e.printStackTrace();}
	}
	public static void addTexture(){
		
	}
	class Tex{
		
		String location;
		String name;
		public Tex(String location, Texture tex){
			
		}
	}
}
