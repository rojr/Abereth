package com.age.graphics.effects;

import java.util.ArrayList;

/**
 * Created by apex on 19/02/14.
 */
public class Animation {
    private ArrayList<Integer> textures = new ArrayList<Integer>();
    private int currentFrame = 0, delay = 0, delayCount = 0;

    /**
     * Most basic
     * Creates a new animation with one frame, with all default values
     * @param texture
     */
    public Animation(int texture){
        this(texture, 0);
    }

    public Animation(int texture, int delay){
        this(new ArrayList<Integer>(texture), delay);
        add(texture);
    }

    public Animation(ArrayList<Integer> textures, int delay){
        for(Integer i : textures){
            add(i);
        }
        this.delay = delay;
    }

    public int getDelay(){
        return this.delay;
    }
    public void setDelay(int delay){
        this.delay = delay;
    }

    /**
     *
     * @param tex a signle texture that will be appended to the end of the animation.
     * @return this class
     */
    public Animation add(int tex){
        textures.add(tex);
        return this;
    }

    /**
     *
     * @param texlist Array list filled with textures will be appended to the end of the list
     * @return this class
     */
    public Animation add(ArrayList<Integer> texlist){
        for(Integer i : texlist){
            textures.add(i);
        }
        return this;
    }

    /**
     *
     * @return ArrayList of textures (int)
     */
    public ArrayList<Integer> get(){
        return textures;
    }

    /**
     * Returns a specific texture from the arraylist
     * @param i index
     * @throws java.lang.ArrayIndexOutOfBoundsException
     */
    public int get(int i){
        if(i > textures.size()) throw new ArrayIndexOutOfBoundsException();
        return textures.get(i);
    }

    public int getCurrentTexture(){
        //Texture to return
        if(delayCount >= delay){
            currentFrame++;
            delayCount = 0;
            if(currentFrame >= textures.size()){
                currentFrame = 0;
            }
        }else{
            delayCount++;
        }
        return textures.get(currentFrame);
    }
}
