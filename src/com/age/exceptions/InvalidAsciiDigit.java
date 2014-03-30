package com.age.exceptions;

/**
 * Created by apex on 28/03/14.
 */
public class InvalidAsciiDigit extends Exception {
    public InvalidAsciiDigit(){
        super("Number is not divisible by 3 so not a real ascii string");
    }
}
