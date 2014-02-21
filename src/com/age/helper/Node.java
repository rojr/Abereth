package com.age.helper;

public class Node {


	//data data that should be stored in each node data
	private Object data;
    // node to point to next node in the linked list
    private Node nextNode;

	// constructor to initialize data
	public Node(Object data){
        this.data = data;
	}
    // empty contructor
    public Node() {
    }
	public Node getNextNode(){
		return this.nextNode;
	}
    public Object getData(){
        return this.data;
    }
	public void setNextNode(Node node){
		this.nextNode = node;
	}
	public void setData(Object data){
		this.data = data;
	}
}
