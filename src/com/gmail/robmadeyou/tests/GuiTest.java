package com.gmail.robmadeyou.tests;


import java.util.Random;

import com.gmail.robmadeyou.Abereth;
import com.gmail.robmadeyou.ABScreen;
import com.gmail.robmadeyou.ABScreen.GameType;
import com.gmail.robmadeyou.draw.ABCollector;
import com.gmail.robmadeyou.effects.ABColor;
import com.gmail.robmadeyou.effects.ABEmitter;
import com.gmail.robmadeyou.effects.ABTextureLoader;
import com.gmail.robmadeyou.effects.ABEmitter.Particle;
import com.gmail.robmadeyou.peripherals.ABKeyboard;
import com.gmail.robmadeyou.peripherals.ABMouse;
import com.gmail.robmadeyou.peripherals.ABKeyboard.ABKey;
import com.gmail.robmadeyou.world.ABCamera;

public class GuiTest {
	public static void main(String args[]){
		ABScreen.create(1080, 1080, "Demo", GameType.SIDE_SCROLLER, false);
		
		ABColor color = ABColor.White;
		Random ran = new Random();
		ABEmitter emit = null;
		while(!ABScreen.isAskedToClose()){
			ABScreen.update(60);
			
				emit = new ABEmitter(20, 20, ran.nextInt(200));
				emit.setX(ABMouse.getX());
				emit.setY(ABMouse.getY());
				emit.setDecayRate(0.005f);
				emit.setVelocity(ran.nextFloat() + 0.5f);
				int dim = ran.nextInt(10);
				emit.setDrawHeight(dim);
				emit.setDrawWidth(dim);
				emit.setOpacity(ran.nextFloat());
				
				Abereth.addNewEmitter(emit);
			
			int col = 1+ran.nextInt(7);
			if(col == 1){
				color = ABColor.White;
			}else if(col == 2){
				color = ABColor.Red;
			}else if(col == 3){
				color = ABColor.Blue;
			}else if(col == 4){
				color = ABColor.Yellow;
			}else if(col == 5){
				color = ABColor.Turquoise_5;
			}else if(col == 6){
				color = ABColor.Green;
			}else if(col == 7){
				color = ABColor.Purple;
			}

			ABScreen.refresh();
		}
		ABScreen.destroy();
	}
}
