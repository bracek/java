package com.ixonos.skillnet.logic.dao;

import com.ixonos.skillnet.logic.bean.Product;
import java.util.List;

public interface ProductDao {

    /**
     * Gets the product list.
     *
     * @return the product list
     */
     List<Product> getProductList();

    /**
     * Update product.
     *
     * @param prod the prod
     */
     void updateProduct(final Product prod);

    /**
     * Adds the product.
     *
     * @param prod the prod
     */
     void addProduct(final Product prod);

    /**
     * Find by id.
     *
     * @param id the id
     *
     * @return the product
     */
    Product findById(final Integer id);

    /**
     * Delete product.
     *
     * @param id the id
     */
     void deleteProduct(final Integer id);
}
