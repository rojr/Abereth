package com.gmail.robmadeyou.Effects;

import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.World.Camera;
import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Screen;

import java.util.ArrayList;
import java.util.Random;

public class Emitter {

    Particles[] part;
    private double x, y, velocity;
    private int width = 5, height = 5;
    private boolean randomSizes = false;
    private boolean repeats = true;
    private int minRandom, maxRandom;
    private Color color;
    private int texID = -1, amount, spawnRate;
    private int amountDone = 0;
    private int speedTimer;
    private float size;
    private int drawType;
    private MovementDirection direction;
    private ArrayList<Integer> textureList;
    private int layer = 0;

    public Emitter(double x, double y, int amount, float size, double velocity, int spawnRate, Color color) {
        this.drawType = 0;
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.spawnRate = spawnRate;
        this.speedTimer = 0;
        this.color = color;
        this.size = size;
        this.amount = amount;
        this.direction = MovementDirection.OUT;
        part = new Particles[this.amount];
    }

    public Emitter(double x, double y, int amount, float size, double velocity, int spawnRate, Color color, int texID) {
        this.drawType = 1;
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.spawnRate = spawnRate;
        this.color = color;
        this.texID = texID;
        this.size = size;
        this.amount = amount;
        this.direction = MovementDirection.OUT;
        part = new Particles[this.amount];
    }

    public Emitter(double x, double y, int amount, float size, double velocity, int spawnRate, Color color, ArrayList<Integer> textureList) {
        this.drawType = 2;
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.spawnRate = spawnRate;
        this.color = color;
        this.textureList = textureList;
        this.size = size;
        this.amount = amount;
        this.direction = MovementDirection.OUT;
        part = new Particles[this.amount];
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public void setCustomTexture(int textureID) {
        this.texID = textureID;
        this.drawType = 1;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setSpawnRate(int rate) {
        this.spawnRate = rate;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public Color getColor() {
        return color;
    }

    public double getVelocity() {
        return velocity;
    }

    public double getSpawnRate() {
        return spawnRate;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public int getLayer() {
        return layer;
    }
    public boolean repeats(){
    	return repeats;
    }
    public void setRandomParticleDimensions(int min, int max) {
        this.randomSizes = true;
        this.minRandom = min;
        this.maxRandom = max;
    }

    public void setCustomParticleWidth(int width) {
        this.width = width;
    }

    public void setCustomParticleHeight(int height) {
        this.height = height;
    }

    public void setMovementDirection(MovementDirection direction) {
        this.direction = direction;
    }

    public void enableFollowPattern(boolean args) {
        this.drawType = 3;
    }
    public void setRepeat(boolean args){
    	this.repeats = args;
    }

    public void onUpdate(int delta, Camera cam) {
    	
    	double tX = cam.getX();
        double tY = cam.getY();
        double cW = cam.getWidth();
        double cH = cam.getHeight();
    	
        if (x >= -tX && x <= -tX + cW && y >= -tY && y <= -tY + cH) {
            speedTimer++;
            if (spawnRate <= speedTimer) {
            	speedTimer = 0;
                for (int i = 0; i < part.length; i++) {
                    if (part[i] == null) {
                    	if(!repeats && amount <= amountDone){
                    		
                    	}else{
                    		Random ran = new Random();
                        	part[i] = new Particles(x, y, ran.nextInt(), ran.nextInt(), i);
                        
                        	if (randomSizes) {
                            	part[i].setHeight(minRandom + ran.nextInt(maxRandom - minRandom));
                            	part[i].setWidth(minRandom + ran.nextInt(maxRandom - minRandom));
                        	} else {
                            	part[i].setHeight(height);
                            	part[i].setWidth(width);
                        	}
                        	amountDone++;
                        	break;
                    	}
                    }
                }
            }
            for (Particles aPart : part) {
                if (aPart != null) {
                    aPart.onUpdate(velocity, delta, size, direction);
                    aPart.draw();
                }
            }
        }
        
        if(!repeats && amountDone >= amount){
        	boolean allIsEmpty = false;
        	for(int i = 0; i < part.length; i++){
        		if(part[i] != null){
        			allIsEmpty = false;
        		}else{
        			allIsEmpty = true;
        		}
        	}
        	if(allIsEmpty){
        		Engine.removeEmitter(this);
        	}
        	
        }
    }

    public class Particles {
        private double x, y, toX, toY;
        private float opacity;
        private int num;
        private int width, height;
        private int texOrder;

        public Particles(double x, double y, double toX, double toY, int num) {
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
                opacity = 1f;
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
            if (drawType == 1) {
            	DrawParameters p = new DrawParameters("box", x, y, width, height);
            		p.setTextureID(texID);
            		p.setColor(color);
            		p.setOpacity(opacity);
            		p.setLayer(layer);
            		p.setUseTranslate(true);
                Collector.add(p);
            } else if (drawType == 2) {
                Random random = new Random();
                int firstTexID = textureList.get(0);
                int randomTexID = firstTexID + random.nextInt(textureList.size());
                
                DrawParameters p = new DrawParameters("box", x, y, width, height);
        			p.setTextureID(randomTexID);
        			p.setColor(color);
        			p.setOpacity(opacity);
        			p.setLayer(layer);
        			p.setUseTranslate(true);
        		Collector.add(p);
            } else if (drawType == 3) {
                try {
                    int maxTexOrder = textureList.size();
                    if (texOrder >= maxTexOrder) texOrder = 0;
                    DrawParameters p = new DrawParameters("box", x, y, width, height);
            			p.setTextureID(texOrder + textureList.get(0));
            			p.setColor(color);
            			p.setOpacity(opacity);
            			p.setLayer(layer);
            			p.setUseTranslate(true);
            		Collector.add(p);
                    texOrder++;
                } catch (NullPointerException e) {
                    e.printStackTrace();
                    System.out.println("ERROR: BAD TEXTURE PATH/NONE SPECIFIED");
                }
            } else {
            	DrawParameters p = new DrawParameters("box", x, y, width, height - 1);
    				p.setColor(color);
    				p.setOpacity(opacity);
    				p.setLayer(layer);
    				p.setUseTranslate(true);
                Collector.add(p);
            }
        }
    }

    public static enum MovementDirection {
        OUT, UP, DOWN, LEFT, RIGHT;

        MovementDirection() {

        }
    }
}
