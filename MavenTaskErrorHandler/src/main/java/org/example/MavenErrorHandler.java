package org.example;

import java.util.Stack;

public class MavenErrorHandler {
    public boolean divideBy0(char[] array )
    {
        for (int i = 0; i < array.length; i++) {
            if(array[i] == '/' && array[i+=1] == '0'){
                System.err.println("Error: divide by 0 , try again");
                return true;
            }
        }
        return false;
    }
    public boolean incorrectBracketsNumber(char[] array){
        int brackets=0;
        for (char c : array) {
            if (c == '(') {
                brackets++;
            }
            if (c == ')') {
                brackets--;
            }
            if(brackets < 0)
            {
                System.err.println("Error: you start with wrong bracket , try again");
                return true;
            }
        }
        if (brackets != 0 )
        {
            System.err.println("Error: the number of brackets is not the same , try again");
            return true;
        }
        return false;
    }

    public boolean errorValidation(char[] c){
        Stack<Boolean> stack = new Stack<>();
        stack.push(divideBy0(c));
        stack.push(incorrectBracketsNumber(c));
        for (boolean b:stack) {
            if(b)
                return true;
        }
        return false;
    }
}