package com.gmail.robmadeyou.Entity;

import com.gmail.robmadeyou.Block.ABBlock;
import com.gmail.robmadeyou.Draw.ABCollector;
import com.gmail.robmadeyou.Draw.ABCollector.DrawParameters;
import com.gmail.robmadeyou.Effects.ABColor;
import com.gmail.robmadeyou.Abereth;
import com.gmail.robmadeyou.Gui.ABMessageArea;
import com.gmail.robmadeyou.Input.ABKeyboard;
import com.gmail.robmadeyou.Input.ABKeyboard.ABKey;
import com.gmail.robmadeyou.Input.ABMouse;
import com.gmail.robmadeyou.Physics.ABPhysics;
import com.gmail.robmadeyou.World.ABWorld;
import com.gmail.robmadeyou.ABScreen;
import com.gmail.robmadeyou.ABScreen.GameType;

import org.lwjgl.util.vector.Vector2f;

public class ABPlayer extends ABEntity{
	//protected Vector2f vLocation, vDimensions;
    protected Vector2f vDirection, vOrigLoc, vOrigDim, vOrigDir;
    //private double x, y;
    //private double dX, dY;
    //private int originalHeight, width, height;
    private int crouchHeight;
    private int number;
    private int texture;
    private double originalSpeed;
    private boolean isJumping;
    private boolean isInAir;
    private boolean isCrouching;
    private boolean textureInverts = false;
    private double jumpDY = 0;
    private boolean hasClicked;
    private double finalJumpDY = 7;
    private boolean isMoving = false;
    private boolean hasEffectTakenPlace = false, hasEffectBeenRemoved = true;
    private ABNpc targetedEnemy;
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
    private ABKey upKey, downKey, rightKey, leftKey;
    private MovementType movementType = MovementType.ARROW_KEYS;

    public ABPlayer(float x, float y, float width, float height) {
    	super(x,y,width,height);
        vDirection = new Vector2f();
        //vLocation = new Vector2f();
        //vDimensions = new Vector2f();
        vOrigDim = new Vector2f();
        vOrigDir = new Vector2f();
        vOrigLoc = new Vector2f();

        //vLocation.setX((float) x);
        //vLocation.setY((float) y);
        vOrigDim.setX(width);
        vOrigDim.setY(height);
        //vDimensions.setX(width);
        //vDimensions.setY(height);
        this.crouchHeight = (int) ((getY() / 4) * 3);
        this.upKey = ABKey.UpArrow;
        this.downKey = ABKey.DownArrow;
        this.rightKey = ABKey.RightArrow;
        this.leftKey = ABKey.LeftArrow;
        this.jumpDY = 0;
        this.texture = -1;
        isJumping = false;
        isInAir = false;
        isCrouching = false;
        hasClicked = false;
    }

    public void setDX(double dX) {
        vDirection.setX((float) dX);
    }

    public void setDY(double dY) {
        vDirection.setY((float) dY);
    }

    public void setFixedMovementType(MovementType type) {
        this.movementType = type;
    }

