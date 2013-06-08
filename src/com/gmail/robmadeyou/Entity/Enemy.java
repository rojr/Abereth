package com.gmail.robmadeyou.Entity;

import java.util.ArrayList;

import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Draw.Collector;
import com.gmail.robmadeyou.Draw.Collector.DrawParameters;
import com.gmail.robmadeyou.Effects.Color;
import com.gmail.robmadeyou.Screen.GameType;
import com.gmail.robmadeyou.World.World;

public class Enemy extends Entity {
	public ArrayList<moveUpdate> MovementArray = new ArrayList<moveUpdate>();
	private double x, y, dX, dY;
	private int number;
	private int width, height;
	private double speed;
	private boolean isJumping = false;
	private boolean isInAir = false;
	private boolean usingLogic = false;
	private double jumpDY = 0;
	private double finalJumpDY = 7;
	private int layer = 0;
	private int delta;
	private Color color;
	private double speedDecrease;
	/*
	 * Directions are as follows (think of a compass)
	 * 
	 *    0
	 *  3   1
	 *    2
	 */
	private int directionFacing = 1;
	public Enemy(double x, double y, int width, int height){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.speed = 1;
		this.color = Color.White;
		this.speedDecrease = speed * 0.8;
	}
	//TODO tidy up pls
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
	public void setLayer(int layer){
		this.layer = layer;
	}
	public void setColor(Color color){
		this.color = color;
	}
	public void setLogic(boolean args){
		this.usingLogic = args;
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
	public int getLayer(){
		return layer;
	}
	public Color getColor(){
		return color;
	}
	public int getNumber() {
		return number;
	}
	public void onUpdate(int delta){
		this.delta = delta;
		gameTypeLogic();
		
		if(MovementArray.size() > 0){
			MovementArray.get(0).move();
		}
		
		draw();
		if(usingLogic){
			logic();
		}
	}
	public void orders(EnemyMovement direction){
		MovementArray.add(new moveUpdate(1, direction));
	}
	public void orders(EnemyMovement direction, int amount){
		MovementArray.add(new moveUpdate(amount, direction));
	}
	private void logic(){
		
	}
	public void moveLeft(){
		if(!World.isSolidLeft(this)){
			x -= (delta * (speed - speedDecrease));
		}
	}
	public void moveRight(){
		if(!World.isSolidRight(this)){
			x += (delta * (speed - speedDecrease));
		}
	}
	public void moveUp(){
		if(Screen.TypeOfGame != GameType.SIDE_SCROLLER){
			if(!World.isSolidAbove(this)){
				y -= (delta * (speed - speedDecrease));
			}
		}
	}
	public void moveDown(){
		if(Screen.TypeOfGame != GameType.SIDE_SCROLLER){
			if(!World.isSolidUnder(this)){
				y += (delta * (speed - speedDecrease));
			}
		}
	}
	public void jump(){
		if(!isJumping){
			isJumping = true;
			jumpDY = finalJumpDY;
		}
	}
	private void gameTypeLogic(){
		if(Screen.TypeOfGame == GameType.SIDE_SCROLLER){
			if(isJumping || isInAir){
				y -= jumpDY * (delta * 0.1);
				jumpDY = jumpDY  - World.gravity(delta);
				if(jumpDY < -8){
					jumpDY = -8;
				}
				if(y + height > World.getWorldHeightInPixels() - (World.BLOCK_SIZE() * 2)){
					isJumping = false;
					jumpDY = 0;
				}
				if(World.isSolidUnder(this)){
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
			while(World.isSolidAtLocation((int) Math.round(((x + ((width / 4) * 2)) - (World.BLOCK_SIZE() / 2)) / World.BLOCK_SIZE()), (int) Math.round((y + height + World.BLOCK_SIZE() / 2)/ World.BLOCK_SIZE() -1))
					|| World.isSolidAtLocation((int) Math.round((x + ((width / 4) * 3) - (World.BLOCK_SIZE() / 2)) / World.BLOCK_SIZE()), (int) Math.round((y + height + World.BLOCK_SIZE() / 2) / World.BLOCK_SIZE() -1))){
						y--;
					}
		}//TODO Logic for other game modes for AI
	}
	public void draw() {
		Collector.add(new DrawParameters("box", x, y, width, height, -1, color, layer, true));
	}
	public enum EnemyMovement{
		UP,LEFT,DOWN,RIGHT,JUMP,WAIT;
	}
	public class moveUpdate{
		private int amount;
		private EnemyMovement direction;
		private int currentTick;
		public moveUpdate(int amount,  EnemyMovement direction){
			this.amount = amount;
			this.direction = direction;
			this.currentTick = 0;
		}
		public void move(){
			currentTick++;
			if(Screen.TypeOfGame != Screen.GameType.SIDE_SCROLLER){
				if(direction == EnemyMovement.UP){
					moveUp();
				}else if(direction == EnemyMovement.DOWN){
					moveDown();
				}
			}else if(direction == EnemyMovement.LEFT){
				moveLeft();
			}else if(direction == EnemyMovement.RIGHT){
				moveRight();
			}else if(direction == EnemyMovement.WAIT){
				
			}else if(direction == EnemyMovement.JUMP){
				jump();
			}
			if(currentTick >= amount){
				MovementArray.remove(0);
			}
		}
	}
}
