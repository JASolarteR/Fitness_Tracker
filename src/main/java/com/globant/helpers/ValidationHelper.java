package com.globant.helpers;

import java.util.Scanner;

public class ValidationHelper {
    private static final Scanner scanner = new Scanner(System.in);

    public static int validateInt(String inputMessage) {
        Integer result = null;
        do {
            System.out.println(inputMessage);
            if (scanner.hasNextInt()) {
                result = scanner.nextInt();
                if (result < 0) {
                    System.out.println("Input must be a positive integer!");
                    result = null;
                }
            } else {
                System.out.println("Input must be an integer!");
                scanner.nextLine();
            }
        } while (result == null);
        return result;
    }

}
