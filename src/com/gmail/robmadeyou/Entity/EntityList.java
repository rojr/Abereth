package com.gmail.robmadeyou.Entity;

import java.util.ArrayList;

public class EntityList {
	
	static ArrayList<Entity> entityList = new ArrayList<Entity>();
	
	public static void addEntity(Entity e){
		entityList.add(e);
		entityList.get(entityList.size() - 1).setNumber(entityList.size() - 1);
	}
	public static void updateAllEntities(int delta){
		for(int i = 0; i < entityList.size(); i++){
			entityList.get(i).onUpdate(delta);
		}
	}
}
