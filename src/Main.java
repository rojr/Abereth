import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Layer;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Entity.Enemy;
import com.gmail.robmadeyou.Entity.Player;
import com.gmail.robmadeyou.Entity.Enemy.EnemyMovement;
import com.gmail.robmadeyou.Gui.Button;
import com.gmail.robmadeyou.Gui.Interface;
import com.gmail.robmadeyou.Gui.Text;
import com.gmail.robmadeyou.Input.Mouse;


public class Main {
public static void main (String []args){
		
		Screen.create(800, 600, "Our Screen", GameType.RPG_STYLE, false);
		Screen.setWorldDimensionsInBlocks(100, 100);
		
		Screen.setUpWorld();
		Player player = new Player(40, 40, 32, 32);
		Engine.addEntity(player);
		player.setLayer(1);
		
		Enemy enemy = new Enemy(0, 40, 32, 32);
		Engine.addEntity(enemy);
		
		Button button = (Button) Interface.add(new Button("", 50, 50, 50, 50, 1));
		button.useTranslate(true);
		while(!Screen.isAskedToClose()){
			//Updating the screen. the maximum frame rate is 60.
			Screen.update(60);
			
			if(button.isReleased()){
				System.out.println("I am released!");
			}
			Text.drawString("abcdefghijklmnopqrstuvwxyz       1234567890", 100, 100, 1, 1, Color.White, true, false);
			
			
			if(Mouse.leftMouseButtonDown && !Mouse.isOverGui){
				Enemy a = (Enemy) Engine.addEntity(new Enemy(Mouse.getX(),Mouse.getY(),32,32));
				a.orders(EnemyMovement.LEFT, 20);
				a.orders(EnemyMovement.UP, 20);
				a.orders(EnemyMovement.RIGHT, 20);
			}
			//Refreshing the screen
			Screen.refresh();
		}
		Screen.destroy();
	}
}
