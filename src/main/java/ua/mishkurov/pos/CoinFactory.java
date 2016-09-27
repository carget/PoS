package ua.mishkurov.pos;

import ua.mishkurov.pos.exceptions.CoinManagerException;

import java.util.HashMap;
import java.util.Map;
import java.util.SortedSet;
import java.util.TreeSet;

/**
 * @author Anton_Mishkurov
 */
public class CoinFactory {
    private static Map<Integer, Coin> allowedCoins;
    private static SortedSet<Coin> sortedCoinsSet;

    private CoinFactory() {}

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

    public static Coin getCoin(int value) {
        Coin coin = allowedCoins.get(value);
        if (coin == null) {
            throw new CoinManagerException("This value is not allowed. Value=" + value);
        }
        return coin;
    }

    public static SortedSet<Coin> getSortedCoins() {
        return sortedCoinsSet;
    }

}
