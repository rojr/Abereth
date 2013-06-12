package com.gmail.robmadeyou.Effects;

import java.util.ArrayList;

public class Animate {
	
	private ArrayList<Integer> listOfTextures = new ArrayList<Integer>();
	private int delayPerFrame;
	private int currentPerFrameDelay;
	private int delayPerLoop;
	private int currentPerLoopDelay;
	private boolean repeats;
	private int numberOfTextures;
	private int currentFrame;
	public Animate(ArrayList<Integer> textureIDs, int delayPerFrame, int delayPerLoop, boolean repeats){
		this.listOfTextures = textureIDs;
		this.delayPerFrame = delayPerFrame;
		this.delayPerLoop = delayPerLoop;
		this.repeats = repeats;
		this.numberOfTextures = textureIDs.size();
		currentPerFrameDelay = 0;
		currentPerLoopDelay = 0;
		currentFrame = 0;
	}
	public int getTextureID(){
		onUpdate();
		return listOfTextures.get(currentFrame);
	}
	public void setCurrentFrame(int frame){
		this.currentFrame = frame;
	}
	public void onUpdate(){
		if(currentPerFrameDelay >= delayPerFrame){
			currentFrame++;
			
			currentPerFrameDelay = 0;
			if(currentFrame >= listOfTextures.size()){
				currentFrame = 0;
			}
		}else{
			currentPerFrameDelay++;
		}
	}
}
