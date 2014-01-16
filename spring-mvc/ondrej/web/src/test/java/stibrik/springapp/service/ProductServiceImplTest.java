package stibrik.springapp.service;


import org.junit.Test;
import stibrik.springapp.bean.Product;

import java.util.List;

/**
 * 
 * @author Ondrej Stibrik
 * 
 */
public class ProductServiceImplTest {

    // -------------------------------- ATTRS ----------------------------------
    ProductServiceImpl productManagerService;
    private List<Product> products;
    private static int PRODUCT_COUNT = 2;
    private static String CHAIR_DESCRIPTION = "Chair";
    private static Double CHAIR_PRICE = new Double(20.50);
    private static String TABLE_DESCRIPTION = "Table";
    private static Double TABLE_PRICE = new Double(150.10);
    private static int POSITIVE_PRICE_INCREASE = 10;

    // ----------------------------- CONSTRUCTORS ------------------------------

    // -------------------------------- METHODS --------------------------------
    /**
     * @throws java.lang.Exception
     *//*
    @Before
    public void setUp() throws Exception {
    productManagerService = new ProductServiceImpl();

    products = new ArrayList<Product>();
    // stub up a list of products
    Product product = new Product();
    product.setDescription(CHAIR_DESCRIPTION);
    product.setPrice(CHAIR_PRICE);
    products.add(product);

    product = new Product();
    product.setDescription(TABLE_DESCRIPTION);
    product.setPrice(TABLE_PRICE);
    products.add(product);

    productManagerService.setProducts(products);
    }

    @Test
    public void testGetProductsWithNoProducts() {
    productManagerService = new ProductServiceImpl();
    assertNull(productManagerService.getProducts());
    }

    @Test
    public void testGetProducts() {

    List<Product> products = productManagerService.getProducts();
    assertNotNull(products);
    assertEquals(PRODUCT_COUNT, productManagerService.getProducts().size());

    Product product = products.get(0);
    assertEquals(CHAIR_DESCRIPTION, product.getDescription());
    assertEquals(CHAIR_PRICE, product.getPrice());

    product = products.get(1);
    assertEquals(TABLE_DESCRIPTION, product.getDescription());
    assertEquals(TABLE_PRICE, product.getPrice());

    }

    @Test
    public void testIncreasePriceWithNullListOfProducts() {
    try {
    productManagerService = new ProductServiceImpl();
    //productManagerService.increasePrice(POSITIVE_PRICE_INCREASE);

    } catch (NullPointerException ex) {
    fail("Products list is null.");
    }
    }

    @Test
    public void testIncreasePriceWithEmptyListOfProducts() {

    try {
    productManagerService = new ProductServiceImpl();
    //TODO productManagerService.setProducts(new ArrayList<Product>());
    productManagerService.increasePrice(POSITIVE_PRICE_INCREASE);

    } catch (Exception ex) {
    fail("Products list is empty.");
    }
    }
     */

    @Test
    public void testIncreasePriceWithPositivePercentage() {
        /* TODO implement test
        productManagerService.increasePrice(POSITIVE_PRICE_INCREASE);
        double expectedChairPriceWithIncrease = 22.55;
        double expectedTablePriceWithIncrease = 165.11;

        List<Product> products = productManagerService.getProducts();

        Product product = products.get(0);
        assertEquals(expectedChairPriceWithIncrease, product.getPrice());

        product = products.get(1);
        assertEquals(expectedTablePriceWithIncrease, product.getPrice());
         */
    }
}
