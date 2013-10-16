package com.gmail.robmadeyou.io;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;

public class ABRead {
	public static Object read(String filedir, Object obj){
       try {
		FileInputStream fi = new FileInputStream(filedir);
		ObjectInputStream os = new ObjectInputStream(fi);
		
		return os.readObject();
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
       
    }
}
