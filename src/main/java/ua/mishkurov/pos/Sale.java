package ua.mishkurov.pos;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Anton_Mishkurov
 */
public class Sale {
    private LocalDate date;
    private List<SalesLineItem> salesLineItemList;

    public Sale(LocalDate date) {
        this.date = date;
        salesLineItemList= new ArrayList<>();
    }

    public int total() {
        int total = 0;
        for (SalesLineItem lineItem : salesLineItemList) {
            total += lineItem.subtotal();
        }
        return total;
    }

    public void makeLineItem(Product product, int quantity) {
        salesLineItemList.add(new SalesLineItem(product, quantity));
    }

    @Override
    public String toString() {
        return "Date:" + date + " Items: " + salesLineItemList;
    }
}
