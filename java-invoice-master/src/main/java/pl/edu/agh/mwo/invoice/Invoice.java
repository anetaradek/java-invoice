package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Collection<Product> products = new ArrayList<Product>();

    public void addProduct(Product product) {
        if (product==null){
            throw new IllegalArgumentException("Product cannot be null");
        }
        products.add(product);
    }

    public void addProduct(Product product, Integer quantity) {
        if(quantity<=0){
            throw new IllegalArgumentException("Quantity cannot be null or negative");
        }
       for (int i = 0; i < quantity; i++) {
           products.add(product);
       }
    }

    public BigDecimal getSubtotal() {
        BigDecimal subtotal = BigDecimal.ZERO;
        if (!products.isEmpty()) {
            for (Product product : products) {
                subtotal = subtotal.add(product.getPrice());
            }
        }

        return subtotal;
    }

    public BigDecimal getTax() {
        BigDecimal subtotalTax = BigDecimal.ZERO;
        if (!products.isEmpty()) {
            for (Product product : products) {
                subtotalTax = subtotalTax.add(product.getPriceWithTax().subtract(product.getPrice()));
            }
        }
        return subtotalTax;
    }

    public BigDecimal getTotal() {
        BigDecimal total = BigDecimal.ZERO;
        if (!products.isEmpty()) {
            for (Product product : products) {
                total = total.add(product.getPriceWithTax());
            }
        }
        return total;
    }
}
