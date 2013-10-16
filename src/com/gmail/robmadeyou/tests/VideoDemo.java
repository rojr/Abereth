package com.gmail.robmadeyou.tests;

import com.gmail.robmadeyou.Abereth;
import com.gmail.robmadeyou.ABLayer;
import com.gmail.robmadeyou.ABScreen;
import com.gmail.robmadeyou.ABScreen.GameType;
import com.gmail.robmadeyou.ABTarget;
import com.gmail.robmadeyou.draw.ABCollector;
import com.gmail.robmadeyou.effects.*;
import com.gmail.robmadeyou.entity.ABNpc;
import com.gmail.robmadeyou.entity.ABPlayer;
import com.gmail.robmadeyou.entity.ABPlayer.MovementType;
import com.gmail.robmadeyou.gui.ABText;
import com.gmail.robmadeyou.object.ABItem;
import com.gmail.robmadeyou.peripherals.ABKeyboard;
import com.gmail.robmadeyou.peripherals.ABMouse;
import com.gmail.robmadeyou.peripherals.ABKeyboard.ABKey;
import com.gmail.robmadeyou.world.ABCamera;

import java.util.ArrayList;


public class VideoDemo {
    
	public static void main(String[] args) {

        ABScreen.create(800, 800, "Our Screen", GameType.RPG_STYLE, false);
        ABScreen.setWorldDimensionsInBlocks(50, 50);
        ABScreen.setUpWorld();
        
        ABPlayer player2 = (ABPlayer) Abereth.addEntity(new ABPlayer(32, 32, 32, 32));
        player2.setFixedMovementType(MovementType.WASD_KEYS);
        player2.setSpeed(3);
        
        ABNpc enemy = new ABNpc(32, 40, 32, 32);
        enemy.setLogic(true);
        enemy.setTargetPlayer(player2);
        Abereth.addEntity(enemy);
        ArrayList<Integer> listOfTextures = new ArrayList<Integer>();
        listOfTextures.add(ABTextureLoader.createTexture("res/sheet01.png", 192, 64, 32, 32));
        listOfTextures.add(ABTextureLoader.createTexture("res/sheet01.png", 224, 64, 32, 32));
        ABLayer.addLayer(3);
        
        ABCamera cam = Abereth.addNewCamera(new ABCamera(0, 0, 0, 0, ABScreen.getWidth(), ABScreen.getHeight()));
        cam.setFollowingTarget(true);
        cam.setTarget(new ABTarget(player2));
        ABEmitter emit = new ABEmitter(player2.getX(), player2.getY(), 50);
        emit.setLayer(2);
        emit.setRepeats(false);
        Abereth.addNewEmitter(emit);
        
        while (!ABScreen.isAskedToClose()) {
            ABScreen.update(60);
            Abereth.get(player2);
            if (ABKeyboard.isKeyPressed(ABKey.T)) {
                enemy.setAStar(!enemy.isAStarActive());
            }
            	if(ABMouse.leftMouseButtonDown){
            		ABEmitter emit2 = new ABEmitter(ABMouse.getTranslatedX(), player2.getY(), 50);
            		emit.setLayer(2);
            		emit.setRepeats(false);
            		Abereth.addNewEmitter(emit2);
            }
            if (Abereth.isDevMode) {
                ABText.drawString(ABScreen.actualFps + "", (float) ABMouse.getTranslatedX() + 10, ABMouse.getTranslatedY(), ABLayer.GUILayer(), 1, 1, ABColor.Black, true, false);
            }
            if(ABMouse.leftMouseButtonPressed){
            	Abereth.addNewItem(new ABItem(ABMouse.getTranslatedX(), ABMouse.getTranslatedY(), 16, 16));
            }
            if(ABKeyboard.isKeyDown(ABKey.A)){
            	player2.setTextureInverts(true);
            }else if(ABKeyboard.isKeyDown(ABKey.D)){
            	player2.setTextureInverts(false);
            }
            ABScreen.refresh();
        }
        ABScreen.destroy();
    }
}

