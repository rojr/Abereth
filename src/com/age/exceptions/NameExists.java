package com.age.exceptions;

/**
 * Created by apex on 19/03/14.
 */
public class NameExists extends Exception{
    public NameExists(){
        super("Name already exists");
    }
}
