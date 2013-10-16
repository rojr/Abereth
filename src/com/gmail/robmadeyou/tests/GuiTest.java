package com.gmail.robmadeyou.tests;


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
		ABScreen.create(1920, 1080, "Demo", GameType.CUSTOM, false);
		
		ABColor color = ABColor.White;
		while(!ABScreen.isAskedToClose()){
			ABScreen.update(60);
			
			ABEmitter emit = Abereth.addNewEmitter(new ABEmitter(ABMouse.getTranslatedX(), ABMouse.getTranslatedY(), 20));
			
			ABEmitter emit1 = Abereth.addNewEmitter(new ABEmitter(ABMouse.getTranslatedX() + 20, ABMouse.getTranslatedY(), 20));
			ABEmitter emit2 = Abereth.addNewEmitter(new ABEmitter(ABMouse.getTranslatedX() - 20, ABMouse.getTranslatedY(), 20));
			ABEmitter emit3 = Abereth.addNewEmitter(new ABEmitter(ABMouse.getTranslatedX(), ABMouse.getTranslatedY() + 20, 20));
			ABEmitter emit4 = Abereth.addNewEmitter(new ABEmitter(ABMouse.getTranslatedX(), ABMouse.getTranslatedY() - 20, 20));
			
			
			
			emit.setDecayRate(0.005f);
			emit.setVelocity(2);
			emit.setDrawHeight(20);
			emit.setDrawWidth(20);
			
			
			emit.setColor(color);
			if(ABKeyboard.isKeyDown(ABKey.One)){
				color = ABColor.White;
			}else if(ABKeyboard.isKeyDown(ABKey.Two)){
				color = ABColor.Red;
			}else if(ABKeyboard.isKeyDown(ABKey.Three)){
				color = ABColor.Blue;
			}else if(ABKeyboard.isKeyDown(ABKey.Four)){
				color = ABColor.Yellow;
			}else if(ABKeyboard.isKeyDown(ABKey.Five)){
				color = ABColor.Turquoise_5;
			}else if(ABKeyboard.isKeyDown(ABKey.Six)){
				color = ABColor.Green;
			}else if(ABKeyboard.isKeyDown(ABKey.Seven)){
				color = ABColor.Purple;
			}
			ABScreen.refresh();
		}
		ABScreen.destroy();
	}
}
