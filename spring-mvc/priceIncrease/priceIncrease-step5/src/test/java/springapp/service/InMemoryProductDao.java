package springapp.repository;

import springapp.domain.Product;

import java.util.List;

public class InMemoryProductDao implements ProductDao {
    private List<Product> productList;

    public InMemoryProductDao(final List<Product> productList) {
        this.productList = productList;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void saveProduct(final Product prod) {
    }
}
