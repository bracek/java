package stibrik.springapp.dao;

import stibrik.springapp.bean.Product;

import java.util.List;

/**
 * The Interface ProductDao.
 * 
 * @author Ondrej Stibrik
 */
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
 void updateProduct(Product prod);


/**
 * Adds the product.
 * 
 * @param prod the prod
 */
 void addProduct(Product prod);

/**
 * Find by id.
 * 
 * @param id the id
 * 
 * @return the product
 */
 Product findById(Integer id);

/**
 * Delete product.
 * 
 * @param id the id
 */
 void deleteProduct(Integer id);
}
