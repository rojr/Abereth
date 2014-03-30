package com.age.helper;

import java.util.ArrayList;

/**
    Class to help with some of the tedious tasks some might
    have to do when trying to manipulate strings.
 */
public class StringHelp {

    /**
     * The alphabet in character format, all uppercase
     */
	public static char[] alphabet = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I',
			'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
			'W', 'X', 'Y', 'Z' };

    /**
     * Breaks up the words that where surrounded by spaces and places
     * them into their own index in the array. Similar to the unix terminal when
     * inputting many options, number of spaces are ignored, as long as the word is
     * surrounded by spaces. Very useful for things like managing input.
     * @param string input string you would like to break up
     * @return String[] array with words
     */
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

    /**
     *
     * @param s ArrayList you would like to join together
     * @param spaces adds spaces between each String
     * @return a full string
     */
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


    /**
     * Joins a string from an array of strings. Option to leave spaces between each index
     * @param s String array you would like to concatenate
     * @param spaces Option to have spaces between each index
     * @return join string from the S array
     */
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

    /**
     * Concatenates character array to form a string
     * @param c char array
     * @return concatenated string
     */
	public static String joinStringFromChar(ArrayList<Character> c) {
		String s = "";
		for (int i = 0; i < c.size(); i++) {
			s += c.get(i);
		}
		return s;
	}

    /**
     * Concatenates character array to form a string
     * @param c char array
     * @return concatenated string
     */
	public static String joinStringFromChar(char[] c) {
		String s = "";
		for (int i = 0; i < c.length; i++) {
			s += c[i];
		}
		return s;
	}

    /**
     * @param i number to find the suffix of
     * @return Suffix for number. e.g. 1 => st, 2 => nd etc.
     */
	public static String getNumberSuffix(int i) {
		String end = "st";
        String tmp = i+"";
        i = Integer.parseInt(tmp.toCharArray()[tmp.length() - 1]+"");
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

    /**
     *
     * @param in String you would like to invert;
     * @return in except backwards.
     */
    public static String backWards(String in){
        String builder = "";
        for(int i = in.toCharArray().length - 1; i >= 0; i--){
            builder += in.toCharArray()[i];
        }
        return builder;
    }
}
