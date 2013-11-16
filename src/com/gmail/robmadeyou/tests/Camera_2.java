package com.gmail.robmadeyou.tests;
import com.gmail.robmadeyou.Abereth;
import com.gmail.robmadeyou.ABLayer;
import com.gmail.robmadeyou.ABScreen;
import com.gmail.robmadeyou.ABScreen.GameType;
import com.gmail.robmadeyou.ABTarget;
import com.gmail.robmadeyou.draw.ABCollector;
import com.gmail.robmadeyou.draw.ABLine;
import com.gmail.robmadeyou.draw.ABCollector.DrawParameters;
import com.gmail.robmadeyou.effects.*;
import com.gmail.robmadeyou.entity.ABNpc;
import com.gmail.robmadeyou.entity.ABPlayer;
import com.gmail.robmadeyou.entity.ABPlayer.MovementType;
import com.gmail.robmadeyou.gui.ABButton;
import com.gmail.robmadeyou.gui.ABGui;
import com.gmail.robmadeyou.gui.ABText;
import com.gmail.robmadeyou.gui.ABUI;
import com.gmail.robmadeyou.object.ABItem;
import com.gmail.robmadeyou.peripherals.ABKeyboard;
import com.gmail.robmadeyou.peripherals.ABMouse;
import com.gmail.robmadeyou.peripherals.ABKeyboard.ABKey;
import com.gmail.robmadeyou.world.ABCamera;

import java.util.ArrayList;

import javax.swing.JOptionPane;

public class Camera_2 {
    public static void main(String[] args) {
        ABScreen.create(800, 738, "Our Screen", GameType.SIDE_SCROLLER, false);
        ABScreen.setWorldDimensionsInBlocks(100,100);
        
        ABScreen.setUpWorld();
        
        ABPlayer player2 = (ABPlayer) Abereth.addEntity(new ABPlayer(32, 32, 32, 32));
        player2.setFixedMovementType(MovementType.WASD_KEYS);
        player2.setSpeed(3);
        ABPlayer player3 = (ABPlayer) Abereth.addEntity(new ABPlayer(100, 32, 32, 32));
        player3.setFixedMovementType(MovementType.ARROW_KEYS);
        player3.setSpeed(3);
        ABNpc enemy = new ABNpc(32, 40, 320, 32);
        enemy.setLogic(true);
        enemy.setTargetPlayer(player2);
        Abereth.addEntity(enemy);

        //Npc enemy2 = new Npc(20, 40, 32, 32);
        //Engine.addEntity(enemy2);
        ABItem item = Abereth.addNewItem(new ABItem(60, 40, 16, 16));

        //MessageArea.setUp(0, 400, 800, 200);
        //MessageArea.setUpTextStart(20, 420);

        ArrayList<Integer> listOfTextures = new ArrayList<Integer>();
        listOfTextures.add(ABTextureLoader.createTexture("res/sheet01.png", 192, 64, 32, 32));
        listOfTextures.add(ABTextureLoader.createTexture("res/sheet01.png", 224, 64, 32, 32));
        ABAnimate animTest = new ABAnimate(listOfTextures, 20, 0, true);
        ABLayer.addLayer(3);
        
     
        
        ABCamera cam = Abereth.addNewCamera(new ABCamera(0, 0, 0, 0, ABScreen.getWidth() / 2, ABScreen.getHeight()));
        cam.setFollowingTarget(true);
        cam.setTarget(new ABTarget(player2));
        
        ABCamera cam2 = Abereth.addNewCamera(new ABCamera(ABScreen.getWidth() / 2, 0, 0, 0, ABScreen.getWidth() / 2, ABScreen.getHeight()));
        cam2.setFollowingTarget(true);
        cam2.setTarget(new ABTarget(player3));
        
        boolean camsCreated = false;
        
        while (!ABScreen.isAskedToClose()) {
            //Updating the screen. the maximum frame rate is 60.
        	
            ABScreen.update(60);
            enemy.setColor(ABColor.White);
            enemy.setTexture(ABTextures.test);
            if(ABKeyboard.isKeyDown(ABKeyboard.ABKey.A)){
            	animTest.setInvert(true);
            }else{
            	animTest.setInvert(false);
            }
            player2.setTexture(animTest.getTextureID());
            
            if(item.isPressed()){
            	Abereth.removeItem(item);
            }
            if (ABKeyboard.isKeyPressed(ABKey.T)) {
                enemy.setAStar(!enemy.isAStarActive());
            }

            if (Abereth.isDevMode) {
                ABText.drawString(ABScreen.actualFps + "  Hel^4lo!awq^3awd^rawdaw^9da^5wda^ndwadwd",(float) ABMouse.getTranslatedX() + 10, ABMouse.getTranslatedY(), ABLayer.GUILayer(), 1, 1, ABColor.Banana, true, false);
            }

            //Refreshing the screen
            DrawParameters p = new DrawParameters("line", ABScreen.getWidth() / 2, 0, ABScreen.getWidth() / 2, ABScreen.getHeight());
            p.setLayer(ABLayer.GUILayer());
            ABCollector.add(p);
            ABScreen.refresh();
        }
        ABScreen.destroy();
    }
}
