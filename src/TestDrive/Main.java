package TestDrive;

import bigNumCalculate.Calculate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String... args) throws IOException {
        String result = null;
        Calculate calculate = new Calculate();

        String firstNumber = enterNumber("Enter first number ");
        String secondNumber = enterNumber("Enter second number ");

        System.out.println(calculate.division(firstNumber, secondNumber));
        if (true)
            return;

        System.out.printf("Enter operation (+ - * /) : ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String operator = reader.readLine();
        long start = System.currentTimeMillis();
        if (operator.equals("+"))
            result = calculate.plusOperation(firstNumber, secondNumber);
        else if (operator.equals("-"))
            result = calculate.minusOperation(firstNumber, secondNumber);
        else if (operator.equals("*"))
            result = calculate.multiplication(firstNumber, secondNumber);
        long time = System.currentTimeMillis() - start;
        System.out.println("Result = " + result);
        System.out.println("--------------------------------------");
        System.out.println("Length first number: " + firstNumber.length());
        System.out.println("Length second number: " + secondNumber.length());
        System.out.println("Runtime = " + time + " ms");

    }

    public static String enterNumber(String message) throws IOException {
        StringBuffer number = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.printf(message + ": ");
        String tmp = reader.readLine();

        boolean firstValueIsZero = true;
        for (int i = 0; i < tmp.length(); i++){
            Character c = tmp.charAt(i);
            if (i == 0 && c == '-'){
                number.append(c);
                continue;
            }
            if (!Character.isDigit(c)){
                throw new RuntimeException("Enter value is not number");
            }
            if (c == '0' && firstValueIsZero)
                continue;
            firstValueIsZero = false;
            number.append(c);
        }
        String result = number.toString();
        if (result.isEmpty())
            return null;
        return result;
    }
}
