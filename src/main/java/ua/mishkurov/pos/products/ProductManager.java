package ua.mishkurov.pos.products;

import ua.mishkurov.pos.exceptions.ProductManagerException;

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

    public void decreaseProductsAmountInStock(Map<Product, Integer> basket) {
        for (Product p : basket.keySet()) {
            Integer currentProductRemainder = productRemainder.get(p);
            currentProductRemainder = currentProductRemainder == null ? 0 : currentProductRemainder;
            Integer basketProductQty = basket.get(p);
            basketProductQty = basketProductQty == null ? 0 : basketProductQty;
            Integer newRemainder = currentProductRemainder - basketProductQty;
            if (newRemainder < 0) {
                throw new ProductManagerException("You cannot buy more products than available! " +
                        "Product remainder=" + newRemainder);
            }
        }

    }

}
