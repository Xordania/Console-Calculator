import com.sun.deploy.util.ArrayUtil;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.*;

public class Main {
    public static void main(String[] args){
        char[] operators = {'+', '-', '*', '/'};
        String input = takeInput();
        List<String> equation = splitEquation(input, operators);

        System.out.println("Answer = " + calculateAnswer(equation));
    }

    public static String calculateAnswer(List<String> equation){

        while(equation.size() > 1){
            equation.set(0, calculateSection(equation.get(0), equation.get(1).charAt(0), equation.get(2)));
            equation.remove(2);
            equation.remove(1);
        }

        return equation.get(0);
    }

    public static String calculateSection(String firstConstant, char operator, String secondConstant){
        int firstNumber = Integer.parseInt(firstConstant);
        int secondNumber = Integer.parseInt(secondConstant);

        //This takes the two given constants and runs the appreciate operation on them and returns the value
        switch (operator) {
            case '+':
                return Integer.toString(firstNumber + secondNumber);
            case '-':
                return Integer.toString(firstNumber - secondNumber);
            case '*':
                return Integer.toString(firstNumber * secondNumber);
            case '/':
                return Integer.toString(firstNumber / secondNumber);
            default:
                return "It's fucked";
        }

    }

    public static List<String> splitEquation(String input, char[] operators){
        //This array will hold the different sections of the input when it's split at the operators
        List<String> partitionedInput = new ArrayList<>();
        String currentSection = new String();

        boolean currentlyReading = true;

        //Runs through every character within the input String and sorts them into an array
        for(int i = 0; i < input.length(); i++){
            //Checks if the currently iterated over character is equal to the value of an operator
            for(int j = 0; j < operators.length; j++) {
                //if the value does equal that of an operator currently reading is set to true
                if(input.charAt(i) == operators[j]){
                    currentlyReading = false;
                }
            }

            //Add the currently iterated value to the currentSection String
            if(currentlyReading) {
                currentSection += input.charAt(i);
            }

            //This adds the current section to the array then the operator into it's own allocated slot
            else{
                partitionedInput.add(currentSection);
                partitionedInput.add(String.valueOf(input.charAt(i)));
                currentSection = "";
                currentlyReading = true;
            }

            if(i == input.length() -1){
                partitionedInput.add(Character.toString(input.charAt(input.length() - 1)));
            }
        }
        return partitionedInput;
    }

    public static String takeInput(){
        Scanner scanner = new Scanner(System.in);
        String input;
        System.out.println("Enter an equation");

        //Uses a scanner to take input from the user
        input = scanner.next();
        return input;
    }


}
