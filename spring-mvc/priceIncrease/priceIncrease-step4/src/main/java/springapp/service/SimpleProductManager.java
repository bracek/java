package springapp.service;

import springapp.domain.Product;

import java.util.List;

public class SimpleProductManager implements ProductManager {

    private List<Product> products;

    public List<Product> getProducts() {
        return products;
    }

    public void increasePrice(final int percentage) {
        if (products != null) {
            for (Product product : products) {
                double newPrice = product.getPrice().doubleValue() *
                        (100 + percentage) / 100;
                product.setPrice(newPrice);
            }
        }
    }

    public void setProducts(final List<Product> products) {
        this.products = products;
    }
}
