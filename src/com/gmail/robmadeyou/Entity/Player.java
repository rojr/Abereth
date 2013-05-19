package com.gmail.robmadeyou.Entity;

import static org.lwjgl.opengl.GL11.GL_QUADS;
import static org.lwjgl.opengl.GL11.GL_LINES;
import static org.lwjgl.opengl.GL11.glBegin;
import static org.lwjgl.opengl.GL11.glEnd;
import static org.lwjgl.opengl.GL11.glPushMatrix;
import static org.lwjgl.opengl.GL11.glPopMatrix;
import static org.lwjgl.opengl.GL11.glTexCoord2d;
import static org.lwjgl.opengl.GL11.glTranslated;
import static org.lwjgl.opengl.GL11.glVertex2d;

import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.World.World;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Gui.Fonts;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;

public class Player implements Entity{
	private double originalX, originalY, x, y;
	private double dX, dY;
	private int originalWidth, originalHeight, width, height;
	private int crouchHeight;
	private int number;
	private double speed;
	private boolean isJumping = false;
	private boolean isInAir = false;
	private boolean isCrouching = false;
	private double jumpDY = 0;
	private boolean hasClicked = false;
	private final double finalJumpDY = 7;
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
	public Player(double x, double y, int width, int height){
		this.x = x;
		this.y = y;
		this.originalX = x;
		this.originalY = y;
		this.originalHeight = height;
		this.originalWidth = width;
		this.width = width;
		this.height = height;
		this.speed = 1;
		this.crouchHeight = (height / 4) * 3;
		this.upKey = Key.UpArrow;
		this.downKey = Key.DownArrow;
		this.rightKey = Key.RightArrow;
		this.leftKey = Key.LeftArrow;
	}
	public void setDX(double dX){
		this.dX = dX;
	}
	public void setDY(double dY){
		this.dY = dY;
	}
	public void setSpeed(double speed){
		this.speed = speed;
	}
	public void setFixedMovementType(MovementType type){
		this.movementType = type;
	}
	public void setCustomMovementType(Key upKey, Key downKey, Key leftKey, Key rightKey){
		this.movementType = MovementType.CUSTOM_KEYS;
		this.upKey = upKey;
		this.downKey = downKey;
		this.leftKey = leftKey;
		this.rightKey = rightKey;
		
	}
	public void setNumber(int num){
		this.number = num;
	}
	public void setX(double x){
		this.x = x;
	}
	public void setY(double y){
		this.y = y;
	}
	public void setWidth(int w) {
		this.width = w;
	}
	public void setHeight(int h) {
		this.height = h;
	}
	public double getX(){
		return x;
	}
	public int getNumber(){
		return number;
	}
	public double getY(){
		return y;
	}
	public int getWidth(){
		return width;
	}
	public int getHeight(){
		return height;
	}
	public MovementType getMovementType(){
		return movementType;
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
	public Key getUpKey(MovementType type){
		if(type.equals(MovementType.WASD_KEYS)){
			return Key.W;
		}else if(type.equals(MovementType.NUMPAD_KEYS)){
			return Key.Numpad_Eight;
		}else if(type.equals(MovementType.IJKL_KEYS)){
			return Key.I;
		}else if(type.equals(MovementType.ARROW_KEYS)){
			return Key.UpArrow;
		}else{
			return upKey;
		}
	}
	public Key getDownKey(MovementType type){
		if(type.equals(MovementType.WASD_KEYS)){
			return Key.S;
		}else if(type.equals(MovementType.NUMPAD_KEYS)){
			return Key.Numpad_Two;
		}else if(type.equals(MovementType.IJKL_KEYS)){
			return Key.K;
		}else if(type.equals(MovementType.ARROW_KEYS)){
			return Key.DownArrow;
		}else{
			return downKey;
		}
	}
	public Key getLeftKey(MovementType type){
		if(type.equals(MovementType.WASD_KEYS)){
			return Key.A;
		}else if(type.equals(MovementType.NUMPAD_KEYS)){
			return Key.Numpad_Four;
		}else if(type.equals(MovementType.IJKL_KEYS)){
			return Key.J;
		}else if(type.equals(MovementType.ARROW_KEYS)){
			return Key.LeftArrow;
		}else{
			return leftKey;
		}
	}
	public Key getRightKey(MovementType type){
		if(type.equals(MovementType.WASD_KEYS)){
			return Key.D;
		}else if(type.equals(MovementType.NUMPAD_KEYS)){
			return Key.Numpad_Six;
		}else if(type.equals(MovementType.IJKL_KEYS)){
			return Key.L;
		}else if(type.equals(MovementType.ARROW_KEYS)){
			return Key.RightArrow;
		}else{
			return rightKey;
		}
	}
	public void handleInput(int delta){
		
		double centerX = (Screen.getWidth() / 2) - Screen.translate_x;
		double distFromSideX = (Screen.getWidth() / 5) - Screen.translate_x;
		double distFromCenterX = centerX - x;
		
		double centerY = (Screen.getHeight() / 2) - Screen.translate_y;
		double distFromSideY = (Screen.getHeight() / 5) - Screen.translate_y;
		double distFromCenterY = centerY - y;
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
			if(Keyboard.isKeyDown(getLeftKey(movementType))){//No need for lots and lots of lines of code! Yaay!
				boolean one = x + width <= (Screen.getWidth() / 5) - Screen.translate_x;
				if(!World.isSolidLeft(this)){
					x -= (delta * (speed - 0.8));
				}
				if(one && Screen.translate_x < 0.0){
					Screen.translate_x += (delta * (speed - 0.8));
				}
				if(!one && Screen.translate_x < 0.0){
					if(distFromCenterX > 0)
						Screen.translate_x += (delta * (speed - 0.8)) * (distFromCenterX / distFromSideX);
				}
				if(Screen.translate_x > 0.0){
					Screen.translate_x = 0.0;
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
			if(Keyboard.isKeyDown(getRightKey(movementType))){
				boolean one = x + width >= (Screen.getWidth() - (Screen.getWidth() / 5)) - Screen.translate_x;
				boolean two = Screen.translate_x - Screen.getWidth() < World.getWorldWidthInPixels() - World.BLOCK_SIZE();
				if(!World.isSolidRight(this)){
					x += (delta * (speed - 0.8));
				}
				if(one && two){
					Screen.translate_x -= (delta * (speed - 0.8));
				}
				if(!one && two){
					if(distFromCenterX < 0)
						Screen.translate_x += (delta * (speed - 0.8)) * (distFromCenterX / distFromSideX);
				}
				if(!two){
					Screen.translate_x = World.getWorldHeightInPixels() - Screen.getWidth();
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
			if(Keyboard.isKeyDown(getUpKey(movementType))){
				if(Screen.TypeOfGame == GameType.SIDE_SCROLLER){
					if(!isJumping){
						isJumping = true;
						jumpDY = finalJumpDY;
					}
				}
				if(Screen.TypeOfGame == GameType.RPG_STYLE){
					boolean checkIfInTopBit = y < (Screen.getHeight() / 5) - Screen.translate_y;
					if(checkIfInTopBit && Screen.translate_y < 0){
						Screen.translate_y += (delta * (speed - 0.8));
					}
					if(Screen.translate_y > 0){
						Screen.translate_y = 0;
					}
					if(!checkIfInTopBit && Screen.translate_y < 0.0){
						if(distFromCenterY > 0)
							Screen.translate_y += (delta * (speed - 0.8)) * (distFromCenterY / distFromSideY);
					}
					if(!World.isSolidAbove(this) && y > 0){
						y -= (delta * (speed - 0.8));
					}
					if(y < 0){
						y = 0;
					}
				}
				//TODO Update different game modes
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
			if(Keyboard.isKeyDown(getDownKey(movementType))){
				if(Screen.TypeOfGame == GameType.SIDE_SCROLLER){
					isCrouching = true;
				}
				if(Screen.TypeOfGame == GameType.RPG_STYLE){
					boolean checkIfInBottomBit = y + height > (Screen.getHeight() - (Screen.getHeight() / 5)) - Screen.translate_y;
					boolean isInBottomBounds = -Screen.translate_y + Screen.getHeight() < World.getWorldHeightInPixels();
					
					if(checkIfInBottomBit && isInBottomBounds){
						Screen.translate_y -= (delta * (speed - 0.8));
					}
					if(!checkIfInBottomBit && Screen.translate_y < 0.0){
						if(distFromCenterY < 0)
							Screen.translate_y += (delta * (speed - 0.8)) * (distFromCenterY / distFromSideY);
					}
					if(!isInBottomBounds){
						Screen.translate_y = -World.getWorldHeightInPixels() + Screen.getHeight();
					}
					if(!World.isSolidUnder(this) && y + height < World.getWorldHeightInPixels()){
						y += (delta * (speed - 0.8));
					}
					if(y + height > World.getWorldHeightInPixels()){
						y = World.getWorldHeightInPixels() - height;
					}
				}
			}else{
				if(Screen.TypeOfGame == GameType.SIDE_SCROLLER){
					isCrouching = false;
				}
			}
		
	}
	
	
	public void onUpdate(int delta) {
		if(Screen.detailsActive){
			Fonts.drawString("x " + x, 0, 0, 1, Color.Blue);
			Fonts.drawString("y " + y, 0, 10, 1, Color.Blue);
		}
		if(Screen.TypeOfGame == GameType.SIDE_SCROLLER){
			boolean checkIfInBottomBit = y + height > (Screen.getHeight() - (Screen.getHeight() / 5)) - Screen.translate_y + Screen.getHeight() / 5;
			if(checkIfInBottomBit){
				Screen.translate_y += jumpDY * (delta * 0.1);
			}
			if(World.getWorldHeightInPixels() - World.BLOCK_SIZE() * 4 <= Screen.translate_y + Screen.getHeight()){
				Screen.translate_y = (World.getWorldHeightInPixels() - World.BLOCK_SIZE() * 2) - Screen.getHeight();
			}
			if(isJumping || isInAir){
				y -= jumpDY * (delta * 0.1);
				jumpDY = jumpDY  - World.gravity(delta);
				if(jumpDY < -16){
					jumpDY = -16;
				}
				if(y + height > World.getWorldHeightInPixels() - (World.BLOCK_SIZE() * 2)){
					isJumping = false;
					jumpDY = 0;
				}
				if(World.isSolidAbove(this)){
					isJumping = true;
					isInAir = true;
				}
			}
			
			if(y + height < World.getWorldHeightInPixels() - (World.BLOCK_SIZE() * 2)){
				isInAir = true;
			}else{
				isInAir = false;
				jumpDY = 0;
			}
			if(World.isSolidUnder(this)){
				isJumping = false;
				jumpDY = 0;
			}
			
			if(isCrouching){
				if(hasClicked){
					height = crouchHeight - 3;
					y += ((originalHeight - crouchHeight) * 4) + + 3;
					hasClicked = false;
				}
			}else{
				if(!World.isSolidAbove(this)){
					if(!hasClicked){
						y -= ((originalHeight - crouchHeight) * 4) + 8;
						height = originalHeight * 2;
						hasClicked = true;
					}
				}
			}
			
		}else if(Screen.TypeOfGame == GameType.RPG_STYLE){
			//TODO RPG STYLE ON UPDATE
		}
		handleInput(delta);
		draw();
	}
	public void draw() {
		glPushMatrix();
		glTranslated(Screen.translate_x, Screen.translate_y, 0);
		Fonts.drawString("Player",(int) x - 5,(int) y - 20, 1, Color.Red);
		glBegin(GL_QUADS);
			glTexCoord2d(0, 0);
			glVertex2d(x , y);
			glTexCoord2d(1, 0);
			glVertex2d(x + width, y);
			glTexCoord2d(1, 1);
			glVertex2d(x + width, y + height);
			glTexCoord2d(0, 1);
			glVertex2d(x , y + height);
		glEnd();
		
		glPopMatrix();
	}
	public enum MovementType{
		ARROW_KEYS(),
		WASD_KEYS(),
		NUMPAD_KEYS(),
		IJKL_KEYS(),
		CUSTOM_KEYS();
		MovementType(){}
	}
}
