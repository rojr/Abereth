package com.gmail.robmadeyou;

import com.gmail.robmadeyou.Entity.Entity;
import com.gmail.robmadeyou.Entity.Npc;
import com.gmail.robmadeyou.Entity.Player;
import com.gmail.robmadeyou.Object.Item;

public class Target {

	Entity player;
	Item item;
	String from = "";
	public Target(Entity player){
		this.player = player;
		from = "p";
	}
	public Target(Item item){
		this.item = item;
		from = "i";
	}
	public float getX(){
		if(from.equals("p")){
			return player.getX();
		}else{
			return item.getX();
		}
	}
	public float getY(){
		if(from.equals("p")){
			return player.getY();
		}else{
			return item.getY();
		}
	}
	public float getWidth(){
		if(from.equals("p")){
			return player.getWidth();
		}else{
			return item.getWidth();
		}
	}
	public float getHeight(){
		if(from.equals("p")){
			return player.getHeight();
		}else{
			return item.getHeight();
		}
	}
	public float getSpeed(){
		if(from.equals("p")){
			return player.getSpeed();
		}else{
			return 4;
		}
	}
}
