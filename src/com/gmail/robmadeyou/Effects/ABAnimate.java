package com.gmail.robmadeyou.Effects;

import java.util.ArrayList;

public class ABAnimate {

    private ArrayList<Integer> listOfTextures = new ArrayList<Integer>();
    private int delayPerFrame;
    private int currentPerFrameDelay;
    private int delayPerLoop;
    private int currentPerLoopDelay;
    private boolean repeats;
    private int numberOfTextures;
    private int currentFrame;
    private boolean inverts;

    public ABAnimate(ArrayList<Integer> textureIDs, int delayPerFrame, int delayPerLoop, boolean repeats) {
        this.listOfTextures = textureIDs;
        this.delayPerFrame = delayPerFrame;
        this.delayPerLoop = delayPerLoop;
        this.repeats = repeats;
        this.numberOfTextures = textureIDs.size();
        currentPerFrameDelay = 0;
        currentPerLoopDelay = 0;
        currentFrame = 0;
        this.inverts = false;
    }

    public ABAnimate(ArrayList<Integer> textureIDs, int delayPerFrame, int delayPerLoop, boolean repeats, boolean inverts) {
        this.listOfTextures = textureIDs;
        this.delayPerFrame = delayPerFrame;
        this.delayPerLoop = delayPerLoop;
        this.repeats = repeats;
        this.numberOfTextures = textureIDs.size();
        currentPerFrameDelay = 0;
        currentPerLoopDelay = 0;
        currentFrame = 0;
        this.inverts = inverts;
    }
    /**
     * Every thime this method is called the animation updates
     * giving the efffect of the given drawable object change
     *  
     * @return current texture ID in the Arraylist
     */
    public int getTextureID() {
        onUpdate();
        return listOfTextures.get(currentFrame);
    }
    /**
     * Set the current frame number
     * 
     * @see ABTextures
     */
    public void setCurrentFrame(int frame) {
        this.currentFrame = frame;
    }
    /**
     * Set if the texture inverts
     * <br> 
     * This is used for making the frames be drawn inverted
     */
    public void setInvert(boolean args) {
        this.inverts = args;
    }
    /**
     * @return boolean - the invert value
     */ 
    public boolean getInverts() {
        return inverts;
    }

    public boolean hasFinished() {
        if (!repeats) {
            if (currentFrame >= listOfTextures.size()) {
                return true;
            }
        }
        return false;
    }

    public void onUpdate() {
        if (currentPerFrameDelay >= delayPerFrame) {
            currentFrame++;

            currentPerFrameDelay = 0;
            if (currentFrame >= listOfTextures.size()) {
                currentFrame = 0;
            }
        } else {
            currentPerFrameDelay++;
        }
    }
}
