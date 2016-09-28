package ua.mishkurov.pos.coins;

import ua.mishkurov.pos.exceptions.CoinManagerException;

import java.util.*;

/**
 *
 * @author Anton_Mishkurov
 */
public class CoinManager {

    private static final int INITIAL_COIN_QUANTITY = 100;

    private Map<Coin, Integer> coinRemainder;

    public CoinManager() {
        coinRemainder = new HashMap<>();
        for (Coin c : getAllowedCoins()) {
            coinRemainder.put(c, INITIAL_COIN_QUANTITY);
        }
    }

    public List<Coin> getChange(int changeValue) {
        if (changeValue < 0) {
            throw new IllegalArgumentException("Change cannot be negative. Change=" + changeValue);
        }
        List<Coin> change = new ArrayList<>();
        for (Coin c : CoinFactory.getSortedCoinsInReverseOrder()) {
            while (changeValue >= c.getValue() && changeValue != 0) {
                if (coinRemainder.get(c) > 0) {
                    change.add(c);
                    coinRemainder.put(c, coinRemainder.get(c) - 1);  //decrease coins quantity
                    changeValue -= c.getValue();
                } else {
                    break;
                }
            }
        }
        if (changeValue > 0) {
            throw new CoinManagerException("Cannot give change due lack of coins in the machine's tray. Not returned change=" + changeValue);
        }
        return change;
    }

    public Set<Coin> getAllowedCoins() {
        return CoinFactory.getSortedCoinsInReverseOrder();
    }

    public void putCoin(Coin coin) {
        if (!CoinFactory.getSortedCoinsInReverseOrder().contains(coin)) {
            throw new IllegalArgumentException("This Coin does not allowed! Value=" + coin.getValue());
        }
        coinRemainder.put(coin, coinRemainder.get(coin) + 1);
    }

}
