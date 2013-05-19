import com.gmail.robmadeyou.Screen;

import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Entity.Enemy;
import com.gmail.robmadeyou.Entity.EntityList;
import com.gmail.robmadeyou.Entity.Player.MovementType;
import com.gmail.robmadeyou.Gui.Interface;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;

import com.gmail.robmadeyou.Entity.Player;

public class Main {
	public static void main(String []arg){
		Screen.setWorldBlockSizeInPixels(32);
		Screen.setWorldDimensionsInBlocks(55, 60);
		Screen.createScreen(800, 600, "name", Screen.GameType.RPG_STYLE, false);
		Player player = new Player(224, 44, 20, 40);
		player.setFixedMovementType(MovementType.WASD_KEYS);
		EntityList.addEntity(player);
		Enemy e = new Enemy(40, 20, 20, 40);
		EntityList.addEntity(e);
		Screen.setUpWorld();
		while(!Screen.isAskedToClose()){
			Screen.update(60);
		}
	}
}
