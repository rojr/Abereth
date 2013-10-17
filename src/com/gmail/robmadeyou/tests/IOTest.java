package com.gmail.robmadeyou.tests;

import java.util.Scanner;

import com.gmail.robmadeyou.io.ABWrite;

public class IOTest {
	public static void main(String[]args){
		Integer[] triNum = new Integer[50];
		triNum[0] = 1;
		for(int i = 1; i < triNum.length; i++){
			triNum[i] = triNum[i-1] + i + 1;
		}
		System.out.println("The first 6 triangular numbers are:");
		for(int i = 0; i < triNum.length; i++){
			ABWrite.writeString("Trinum.txt", "num:" + triNum[i], true);
		}
	}
}
