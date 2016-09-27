package ua.mishkurov.pos;

/**
 * Created by Anton_Mishkurov on 9/27/2016.
 */
public class SalesLineItem {
    private int quantity;
    private Product product;

    public SalesLineItem(Product product, int quantity){
        this.product=product;
        this.quantity=quantity;
    }

    public int subtotal() {
        return product.getPrice() * quantity;
    }


    @Override
    public String toString() {
        return "Product: " + product + " qty: " + quantity + " subtotal=" + subtotal();
    }
}
