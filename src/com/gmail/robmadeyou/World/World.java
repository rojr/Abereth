package com.gmail.robmadeyou.World;

import com.gmail.robmadeyou.Screen;
import com.gmail.robmadeyou.Block.Block;
import com.gmail.robmadeyou.Block.BlockAir;
import com.gmail.robmadeyou.Block.BlockStone;
import com.gmail.robmadeyou.Entity.Entity;
import com.gmail.robmadeyou.Input.Mouse;

public class World {
	
	public static int WorldArrayWidth = 0;
	public static int WorldArrayHeight = 0;
	/*
	 * Array list that holds the current world currently visible on screen
	 */
	private static Block[][] blockList;
	
	public static final int BLOCK_SIZE(){
		if(Screen.WorldTileSize != 32){
			return Screen.WorldTileSize;
		}
		return 32;
	};
	
	public static double gravity(int delta){
		return (delta * 0.02);
	};
	private static int camXDivided = (int) Math.round(Screen.translate_x / BLOCK_SIZE());
	private static int camYDivided = (int) Math.round(Screen.translate_y / BLOCK_SIZE());
	private static int camWidthDivided = (int) Math.round(Screen.getWidth() / BLOCK_SIZE()) + 1;
	private static int camHeightDivided = (int) Math.round(Screen.getHeight() / BLOCK_SIZE()) + 1;
	
	public static void setWorldDimensions(int x, int y){
		blockList = new Block[x][y];
		WorldArrayWidth = x;
		WorldArrayHeight = y;
	}
	
	public static Block getBlockTypeAtLocation(int x, int y){
		return blockList[x][y].getType();
		
	}
	public static void setArrayListClear(){
		for(int x = 0; x < WorldArrayWidth; x++){
			for(int y = 0; y < WorldArrayHeight; y++){
				blockList[x][y] = new BlockAir(x, y);
			}
		}
	}
	/*
	 * Checks collision for the legs of the entity, this will
	 * decide if player can go down, or when falling is possible to jump on the block and stop
	 * instead of falling constantly
	 */
	public static boolean isSolidUnder(Entity e){
		for(int sX = camXDivided; sX < camXDivided + camWidthDivided + 1; sX++){
			for(int y = camYDivided; y < camYDivided + camHeightDivided; y++){
				int x;
				if(sX >= 1){
					x = sX -1;
				}else{
					x = sX;
				}
				int eX = (int)e.getX();
				int eY = (int)e.getY();
				int eW = e.getWidth();
				int eH = e.getHeight();
				
				int bX = blockList[x][y].getX() * World.BLOCK_SIZE();
				int bY = blockList[x][y].getY() * World.BLOCK_SIZE();
				int bDimensions = World.BLOCK_SIZE();
				
				//Bottom left
				boolean one = eX >= bX && eX <= bX + bDimensions && eY + eH >= bY  - 5 && eY + eH <= bY + 5;
				//Bottom right
				boolean two = eX + eW >= bX && eX + eW <= bX + bDimensions && eY + eH >= bY - 5 && eY + eH <= bY + 5;
				if(one || two){
					if(blockList[x][y].isSolid()){
						e.setY((y * BLOCK_SIZE()) - eH);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	public static boolean isSolidAbove(Entity e){
		int eX = (int)e.getX();
		int eY = (int)e.getY();
		int eW = e.getWidth();
		int eH = e.getHeight();
		int bDimensions = World.BLOCK_SIZE();
		for(int sX = camXDivided; sX < camXDivided + camWidthDivided + 1; sX++){
			for(int y = camYDivided; y < camYDivided + camHeightDivided; y++){
				int x;
				if(sX >= 1){
					x = sX -1;
				}else{
					x = sX;
				}
				int bX = blockList[x][y].getX() * World.BLOCK_SIZE();
				int bY = blockList[x][y].getY() * World.BLOCK_SIZE();
				for(int x2 = 0; x2 <= eW / 4; x2++){
					boolean one = eX + (x2 * 4) >= bX && eX + (x2 * 4) <= bX + bDimensions && eY >= bY + bDimensions - 5 && eY <= bY + bDimensions + 5;
					if(one){
						if(blockList[x][y].isSolid()){
							e.setY(y * BLOCK_SIZE() + BLOCK_SIZE() + 5);
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	public static boolean isSolidLeft(Entity e){
		for(int sX = camXDivided; sX < camXDivided + camWidthDivided + 1; sX++){
			for(int y = camYDivided; y < camYDivided + camHeightDivided; y++){
				int x;
				if(sX >= 1){
					x = sX -1;
				}else{
					x = sX;
				}
				int eX = (int)e.getX();
				int eY = (int)e.getY();
				int eW = e.getWidth();
				int eH = e.getHeight();
				
				int bX = blockList[x][y].getX() * World.BLOCK_SIZE();
				int bY = blockList[x][y].getY() * World.BLOCK_SIZE();
				int bDimensions = World.BLOCK_SIZE();
				for(int y2 = 0; y2 <= e.getHeight() / 4; y2++){
					boolean one = eX <= bX + bDimensions + 3 && eX >= bX + bDimensions - 3 && eY - 1+ (4 * y2) >= bY && eY- 1 + (4 * y2) <= bY + bDimensions;
					if(one){
						if(blockList[x][y].isSolid()){
							e.setX(x * BLOCK_SIZE() + BLOCK_SIZE() + 2);
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	public static boolean isSolidRight(Entity e){
		for(int sX = camXDivided; sX < camXDivided + camWidthDivided + 1; sX++){
			for(int y = camYDivided; y < camYDivided + camHeightDivided; y++){
				int x;
				if(sX >= 1){
					x = sX -1;
				}else{
					x = sX;
				}
				int eX = (int)e.getX();
				int eY = (int)e.getY();
				int eW = e.getWidth();
				int eH = e.getHeight();
				
				int bX = blockList[x][y].getX() * World.BLOCK_SIZE();
				int bY = blockList[x][y].getY() * World.BLOCK_SIZE();
				int bDimensions = World.BLOCK_SIZE();
				for(int y2 = 0; y2 <= e.getHeight() / 4; y2++){
					boolean one = eX + eW<= bX + 3 && eX + eW>= bX - 3 && eY - 1+ (4 * y2) >= bY && eY- 1 + (4 * y2) <= bY + bDimensions;
					if(one){
						if(blockList[x][y].isSolid()){
							return true;
						}
					}
				}
			}
		}
		return false;
	}
	public static void onUpdate(){
		camXDivided = (int) Math.round(-Screen.translate_x / BLOCK_SIZE());
		camYDivided = (int) Math.round(-Screen.translate_y / BLOCK_SIZE());
		
		for(int sX = camXDivided; sX < camXDivided + camWidthDivided + 1; sX++){
			for(int y = camYDivided; y < camYDivided + camHeightDivided; y++){
				int x;
				if(sX >= 1){
					x = sX -1;
				}else{
					x = sX;
				}
				int mX = Math.round((Mouse.getX() - (int) Screen.translate_x) / 32);
				int mY = Math.round(Mouse.getY() / 32);
				if(Mouse.leftMouseButtonDown){
					blockList[mX][mY] = new BlockStone(mX,mY);
				}
				if(x < WorldArrayWidth && y < WorldArrayHeight){
					blockList[x][y].onUpdate();
				}
			}
		}
	}
}