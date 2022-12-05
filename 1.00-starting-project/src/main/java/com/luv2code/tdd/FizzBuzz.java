package com.luv2code.tdd;

public class FizzBuzz {

    public static String computer(int i) {

        if((i % 3 == 0) && (i % 5 == 0)) {
            return "FizzBuzz";
        }else if(i % 3 == 0) {
            return "Fizz";
        } else if(i % 5 == 0) {
            return "Buzz";
        } else {
            return Integer.toString(i);
        }
    }
}
