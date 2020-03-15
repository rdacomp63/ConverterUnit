package org.company;

public class PrintResult{
    void PrintResult(String keyA, String keyB, Float valueA, Float result) {
        String stringResult;
        String stringValueA;

        result = result*valueA;

        if(result % 1 == 0) {
            stringResult = ((Integer)Math.round(result)).toString();
        }
        else {
            stringResult = ((Float)(result)).toString();
        }

        if(valueA % 1 == 0) {
            stringValueA = ((Integer)Math.round(valueA)).toString();
        }
        else {
            stringValueA = ((Float)(valueA)).toString();
        }

        System.out.format("%s %s = %s %s%n", stringValueA, keyA, stringResult, keyB);
    }
}
