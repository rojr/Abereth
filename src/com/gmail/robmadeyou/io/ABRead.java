package com.gmail.robmadeyou.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

import com.gmail.robmadeyou.Data;

public class ABRead {
	public static Object read(String filedir, Object obj){
		BufferedReader br = null;
       try {
		FileInputStream fi = new FileInputStream(filedir);
		try{
            String currentLine;
            br = new BufferedReader(new FileReader(filedir));
            while((currentLine = br.readLine()) != null){
            	
            }

        }catch(IOException e){
            e.printStackTrace();
        }finally {
            try{
                if(br != null) br.close();
            }catch(IOException ex){
                ex.printStackTrace();
            }
        }
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
    }
}