    public void setCustomMovementType(ABKey upKey, ABKey downKey, ABKey leftKey, ABKey rightKey) {
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

    public void setTextureInverts(boolean args){
    	this.textureInverts = args;
    }

    public int getNumber() {
        return number;
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

    public int getFacingDirection() {
        return direction;
    }

    public double getOriginalSpeed() {
        return originalSpeed;
    }
    public boolean textureInverts(){
    	return textureInverts;
    }

    public ABKey getUpKey(MovementType type) {
        if (type.equals(MovementType.WASD_KEYS)) {
            return ABKey.W;
        } else if (type.equals(MovementType.NUMPAD_KEYS)) {
            return ABKey.Numpad_Eight;
        } else if (type.equals(MovementType.IJKL_KEYS)) {
            return ABKey.I;
        } else if (type.equals(MovementType.ARROW_KEYS)) {
            return ABKey.UpArrow;
        } else {
            return upKey;
        }
    }

    public ABKey getDownKey(MovementType type) {
        if (type.equals(MovementType.WASD_KEYS)) {
            return ABKey.S;
        } else if (type.equals(MovementType.NUMPAD_KEYS)) {
            return ABKey.Numpad_Two;
        } else if (type.equals(MovementType.IJKL_KEYS)) {
            return ABKey.K;
        } else if (type.equals(MovementType.ARROW_KEYS)) {
            return ABKey.DownArrow;
        } else {
            return downKey;
        }
    }

    public ABKey getLeftKey(MovementType type) {
        if (type.equals(MovementType.WASD_KEYS)) {
            return ABKey.A;
        } else if (type.equals(MovementType.NUMPAD_KEYS)) {
            return ABKey.Numpad_Four;
        } else if (type.equals(MovementType.IJKL_KEYS)) {
            return ABKey.J;
        } else if (type.equals(MovementType.ARROW_KEYS)) {
            return ABKey.LeftArrow;
        } else {
            return leftKey;
        }
    }

    public ABKey getRightKey(MovementType type) {
        if (type.equals(MovementType.WASD_KEYS)) {
            return ABKey.D;
        } else if (type.equals(MovementType.NUMPAD_KEYS)) {
            return ABKey.Numpad_Six;
        } else if (type.equals(MovementType.IJKL_KEYS)) {
            return ABKey.L;
        } else if (type.equals(MovementType.ARROW_KEYS)) {
            return ABKey.RightArrow;
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
        if (ABKeyboard.isKeyDown(getLeftKey(movementType))) {//No need for lots and lots of lines of code! Yaay!
        	direction = 3;
            if (!ABPhysics.isSolidLeft(this)) {
                setX((getX() - (delta * (getSpeed() - getSpeedDecrease()))));
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
        if (ABKeyboard.isKeyDown(getRightKey(movementType))) {
            direction = 1;
            if (!ABPhysics.isSolidRight(this)) {
                setX((getX() + (delta * (getSpeed() - getSpeedDecrease()))));
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
        if (ABKeyboard.isKeyDown(getUpKey(movementType))) {
            if (ABScreen.TypeOfGame == GameType.SIDE_SCROLLER) {
                if (!ABWorld.isSolidAtLocation((int) Math.round(getX() / ABWorld.BLOCK_SIZE()), (int) Math.round(getY() / ABWorld.BLOCK_SIZE()) - 1)) {
                    if (!isJumping) {
                        isJumping = true;
                        jumpDY = finalJumpDY;
                    }
                }
            }
            if (ABScreen.TypeOfGame == GameType.RPG_STYLE) {
                direction = 0;
                if (!ABPhysics.isSolidAbove(this) && getY() > 0) {
                    setY((float) (getY() - (delta * (getSpeed() - getSpeedDecrease()))));
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
        if (ABKeyboard.isKeyDown(getDownKey(movementType))) {
            if (ABScreen.TypeOfGame == GameType.SIDE_SCROLLER) {
                isCrouching = true;
            }
            if (ABScreen.TypeOfGame == GameType.RPG_STYLE) {
                direction = 2;
                if (!ABPhysics.isSolidUnder(this)) {
                    if (getY() + getHeight() < ABWorld.getWorldHeightInPixels()) {
                        setY((getY() + (delta * (getSpeed() - getSpeedDecrease()))));
                    }
                }
                if (getY() + getHeight() > ABWorld.getWorldHeightInPixels()) {
                    setY(ABWorld.getWorldHeightInPixels() - getHeight());
                }
            }
        } else {
            if (ABScreen.TypeOfGame == GameType.SIDE_SCROLLER) {
                isCrouching = false;
            }
        }
    }

    public void doEffectFromBlock(ABBlock type) {
        if (!hasEffectTakenPlace && hasEffectBeenRemoved) {
            type.doEffect(this);
            hasEffectTakenPlace = true;
            hasEffectBeenRemoved = false;
        }
    }

    public void removeEffectFromBlock(ABBlock type) {
        if (hasEffectTakenPlace && !hasEffectBeenRemoved) {
            type.removeEffect(this);
            hasEffectTakenPlace = false;
            hasEffectBeenRemoved = true;
        }
    }

    public void onUpdate(int delta) {
    	update();
    	backgroundRender();
        isMoving = ABKeyboard.isKeyDown(getLeftKey(movementType)) || ABKeyboard.isKeyDown(getRightKey(movementType));
        if (ABScreen.TypeOfGame == GameType.SIDE_SCROLLER) {
            if (getY() < 0) {
                setY(0);
            }
            if (isJumping || isInAir) {
                setY((getY() - jumpDY * (delta * 0.1)));
                jumpDY = jumpDY - ABWorld.gravity(delta);
                if (jumpDY < -16) {
                    jumpDY = -16;
                }
                if (getY() + getHeight() > ABWorld.getWorldHeightInPixels() - (ABWorld.BLOCK_SIZE() * 2)) {
                    isJumping = false;
                    jumpDY = 0;
                }
                if (ABWorld.isSolidAbove(this)) {
                    isJumping = true;
                    isInAir = true;
                }
            }

            if (getY() + getHeight() < ABWorld.getWorldHeightInPixels() - (ABWorld.BLOCK_SIZE() * 2)) {
                isInAir = true;
            } else {
                isInAir = false;
                jumpDY = 0;
            }
            if (ABWorld.isSolidUnder(this)) {
                isJumping = false;
                jumpDY = 0;
            } else {
                this.removeEffectFromBlock(ABWorld.blockList.getBlock(ABWorld.blockEffectX, ABWorld.blockEffectY).getType());
            }
            if (isCrouching) {
                if (hasClicked) {
                    setHeight(crouchHeight);
                    setY(getY() + vOrigDim.y - crouchHeight);
                    hasClicked = false;
                }
            } else {
                if (!hasClicked) {
                    if (!ABWorld.isSolidAbove(this) || !ABWorld.isSolidAtLocation((int) Math.round((getX() - (ABWorld.BLOCK_SIZE() / 2)) / ABWorld.BLOCK_SIZE()), (int) Math.round((getY()) / ABWorld.BLOCK_SIZE() - 1))
                            || !ABWorld.isSolidAtLocation((int) Math.round((getX() + getWidth() - (ABWorld.BLOCK_SIZE() / 2)) / ABWorld.BLOCK_SIZE()), (int) Math.round((getY()) / ABWorld.BLOCK_SIZE() - 1))) {
                        setHeight((int) vOrigDim.y);
                        hasClicked = true;
                    }
                }
            }
            while (ABWorld.isSolidAtLocation((int) Math.round(((getX() + ((getWidth() / 4) * 2)) - (ABWorld.BLOCK_SIZE() / 2)) / ABWorld.BLOCK_SIZE()), (int) Math.round((getY() + getHeight() + ABWorld.BLOCK_SIZE() / 2) / ABWorld.BLOCK_SIZE() - 1))
                    || ABWorld.isSolidAtLocation((int) Math.round((getX() + ((getWidth() / 4) * 3) - (ABWorld.BLOCK_SIZE() / 2)) / ABWorld.BLOCK_SIZE()), (int) Math.round((getY() + getHeight() + ABWorld.BLOCK_SIZE() / 2) / ABWorld.BLOCK_SIZE() - 1))) {
                setY(getY() - 1);
            }
        } else if (ABScreen.TypeOfGame == GameType.RPG_STYLE) {
            //TODO RPG STYLE ON UPDATE
        }

		/*
		 * Check if enemy is near and then target it.
		 *
		 */
        for (int i = 0; i < Abereth.onScreenEntity.size(); i++) {
            float x2 = Abereth.onScreenEntity.get(i).getX();
            float y2 = Abereth.onScreenEntity.get(i).getY();
            float width2 = Abereth.onScreenEntity.get(i).getWidth();
            float height2 = Abereth.onScreenEntity.get(i).getHeight();
            if (isNear(Abereth.onScreenEntity.get(i)) && Abereth.onScreenEntity.get(i).getNumber() != number) {
            	DrawParameters p = new DrawParameters("box", x2, y2, width2, height2);
            		p.setColor(ABColor.Blue);
            		p.setUseTranslate(true);
                ABCollector.add(p);
                //Check if right mouse button is pressed on the enemy to select it
                float mX = ABMouse.getX();
                float mY = ABMouse.getY();
                if (mX >= x2 && mX <= x2 + getWidth() && mY >= y2 && mY <= y2 + getHeight()) {
                    if (ABMouse.isRightMouseButtonPressed()) {
                        try {
                            targetedEnemy = (ABNpc) Abereth.onScreenEntity.get(i);
                            String message[] = {"Hi, my name is: " + targetedEnemy.getNumber()};
                            ABMessageArea.addListOfMessages(message);
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
        ABColor color = ABColor.White;
		/*
		 *     0
		 *   3   1
		 *     2
		 *
		 */

        if (isMoving) {
            if (direction == 0) {
                color = ABColor.Black;
            } else if (direction == 1) {
                color = ABColor.White;
            } else if (direction == 2) {
                color = ABColor.Red;
            } else {
            }
        }
        if (targetedEnemy != null) {
            float x2 = targetedEnemy.getX();
            float y2 = targetedEnemy.getY();
            float width2 = targetedEnemy.getWidth();
            float height2 = targetedEnemy.getHeight();
            DrawParameters p = new DrawParameters("box", x2, y2, width2, height2);
            	p.setColor(ABColor.Red);
            	p.setLayer(getLayer());
            	p.setUseTranslate(true);
            ABCollector.add(p);
        }
        DrawParameters p = new DrawParameters("box", getX(), getY(), getWidth(), getHeight());
        	p.setTextureID(texture);
        	p.setColor(color);
        	p.setLayer(getLayer());
        	p.setUseTranslate(true);
        	p.setInverts(textureInverts);
        isOnScreen(ABCollector.add(p));
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
    
	public void update() {}
}
