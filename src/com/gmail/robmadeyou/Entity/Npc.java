package com.gmail.robmadeyou.Entity;

import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Physics.Physics;
import com.gmail.robmadeyou.Astar.Astar;
import com.gmail.robmadeyou.Astar.Heuristic;
import com.gmail.robmadeyou.Astar.ManhattenHeuristic;
import com.gmail.robmadeyou.Block.Block;
import com.gmail.robmadeyou.Block.BlockAir;
import com.gmail.robmadeyou.Block.BlockGravel;
import com.gmail.robmadeyou.Block.BlockStone;
import com.gmail.robmadeyou.Gui.Text;
import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Layer;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.World.World;

import java.util.ArrayList;
import java.util.List;

public class Npc extends Entity {
    public ArrayList<moveUpdate> MovementArray = new ArrayList<moveUpdate>();
    private double x, y, dX, dY;
    private int number;
    private int width, height;
    private double speed;
    private int cX;
    private int cY;
    private boolean isAStarActive = false;
    private boolean isJumping = false;
    private boolean isInAir = false;
    private boolean usingLogic = false;
    private double jumpDY = 0;
    private double finalJumpDY = 7;
    private int layer = 0;
    private int delta;
    private Color color;
    private double speedDecrease;
    private int texture;
    private Player targetPlayer;
    /*
     * Directions are as follows (think of a compass)
     *
     *    0
     *  3   1
     *    2
     */
    private int directionFacing = 1;


    public Npc(double x, double y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.speed = 1;
        this.color = Color.White;
        this.speedDecrease = speed * 0.8;
        this.texture = -1;
    }

    //TODO tidy up pls

    public Player getTargetPlayer() {
        return targetPlayer;
    }

