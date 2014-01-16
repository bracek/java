package stibrik.springapp.bean;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

/**
 * The Class ProductTest.
 */
public class ProductTest {

// -------------------------------- ATTRS ----------------------------------
private Product product;

// ----------------------------- CONSTRUCTORS ------------------------------

// -------------------------------- METHODS --------------------------------
/**
 * Sets the up.
 * 
 * @throws Exception the exception
 */
@Before
public void setUp() throws Exception {
product = new Product();
}

/**
 * Test get price.
 */
@Test
public void testSetAndGetPrice() {
String testDescription = "aDescription";
assertNull(product.getDescription());
product.setDescription(testDescription);
assertEquals(testDescription, product.getDescription());
}

/**
 * Test get description.
 */
@Test
public void testSetAndGetDescription() {
double testPrice = 100.00;
assertEquals(0, 0, 0);
product.setPrice(testPrice);
assertEquals(testPrice, product.getPrice(), 0);
}
}
