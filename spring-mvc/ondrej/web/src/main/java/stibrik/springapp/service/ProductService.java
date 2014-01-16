package stibrik.springapp.service;

import java.util.List;

import stibrik.springapp.bean.Product;

/**
 * The Interface ProductManager.
 */
public interface ProductService {
	
	/**
	 * Increase price.
	 * 
	 * @param percentage the percentage
	 */
	 void increasePrice(int percentage);
	
	/**
	 * Gets the products.
	 * 
	 * @return the products
	 */
	 List<Product> getProducts();
	
	/**
	 * Save product.
	 * 
	 * @param prod the prod
	 */
	 void saveProduct(Product prod);
	
	/**
	 * Delete product.
	 * 
	 * @param id the id
	 */
	 void deleteProduct(Integer id);
	
	/**
	 * Find by id.
	 * 
	 * @param id the id
	 * 
	 * @return the product
	 */
	 Product findById(Integer id);

}
