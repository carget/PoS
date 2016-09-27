package ua.mishkurov.pos;

import java.util.*;

/**
 * Created by Anton_Mishkurov on 9/26/2016.
 */
public class Main {
    private static Set<String> allowedCommands;

    public static void main(String[] args) {
        allowedCommands = new HashSet<String>() {{
            add("listProducts");
            add("listAllowedCoins");
            add("insertCoin");
            add("pay");
            add("cancel");
            add("selectProduct");
            add("getSalesList");
            add("quit");
            add("help");
        }};

        PoS pos = new PoS();
        Scanner sc = new Scanner(System.in);

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
                    System.out.println(pos.getAvailableProducts());
                    break;
                case "listAllowedCoins":
                    System.out.println(pos.getAllowedCoin());
                    break;
                case "insertCoin":
                    insertCoinCommand(sc, pos);
                    break;
                case "pay":
                    payCommand(pos);
                    break;
                case "getSalesList":
                    System.out.println(pos.getSalesList());
                    break;
                case "cancel":
                    cancelCommand(pos);
                    break;
                case "selectProduct":
                    selectProductCommand(sc, pos);
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

    private static void selectProductCommand(Scanner sc, PoS pos) {
        System.out.println(pos.getAvailableProducts());
        System.out.println("Enter product id: ");
        Product productById = ProductFactory.getProductById(sc.nextInt());
        System.out.println("You've selected product:" + productById);
        pos.chooseProduct(productById);
    }

    private static void insertCoinCommand(Scanner sc, PoS pos) {
        System.out.println("Enter coin's value:");
        pos.insertCoin(CoinFactory.getCoin(sc.nextInt()));
        System.out.println("Your deposit: " + pos.getDeposit());
    }

    private static void payCommand(PoS pos) {
        List<Coin> change = pos.pay();
        if (change == null) {
            System.out.println("Your deposit is:" + pos.getDeposit());
        } else {
            System.out.println("Your change is:" + change);
        }
    }

    private static void cancelCommand(PoS pos) {
        System.out.println("Canceling...");
        System.out.println("Yor change:");
        Collection<Coin> change = pos.cancel();
        System.out.println(change);
    }

    private static void printAllowedCommands() {
        System.out.println("Allowed commands:");
        for (String command : allowedCommands) {
            System.out.print(command + ", ");
        }
        System.out.println();
    }

}
