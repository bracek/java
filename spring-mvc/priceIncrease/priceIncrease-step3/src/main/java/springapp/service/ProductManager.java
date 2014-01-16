package springapp.service;
import java.io.Serializable;
import java.util.List;
import springapp.domain.Product;

public interface ProductManager extends Serializable{
     void increasePrice(int percentage);
     List<Product> getProducts();
}
