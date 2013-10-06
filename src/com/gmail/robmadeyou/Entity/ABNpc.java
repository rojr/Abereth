package com.gmail.robmadeyou.Entity;

import com.gmail.robmadeyou.Astar.AstarSearch;
import com.gmail.robmadeyou.Astar.DijkstraHeuristic;
import com.gmail.robmadeyou.Astar.Heuristic;
import com.gmail.robmadeyou.Astar.ManhattenHeuristic;
import com.gmail.robmadeyou.Block.ABBlock;
import com.gmail.robmadeyou.Draw.ABCollector;
import com.gmail.robmadeyou.Draw.ABCollector.DrawParameters;
import com.gmail.robmadeyou.Effects.ABColor;
import com.gmail.robmadeyou.Abereth;
import com.gmail.robmadeyou.ABLayer;
import com.gmail.robmadeyou.ABScreen;
import com.gmail.robmadeyou.ABScreen.GameType;
import com.gmail.robmadeyou.World.ABWorld;

import java.util.ArrayList;
import java.util.List;

public class ABNpc extends ABEntity {
    public ArrayList<moveUpdate> MovementArray = new ArrayList<moveUpdate>();
    private double dX, dY;
    private int number;
    private float width, height;
    private double speed;
    private int cX;
    private int cY;
    private boolean isAStarActive = false;
    private boolean isJumping = false;
    private boolean isInAir = false;
    private boolean usingLogic = false;
    private double jumpDY = 0;
    private double finalJumpDY = 7;
    private int delta;
    private ABColor color;
    private double speedDecrease;
    private int texture;
    private ABPlayer targetPlayer;
    /*
     * Directions are as follows (think of a compass)
     *
     *    0
     *  3   1
     *    2
     */
    private int directionFacing = 1;


    public ABNpc(float x, float y, float width, float height) {
    	super(x,y,width,height);
        this.width = width;
        this.height = height;
        this.speed = 1;
        this.color = ABColor.White;
        this.speedDecrease = speed * 0.8;
        this.texture = -1;
    }

    //TODO tidy up pls

    public ABPlayer getTargetPlayer() {
        return targetPlayer;
    }

    public void setTargetPlayer(ABPlayer targetPlayer) {
        this.targetPlayer = targetPlayer;
    }

