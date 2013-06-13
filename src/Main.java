
import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.Entity.Player;


public class Main {
public static void main (String []args){
		
		Screen.create(800, 600, "Our Screen", GameType.SIDE_SCROLLER, false);
		
		Screen.setUpWorld();
		Player player = new Player(40, 40, 50, 50);
		Engine.addEntity(player);
		
		while(!Screen.isAskedToClose()){
			//Updating the screen. the maximum frame rate is 60.
			Screen.update(60);
			
			//Refreshing the screen
			Screen.refresh();
		}
		Screen.destroy();
	}
}