    public void setTargetPlayer(Player targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setWidth(int w) {
        this.width = w;
    }

    public void setHeight(int h) {
        this.height = h;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }

    public void setDX(double dX) {
        this.dX = dX;
    }

    public void setDY(double dY) {
        this.dY = dY;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
        this.speedDecrease = speed * 0.8;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public void setLogic(boolean args) {
        this.usingLogic = args;
    }

    public void setTexture(int tex) {
        this.texture = tex;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getDX() {
        return dX;
    }

    public double getDY() {
        return dY;
    }

    public double getSpeed() {
        return speed;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getLayer() {
        return layer;
    }

    public Color getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    public int getTexture() {
        return texture;
    }

    public void onUpdate(int delta) {
    	cX = (int) Math.round(x / World.BLOCK_SIZE());
        cY = (int) Math.round(y / World.BLOCK_SIZE());
        this.delta = delta;
        gameTypeLogic();

        if (MovementArray.size() > 0) {
            MovementArray.get(0).move();
        }

        if (usingLogic) {
            logic();
        }
    }

    public void orders(EnemyMovement direction) {
        MovementArray.add(new moveUpdate(1, direction));
    }

    public void orders(EnemyMovement direction, int amount) {
        MovementArray.add(new moveUpdate(amount, direction));
    }

    private void logic() {
        //Block[][] map = World.blockList;
        Heuristic heuristic = new ManhattenHeuristic();
        Astar myAstar = new Astar(World.blockList, heuristic);


        List<Block> resultList = null;


        int sX = (int)Math.round(this.x/ World.BLOCK_SIZE());
        int sY = (int) Math.round(this.y / World.BLOCK_SIZE());
        int eX = (int) Math.round(targetPlayer.getX() / World.BLOCK_SIZE());
        int eY = (int) Math.round(targetPlayer.getY() / World.BLOCK_SIZE());
        Block start = World.blockList[sX][sY];
        Block end = World.blockList[eX][eY];
        resultList = myAstar.search(start, end);

        if (resultList == null) {
            System.err.println("No path found! Exiting...");
        }


//        int openSetCount = 0;
//        for (int i = 0; i < World.blockList.length; ++i)
//            for (int j = 0; j < World.blockList[0].length; ++j)
//                openSetCount += World.blockList[i][j].isConsidered() ? 1 : 0;
//        System.out.println("Total size of map:" + World.blockList.length * World.blockList[0].length);
//        System.out.println("Number of points in open set: " + openSetCount);
//        System.out.println("Path length: " + resultList.size());
        int lastX = -1;
        int lastY = -1;
        for (Block b : resultList) {
        	
        	if(isAStarActive){
        		moveToBlock(b.getX()/World.BLOCK_SIZE(),b.getY()/World.BLOCK_SIZE());
        		if(lastX != -1 && lastY != -1){
        			if(Engine.isDevMode)
        				Collector.add(new DrawParameters("line", b.getX() * World.BLOCK_SIZE(), b.getY() * World.BLOCK_SIZE(), lastX * World.BLOCK_SIZE(),
        												lastY * World.BLOCK_SIZE(), -1, color, 1, Layer.GUILayer(), true));
        		}
        	}
            lastX = b.getX();
            lastY = b.getY();
        }
        if(isAStarActive){
        	MovementArray.clear();
        	moveToBlock(resultList.get(0).getX(), resultList.get(0).getY());
        }

    }

    public void moveToBlock(int xx, int yy){
                /*
                        Calculates how many pixels can the npc potentially move, only every tick. This will
                        help us check how many times does he have to move in the certain direction
                */
        double pixelsMovedByEveryTick = delta * (speed - speedDecrease);
                /*
                        Check how many times npc has to move to move exactly one block
                */
        int times = (int)Math.round(World.BLOCK_SIZE() / pixelsMovedByEveryTick);
        EnemyMovement direction = EnemyMovement.RIGHT;

        if(cX < xx){
            direction = EnemyMovement.RIGHT;
            orders(direction, times);
        }else if(cX > xx){
            direction = EnemyMovement.LEFT;
            orders(direction, times);
        }else if(cY > yy){
            direction = EnemyMovement.UP;
            orders(direction, times);
        }else if(cY < yy){
            direction = EnemyMovement.DOWN;
            orders(direction, times);
        }

    }
    public void setAStar(boolean AStar){
    	this.isAStarActive = AStar;
    }
    public boolean isAStarActive(){
    	return isAStarActive;
    }
    public void moveLeft() {
        if (!World.isSolidLeft(this)) {
            x -= (delta * (speed - speedDecrease));
        }
    }

    public void moveRight() {
        if (!World.isSolidRight(this)) {
            x += (delta * (speed - speedDecrease));
        }
    }

    public void moveUp() {
        if (Screen.TypeOfGame != GameType.SIDE_SCROLLER) {
            if (!World.isSolidAbove(this)) {
                y -= (delta * (speed - speedDecrease));
            }
        }
    }

    public void moveDown() {
        if (Screen.TypeOfGame != GameType.SIDE_SCROLLER) {
            if (!World.isSolidUnder(this)) {
                y += (delta * (speed - speedDecrease));
            }
        }
    }

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            jumpDY = finalJumpDY;
        }
    }

    public boolean isNear(Entity other) {
        int oX = (int) other.getX() + getWidth() / 2;
        int oY = (int) other.getY() + getHeight() / 2;

        if (oX >= x && oX <= x + width && oY >= y && oY <= y + height) {
            return true;
        }

        return false;
    }

    public void onTalk() {

    }

    private void gameTypeLogic() {
        if (Screen.TypeOfGame == GameType.SIDE_SCROLLER) {
            if (isJumping || isInAir) {
                y -= jumpDY * (delta * 0.1);
                jumpDY = jumpDY - World.gravity(delta);
                if (jumpDY < -8) {
                    jumpDY = -8;
                }
                if (y + height > World.getWorldHeightInPixels() - (World.BLOCK_SIZE() * 2)) {
                    isJumping = false;
                    jumpDY = 0;
                }
                if (World.isSolidUnder(this)) {
                    isJumping = false;
                    jumpDY = 0;
                }
                if (World.isSolidAbove(this)) {
                    isJumping = true;
                    isInAir = true;
                }
            }

            if (y + height < World.getWorldHeightInPixels() - (World.BLOCK_SIZE() * 2)) {
                isInAir = true;
            } else {
                isInAir = false;
                jumpDY = 0;
            }

            while (World.isSolidAtLocation((int) Math.round(((x + ((width / 4) * 2)) - (World.BLOCK_SIZE() / 2)) / World.BLOCK_SIZE()), (int) Math.round((y + height + World.BLOCK_SIZE() / 2) / World.BLOCK_SIZE() - 1))
                    || World.isSolidAtLocation((int) Math.round((x + ((width / 4) * 3) - (World.BLOCK_SIZE() / 2)) / World.BLOCK_SIZE()), (int) Math.round((y + height + World.BLOCK_SIZE() / 2) / World.BLOCK_SIZE() - 1))) {
                y--;
            }
        }//TODO Logic for other game modes for AI
    }

    public void draw() {
    	Text.drawString("cX = " + cX, x + width, y, 1, 2, 1, color, true, false);
        Collector.add(new DrawParameters("box", x, y, width, height, texture, color, layer, true));
    }

    public enum EnemyMovement {
        UP, LEFT, DOWN, RIGHT, JUMP, WAIT;
    }

    public class moveUpdate {
        private int amount;
        private EnemyMovement direction;
        private int currentTick;

        public moveUpdate(int amount, EnemyMovement direction) {
            this.amount = amount;
            this.direction = direction;
            this.currentTick = 0;
        }

        public void move() {
            currentTick++;
            if (Screen.TypeOfGame != Screen.GameType.SIDE_SCROLLER) {
                if (direction == EnemyMovement.UP) {
                    moveUp();
                } else if (direction == EnemyMovement.DOWN) {
                    moveDown();
                }
            }
            if (direction == EnemyMovement.LEFT) {
                moveLeft();
            } else if (direction == EnemyMovement.RIGHT) {
                moveRight();
            } else if (direction == EnemyMovement.WAIT) {

            } else if (direction == EnemyMovement.JUMP) {
                jump();
            }
            if (currentTick >= amount) {
                MovementArray.remove(0);
            }
        }
    }
}
