package org.example;

import org.xml.sax.ErrorHandler;

import java.util.Scanner;
import java.util.Stack;

public class Calculator {
    static char[] equation;
    static int currentPosition = 0;

    public  int[] numberAssemble(char[] tempArray, int index) {
        StringBuilder stringBuilder = new StringBuilder();
        while (index < tempArray.length && Character.isDigit(tempArray[index])) {
            stringBuilder.append(tempArray[index]);
            index++;
        }
        return new int[]{Integer.parseInt(stringBuilder.toString()), index};
    }

    public  void addElementToStack(Stack<Integer> stack, int curNum, char operator) {
        if (operator == '-') {
            curNum *= -1;
        } else if (operator == '/') {
            try{
                curNum = stack.pop() / curNum;
            }catch (ArithmeticException e)
            {
                InputHandler inputHandler = new InputHandler();
                System.err.println("Error : your equation have mathematical paradigm , try again ");
//                calculate(inputHandler.takeInput());
            }
        } else if (operator == '*') {
            curNum *= stack.pop();
        }
        stack.push(curNum);
    }

    public  int calculateEquation() {
        Stack<Integer> stack = new Stack<>();
        char operator = '+';

        int[] array;
        while (currentPosition < equation.length) {
            if (equation[currentPosition] != ' ') {
                if (Character.isDigit(equation[currentPosition])) {
                    array = numberAssemble(equation, currentPosition);
                    currentPosition = array[1]-1;
                    int currentNumber = array[0];
                    addElementToStack(stack, currentNumber, operator);
                } else if (equation[currentPosition] == '(') {
                    currentPosition++;
                    int currentNumber = calculateEquation();
                    addElementToStack(stack , currentNumber , operator);
                } else if (equation[currentPosition] == ')') {
                    break;
                } else {
                    operator = equation[currentPosition];
                }
            }
            currentPosition++;
        }

        int total =0;
        while (!stack.isEmpty())
        {
            total+= stack.pop();
        }
        return total;
    }

    public  int calculate(String s){
        equation= s.toCharArray();
        MavenErrorHandler mavenErrorHandler = new MavenErrorHandler();
        while (mavenErrorHandler.errorValidation(equation))
        {
            Scanner scanner = new Scanner(System.in);
            String tempString = scanner.nextLine();
            equation = tempString.toCharArray();
        }
        return calculateEquation();
    }
}
