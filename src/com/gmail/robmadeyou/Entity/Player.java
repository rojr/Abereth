package com.gmail.robmadeyou.Entity;

import com.gmail.robmadeyou.Block.Block;
import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Gui.MessageArea;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;
import com.gmail.robmadeyou.Input.Mouse;
import com.gmail.robmadeyou.Physics.Physics;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.World.World;

import org.lwjgl.util.vector.Vector2f;

public class Player extends Entity {
    protected Vector2f vLocation, vDimensions, vDirection, vOrigLoc, vOrigDim, vOrigDir;
    //private double x, y;
    //private double dX, dY;
    //private int originalHeight, width, height;
    private int crouchHeight;
    private int number;
    private int texture;
    private double speed;
    private double originalSpeed;
    private double speedDecrease;
    private boolean isJumping;
    private boolean isInAir;
    private boolean isCrouching;
    private boolean textureInverts = false;
    private double jumpDY = 0;
    private boolean hasClicked;
    private int amountOfHealth;
    private double finalJumpDY = 7;
    private boolean isMoving = false;
    private boolean hasEffectTakenPlace = false, hasEffectBeenRemoved = true;
    private boolean isSolidLeft = false, isSolidRight = false, isSolidAbove = false, isSolidBelow = false;
    private int layer;
    private Npc targetedEnemy;
    /*
     * Direction is as shown:
     * (think of a compass)
     *         0
     *       3   1
     *         2
     *
     * This would translate to:
     *
     * 			up
     * 	  right     left
     * 		   down
     */
    private int direction = 1;
    private Key upKey, downKey, rightKey, leftKey;
    private MovementType movementType = MovementType.ARROW_KEYS;

    public Player(double x, double y, int width, int height) {

        vDirection = new Vector2f();
        vLocation = new Vector2f();
        vDimensions = new Vector2f();
        vOrigDim = new Vector2f();
        vOrigDir = new Vector2f();
        vOrigLoc = new Vector2f();

        vLocation.setX((float) x);
        vLocation.setY((float) y);
        vOrigDim.setX(width);
        vOrigDim.setY(height);
        vDimensions.setX(width);
        vDimensions.setY(height);
        this.speed = 1;
        this.crouchHeight = (int) ((vDimensions.getY() / 4) * 3);
        this.upKey = Key.UpArrow;
        this.downKey = Key.DownArrow;
        this.rightKey = Key.RightArrow;
        this.leftKey = Key.LeftArrow;
        this.jumpDY = 0;
        this.speedDecrease = speed * 0.8;
        this.originalSpeed = speed;
        this.texture = -1;
        isJumping = false;
        isInAir = false;
        isCrouching = false;
        hasClicked = false;
        layer = 0;
    }

