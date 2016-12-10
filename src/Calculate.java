import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Calculate {
    public static void main(String... args) throws IOException {
        String result = null;
        Calculate calculate = new Calculate();

        String firstNumber = calculate.enterNumber("Enter first number ");
        String secondNumber = calculate.enterNumber("Enter second number ");
        System.out.printf("Enter operation (+ - * /) : ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String operator = reader.readLine();
        if (operator.equals("+"))
            result = calculate.plusOperation(firstNumber, secondNumber);
        else if (operator.equals("-"))
            result = calculate.minusOperation(firstNumber, secondNumber);
        System.out.println("Result = " + result);

    }

    private String minusOperation(String firstNumber, String secondNumber) {
        if (firstNumber.isEmpty())
            firstNumber = "0";
        if (secondNumber.isEmpty())
            return firstNumber;

        String result;

        // *******************************
        StringWrapper first = new StringWrapper(firstNumber);
        StringWrapper second = new StringWrapper(secondNumber);
        lengthСomparison(first, second);

        firstNumber = first.getValue();
        secondNumber = second.getValue();
        // *******************************

        StringBuffer buffer = new StringBuffer();

        // set minus
        boolean needMinus = false;
        for (int i = 0; i < firstNumber.length(); i++) {
            char c1 = firstNumber.charAt(i);
            char c2 = secondNumber.charAt(i);
            if (c1 < c2) {
                needMinus = true;
                break;
            }
        }
        if (needMinus){
            String tmp = firstNumber;
            firstNumber = secondNumber;
            secondNumber = tmp;
        }
        // *******************************

        boolean loan = false;
        for (int i = firstNumber.length()-1; i >= 0; i--){
            int firstValue = Integer.parseInt(String.valueOf(firstNumber.charAt(i)));
            int secondValue = Integer.parseInt(String.valueOf(secondNumber.charAt(i)));
            if (loan)
                firstValue --;
            if (firstValue < secondValue){
                firstValue += 10;
                loan = true;
            }else
                loan = false;
            int diff = firstValue - secondValue;
            buffer.append(diff);
        }
        if (needMinus)
            buffer.append('-');

        buffer.reverse();
        StringWrapper wrapper = new StringWrapper(buffer.toString());
        stripZero(wrapper);
        result = wrapper.getValue();
        return result;
    }

    private String plusOperation(String firstNumber, String secondNumber) {
        if (firstNumber.isEmpty())
            return secondNumber;
        if (secondNumber.isEmpty())
            return firstNumber;

        String result = null;
        int transfer = 0;

        StringWrapper first = new StringWrapper(firstNumber);
        StringWrapper second = new StringWrapper(secondNumber);
        lengthСomparison(first, second);

        firstNumber = first.getValue();
        secondNumber = second.getValue();


        StringBuffer buffer = new StringBuffer();

        for (int i = firstNumber.length()-1; i >= 0 ; i--){
            char firstValue = firstNumber.charAt(i);
            char secondValue = secondNumber.charAt(i);

            int sum = Integer.parseInt(String.valueOf(firstValue)) + Integer.parseInt(String.valueOf(secondValue)) + transfer;
            buffer.append(sum % 10);

            String sumString = String.valueOf(sum);
            if (sumString.length() > 1)
                transfer = Integer.parseInt(String.valueOf(sumString.charAt(0)));
            else
                transfer = 0;
        }
        if (transfer > 0)
            buffer.append(transfer);
        buffer.reverse();
        result = buffer.toString();
        return result;
    }

    private String enterNumber(String message) throws IOException {
        StringBuffer number = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.printf(message + ": ");
        String tmp = reader.readLine();

        boolean firstValueIsZero = true;
        for (int i = 0; i < tmp.length(); i++){
            Character c = tmp.charAt(i);
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

    private void lengthСomparison(StringWrapper first, StringWrapper second){
        String firstNumber = first.getValue();
        String secondNumber = second.getValue();

        int maxLength = firstNumber.length();
        StringBuffer tmp = new StringBuffer();

        if (maxLength < secondNumber.length()) {
            maxLength = secondNumber.length();
            int delta = maxLength - firstNumber.length();
            for (int i = 0; i < delta; i++){
                tmp.append('0');
            }

            tmp.append(firstNumber);
            firstNumber = tmp.toString();

        } else if (maxLength > secondNumber.length()){
            int delta = maxLength - secondNumber.length();
            for (int i = 0; i < delta; i++){
                tmp.append('0');
            }
            tmp.append(secondNumber);
            secondNumber = tmp.toString();
        }

        first.setValue(firstNumber);
        second.setValue(secondNumber);
    }

    private void stripZero(StringWrapper string){
        String s = string.getValue();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
            boolean firstValueIsZero = true;
            if (Character.isDigit(c)){
                if (c == '0' && firstValueIsZero)
                    continue;
                firstValueIsZero = false;
                buffer.append(c);
            }
            else{
                buffer.append(c);
            }
        }
        String result = buffer.toString();
        if (result.isEmpty())
            string.setValue("0");
        else
            string.setValue(result);

    }
}


class StringWrapper{
    private String value;

    StringWrapper(String string){
        this.value = string;
    }

    public void setValue(String string){
        this.value = string;
    }

    public String getValue(){
        return this.value;
    }
}