package com.gmail.robmadeyou.Commands;

public abstract class Extension {
	
	private String location, name;
	private String activator;
	public Extension(String name, String location, String activation){
		this.location = location;
		this.name = name;
		this.activator = activation;
		TheArchitect.addExtension(this);
	}
	public void print(){
		System.out.println("Hello!");
	}
	public String getName(){
		return name;
	}
	public String getActivation(){
		return activator;
	}
	public abstract void active(String s);
}
