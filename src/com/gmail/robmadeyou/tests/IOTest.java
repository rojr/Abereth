package com.gmail.robmadeyou.tests;

import com.gmail.robmadeyou.io.ABWrite;

public class IOTest {
	public static void main(String[]args){
		ABWrite.read("ehlo.txt", "w");
		ABWrite.read("ehlo.txt", "r");
		ABWrite.read("ehlo.txt", "i");
	}
}
