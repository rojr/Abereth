package com.gmail.robmadeyou.io;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;



public class ABWrite {
	public static void writeString(String filedir, String mess, boolean newLine){
		try
		{
		    FileWriter fw = new FileWriter(filedir, true); //the true will append the new data
		    BufferedWriter bw = new BufferedWriter(fw);
		    if(newLine){
		    	bw.newLine();
		    }
		    for(char c : mess.toCharArray()){
		    	if(c != '\n'){
		    		bw.write(c);
		    	}else{
		    		bw.newLine();
		    	}
		    }
		    
		    
		    
		    bw.close();
		}
		catch(IOException ioe)
		{
		    System.err.println("IOException: " + ioe.getMessage());
		}
	}
	public static void writeString(String filedir, String[] mess){
		try {
			FileWriter file = new FileWriter(filedir);
			BufferedWriter bw = new BufferedWriter(file);
			for(int i = 0; i < mess.length; i++){
				bw.write(mess[i]);
			}
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
