package com.age.helper;

import java.util.ArrayList;

public class StringHelp {
	
	static char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z' };

	public static String[] breakString(String string) {
		int size = 0;
		char[] toArray = string.toCharArray();
		for (int i = 0; i < toArray.length; i++) {
			if (toArray[i] == ' ' && toArray[i] != toArray.length - 1) {
				size++;
			}
		}
		size++;
		String[] brokenUp = new String[size];
		ArrayList<Character> temp = new ArrayList<Character>();
		int wordsDone = 0;
		boolean wasLastSpace = false;
		for (int i = 0; i < toArray.length; i++) {
			if (toArray[i] != ' ') {
				wasLastSpace = false;
				temp.add(toArray[i]);
				if (i == toArray.length - 1) {
					brokenUp[wordsDone] = joinStringFromChar(temp);
					wordsDone++;
					temp.clear();
				}
			} else {
				if (!wasLastSpace) {
					brokenUp[wordsDone] = joinStringFromChar(temp);
					wordsDone++;
					temp.clear();
				}
				wasLastSpace = true;
			}
		}
		return brokenUp;
	}

	public static String joinStringFromString(ArrayList<String> s, boolean spaces){
		String sT = "";
		
		for(int i = 0; i < s.size(); i++){
			if(spaces && i != 0){
				sT += " " + s.get(i);
			}else{
				sT += s.get(i);
			}
		}
		
		return sT;
	}
	
	public static String joinStringFromString(String[] s, boolean spaces){
		String sT = "";
		
		for(int i = 0; i < s.length; i++){
			if(spaces && i != 0){
				sT += " " + s[i];
			}else{
				sT += s[i];
			}
		}
		
		return sT;
	}
	
	public static String joinStringFromChar(ArrayList<Character> c) {
		String s = "";
		for (int i = 0; i < c.size(); i++) {
			s += c.get(i);
		}
		return s;
	}
	public static String joinStringFromChar(char[] c) {
		String s = "";
		for (int i = 0; i < c.length; i++) {
			s += c[i];
		}
		return s;
	}

	public static void print(String s) {
		System.out.println(s);
	}

	public static String getNumberSuffix(int i) {
		String end = "st";
		if (i == 1) {
			end = "st";
		} else if (i == 2) {
			end = "nd";
		} else if (i == 3) {
			end = "rd";
		} else {
			end = "th";
		}
		return end;
	}

    public static String backWards(String in){
        String builder = "";
        for(int i = in.toCharArray().length - 1; i >= 0; i--){
            builder += in.toCharArray()[i];
        }
        return builder;
    }
}
