package ua.mishkurov.pos;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Anton_Mishkurov on 9/27/2016.
 */
public class ProductManager {

    private static final int INITIAL_QUANTITY = 100;
    private Map<Product, Integer> allProducts;

    public ProductManager() {
        allProducts = new HashMap<>();
        for (Product p : ProductFactory.getAvailableProducts()) {
            allProducts.put(p, INITIAL_QUANTITY);
        }
    }

    public Collection<Product> getProductList() {
        return allProducts.keySet();
    }

    public Collection<Product> getAvailableProducts() {
        Collection<Product> result = new ArrayList<>();
        for (Product p : allProducts.keySet()) {
            if (allProducts.get(p) > 0) {
                result.add(p);
            }
        }
        return result;
    }

    public void getProduct(Product product) {
        allProducts.put(product, allProducts.get(product) - 1);
    }

}
