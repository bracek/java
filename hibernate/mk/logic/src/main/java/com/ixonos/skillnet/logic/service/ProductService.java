
package com.ixonos.skillnet.logic.service;

import com.ixonos.skillnet.logic.bean.Product;
import java.util.List;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author katrami
 */
public interface ProductService {

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    void deleteProduct(Integer id);

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    Product findById(Integer id);

    @Transactional(readOnly = true, propagation = Propagation.REQUIRED)
    List<Product> getProducts();

    void increasePrice(int percentage);

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    void saveProduct(Product prod);

}
