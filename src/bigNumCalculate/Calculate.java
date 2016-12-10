package bigNumCalculate;


import java.util.ArrayList;

public class Calculate {

    public String minusOperation(String firstNumber, String secondNumber) {
        if (firstNumber == null)
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

    public String plusOperation(String firstNumber, String secondNumber) {
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

    public String multiplication(String firstNumber, String secondNumber){
        if (firstNumber.equals("0") || secondNumber.equals("0"))
            return "0";
        String result = "0";
        ArrayList<String> arrayMull = new ArrayList<String>();
        for (int i = secondNumber.length()-1; i >= 0 ; i--){
            StringBuffer buffer = new StringBuffer();
            String auxiliaryLine = "";
            for (int j = 0; j < secondNumber.length()-i-1; j++)
                auxiliaryLine += "0";
            String mul = String.valueOf(secondNumber.charAt(i));
            buffer.append(simpleMultiplication(firstNumber, mul));
            buffer.append(auxiliaryLine);
            arrayMull.add(buffer.toString());
        }

        while (!arrayMull.isEmpty()){
            result = plusOperation(result, arrayMull.remove(0));
        }
        return result;
    }

    private String simpleMultiplication(String number, String mull){
        String result;
        StringBuffer buffer = new StringBuffer();

        int mul = Integer.parseInt(mull);
        int transfer = 0;
        for (int i = number.length()-1; i >= 0; i--){
            int x = Integer.parseInt(String.valueOf(number.charAt(i)));
            int tmp = x * mul + transfer;
            buffer.append(tmp % 10);
            String tmpString = String.valueOf(tmp);
            if (tmpString.length() > 1){
                transfer = Integer.parseInt(String.valueOf(tmpString.charAt(0)));
            } else
                transfer = 0;
        }
        if (transfer > 0)
            buffer.append(transfer);
        buffer.reverse();

        StringWrapper wrapper = new StringWrapper(buffer.toString());
        stripZero(wrapper);
        result = wrapper.getValue();
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
        boolean firstValueIsZero = true;
        for (int i = 0; i < s.length(); i++){
            char c = s.charAt(i);
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
