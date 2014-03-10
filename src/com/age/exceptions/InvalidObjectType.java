package com.age.exceptions;

/**
 * Created by apex on 10/03/14.
 */
public class InvalidObjectType extends Exception{
    public InvalidObjectType(String expected){
        super("Invalid object type. Expected " + expected);
    }
    public InvalidObjectType(Object expected){
        super("Invalid object type. Expected " + expected.toString());
    }
}
