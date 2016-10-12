package ua.mishkurov.pos;

import ua.mishkurov.pos.coins.Coin;
import ua.mishkurov.pos.coins.CoinFactory;
import ua.mishkurov.pos.products.Product;
import ua.mishkurov.pos.products.ProductFactory;

import java.util.*;

/**
 *
 * @author Anton_Mishkurov
 */
public class Main {
    private static Set<String> allowedCommands;

    public static void main(String[] args) {
        allowedCommands = new HashSet<String>() {{
            add("listProducts");
            add("listAllowedCoins");
            add("insertCoin");
            add("checkout");
            add("cancelAndGetChange");
            add("selectProduct");
            add("getSalesList");
            add("quit");
            add("help");
        }};

        Pos pos = new SimplePos();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("Enter command:");
            printAllowedCommands();
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
                    System.out.println(pos.getAllowedCoins());
                    break;
                case "insertCoin":
                    insertCoinCommand(sc, pos);
                    break;
                case "checkout":
                    checkoutCommand(pos);
                    break;
                case "getSalesList":
                    System.out.println(pos.getSalesList());
                    break;
                case "cancelAndGetChange":
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

    private static void selectProductCommand(Scanner sc, Pos pos) {
        System.out.println(pos.getAvailableProducts());
        System.out.println("Enter product id: ");
        Product productById = ProductFactory.getProductById(sc.nextInt());
        System.out.println("You've selected product:" + productById);
        pos.addProductToBasket(productById);
    }

    private static void insertCoinCommand(Scanner sc, Pos pos) {
        System.out.println("Enter coin's value:");
        pos.insertCoin(CoinFactory.getCoin(sc.nextInt()));
        System.out.println("Your deposit: " + pos.getDeposit());
    }

    private static void checkoutCommand(Pos pos) {
        List<Coin> change = pos.checkout();
        if (change == null) {
            System.out.println("Your deposit is:" + pos.getDeposit());
        } else {
            System.out.println("Your change is:" + change);
        }
    }

    private static void cancelCommand(Pos pos) {
        System.out.println("Canceling...");
        System.out.println("Yor change:");
        Collection<Coin> change = pos.cancelAndGetChange();
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
