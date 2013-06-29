package com.gmail.robmadeyou.Entity;

import com.gmail.robmadeyou.Engine;
import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.World.World;
import com.gmail.robmadeyou.Block.Block;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Entity.Enemy.EnemyMovement;
import com.gmail.robmadeyou.Input.Keyboard;
import com.gmail.robmadeyou.Input.Keyboard.Key;
import com.gmail.robmadeyou.Input.Mouse;
import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;

public class Player extends Entity{
	private double x, y;
	private double dX, dY;
	private int originalHeight, width, height;
	private int crouchHeight;
	private int number;
	private double speed;
	private double originalSpeed;
	private double speedDecrease;
	private boolean isJumping;
	private boolean isInAir;
	private boolean isCrouching;
	private double jumpDY = 0;
	private boolean hasClicked;
	private int amountOfHealth;
	private double finalJumpDY = 7;
	private boolean isMoving = false;
	private boolean hasEffectTakenPlace = false, hasEffectBeenRemoved = true;
	private boolean isSolidLeft = false, isSolidRight = false, isSolidAbove = false, isSolidBelow = false;
	private int layer;
	private Enemy targetedEnemy;
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
		this.originalHeight = height;
		this.width = width;
		this.height = height;
		this.speed = 1;
		this.crouchHeight = (height / 4) * 3;
		this.upKey = Key.UpArrow;
		this.downKey = Key.DownArrow;
		this.rightKey = Key.RightArrow;
		this.leftKey = Key.LeftArrow;
		this.jumpDY = 0;
		this.speedDecrease = speed * 0.8;
		this.originalSpeed = speed;
		isJumping = false;
		isInAir = false;
		isCrouching = false;
		hasClicked = false;
		layer = 0;
	}
	public void setHealth(int amount){
		this.amountOfHealth = amount;
	}
	public void setLayer(int layer){
		this.layer = layer;
	}
	public void setDX(double dX){
		this.dX = dX;
	}
	public void setDY(double dY){
		this.dY = dY;
	}
	public void setSpeed(double speed){
		this.speed = speed;
		this.speedDecrease = speed * 0.8;
		/*
		 * I was so stupid.. I did
		 * this.speedDecrase = speed * speedDecrease
		 * 
		 * I couldn't understand why the speed was inverting and increasing
		 * every time I jumped. I'm honestly too stupid :s
		 */
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
	public void setDirection(int direction){
		this.direction = direction;
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
	public int getHealth(){
		return amountOfHealth;
	}
	public double getX(){
		return x;
	}
	public int getLayer(){
		return layer;
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
	public int getFacingDirection(){
		return direction;
	}
	public double getOriginalSpeed(){
		return originalSpeed;
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
			direction = 3;
			boolean one = x + width <= (Screen.getWidth() / 5) - Screen.translate_x;
			if(!World.isSolidLeft(this)){
				x -= (delta * (speed - speedDecrease));
				isSolidLeft = false;
			}else{
				isSolidLeft = true;
			}
			if(one && Screen.translate_x < 0.0){
				Screen.translate_x += (delta * (speed - speedDecrease));
			}
			if(!one && Screen.translate_x < 0.0){
				if(distFromCenterX > 0)
					Screen.translate_x += (delta * (speed - speedDecrease)) * (distFromCenterX / distFromSideX);
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
			direction = 1;
			boolean one = x + width >= (Screen.getWidth() - (Screen.getWidth() / 5)) - Screen.translate_x;
			boolean two = -Screen.translate_x + Screen.getWidth() < World.getWorldWidthInPixels() - World.BLOCK_SIZE();
			if(!World.isSolidRight(this)){
				x += (delta * (speed - speedDecrease));
				isSolidRight = false;
			}else{
				isSolidRight = true;
			}
			if(one && two){
				Screen.translate_x -= (delta * (speed - speedDecrease));
			}
			if(!one && two){
				if(distFromCenterX < 0)
					Screen.translate_x += (delta * (speed - speedDecrease)) * (distFromCenterX / distFromSideX);
			}
			if(!two){
				Screen.translate_x = -World.getWorldWidthInPixels() + Screen.getWidth() + World.BLOCK_SIZE();
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
				if(!World.isSolidAtLocation((int)Math.round(x / World.BLOCK_SIZE()),(int) Math.round(y / World.BLOCK_SIZE()) - 1)){
					if(!isJumping){
						isJumping = true;
						jumpDY = finalJumpDY;
						isSolidAbove = false;
					}
				}else{
					isSolidAbove = true;
				}
			}
			if(Screen.TypeOfGame == GameType.RPG_STYLE){
				direction = 0;
				boolean checkIfInTopBit = y < (Screen.getHeight() / 5) - Screen.translate_y;
				if(checkIfInTopBit && Screen.translate_y < 0){
					Screen.translate_y += (delta * (speed - speedDecrease));
				}
				if(Screen.translate_y > 0){
					Screen.translate_y = 0;
				}
				if(!checkIfInTopBit && Screen.translate_y < 0.0){
					if(distFromCenterY > 0)
						Screen.translate_y += (delta * (speed - speedDecrease)) * (distFromCenterY / distFromSideY);
				}
				if(!World.isSolidAbove(this) && y > 0){
					y -= (delta * (speed - speedDecrease));
					isSolidAbove = false;
				}else{
					isSolidAbove = true;
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
				direction = 2;
				boolean checkIfInBottomBit = y + height > (Screen.getHeight() - (Screen.getHeight() / 5)) - Screen.translate_y;
				boolean isInBottomBounds = -Screen.translate_y + Screen.getHeight() < World.getWorldHeightInPixels();
				if(checkIfInBottomBit && isInBottomBounds){
					Screen.translate_y -= (delta * (speed - speedDecrease));
				}
				if(!checkIfInBottomBit && Screen.translate_y < 0.0){
					if(distFromCenterY < 0)
						Screen.translate_y += (delta * (speed - speedDecrease)) * (distFromCenterY / distFromSideY);
				}
				if(!isInBottomBounds){
					Screen.translate_y = -World.getWorldHeightInPixels() + Screen.getHeight();
				}
				if(!World.isSolidUnder(this)){
					isSolidBelow = false;
					if(y + height < World.getWorldHeightInPixels()){
						y += (delta * (speed - speedDecrease));
					}
				}else{
					isSolidBelow = true;
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
	public void doEffectFromBlock(Block type){
		if(!hasEffectTakenPlace && hasEffectBeenRemoved){
			type.doEffect(this);
			hasEffectTakenPlace = true;
			hasEffectBeenRemoved = false;
		}
	}
	public void removeEffectFromBlock(Block type){
		if(hasEffectTakenPlace && !hasEffectBeenRemoved){
			type.removeEffect(this);
			hasEffectTakenPlace = false;
			hasEffectBeenRemoved = true;
		}
	}
	public void onUpdate(int delta) {
		if(Keyboard.isKeyDown(getLeftKey(movementType)) || Keyboard.isKeyDown(getRightKey(movementType))){
			isMoving = true;
		}else{
			isMoving = false;
		}
		if(Screen.TypeOfGame == GameType.SIDE_SCROLLER){
			double centerY = (Screen.getHeight() / 2) - Screen.translate_y;
			double distFromSideY = (Screen.getHeight() / 5) - Screen.translate_y;
			double distFromCenterY = centerY - y - height;
			boolean checkIfInTopBit = y + height - Screen.translate_y< (Screen.getHeight() / 5);
			if(checkIfInTopBit && Screen.translate_y < 0 && jumpDY > 0){
				Screen.translate_y += jumpDY * (delta * 0.1);
			}
			if(Screen.translate_y > 0){
				Screen.translate_y = 0;
			}
			if(!checkIfInTopBit && Screen.translate_y < 0.0 && jumpDY > 0){
				if(distFromCenterY > 0 && jumpDY > 0)
					Screen.translate_y += (jumpDY * (delta * 0.1)) * (distFromCenterY / distFromSideY);
			}
			if(y < 0){
				y = 0;
			}
			if(isJumping || isInAir){
				/*
				 * Screen moving down if player is in bottom area and is falling, otherwise it would
				 * make things really awkward all the time
				 */
				boolean checkIfInBottomBit = y + height > (Screen.getHeight() - (Screen.getHeight() / 5)) - Screen.translate_y;
				boolean isInBottomBounds = -Screen.translate_y + Screen.getHeight() < World.getWorldHeightInPixels();
				
				if(checkIfInBottomBit && isInBottomBounds && jumpDY < 0){
					Screen.translate_y += jumpDY * (delta * 0.1);
				}
				if(!checkIfInBottomBit && Screen.translate_y < 0.0 && jumpDY < 0){
					if(distFromCenterY < 0 && jumpDY < 0)
						Screen.translate_y += (jumpDY * (delta * 0.1)) * (-distFromCenterY / distFromSideY);
				}
				if(!isInBottomBounds){
					Screen.translate_y = -World.getWorldHeightInPixels() + Screen.getHeight();
				}
				/*
				 * End of screen moving down
				 */
				
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
					isSolidAbove = true;
					isJumping = true;
					isInAir = true;
				}else{
					isSolidAbove = false;
				}
			}
			
			if(y + height < World.getWorldHeightInPixels() - (World.BLOCK_SIZE() * 2)){
				isInAir = true;
			}else{
				isInAir = false;
				jumpDY = 0;
			}
			if(World.isSolidUnder(this)){
				isSolidBelow = true;
				isJumping = false;
				jumpDY = 0;
			}else{
				isSolidBelow = false;
				this.removeEffectFromBlock(World.blockList[World.blockEffectX][World.blockEffectY].getType());
			}
			
			if(isCrouching){
				if(hasClicked){
					height = crouchHeight;
					y += originalHeight - crouchHeight;
					hasClicked = false;
					
				}
				
			}else{
				if(!World.isSolidAbove(this) || !World.isSolidAtLocation((int) Math.round((x - (World.BLOCK_SIZE() / 2)) / World.BLOCK_SIZE()), (int) Math.round((y)/ World.BLOCK_SIZE() -1))
						|| !World.isSolidAtLocation((int) Math.round((x + width - (World.BLOCK_SIZE() / 2)) / World.BLOCK_SIZE()), (int) Math.round((y) / World.BLOCK_SIZE() -1))){
					if(!hasClicked){
						height = originalHeight;
						hasClicked = true;
						
					}
				}
			}
			while(World.isSolidAtLocation((int) Math.round(((x + ((width / 4) * 2)) - (World.BLOCK_SIZE() / 2)) / World.BLOCK_SIZE()), (int) Math.round((y + height + World.BLOCK_SIZE() / 2)/ World.BLOCK_SIZE() -1))
			|| World.isSolidAtLocation((int) Math.round((x + ((width / 4) * 3) - (World.BLOCK_SIZE() / 2)) / World.BLOCK_SIZE()), (int) Math.round((y + height + World.BLOCK_SIZE() / 2) / World.BLOCK_SIZE() -1))){
				y--;
			}
		}else if(Screen.TypeOfGame == GameType.RPG_STYLE){
			//TODO RPG STYLE ON UPDATE
		}
		
		/*
		 * Check if enemy is near and then target it. 
		 * 
		 */
		for(int i = 0; i < Engine.onScreenEntity.size(); i++){
			double x2 = Engine.onScreenEntity.get(i).getX();
			double y2 = Engine.onScreenEntity.get(i).getY();
			int width2= Engine.onScreenEntity.get(i).getWidth();
			int height2 = Engine.onScreenEntity.get(i).getHeight();
			if(isNear(Engine.onScreenEntity.get(i)) && Engine.onScreenEntity.get(i).getNumber() != number){
				Collector.add(new DrawParameters("box", x2, y2, width2, height2, -1, Color.Blue, 1, 1, true));
				//Check if right mouse button is pressed on the enemy to select it
				int mX = Mouse.getX();
				int mY = Mouse.getY();
				if(mX >= x2 && mX <= x2 + width && mY >= y2 && mY <= y2 + height){
					if(Mouse.rightMouseButtonPressed){
						targetedEnemy = (Enemy) Engine.onScreenEntity.get(i);
					}
				}
			}
		}
		if(targetedEnemy != null){
		int mX = Mouse.getX();
		int mY = Mouse.getY();
		double x2 = targetedEnemy.getX();
		double y2 = targetedEnemy.getY();
		int width2= targetedEnemy.getWidth();
		int height2 = targetedEnemy.getHeight();
		if(mX >= x2 && mX <= x2 + width && mY >= y2 && mY <= y2 + height){
			if(Mouse.rightMouseButtonPressed){
				targetedEnemy.orders(EnemyMovement.RIGHT, 20);
			}
		}
		}
		if(!isNear(targetedEnemy)){
			targetedEnemy = null;
		}
		
		handleInput(delta);
	}
	
	public boolean isNear(Entity other){
		//Gotta do them null checks man
		if(other != null){
			int oX =(int) other.getX() + getWidth() / 2;
			int oY =(int) other.getY() + getHeight() / 2;
		
			boolean one = oX >= x && oX <= x + width && oY >= y && oY <= y + height;
			boolean two = oX >= x - width && oX <= x + width * 2&& oY >= y - height && oY <= y + height * 2;
		
			if(one || two){
				return true;
			}
		}
		
		return false;
	}
	
	public void draw() {
		Color color = Color.White;
		/*
		 *     0
		 *   3   1
		 *     2
		 *
		 */
		 
		if(isMoving){
			if(direction == 0){
				color = Color.Black;
			}else if(direction == 1){
				color = Color.White;
			}else if(direction == 2){
				color = Color.Red;
			}else{
			}
		}
		if(targetedEnemy != null){
			double x2 = targetedEnemy.getX();
			double y2 = targetedEnemy.getY();
			int width2= targetedEnemy.getWidth();
			int height2 = targetedEnemy.getHeight();
			Collector.add(new DrawParameters("box", x2, y2, width2, height2, -1, Color.Red, 1, layer, true, false));
		}
		
		Collector.add(new DrawParameters("box", x, y, width, height, -1, color, 1, layer, true, false));
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
