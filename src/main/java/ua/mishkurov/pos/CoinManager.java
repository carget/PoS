package ua.mishkurov.pos;

import ua.mishkurov.pos.exceptions.CoinManagerException;

import java.util.*;

/**
 *
 * Created by Anton_Mishkurov on 9/26/2016.
 */
public class CoinManager {

    private static final int INITIAL_COIN_QUANTITY = 100;

    private Map<Coin, Integer> allCoins;

    public CoinManager() {
        allCoins = new HashMap<>();
        for (Coin c : getAllowedCoins()) {
            allCoins.put(c, INITIAL_COIN_QUANTITY);
        }
    }

    public List<Coin> getChange(int changeValue) {
        if (changeValue < 0) {
            throw new IllegalArgumentException("Change cannot be negative. Change=" + changeValue);
        }
        List<Coin> change = new ArrayList<>();
        for (Coin c : CoinFactory.getSortedCoins()) {
            while (changeValue >= c.getValue() && changeValue != 0) {
                if (allCoins.get(c) > 0) {
                    change.add(c);
                    allCoins.put(c, allCoins.get(c) - 1);  //decrease coins quantity
                    changeValue -= c.getValue();
                } else {
                    break;
                }
            }
        }
        if (changeValue > 0) {
            throw new CoinManagerException("Cannot give change due lack of coins in the machine. Not returned change=" + changeValue);
        }
        return change;
    }

    public Set<Coin> getAllowedCoins() {
        return CoinFactory.getSortedCoins();
    }

    public void putCoin(Coin coin) {
        if (!CoinFactory.getSortedCoins().contains(coin)) {
            throw new IllegalArgumentException("This Coin does not allowed! Value=" + coin.getValue());
        }
        allCoins.put(coin, allCoins.get(coin) + 1);
    }

}
