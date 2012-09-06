package com.ixonos.skillnet.logic.dao;

import com.ixonos.skillnet.logic.bean.Product;
import java.util.List;

public interface ProductDao {

    /**
     * Gets the product list.
     *
     * @return the product list
     */
    public List<Product> getProductList();

    /**
     * Update product.
     *
     * @param prod the prod
     */
    public void updateProduct(Product prod);

    /**
     * Adds the product.
     *
     * @param prod the prod
     */
    public void addProduct(Product prod);

    /**
     * Find by id.
     *
     * @param id the id
     *
     * @return the product
     */
   public Product findById(Integer id);

    /**
     * Delete product.
     *
     * @param id the id
     */
    public void deleteProduct(Integer id);
}