    public void setNumber(int number) {
        this.number = number;
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

    public void setLogic(boolean args) {
        this.usingLogic = args;
    }

    public double getDX() {
        return dX;
    }

    public double getDY() {
        return dY;
    }

    public int getNumber() {
        return number;
    }

    public void onUpdate(int delta) {
        cX = (int) Math.round(getX() / ABWorld.BLOCK_SIZE());
        cY = (int) Math.round(getY() / ABWorld.BLOCK_SIZE());
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
            if (ABScreen.TypeOfGame == GameType.SIDE_SCROLLER) {
                heuristic = new DijkstraHeuristic();
            } else {
                heuristic = new ManhattenHeuristic();
            }
            AstarSearch myAstar = new AstarSearch(ABWorld.blockList, heuristic);


            List<ABBlock> resultList;

            int sX = (int) Math.round(this.getX() / ABWorld.BLOCK_SIZE());
            int sY = (int) Math.round(this.getY() / ABWorld.BLOCK_SIZE());
            int eX = (int) Math.round(targetPlayer.getX() / ABWorld.BLOCK_SIZE());
            int eY = (int) Math.round(targetPlayer.getY() / ABWorld.BLOCK_SIZE());
            ABBlock start = ABWorld.blockList.getBlock(sX, sY);
            ABBlock end = ABWorld.blockList.getBlock(eX, eY);
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
                for (ABBlock b : resultList) {
                    if (isAStarActive) {
                        moveToBlock(b.getX() / ABWorld.BLOCK_SIZE(), b.getY() / ABWorld.BLOCK_SIZE());
                        if (lastX != -1 && lastY != -1) {
                            if (Abereth.isDevMode){
                            	DrawParameters param = new DrawParameters("line", b.getX() * ABWorld.BLOCK_SIZE(), b.getY() * ABWorld.BLOCK_SIZE(), lastX * ABWorld.BLOCK_SIZE(), lastY * ABWorld.BLOCK_SIZE());
                            		param.setColor(color);
                            		param.setLayer(ABLayer.GUILayer());
                            		param.setUseTranslate(true);
                                ABCollector.add(param);
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
        int times = (int) Math.round(ABWorld.BLOCK_SIZE() / pixelsMovedByEveryTick);
        EnemyMovement direction = EnemyMovement.WAIT;

        if (cX < xx) {
            direction = EnemyMovement.RIGHT;
            orders(direction, times);
        } else if (cX > xx) {
            direction = EnemyMovement.LEFT;
            orders(direction, times);
        } else if (cY > yy) {
            direction = ABScreen.TypeOfGame == GameType.RPG_STYLE ? EnemyMovement.UP : EnemyMovement.JUMP;   // hack for now later check for solid blocks in movement direction
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
        if (!ABWorld.isSolidLeft(this)) {
            setX(getX() - (delta * (speed - speedDecrease)));
        }
    }

    public void moveRight() {
        if (!ABWorld.isSolidRight(this)) {
            setX(getX() + (delta * (speed - speedDecrease)));
        }
    }

    public void moveUp() {
        if (ABScreen.TypeOfGame != GameType.SIDE_SCROLLER) {
            if (!ABWorld.isSolidAbove(this)) {
                setY(getY() - (delta * (speed - speedDecrease)));
            }
        }
    }

    public void moveDown() {
        if (ABScreen.TypeOfGame != GameType.SIDE_SCROLLER) {
            if (!ABWorld.isSolidUnder(this)) {
                setY(getY() + (delta * (speed - speedDecrease)));
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
        if (ABScreen.TypeOfGame == GameType.SIDE_SCROLLER) {
            if (isJumping || isInAir) {
                setY(getY() - jumpDY * (delta * 0.1));
                jumpDY = jumpDY - ABWorld.gravity(delta);
                if (jumpDY < -8) {
                    jumpDY = -8;
                }
                if (getY() + height > ABWorld.getWorldHeightInPixels() - (ABWorld.BLOCK_SIZE() * 2)) {
                    isJumping = false;
                    jumpDY = 0;
                }
                if (ABWorld.isSolidUnder(this)) {
                    isJumping = false;
                    jumpDY = 0;
                }
                if (ABWorld.isSolidAbove(this)) {
                    isJumping = true;
                    isInAir = true;
                }
            }

            if (getY() + height < ABWorld.getWorldHeightInPixels() - (ABWorld.BLOCK_SIZE() * 2)) {
                isInAir = true;
            } else {
                isInAir = false;
                jumpDY = 0;
            }

            while (ABWorld.isSolidAtLocation((int) Math.round(((getX() + ((width / 4) * 2)) - (ABWorld.BLOCK_SIZE() / 2)) / ABWorld.BLOCK_SIZE()), (int) Math.round((getY() + height + ABWorld.BLOCK_SIZE() / 2) / ABWorld.BLOCK_SIZE() - 1))
                    || ABWorld.isSolidAtLocation((int) Math.round((getX() + ((width / 4) * 3) - (ABWorld.BLOCK_SIZE() / 2)) / ABWorld.BLOCK_SIZE()), (int) Math.round((getY() + height + ABWorld.BLOCK_SIZE() / 2) / ABWorld.BLOCK_SIZE() - 1))) {
                setY(getY() - 1);
            }
        }//TODO Logic for other game modes for AI
    }

    public void draw() {
    	DrawParameters p = new DrawParameters("box", getX(), getY(), width, height);
    		p.setTextureID(texture);
    		p.setColor(color);
    		p.setLayer(getLayer());
    		p.setUseTranslate(true);
        isOnScreen(ABCollector.add(p));
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
            if (ABScreen.TypeOfGame != ABScreen.GameType.SIDE_SCROLLER) {
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

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