    public void setHealth(int amount) {
        this.amountOfHealth = amount;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public void setDX(double dX) {
        vDirection.setX((float) dX);
    }

    public void setDY(double dY) {
        vDirection.setY((float) dY);
    }

    public void setTexture(int tex) {
        this.texture = tex;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
        this.speedDecrease = speed * 0.8;
        /*
         * I was so stupid.. I did
		 * this.speedDecrease = speed * speedDecrease
		 *
		 * I couldn't understand why the speed was inverting and increasing
		 * every time I jumped. I'm honestly too stupid :s
		 */
    }

    public void setFixedMovementType(MovementType type) {
        this.movementType = type;
    }

    public void setCustomMovementType(Key upKey, Key downKey, Key leftKey, Key rightKey) {
        this.movementType = MovementType.CUSTOM_KEYS;
        this.upKey = upKey;
        this.downKey = downKey;
        this.leftKey = leftKey;
        this.rightKey = rightKey;

    }

    public void setDirection(int direction) {
        this.direction = direction;
    }

    public void setNumber(int num) {
        this.number = num;
    }

    public void setX(double x) {
        vLocation.x = (float) x;
    }

    public void setY(double y) {
        vLocation.y = (float) y;
    }

    public void setWidth(int w) {
        vDimensions.x = w;
    }

    public void setHeight(int h) {
        vDimensions.y = h;
    }
    
    public void setTextureInverts(boolean args){
    	this.textureInverts = args;
    }

    public int getHealth() {
        return amountOfHealth;
    }

    public double getX() {
        return vLocation.x;
    }

    public int getLayer() {
        return layer;
    }

    public int getNumber() {
        return number;
    }

    public double getY() {
        return vLocation.y;
    }

    public int getWidth() {
        return (int) vDimensions.x;
    }

    public int getHeight() {
        return (int) vDimensions.y;
    }

    public MovementType getMovementType() {
        return movementType;
    }

    public double getDX() {
        return vDirection.x;
    }

    public double getDY() {
        return vDirection.y;
    }

    public double getSpeed() {
        return speed;
    }

    public int getTexture() {
        return texture;
    }

    public int getFacingDirection() {
        return direction;
    }

    public double getOriginalSpeed() {
        return originalSpeed;
    }
    public boolean textureInverts(){
    	return textureInverts;
    }

    public Key getUpKey(MovementType type) {
        if (type.equals(MovementType.WASD_KEYS)) {
            return Key.W;
        } else if (type.equals(MovementType.NUMPAD_KEYS)) {
            return Key.Numpad_Eight;
        } else if (type.equals(MovementType.IJKL_KEYS)) {
            return Key.I;
        } else if (type.equals(MovementType.ARROW_KEYS)) {
            return Key.UpArrow;
        } else {
            return upKey;
        }
    }

    public Key getDownKey(MovementType type) {
        if (type.equals(MovementType.WASD_KEYS)) {
            return Key.S;
        } else if (type.equals(MovementType.NUMPAD_KEYS)) {
            return Key.Numpad_Two;
        } else if (type.equals(MovementType.IJKL_KEYS)) {
            return Key.K;
        } else if (type.equals(MovementType.ARROW_KEYS)) {
            return Key.DownArrow;
        } else {
            return downKey;
        }
    }

    public Key getLeftKey(MovementType type) {
        if (type.equals(MovementType.WASD_KEYS)) {
            return Key.A;
        } else if (type.equals(MovementType.NUMPAD_KEYS)) {
            return Key.Numpad_Four;
        } else if (type.equals(MovementType.IJKL_KEYS)) {
            return Key.J;
        } else if (type.equals(MovementType.ARROW_KEYS)) {
            return Key.LeftArrow;
        } else {
            return leftKey;
        }
    }

    public Key getRightKey(MovementType type) {
        if (type.equals(MovementType.WASD_KEYS)) {
            return Key.D;
        } else if (type.equals(MovementType.NUMPAD_KEYS)) {
            return Key.Numpad_Six;
        } else if (type.equals(MovementType.IJKL_KEYS)) {
            return Key.L;
        } else if (type.equals(MovementType.ARROW_KEYS)) {
            return Key.RightArrow;
        } else {
            return rightKey;
        }
    }

    public void handleInput(int delta) {


			/*
		 	*     /
		 	*    /
		 	*   /
		 	*  /
		 	* ---------------
		 	*  \
		 	*   \
		 	*    \
		 	*     \
		 	*
		 	*/
        if (Keyboard.isKeyDown(getLeftKey(movementType))) {//No need for lots and lots of lines of code! Yaay!
            direction = 3;
            if (!Physics.isSolidLeft(this)) {
                setX((getX() - (delta * (speed - speedDecrease))));
                isSolidLeft = false;
            } else {
                isSolidLeft = true;
            }
            if (getX() < 0) {
                setX(0);
            }
        }
			/*
			 *           \
			 *            \
			 *             \
			 *              \
			 * ---------------
			 *              /
			 *             /
			 *            /
			 *           /
			 *
			 */
        if (Keyboard.isKeyDown(getRightKey(movementType))) {
            direction = 1;
            if (!Physics.isSolidRight(this)) {
                setX((getX() + (delta * (speed - speedDecrease))));
                isSolidRight = false;
            } else {
                isSolidRight = true;
            }
        }
			/*
			 *              .
			 *             /|\
			 *            / | \
			 *           /  |  \
			 *              |
			 *              |
			 *              |
			 *              |
			 *              |
			 */
        if (Keyboard.isKeyDown(getUpKey(movementType))) {
            if (Screen.TypeOfGame == GameType.SIDE_SCROLLER) {
                if (!World.isSolidAtLocation((int) Math.round(getX() / World.BLOCK_SIZE()), (int) Math.round(getY() / World.BLOCK_SIZE()) - 1)) {
                    if (!isJumping) {
                        isJumping = true;
                        jumpDY = finalJumpDY;
                        isSolidAbove = false;
                    }
                } else {
                    isSolidAbove = true;
                }
            }
            if (Screen.TypeOfGame == GameType.RPG_STYLE) {
                direction = 0;
                if (!Physics.isSolidAbove(this) && getY() > 0) {
                    setY((float) (getY() - (delta * (speed - speedDecrease))));
                    isSolidAbove = false;
                } else {
                    isSolidAbove = true;
                }
                if (getY() < 0) {
                    setY(0);
                }
            }
        }
			/*
			 *              |
			 *              |
			 *              |
			 *              |
			 *              |
			 *           \  |  /
			 *            \ | /
			 *             \|/
			 *              `
			 */
        if (Keyboard.isKeyDown(getDownKey(movementType))) {
            if (Screen.TypeOfGame == GameType.SIDE_SCROLLER) {
                isCrouching = true;
            }
            if (Screen.TypeOfGame == GameType.RPG_STYLE) {
                direction = 2;
                if (!Physics.isSolidUnder(this)) {
                    isSolidBelow = false;
                    if (getY() + getHeight() < World.getWorldHeightInPixels()) {
                        setY((getY() + (delta * (speed - speedDecrease))));
                    }
                } else {
                    isSolidBelow = true;
                }
                if (getY() + getHeight() > World.getWorldHeightInPixels()) {
                    setY(World.getWorldHeightInPixels() - getHeight());
                }
            }
        } else {
            if (Screen.TypeOfGame == GameType.SIDE_SCROLLER) {
                isCrouching = false;
            }
        }
    }

    public void doEffectFromBlock(Block type) {
        if (!hasEffectTakenPlace && hasEffectBeenRemoved) {
            type.doEffect(this);
            hasEffectTakenPlace = true;
            hasEffectBeenRemoved = false;
        }
    }

    public void removeEffectFromBlock(Block type) {
        if (hasEffectTakenPlace && !hasEffectBeenRemoved) {
            type.removeEffect(this);
            hasEffectTakenPlace = false;
            hasEffectBeenRemoved = true;
        }
    }

    public void onUpdate(int delta) {
        isMoving = Keyboard.isKeyDown(getLeftKey(movementType)) || Keyboard.isKeyDown(getRightKey(movementType));
        if (Screen.TypeOfGame == GameType.SIDE_SCROLLER) {
            if (getY() < 0) {
                setY(0);
            }
            if (isJumping || isInAir) {
                setY((getY() - jumpDY * (delta * 0.1)));
                jumpDY = jumpDY - World.gravity(delta);
                if (jumpDY < -16) {
                    jumpDY = -16;
                }
                if (getY() + getHeight() > World.getWorldHeightInPixels() - (World.BLOCK_SIZE() * 2)) {
                    isJumping = false;
                    jumpDY = 0;
                }
                if (Physics.isSolidAbove(this)) {
                    isSolidAbove = true;
                    isJumping = true;
                    isInAir = true;
                } else {
                    isSolidAbove = false;
                }
            }

            if (getY() + getHeight() < World.getWorldHeightInPixels() - (World.BLOCK_SIZE() * 2)) {
                isInAir = true;
            } else {
                isInAir = false;
                jumpDY = 0;
            }
            if (Physics.isSolidUnder(this)) {
                isSolidBelow = true;
                isJumping = false;
                jumpDY = 0;
            } else {
                isSolidBelow = false;
                this.removeEffectFromBlock(World.blockList.getBlock(World.blockEffectX, World.blockEffectY).getType());
            }
            if (isCrouching) {
                if (hasClicked) {
                    setHeight(crouchHeight);
                    setY(getY() + vOrigDim.y - crouchHeight);
                    hasClicked = false;
                }
            } else {
                if (!hasClicked) {
                    if (!Physics.isSolidAbove(this) || !World.isSolidAtLocation((int) Math.round((getX() - (World.BLOCK_SIZE() / 2)) / World.BLOCK_SIZE()), (int) Math.round((getY()) / World.BLOCK_SIZE() - 1))
                            || !World.isSolidAtLocation((int) Math.round((getX() + getWidth() - (World.BLOCK_SIZE() / 2)) / World.BLOCK_SIZE()), (int) Math.round((getY()) / World.BLOCK_SIZE() - 1))) {
                        setHeight((int) vOrigDim.y);
                        hasClicked = true;
                    }
                }
            }
            while (World.isSolidAtLocation((int) Math.round(((getX() + ((getWidth() / 4) * 2)) - (World.BLOCK_SIZE() / 2)) / World.BLOCK_SIZE()), (int) Math.round((getY() + getHeight() + World.BLOCK_SIZE() / 2) / World.BLOCK_SIZE() - 1))
                    || World.isSolidAtLocation((int) Math.round((getX() + ((getWidth() / 4) * 3) - (World.BLOCK_SIZE() / 2)) / World.BLOCK_SIZE()), (int) Math.round((getY() + getHeight() + World.BLOCK_SIZE() / 2) / World.BLOCK_SIZE() - 1))) {
                setY(getY() - 1);
            }
        } else if (Screen.TypeOfGame == GameType.RPG_STYLE) {
            //TODO RPG STYLE ON UPDATE
        }

		/*
		 * Check if enemy is near and then target it.
		 *
		 */
        for (int i = 0; i < Engine.onScreenEntity.size(); i++) {
            double x2 = Engine.onScreenEntity.get(i).getX();
            double y2 = Engine.onScreenEntity.get(i).getY();
            int width2 = Engine.onScreenEntity.get(i).getWidth();
            int height2 = Engine.onScreenEntity.get(i).getHeight();
            if (isNear(Engine.onScreenEntity.get(i)) && Engine.onScreenEntity.get(i).getNumber() != number) {
            	DrawParameters p = new DrawParameters("box", x2, y2, width2, height2);
            		p.setColor(Color.Blue);
            		p.setUseTranslate(true);
                Collector.add(p);
                //Check if right mouse button is pressed on the enemy to select it
                int mX = Mouse.getX();
                int mY = Mouse.getY();
                if (mX >= x2 && mX <= x2 + getWidth() && mY >= y2 && mY <= y2 + getHeight()) {
                    if (Mouse.isRightMouseButtonPressed()) {
                        try {
                            targetedEnemy = (Npc) Engine.onScreenEntity.get(i);
                            String message[] = {"Hi, my name is: " + targetedEnemy.getNumber()};
                            MessageArea.addListOfMessages(message);
                        } catch (ClassCastException c) {
                            c.printStackTrace();
                        }
                    }
                }
            }
        }

        if (!isNear(targetedEnemy)) {
            targetedEnemy = null;
        }

        handleInput(delta);
    }

    

    public void draw() {
        Color color = Color.White;
		/*
		 *     0
		 *   3   1
		 *     2
		 *
		 */

        if (isMoving) {
            if (direction == 0) {
                color = Color.Black;
            } else if (direction == 1) {
                color = Color.White;
            } else if (direction == 2) {
                color = Color.Red;
            } else {
            }
        }
        if (targetedEnemy != null) {
            double x2 = targetedEnemy.getX();
            double y2 = targetedEnemy.getY();
            int width2 = targetedEnemy.getWidth();
            int height2 = targetedEnemy.getHeight();
            DrawParameters p = new DrawParameters("box", x2, y2, width2, height2);
            	p.setColor(Color.Red);
            	p.setLayer(layer);
            	p.setUseTranslate(true);
            Collector.add(p);
        }
        DrawParameters p = new DrawParameters("box", getX(), getY(), getWidth(), getHeight());
        	p.setTextureID(texture);
        	p.setColor(color);
        	p.setLayer(layer);
        	p.setUseTranslate(true);
        	p.setInverts(textureInverts);
        isOnScreen(Collector.add(p));
    }

    public enum MovementType {
        ARROW_KEYS(),
        WASD_KEYS(),
        NUMPAD_KEYS(),
        IJKL_KEYS(),
        CUSTOM_KEYS();

        MovementType() {
        }
    }
}
