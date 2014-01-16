package springapp.service;

import springapp.domain.Product;

import java.io.Serializable;
import java.util.List;

public interface ProductManager extends Serializable {
    void increasePrice(int percentage);

    List<Product> getProducts();
}
