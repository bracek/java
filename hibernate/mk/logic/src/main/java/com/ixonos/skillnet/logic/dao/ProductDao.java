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
    public void updateProduct(final Product prod);

    /**
     * Adds the product.
     *
     * @param prod the prod
     */
    public void addProduct(final Product prod);

    /**
     * Find by id.
     *
     * @param id the id
     *
     * @return the product
     */
   public Product findById(final Integer id);

    /**
     * Delete product.
     *
     * @param id the id
     */
    public void deleteProduct(final Integer id);
}
