package com.gmail.robmadeyou;

import com.gmail.robmadeyou.Effects.ABAnimate;
import com.gmail.robmadeyou.Effects.ABEmitter;
import com.gmail.robmadeyou.Entity.ABEntity;
import com.gmail.robmadeyou.Input.ABKeyboard;
import com.gmail.robmadeyou.Input.ABKeyboard.ABKey;
import com.gmail.robmadeyou.Object.ABItem;
import com.gmail.robmadeyou.Quest.Quest;
import com.gmail.robmadeyou.World.ABCamera;
import com.gmail.robmadeyou.World.ABWorld;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;
import java.util.Random;

public class Abereth {

    public static Quest questList[];
    public static Quest openQuests[];

    static ArrayList<ABEmitter> Emitters = new ArrayList<ABEmitter>();

    public static ABEmitter addNewEmitter(ABEmitter e) {
        Emitters.add(e);
        return Emitters.get(Emitters.size() - 1);
    }
    public static void removeEmitter(ABEmitter e){
    	Emitters.remove(e);
    }

    public static void updateAllEmitters() {
    	try{
    		for (ABEmitter emitter : Emitters) {
        		emitter.onUpdate();
        	}
    	}catch(ConcurrentModificationException e){}
    }

    public static void update(int delta) {
        for(int i = cameraList.size() - 1; i >= 0; i--){
        	ABCamera c = cameraList.get(i);
        	if(ABScreen.worldCreated) {
                ABWorld.onUpdate(c);
            }
        }
        updateAllEmitters();
        updateAllItems();
        updateAllEntities(delta);
        
        if(ABKeyboard.isKeyPressed(DevModeKey)) {
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
    public static ArrayList<ABEntity> entityList = new ArrayList<ABEntity>();
    public static ArrayList<ABEntity> onScreenEntity = new ArrayList<ABEntity>();

    public static ABEntity addEntity(ABEntity e) {
        entityList.add(e);
        Random ran = new Random();
        int id = ran.nextInt(1024);
        boolean hasIDSet = false;
        while (!hasIDSet) {
            for (ABEntity anEntityList : entityList) {
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
    	
    	for (ABEntity anEntityList : entityList) {
            if(anEntityList.isOnScreen()){
            	onScreenEntity.add(anEntityList);
            }
        }
        for (ABEntity anEntityList : entityList) {
            anEntityList.onUpdate(delta);
            anEntityList.draw();
        }
    }

    public static ABEntity getEntityByNumber(int number) {
        for (ABEntity anEntityList : entityList) {
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
    public static ArrayList<ABAnimate> animID = new ArrayList<ABAnimate>();

    public static ABAnimate createAnimation(ABAnimate animation) {
        animID.add(animation);
        return animID.get(animID.size() - 1);
    }

    /*
     *
     * CODE FOR ITEMS
     *
     *
     */
    public static ArrayList<ABItem> itemList = new ArrayList<ABItem>();
    public static ArrayList<ABItem> VisibleItemList = new ArrayList<ABItem>();

    public static ABItem addNewItem(ABItem i){
        itemList.add(i);
        return i;
    }
    public static void removeItem(ABItem i){
    	itemList.remove(i);
    }

    public static void updateAllItems(){
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
    
    public static ArrayList<ABCamera> cameraList = new ArrayList<ABCamera>();
    
    public static ABCamera addNewCamera(ABCamera c){
    	if(!hasAddedNewCamera){
    		hasAddedNewCamera = true;
    		cameraList.clear();
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
    
    public static ABKey DevModeKey = ABKeyboard.ABKey.L;

}
