package com.ixonos.skillnet.logic.service.impl;

import com.ixonos.skillnet.logic.service.ProductService;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.ixonos.skillnet.logic.bean.Product;
import com.ixonos.skillnet.logic.dao.ProductDao;

/**
 * The Class ProductServiceImpl.
 * 
 * @author Ondrej Stibrik
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

    // -------------------------------- ATTRS ----------------------------------
    /** The logger. */
    private static final Log logger = LogFactory.getLog(ProductServiceImpl.class);
    /** The product dao. */
    @Resource
    private ProductDao productDao;
    /** The products. */
    private List<Product> products;

    // ----------------------------- CONSTRUCTORS ------------------------------
    // -------------------------------- METHODS --------------------------------
	/*
     * (non-Javadoc)
     *
     * @see stibrik.springapp.service.ProductManager#getProducts()
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public List<Product> getProducts() {
        logger.debug("getProducts(): Method has just been started.");
        return productDao.getProductList();
    }

    /*
     * (non-Javadoc)
     *
     * @see stibrik.springapp.service.ProductManager#increasePrice(int)
     */
    public void increasePrice(final int percentage) {
        if (products != null) {
            for (Product product : products) {
                Double newPrice = product.getPrice() * (100 + percentage) / 100;
                product.setPrice(newPrice);
            }
        }
    }

    /* (non-Javadoc)
     * @see stibrik.springapp.service.ProductService#saveProduct(stibrik.springapp.bean.Product)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void saveProduct(final Product prod) {
        logger.debug("saveProduct(): Method has just been started.");
        if (prod != null) {
            if (prod.getId() == null) {
                logger.debug("saveProduct(): Product id not found, adding new record.");
                productDao.addProduct(prod);

            } else {
                logger.debug("saveProduct(): Product id found, updating record with id=" + prod.getId());
                productDao.updateProduct(prod);
            }
            logger.debug("saveProduct(): Product is stored.");
        }
    }

    /* (non-Javadoc)
     * @see stibrik.springapp.service.ProductService#deleteProduct(java.lang.Integer)
     */
    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void deleteProduct(final Integer id) {
        logger.debug("deleteProduct(): Method has just been started. Deleteing product with id = " + id);
        if (id != null) {
            productDao.deleteProduct(id);
        }
    }

    /* (non-Javadoc)
     * @see stibrik.springapp.service.ProductService#findById(java.lang.Integer)
     */
    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    public Product findById(final Integer id) {
        logger.debug("findById(): Method has just been started.");
        return productDao.findById(id);
    }
}
