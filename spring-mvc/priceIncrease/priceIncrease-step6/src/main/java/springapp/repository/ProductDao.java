package springapp.repository;

import springapp.domain.Product;

import java.util.List;

public interface ProductDao {
    List<Product> getProductList();

    void saveProduct(Product prod);
}
