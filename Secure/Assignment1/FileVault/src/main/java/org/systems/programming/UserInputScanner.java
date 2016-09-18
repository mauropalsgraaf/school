package org.systems.programming;

import java.util.Scanner;

public class UserInputScanner {

    public static String getInputFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine().replace("\\n", System.lineSeparator());
    }
}
