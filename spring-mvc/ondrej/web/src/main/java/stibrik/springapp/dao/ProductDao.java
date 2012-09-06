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