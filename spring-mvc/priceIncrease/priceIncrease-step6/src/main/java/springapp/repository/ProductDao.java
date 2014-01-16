package springapp.repository;
import java.util.List;
import springapp.domain.Product;
public interface ProductDao {
     List<Product> getProductList();
     void saveProduct(Product prod);
}
