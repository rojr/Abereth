import java.util.ArrayList;

import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.Draw.Box;
import com.gmail.robmadeyou.Effects.Animate;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Effects.Emitter;
import com.gmail.robmadeyou.Effects.TextureLoader;
import com.gmail.robmadeyou.Entity.Player;
import com.gmail.robmadeyou.Input.Mouse;


public class Main {
public static void main (String []args){
		
		Screen.create(800, 600, "Our Screen", GameType.SIDE_SCROLLER, false);
		
		int flame = TextureLoader.createTexture("flame.png");
		int lol = TextureLoader.createTexture("res/none.png");
		int aa = TextureLoader.createTexture("res/powerUp/BallSpeedDecrease.png");
		
		ArrayList<Integer> textures = new ArrayList<Integer>();
		
		textures.add(flame);
		textures.add(lol);
		textures.add(aa);
		
		Animate animID = Engine.createAnimation(new Animate(textures, 20, 0, true));
		Emitter em = Engine.addNewEmitter(new Emitter(Mouse.getX(), Mouse.getY(), 40, 0.04f, 3, 0, Color.Banana));
		
		Animate two = Engine.createAnimation(new Animate(textures, 5, 2, true));
		Screen.setUpWorld();
		Player player = new Player(40, 40, 20, 20);
		Engine.addEntity(player);
		em.setCustomParticleHeight(40);
		em.setCustomParticleWidth(40);
		while(!Screen.isAskedToClose()){
			//Updating the screen. the maximum frame rate is 60.
			Screen.update(60);
			
			Box.drawBox(40, 50, 50, 50, two.getTextureID());
			em.setX(Mouse.getX());
			em.setY(Mouse.getY());
			
			em.setCustomTexture(two.getTextureID());
			
			if(Mouse.leftMouseButtonDown){
				Engine.addNewEmitter(new Emitter(Mouse.getX(), Mouse.getY(), 20, (float) 0.04, 2, 1, Color.Yellow));
			}
			//Refreshing the screen
			Screen.refresh();
		}
		Screen.destroy();
	}
}
