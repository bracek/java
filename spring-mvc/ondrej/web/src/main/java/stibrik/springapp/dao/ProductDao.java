package stibrik.springapp.dao;

import java.util.List;

import stibrik.springapp.bean.Product;

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
