import com.gmail.robmadeyou.Screen;

import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Entity.EntityList;
import com.gmail.robmadeyou.Gui.Interface;

import com.gmail.robmadeyou.Entity.Player;

public class Main {
	public static void main(String []arg){
		Screen.setWorldDimensionsInBlocks(88, 60);
		Screen.createScreen(800, 600, "name", Screen.GameType.SIDE_SCROLLER, false);
		Player player = new Player(224, 44, 20, 40);
		EntityList.addEntity(player);
		while(!Screen.isAskedToClose()){
			Screen.screenUpdate(60);
		}
	}
}
