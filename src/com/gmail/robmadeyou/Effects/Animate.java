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
    private boolean inverts;

    public Animate(ArrayList<Integer> textureIDs, int delayPerFrame, int delayPerLoop, boolean repeats) {
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

    public Animate(ArrayList<Integer> textureIDs, int delayPerFrame, int delayPerLoop, boolean repeats, boolean inverts) {
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

    public int getTextureID() {
        onUpdate();
        return listOfTextures.get(currentFrame);
    }

    public void setCurrentFrame(int frame) {
        this.currentFrame = frame;
    }

    public void setInvert(boolean args) {
        this.inverts = args;
    }

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
