package ua.mishkurov.pos;

import ua.mishkurov.pos.coins.Coin;
import ua.mishkurov.pos.products.Product;
import ua.mishkurov.pos.sales.Sale;

import java.util.Collection;
import java.util.List;
import java.util.Map;

/**
 * @author Anton_Mishkurov
 */
public interface Pos {
    void insertCoin(Coin coin);

    Collection<Coin> cancelAndGetChange();

    Collection<Coin> getAllowedCoins();

    Collection<Product> getAvailableProducts();

    void addProductToBasket(Product product);

    int getDeposit();

    List<Sale> getSalesList();

    List<Coin> checkout();

    Map<Product, Integer> getBasket();
}
