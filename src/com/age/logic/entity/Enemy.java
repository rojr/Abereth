package com.age.logic.entity;

import com.age.graphics.effects.Animation;
import com.age.graphics.effects.TextureLoader;
import org.lwjgl.Sys;

import java.util.Random;

/**
 * Created by apex on 20/02/14.
 */
public class Enemy extends Entity {

    private Random decision;
    private boolean hasExecutedDecision;
    /**
     * Soon I'll write up a large-ish list of events that the enemy could undergo, each action will have a set time that it will
     * run for if the state is not changed in some way.
     */
    private int currentAction = 0;
    /**
     * Time the current action will run for, unless triggered by other event
     */
    private long timeToRunFor;
    private long currentTime;
    private long actionStart = Sys.getTime();
    public Enemy(double x, double y, double width, double height){
        super(x,y,width,height);
        decision = new Random();
        hasExecutedDecision = true;
        timeToRunFor = 0;
        currentTime = 0;
    }

    @Override
    public void update(double delta){
        super.update(delta);
        if(hasExecutedDecision){
            currentAction = decision.nextInt(8);
            timeToRunFor = decision.nextInt(10000);
            hasExecutedDecision = false;
            actionStart = Sys.getTime();
            //System.out.println("decided" + currentAction + "    " + timeToRunFor);
        }else{
            currentTime = Sys.getTime() - actionStart;
            if(currentTime <= timeToRunFor){
                switch(currentAction){
                    case 0:
                        hasExecutedDecision = moveRight();
                        break;
                    case 1:
                        hasExecutedDecision = moveLeft();
                        break;
                    case 2:
                        jump(-4);
                        break;
                    case 3:
                        moveLeft();
                        jump(-4);
                        break;
                    case 4:
                        moveRight();
                        jump(-4);
                        break;
                    case 5:
                        setInvertsX(true);
                        break;
                    case 6:
                        setInvertsX(false);
                        break;
                    case 7:
                        break;
                }
            }else{
                hasExecutedDecision = true;
            }
        }
    }
    @Override
    public boolean moveLeft(){
        super.moveLeft();
        setInvertsX(true);
        return super.moveLeft();
    }

    @Override
    public boolean moveRight(){
        super.moveRight();
        setInvertsX(false);
        return super.moveRight();
    }
}