package com.epam.mishkurov.pos;

import java.util.*;

/**
 * Created by Anton_Mishkurov on 9/26/2016.
 */
public class CoinManager {

    private static final int INITIAL_COIN_QUANTITY = 100;

    private Map<Coin, Integer> allCoins;

    public CoinManager() {
        for (Coin c : getAllowedCoins()) {
            allCoins.put(c, INITIAL_COIN_QUANTITY);
        }
    }

    public List<Coin> getChange(int changeValue) {
        if (changeValue < 0) {
            throw new IllegalArgumentException("Change cannot be negative. Change=" + changeValue);
        }
        List<Coin> change = new ArrayList<>();
        while (changeValue != 0) {
            for (Coin c : CoinFabric.getSortedCoins()) {
                if (changeValue < c.getValue()) {
                    continue;
                }
                if (allCoins.get(c) > 0) {
                    change.add(c);
                    allCoins.put(c, allCoins.get(c) - 1);  //decrease coins quantity
                    changeValue -= c.getValue();
                }
            }
        }
        return change;
    }

    public Set<Coin> getAllowedCoins() {
        return CoinFabric.getSortedCoins();
    }

    public void putCoin(Coin coin) {
        if (!CoinFabric.getSortedCoins().contains(coin)) {
            throw new IllegalArgumentException("This Coin does not allowed! Value=" + coin.getValue());
        }
        allCoins.put(coin, allCoins.get(coin) + 1);
    }

}
