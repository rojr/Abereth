package com.gmail.robmadeyou.Input;

public class Keyboard {
	
	public static boolean isKeyPressed(Key key){
		if(key.isPressed){
			return true;
		}
		return false;
	}
	public static boolean isKeyReleased(Key key){
		if(key.isReleased){
			return true;
		}
		return false;
	}
	public static boolean isKeyDown(Key key){
		if(key.isDown){
			return false;
		}
		return true;
	}
	public void onUpdate(){
		while(Keyboard.next(){
			if(Keyboard.getEventStateKey()){
				if(Keyboad.isKeyDown(Keyboard.)
			}
		}
	}
}
