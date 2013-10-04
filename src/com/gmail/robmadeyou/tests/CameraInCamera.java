package com.gmail.robmadeyou.tests;

import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.Target;
import com.gmail.robmadeyou.Entity.Player;
import com.gmail.robmadeyou.World.Camera;

public class CameraInCamera {
	public static void main(String args[]){
		Screen.create(700, 600, "CaminCam", GameType.SIDE_SCROLLER, false);
		
		
		Screen.setUpWorld();
		Player player = (Player) Engine.addEntity(new Player(0, 0, 32, 64));
		Camera cam = Engine.addNewCamera(new Camera(0, 0, 0, 0, Screen.getWidth(), Screen.getWidth()));
		cam.setCameraBounds(true);
		cam.setFollowingTarget(true);
		cam.setTarget(new Target(player));
		Camera cam2 = Engine.addNewCamera(new Camera(400, 0, 0, 0, 300, 200));
		
		
		while(!Screen.isAskedToClose()){
			Screen.update(60);
			
			
			
			Screen.refresh();
		}
	}
}
