package com.epam.mishkurov.pos;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * Created by Anton_Mishkurov on 9/26/2016.
 */
public class CoinFabric {
    private static Map<Integer, Coin> allowedCoins;
    private static SortedSet<Coin> sortedCoinsSet;

    //a bit hardcode for allowed coins
    static {
        allowedCoins = new HashMap<Integer, Coin>() {{
            put(1, new Coin(1));
            put(5, new Coin(5));
            put(10, new Coin(10));
            put(25, new Coin(25));
            put(50, new Coin(50));
        }};
        sortedCoinsSet = new TreeSet<>();
        sortedCoinsSet.addAll(allowedCoins.values());
    }

    public static Coin getCoin1() {
        return allowedCoins.get(1);
    }

    public static Coin getCoin5() {
        return allowedCoins.get(5);
    }

    public static Coin getCoin10() {
        return allowedCoins.get(10);
    }

    public static Coin getCoin25() {
        return allowedCoins.get(25);
    }

    public static Coin getCoin50() {
        return allowedCoins.get(50);
    }

    public static SortedSet<Coin> getSortedCoins() {
        return sortedCoinsSet;
    }

}
