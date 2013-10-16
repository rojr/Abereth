package com.gmail.robmadeyou.commands;

import java.io.File;
import java.io.IOException;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.jar.JarFile;
import java.util.jar.JarEntry;
import java.util.Enumeration;
import java.net.URL;

import java.lang.reflect.*;

public class TheArchitect {
	
	private static ArrayList<Extension> extensioList = new ArrayList<Extension>();
	
	public static void searchPackages() throws IOException{
		File file = new File(System.getProperty("user.home") + "/myjar.jar");
        
        URLClassLoader clazzLoader = URLClassLoader.newInstance(new URL[]{file.toURI().toURL()});
//      System.class.getClassLoader()
         
        JarFile jarFile = new JarFile(file);
        Enumeration<JarEntry> entries = jarFile.entries();
         
        while (entries.hasMoreElements()) {
            JarEntry element = entries.nextElement();
            if (element.getName().endsWith(".class")) {
                try {
                    Class c = clazzLoader.loadClass(element.getName().replaceAll(".class", "").replaceAll("/", "."));
                    if(c.getCanonicalName().equals("Task1.Test")){
                    	Method get = c.getMethod("get", null);
                    	get.notify();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
	}
	
	public static void searchPackages(String location){
		
	}
	
	public static void addExtension(Extension e){
		extensioList.add(e);
	}
	
	public void in(String message){
		for(Extension e : extensioList){
			if(message.startsWith(e.getActivation())){
				message.substring(e.getActivation().length() + 1);
				System.out.println(message);
				e.active(message);
			}
		}
	}
}
