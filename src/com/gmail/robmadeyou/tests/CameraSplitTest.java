package com.gmail.robmadeyou.tests;

import com.gmail.robmadeyou.ABScreen;
import com.gmail.robmadeyou.ABTarget;
import com.gmail.robmadeyou.Abereth;
import com.gmail.robmadeyou.ABScreen.GameType;
import com.gmail.robmadeyou.effects.ABColor;
import com.gmail.robmadeyou.entity.ABPlayer;
import com.gmail.robmadeyou.entity.ABPlayer.MovementType;
import com.gmail.robmadeyou.peripherals.ABKeyboard;
import com.gmail.robmadeyou.peripherals.ABKeyboard.ABKey;
import com.gmail.robmadeyou.world.ABCamera;

public class CameraSplitTest {
	public static void main(String args[]){
		ABScreen.create(800, 512, "aaa", GameType.SIDE_SCROLLER, false);
		
		ABCamera cam = Abereth.addNewCamera(new ABCamera(0,0,0,0,ABScreen.getWidth(), ABScreen.getHeight()));
		ABScreen.setUpWorld();
		ABPlayer player = (ABPlayer) Abereth.addEntity(new ABPlayer(200, 32, 32, 32));
		player.setFixedMovementType(MovementType.IJKL_KEYS);
		ABPlayer player3 = (ABPlayer) Abereth.addEntity(new ABPlayer(100, 64, 32, 32));
        player3.setFixedMovementType(MovementType.ARROW_KEYS);
        cam.setTarget(new ABTarget(player3));
        cam.setFollowingTarget(true);
        player.setColor(ABColor.Blue);
		while(!ABScreen.isAskedToClose()){
			ABScreen.update(60);
			
			if(ABKeyboard.isKeyPressed(ABKey.A)){
				ABCamera c = cam.verticalSplit();
				c.setFollowingTarget(true);
				c.setTarget(new ABTarget(player));
				Abereth.addNewCamera(c);
			}
			if(ABKeyboard.isKeyPressed(ABKey.Z)){
				ABCamera c = cam.horizontalSplit();
				c.setFollowingTarget(true);
				c.setTarget(new ABTarget(player));
				Abereth.addNewCamera(c);
			}
			if(ABKeyboard.isKeyDown(ABKey.D)){
				Abereth.isDevMode = true;
			}
			
			ABScreen.refresh();
		}
		
		
	}
}
