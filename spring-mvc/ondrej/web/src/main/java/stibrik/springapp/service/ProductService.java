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
	public void increasePrice(int percentage);
	
	/**
	 * Gets the products.
	 * 
	 * @return the products
	 */
	public List<Product> getProducts();
	
	/**
	 * Save product.
	 * 
	 * @param prod the prod
	 */
	public void saveProduct(Product prod);
	
	/**
	 * Delete product.
	 * 
	 * @param id the id
	 */
	public void deleteProduct(Integer id);
	
	/**
	 * Find by id.
	 * 
	 * @param id the id
	 * 
	 * @return the product
	 */
	public Product findById(Integer id);

}
