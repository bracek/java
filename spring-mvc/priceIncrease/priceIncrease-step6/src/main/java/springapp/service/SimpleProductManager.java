package springapp.service;

import springapp.domain.Product;
import springapp.repository.ProductDao;

import java.util.List;

public class SimpleProductManager implements ProductManager {
    // private List<Product> products;
    private ProductDao productDao;

    public List<Product> getProducts() {
        // return products;
        return productDao.getProductList();
    }

    public void increasePrice(final int percentage) {
        List<Product> products = productDao.getProductList();
        if (products != null) {
            for (Product product : products) {
                double newPrice = product.getPrice().doubleValue() *
                        (100 + percentage) / 100;
                product.setPrice(newPrice);
                productDao.saveProduct(product);
            }
        }
    }

    public void setProductDao(final ProductDao productDao) {
        this.productDao = productDao;
    }
//    public void setProducts(List<Product> products) {
//        this.products = products;
//    }
}
