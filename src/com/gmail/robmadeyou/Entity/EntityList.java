package com.gmail.robmadeyou.Entity;

import java.util.ArrayList;
import java.util.Random;

public class EntityList {
	
	public static ArrayList<Entity> entityList = new ArrayList<Entity>();
	
	public static void addEntity(Entity e){
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
	}
	public static void updateAllEntities(int delta){
		for(int i = 0; i < entityList.size(); i++){
			entityList.get(i).onUpdate(delta);
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
}
