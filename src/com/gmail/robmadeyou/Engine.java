package com.gmail.robmadeyou;

import java.util.ArrayList;
import java.util.Random;

import com.gmail.robmadeyou.Effects.Animate;
import com.gmail.robmadeyou.Effects.Emitter;
import com.gmail.robmadeyou.Entity.Entity;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;
import com.gmail.robmadeyou.Item.Item;
import com.gmail.robmadeyou.Quest.Quest;

public class Engine {
	
	public static Quest questList[];
	public static Quest openQuests[];
	
	static ArrayList<Emitter> Emitters = new ArrayList<Emitter>();
	public static Emitter addNewEmitter(Emitter e){
		Emitters.add(e);
		return Emitters.get(Emitters.size() - 1);
	}
	public static void updateAllEmitters(int delta){
		for(int i = 0; i < Emitters.size(); i++){
			Emitters.get(i).onUpdate(delta);
		}
	}
	public static void update(int delta){
		updateAllEmitters(delta);
		updateAllEntities(delta);
		
		if(Keyboard.isKeyPressed(Key.L)){
			if(isDevMode){
				isDevMode = false;
			}else{
				isDevMode = true;
			}
		}
	}
	/*
	 * 
	 * 
	 * 	CODE FOR ADDING ENITITES
	 * 
	 * 
	 * 
	 */
	public static ArrayList<Entity> entityList = new ArrayList<Entity>();
	public static ArrayList<Entity> onScreenEntity = new ArrayList<Entity>();
	
	public static Entity addEntity(Entity e){
		entityList.add(e);
		Random ran = new Random();
		int id = ran.nextInt(1024);
		boolean hasIDSet = false;
		while(!hasIDSet){
			for(int i = 0; i < entityList.size(); i++){
				if(entityList.get(i).getNumber() == id){
					id = ran.nextInt(1024);
					break;
				}
			}
			entityList.get(entityList.size() - 1).setNumber(id);
			hasIDSet = true;
		}
		return e;
	}
	public static void updateAllEntities(int delta){
		
		for(int i = 0; i < entityList.size(); i++){
			entityList.get(i).onUpdate(delta);
		}
		onScreenEntity.clear();
		for(int i = entityList.size() -1; i >= 0; i--){
			double eX = entityList.get(i).getX();
			double eY = entityList.get(i).getY();
			int eW = entityList.get(i).getWidth();
			int eH = entityList.get(i).getHeight();
			boolean one = eX >= -Screen.translate_x && eX <= -Screen.translate_x + Screen.getWidth() &&
					eY >= -Screen.translate_y && eY <= -Screen.translate_y + Screen.getHeight();
			boolean two = eX + eW >= -Screen.translate_x && eX + eW<= -Screen.translate_x + Screen.getWidth() &&
					eY >= -Screen.translate_y && eY <= -Screen.translate_y + Screen.getHeight();
			boolean three = eX + eW >= -Screen.translate_x && eX + eW <= -Screen.translate_x + Screen.getWidth() &&
					eY + eH >= -Screen.translate_y && eY + eH <= -Screen.translate_y + Screen.getHeight();
			boolean four = eX >= -Screen.translate_x && eX <= -Screen.translate_x + Screen.getWidth() &&
					eY + eH >= -Screen.translate_y && eY + eH <= -Screen.translate_y + Screen.getHeight();
			if(one || two || three || four){
				onScreenEntity.add(entityList.get(i));
				entityList.get(i).draw();
			}
		}
	}
	public static Entity getEntityByNumber(int number){
		for(int i = 0; i < entityList.size(); i++){
			if(entityList.get(i).getNumber() == number){
				return entityList.get(i).getType();
			}
		}
		return null;
	}
	
	/*
	 * 
	 * CODE FOR ANIMATION
	 * 
	 * 
	 */
	public static ArrayList<Animate> animID = new ArrayList<Animate>();
	
	public static Animate createAnimation(Animate animation){
		animID.add(animation);
		return animID.get(animID.size() - 1); 
	}
	
	/*
	 * 
	 * CODE FOR ITEMS
	 * 
	 * 
	 */
	public static ArrayList<Item> itemList = new ArrayList<Item>();
	public static ArrayList<Item> VisibleItemList = new ArrayList<Item>();
	
	public static Item addNewItem(Item i){
		itemList.add(i);
		return i;
	}
	
	public static void updateItems(){
		
		VisibleItemList.clear();
		for(int i = 0; i < itemList.size(); i++){
			double eX = itemList.get(i).getX();
			double eY = itemList.get(i).getY();
			int eW = itemList.get(i).getWidth();
			int eH = itemList.get(i).getHeight();
			boolean one = eX >= -Screen.translate_x && eX <= -Screen.translate_x + Screen.getWidth() &&
						eY >= -Screen.translate_y && eY <= -Screen.translate_y + Screen.getHeight();
			boolean two = eX + eW >= -Screen.translate_x && eX + eW<= -Screen.translate_x + Screen.getWidth() &&
						eY >= -Screen.translate_y && eY <= -Screen.translate_y + Screen.getHeight();
			boolean three = eX + eW >= -Screen.translate_x && eX + eW <= -Screen.translate_x + Screen.getWidth() &&
						eY + eH >= -Screen.translate_y && eY + eH <= -Screen.translate_y + Screen.getHeight();
			boolean four = eX >= -Screen.translate_x && eX <= -Screen.translate_x + Screen.getWidth() &&
						eY + eH >= -Screen.translate_y && eY + eH <= -Screen.translate_y + Screen.getHeight();
			if(one || two || three || four){
				VisibleItemList.add(itemList.get(i));
				itemList.get(i).onUpdate();
				itemList.get(i).draw();
			}
		}
	}
	
	
	/*
	 * 
	 * Various small handlers
	 * 
	 * 
	 */
	public static boolean isDevMode = false;
	
	public static boolean islmbpThisTick = false;
	public static boolean isrmbpThisTick = false;
	
}
