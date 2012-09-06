package stibrik.springapp.web;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.springframework.ui.ModelMap;

import stibrik.springapp.service.ProductServiceImpl;

/**
 * The Class InventoryManagerControllerTest.
 */
public class InventoryManagerControllerTest {

	// -------------------------------- ATTRS ----------------------------------

	// ----------------------------- CONSTRUCTORS ------------------------------

	// -------------------------------- METHODS --------------------------------
	/**
	 * Sets the up.
	 * 
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
	}

	/**
	 * Test handle.
	 * 
	 * @throws Exception the exception
	 */
	@Test
	public void testHandle() throws Exception{
		/*
		InventoryManagerController controller = new InventoryManagerController();
		controller.setProductManagerService(new ProductServiceImpl());

		ModelMap model = new ModelMap();
		String viewName = controller.handle(model, null, null);
		
		assertEquals("inventory", viewName);
		assertNotNull(model);
					
		assertNull(model.get("products"));
		*/
	}
}
