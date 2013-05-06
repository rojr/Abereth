import com.gmail.robmadeyou.Screen;

import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Entity.EntityList;
import com.gmail.robmadeyou.Gui.Interface;

import com.gmail.robmadeyou.Entity.Player;

public class Main {
	public static void main(String []arg){
		Screen.createScreen(600, 400, "name", Screen.GameType.SIDE_SCROLLER, false);
		
		Interface.addButton("Blih",20, 50, 100, 40, Color.Blue, null, "null");

		
		Interface.addBox("Blah", 20, 100, 60, 60, null, Color.Blue, "null");
		
		Player aww = new Player(224, 44, 20, 40);
		
		EntityList.addEntity(aww);
		
		while(!Screen.isAskedToClose()){
			Screen.screenUpdate(60);
		}
	}
}
