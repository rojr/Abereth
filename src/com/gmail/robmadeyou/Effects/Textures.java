package com.gmail.robmadeyou.Effects;

import java.io.IOException;
import java.util.ArrayList;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

public class Textures {
	
	public static boolean texSetUp = false;
	
	public static Texture none;
	
	static ArrayList<Tex> tex = new ArrayList<Tex>();
	
	public static void setUpTextures(){
		
		try {
			none = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("res/none.png"), true);
			texSetUp = true;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
