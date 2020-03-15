package org.company;

import java.util.Scanner;

/**
 * MyStatsApp is a simple console application which computes
 * basic statistics of a series of data values. The application takes
 * a file of data as its single argument.
 *
 * @author Rykovanov Dmitriy
 */
public class MyApp {
    String typeA, typeB;
    Float valueA, valueB, valueResult;
    String[] arr;
    Container container = Container.getInstance();

    /**
     * Runs the application
     *
     * @param args an array of String arguments to be parsed
     */
    public void run(String[] args) {
        printAppHelp();
        parseIn();
    }

    /*
     * Parses application arguments
     *
     * @Parser System.in application
     * @return result 0 of exit application
     */
    private void parseIn() {

        Scanner scn = new Scanner(System.in);
        PrintResult print = new PrintResult();

        while (scn.hasNextLine()) {
            String line = scn.nextLine();

            if(!checkLine(line))
                continue;

            typeA = arr[1];
            typeB = arr[4];

            try {
                valueA = Float.parseFloat(arr[0]);
            }
            catch (NumberFormatException e)
            {
                System.out.println("Number parameter cannot be characters");
                continue;
            }

            if (line.indexOf("?") > 0) {
                valueResult = container.Find(typeA, typeB);
                if (valueResult == null) {
                    System.out.println("Conversion not possible");
                }

                print.PrintResult(typeA, typeB, valueA, valueResult);
                continue;
            } else {
                try {
                    valueB = Float.parseFloat(arr[3]);
                } catch (NumberFormatException e) {
                    System.out.println("Number parameter cannot be characters");
                    continue;
                }
                AddContainer(typeA, typeB, valueA, valueB);
            }
        }
    }

    /**
     * Prints application help
     */
    private void printAppHelp() {
        // Краткая справка
        System.out.println("Format for data entry: Value Type = Value Type");
        System.out.println("Format for converting values: Value Type = ? A type");
    }

    /**
     * Add container data
     */
    private void AddContainer(String typeA, String typeB, Float valueA, Float valueB) {
        // Добавить в коллекцию прямое и обратное преобразование для ускорения поиска;
        container.Add(typeA, typeB, valueB/valueA);
        container.Add(typeB, typeA, valueA/valueB);
    }

    private Boolean checkLine(String line) {
        try {
            arr = line.split(" ");
            if (!line.contains("=")) {
                System.out.println("Not the character '=' or '?'");
                return false;
            }
            if (arr.length < 5) {
                System.out.println("Missing parameter");
                return false;
            }
            if (arr.length > 5) {
                System.out.println("More parameters than required");
                return false;
            }
        } catch (NullPointerException e) {
            System.out.println("Format error");
            return false;
        }

        if (!arr[1].matches("[a-zA-z]+") | !arr[4].matches("[a-zA-z]+")) {
            System.out.println("Parameter error");
            return false;
        }

        return true;
    }
}

