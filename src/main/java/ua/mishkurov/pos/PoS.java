package ua.mishkurov.pos;

import java.time.LocalDate;
import java.util.*;

/**
 * @author Anton_Mishkurov
 */
public class PoS {
    private CoinManager coinManager;
    private ProductManager productManager;
    private List<Sale> saleList;
    private int deposit;
    private Map<Product, Integer> chosenProducts;

    public PoS() {
        coinManager = new CoinManager();
        productManager = new ProductManager();
        saleList = new ArrayList<>();
        chosenProducts = new HashMap<>();
        deposit = 0;
    }

    public void insertCoin(Coin coin) {
        deposit += coin.getValue();
        coinManager.putCoin(coin);
    }

    public Collection<Coin> cancelAndGetChange() {
        saleList = new ArrayList<>();
        List<Coin> change = coinManager.getChange(deposit);
        deposit = 0;
        return change;
    }

    public Collection<Coin> getAllowedCoins() {
        return coinManager.getAllowedCoins();
    }

    public Collection<Product> getAvailableProducts() {
        return productManager.getAvailableProducts();
    }

    public void addProductToBasket(Product product) {
        Integer productQuantity = chosenProducts.get(product);
        productQuantity = productQuantity == null ? 0 : productQuantity;
        chosenProducts.put(product, productQuantity + 1);
    }

    public int getDeposit() {
        return deposit;
    }

    public List<Sale> getSalesList() {
        return saleList;
    }

    public List<Coin> checkout() {
        Sale sale = new Sale(LocalDate.now());
        for (Product p : chosenProducts.keySet()) {
            sale.makeLineItem(p, chosenProducts.get(p));
        }
        if (deposit < sale.total()) {
            System.out.println("Deposit is to low :-( Insert more coins.");
            return null;
        }
        int changeValue = deposit - sale.total();
        List<Coin> change = coinManager.getChange(changeValue);
        //decrease number of products in stock
        //TODO
        deposit = 0;
        chosenProducts = new HashMap<>();
        saleList.add(sale);
        return change;
    }
}
