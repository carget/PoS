package com.epam.mishkurov.pos;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * Created by Anton_Mishkurov on 9/26/2016.
 */
public class Main {
    private static Set<String> allowedCommands;

    public static void main(String[] args) {
        allowedCommands = new HashSet<String>() {{
            add("listProducts");
            add("insertCoin");
            add("pay");
            add("cancel");
            add("selectProduct");
            add("quit");
            add("help");
        }};

        Scanner sc = new Scanner(System.in);
        PoS pos = new PoS();

        while (true) {
            System.out.println("Enter command:");
            String command = sc.nextLine();
            if (!allowedCommands.contains(command)) {
                System.out.println("Enter allowed command");
                printAllowedCommands();
                continue;
            }
            switch (command) {
                case "listProducts":
                    break;
                case "insertCoin":
                    break;
                case "pay":
                    break;
                case "cancel":
                    break;
                case "selectProduct":

                    break;
                case "quit":
                    System.exit(0);
                    break;
                case "help":
                    printAllowedCommands();
                    break;
            }
        }


    }

    private static void printAllowedCommands() {
        System.out.println("Allowed commands:");
        for (String c : allowedCommands) {
            System.out.println(c);
        }
    }

}
