package com.gmail.robmadeyou.Effects;

import java.util.ArrayList;
import java.util.Random;

import org.lwjgl.util.vector.Vector2f;

import com.gmail.robmadeyou.ABScreen;
import com.gmail.robmadeyou.Draw.ABCollector;
import com.gmail.robmadeyou.Draw.ABCollector.DrawParameters;
import com.gmail.robmadeyou.Draw.ABDrawable;

public class ABEmitter extends ABDrawable{

	
	
	Particle[] part;
	private float velocity, decayRate;
	private int spawnRate, amount, minimum, maximum, amountDone = 0;
	private int speedTimer = 0;
	private boolean repeats = false, randomSizes = false;
	private int drawType;
	private ArrayList<Integer> textureList;
	private MovementDirection direction;
	public ABEmitter(float x, float y, int amount) {
		super(new Vector2f(), new Vector2f());
		setX(x);
		setY(y);
		setDrawWidth(5);
		setDrawHeight(5);
		velocity = 1;
		decayRate = 0.05f;
		this.amount = amount;
		part = new Particle[amount];
		drawType = 0;
		setLayer(1);
		direction = MovementDirection.OUT;
	}
	/*
	 * Getters
	 */
	public float getX(){
		return getDrawX();
	}
	public float getY(){
		return getDrawY();
	}
	public float getVelocity(){
		return velocity;
	}
	public float getDecayRate(){
		return decayRate;
	}
	public int getSpawnRate(){
		return spawnRate;
	}
	public int getAmount(){
		return amount;
	}
	public boolean getRepeats(){
		return repeats;
	}
	/*
	 * Setters
	 */
	public void setX(float x){
		setDrawX(x);
	}
	public void setY(float y){
		setDrawY(y);
	}
	public void setVelocity(float velocity){
		this.velocity = velocity;
	}
	public void setDecayRate(float decayRate){
		this.decayRate = decayRate;
	}
	public void setSpawnRate(int rate){
		this.spawnRate = rate;
	}
	public void setAmount(int amount){
		this.amount = amount;
	}
	@Override
	public void setTexture(int texture){
		super.setTexture(texture);
		drawType = 1;
	}
	public void setTextureList(ArrayList<Integer> i){
		drawType = 2;
		this.textureList = i;
	}
	public void setRandomSizes(int minimum, int maximum){
		randomSizes = true;
		this.minimum = minimum;
		this.maximum = maximum;
	}
	public void setRepeats(boolean args){
		this.repeats = args;
	}
	
	public void draw() {
		
	}

	public void onUpdate() {
            speedTimer++;
            if (spawnRate <= speedTimer) {
            	speedTimer = 0;
                for (int i = 0; i < part.length; i++) {
                    if (part[i] == null) {
                    	if(!repeats && amount <= amountDone){
                    		
                    	}else{
                    		Random ran = new Random();
                        	part[i] = new Particle(getX(), getY(), ran.nextInt(), ran.nextInt(), i);
                        
                        	if (randomSizes) {
                            	part[i].setHeight(minimum + ran.nextInt(maximum - minimum));
                            	part[i].setWidth(minimum + ran.nextInt(maximum - minimum));
                        	} else {
                            	part[i].setHeight((int) getDrawHeight());
                            	part[i].setWidth((int) getDrawWidth());
                        	}
                        	amountDone++;
                        	break;
                    	}
                    }
                }
            for (Particle aPart : part) {
                if (aPart != null) {
                    aPart.onUpdate(velocity, ABScreen.delta, decayRate, direction);
                    
                    aPart.draw();
                }
            }
        }
	}
	
	public class Particle{
        private double x, y, toX, toY;
        private float opacity;
        private int num;
        private int width, height;
        public Particle(double x, double y, double toX, double toY, int num) {
            this.x = x;
            this.y = y;
            this.toX = toX;
            this.toY = toY;
            this.num = num;
            this.opacity = 1;
            this.width = 5;
            this.height = 5;
        }

        public void onUpdate(double speed, int delta, float size, MovementDirection direction) {
            double s = speed;
            double tan = (float) Math.atan2(toX, toY); // (toX, toY)
            double dX = s * Math.sin(tan);
            double dY = s * Math.cos(tan);
            if (direction == MovementDirection.OUT) {
                x -= dX;
                y -= dY;
            } else if (direction == MovementDirection.UP) {
                x -= dX;
                if (dY < 0) {
                    y -= -dY;
                } else {
                    y -= dY;
                }
            } else if (direction == MovementDirection.DOWN) {
                x -= dX;
                if (dY < 0) {
                    y -= dY;
                } else {
                    y -= -dY;
                }
            } else if (direction == MovementDirection.LEFT) {
                if (dX < 0) {
                    x -= -dX;
                } else {
                    x -= dX;
                }
                y -= dY;
            } else if (direction == MovementDirection.RIGHT) {
                if (dX < 0) {
                    x -= dX;
                } else {
                    x -= -dX;
                }
                y -= dY;
            }
            if (opacity > 0.0 && opacity <= 1.0) {
                opacity -= size;
            }
            if (0.0 > opacity) {
                part[num] = null;
            }
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public void draw() {
        	DrawParameters p = new DrawParameters("box",(float) x, (float) y, width, height);
        		p.setOpacity(opacity);
        		p.setColor(getColor());
        		p.setLayer(getLayer());
            if (drawType == 1) {
            		p.setTextureID(getTexture());
            		p.setUseTranslate(true);
                ABCollector.add(p);
            } else if (drawType == 2) {
                Random random = new Random();
                int firstTexID = textureList.get(0);
                int randomTexID = firstTexID + random.nextInt(textureList.size());
        			p.setTextureID(randomTexID);
        			p.setUseTranslate(true);
        		ABCollector.add(p);
            } else {
    				p.setUseTranslate(true);
                ABCollector.add(p);
            }
        }
    }

    public static enum MovementDirection {
        OUT, UP, DOWN, LEFT, RIGHT;

        MovementDirection() {

        }
    }
	
}
