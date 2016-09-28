package ua.mishkurov.pos;

import java.util.*;

/**
 * @author Anton_Mishkurov
 */
public class ProductManager {

    private static final int INITIAL_QUANTITY = 100;
    private Map<Product, Integer> productRemainder;

    public ProductManager() {
        productRemainder = new HashMap<>();
        for (Product p : ProductFactory.getAvailableProducts()) {
            productRemainder.put(p, INITIAL_QUANTITY);
        }
    }

    public Set<Product> getProductList() {
        return productRemainder.keySet();
    }

    public Set<Product> getAvailableProducts() {
        Set<Product> result = new HashSet<>();
        for (Product p : productRemainder.keySet()) {
            if (productRemainder.get(p) > 0) {
                result.add(p);
            }
        }
        return result;
    }

    public void getProduct(Product product) {
        Integer currentProductRemainder = productRemainder.get(product);
        currentProductRemainder = currentProductRemainder == null ? 0 : currentProductRemainder;
        productRemainder.put(product, currentProductRemainder - 1);
    }

}
