package com.age.helper;

public class Node {
	
	private Object oject;
	private Node target;
	
	public Node(){
	}
	
	public Node node(){
		return this.target;
	}
	public void node(Node node){
		this.target = node;
	}
	
	public Object object(){
		return this.object();
	}
	
	public void object(Object obj){
		this.oject = obj;
	}
}
