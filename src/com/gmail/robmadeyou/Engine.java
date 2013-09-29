package com.gmail.robmadeyou;

import com.gmail.robmadeyou.Effects.Animate;
import com.gmail.robmadeyou.Effects.Emitter;
import com.gmail.robmadeyou.Entity.Entity;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;
import com.gmail.robmadeyou.Object.Item;
import com.gmail.robmadeyou.Quest.Quest;
import com.gmail.robmadeyou.World.Camera;
import com.gmail.robmadeyou.World.World;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

public class Engine {

    public static Quest questList[];
    public static Quest openQuests[];

    static ArrayList<Emitter> Emitters = new ArrayList<Emitter>();

    public static Emitter addNewEmitter(Emitter e) {
        Emitters.add(e);
        return Emitters.get(Emitters.size() - 1);
    }
    public static void removeEmitter(Emitter e){
    	Emitters.remove(e);
    }

    public static void updateAllEmitters(int delta, Camera cam) {
    	try{
    		for (Emitter emitter : Emitters) {
        		emitter.onUpdate(delta, cam);
        	}
    	}catch(ConcurrentModificationException e){}
    }

    public static void update(int delta) {
        for(int i = cameraList.size() - 1; i >= 0; i--){
        	Camera c = cameraList.get(i);
        	updateAllEmitters(delta, c);
        	if(Screen.worldCreated) {
                World.onUpdate(c);
            }
        	updateAllItems(c);
        }
        
        updateAllEntities(delta);
        
        if(Keyboard.isKeyPressed(DevModeKey)) {
            isDevMode = !isDevMode;
        }
    }

    /*
     *
     *
     * 	CODE FOR ADDING ENTITIES
     *
     *
     *
     */
    public static ArrayList<Entity> entityList = new ArrayList<Entity>();
    public static ArrayList<Entity> onScreenEntity = new ArrayList<Entity>();

    public static Entity addEntity(Entity e) {
        entityList.add(e);
        Random ran = new Random();
        int id = ran.nextInt(1024);
        boolean hasIDSet = false;
        while (!hasIDSet) {
            for (Entity anEntityList : entityList) {
                if (anEntityList.getNumber() == id) {
                    id = ran.nextInt(2048);
                    break;
                }
            }
            entityList.get(entityList.size() - 1).setNumber(id);
            hasIDSet = true;
        }
        return e;
    }

    public static void updateAllEntities(int delta) {
    	onScreenEntity.clear();
    	
    	for (Entity anEntityList : entityList) {
            if(anEntityList.isOnScreen()){
            	onScreenEntity.add(anEntityList);
            }
        }
        for (Entity anEntityList : entityList) {
            anEntityList.onUpdate(delta);
            anEntityList.draw();
        }
    }

    public static Entity getEntityByNumber(int number) {
        for (Entity anEntityList : entityList) {
            if (anEntityList.getNumber() == number) {
                return anEntityList.getType();
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

    public static Animate createAnimation(Animate animation) {
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
    public static void removeItem(Item i){
    	itemList.remove(i);
    }

    public static void updateAllItems(Camera cam){
        VisibleItemList.clear();
        for (int i = 0; i < itemList.size(); i++){
            itemList.get(i).onUpdate();
            itemList.get(i).draw();
            if(itemList.get(i).isVisible()){
            	VisibleItemList.add(itemList.get(i));
            }
        }
    }
    
    /*
     * 
     * Multiple camera stuff here
     * 
     */
    static boolean hasAddedNewCamera = false;
    
    public static ArrayList<Camera> cameraList = new ArrayList<Camera>();
    
    public static Camera addNewCamera(Camera c){
    	if(!hasAddedNewCamera){
    		hasAddedNewCamera = true;
    		cameraList.add(c);
    		return c;
    	}else{
    		cameraList.add(c);
    		return c;
    	}
    }
    
    public static void updateAllCameras(){
    	for(int i = 0; i < cameraList.size(); i++){
    		cameraList.get(i).onUpdate();
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
    
    public static Key DevModeKey = Keyboard.Key.L;

}
