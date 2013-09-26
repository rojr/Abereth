package com.gmail.robmadeyou.Entity;

import com.gmail.robmadeyou.Astar.AstarSearch;
import com.gmail.robmadeyou.Astar.DijkstraHeuristic;
import com.gmail.robmadeyou.Astar.Heuristic;
import com.gmail.robmadeyou.Astar.ManhattenHeuristic;
import com.gmail.robmadeyou.Block.Block;
import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Gui.Text;
import com.gmail.robmadeyou.Layer;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.World.World;

import java.util.ArrayList;
import java.util.List;

import sun.security.acl.WorldGroupImpl;

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
        // quick fix: when isAstarActive boolean
        // //is set to false the Astar algorithm is still being calculated but move to block is not because logic was still being called
        // this caused a block on the thread?  making sure both are activated eliminated the bug

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
        if (isAStarActive) {
            activateAstarSearch();
        }

    }

    private void activateAstarSearch() {
        try {
            Heuristic heuristic;
            if (Screen.TypeOfGame == GameType.SIDE_SCROLLER) {
                heuristic = new DijkstraHeuristic();
            } else {
                heuristic = new ManhattenHeuristic();
            }
            AstarSearch myAstar = new AstarSearch(World.blockList, heuristic);


            List<Block> resultList;

            int sX = (int) Math.round(this.x / World.BLOCK_SIZE());
            int sY = (int) Math.round(this.y / World.BLOCK_SIZE());
            int eX = (int) Math.round(targetPlayer.getX() / World.BLOCK_SIZE());
            int eY = (int) Math.round(targetPlayer.getY() / World.BLOCK_SIZE());
            Block start = World.blockList.getBlock(sX, sY);
            Block end = World.blockList.getBlock(eX, eY);
            resultList = null;
            try {
                resultList = myAstar.search(start, end);
            } catch (NullPointerException e) {
            }

            if (resultList == null) {
                System.err.println("No path found! Exiting...");
            }
            int lastX = -1;
            int lastY = -1;
            if (resultList != null) {
                for (Block b : resultList) {
                    if (isAStarActive) {
                        moveToBlock(b.getX() / World.BLOCK_SIZE(), b.getY() / World.BLOCK_SIZE());
                        if (lastX != -1 && lastY != -1) {
                            if (Engine.isDevMode){
                            	DrawParameters param = new DrawParameters("line", b.getX() * World.BLOCK_SIZE(), b.getY() * World.BLOCK_SIZE(), lastX * World.BLOCK_SIZE(), lastY * World.BLOCK_SIZE());
                            		param.setColor(color);
                            		param.setLayer(Layer.GUILayer());
                            		param.setUseTranslate(true);
                                Collector.add(param);
                            }
                        }
                    }
                    lastX = b.getX();
                    lastY = b.getY();
                }
            }
            if (isAStarActive) {
                MovementArray.clear();
                moveToBlock(resultList.get(0).getX(), resultList.get(0).getY());
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            e.printStackTrace();
        }
    }

    public void moveToBlock(int xx, int yy) {
                /*
                        Calculates how many pixels can the npc potentially move, only every tick. This will
                        help us check how many times does he have to move in the certain direction
                */
        double pixelsMovedByEveryTick = delta * (speed - speedDecrease);
                /*
                        Check how many times npc has to move to move exactly one block
                */
        int times = (int) Math.round(World.BLOCK_SIZE() / pixelsMovedByEveryTick);
        EnemyMovement direction = EnemyMovement.WAIT;

        if (cX < xx) {
            direction = EnemyMovement.RIGHT;
            orders(direction, times);
        } else if (cX > xx) {
            direction = EnemyMovement.LEFT;
            orders(direction, times);
        } else if (cY > yy) {
            direction = Screen.TypeOfGame == GameType.RPG_STYLE ? EnemyMovement.UP : EnemyMovement.JUMP;   // hack for now later check for solid blocks in movement direction
            orders(direction, times);
        } else if (cY < yy) {
            direction = EnemyMovement.DOWN;
            orders(direction, times);
        }
    }

    public void setAStar(boolean AStar) {
        this.isAStarActive = AStar;
    }

    public boolean isAStarActive() {
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
    	DrawParameters p = new DrawParameters("box", x, y, width, height);
    		p.setTextureID(texture);
    		p.setColor(color);
    		p.setLayer(layer);
    		p.setUseTranslate(true);
        isOnScreen(Collector.add(p));
    }

    public enum EnemyMovement {
        UP, LEFT, DOWN, RIGHT, JUMP, WAIT
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
