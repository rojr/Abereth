package com.gmail.robmadeyou.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Writer;

import javax.xml.stream.XMLStreamException;

import com.sun.xml.internal.stream.buffer.stax.StreamWriterBufferCreator;


public class ABWrite {
	//TODO
	public static void read(String filedir, String message){
		try {
			File f = new File(filedir);
			Thread.sleep(5000);
			System.out.println("bufer");
			BufferedWriter bf = new BufferedWriter(new Writer() {
				
				@Override
				public void write(char[] arg0, int arg1, int arg2) throws IOException {
					
				}
				
				@Override
				public void flush() throws IOException {
					// TODO Auto-generated method stub
					
				}
				
				@Override
				public void close() throws IOException {
					// TODO Auto-generated method stub
					
				}
			});
			Thread.sleep(5000);
			bf.write(message);
			bf.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
