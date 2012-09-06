package stibrik.springapp.web.dwr;

import javax.annotation.Resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.directwebremoting.annotations.Param;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.directwebremoting.spring.SpringCreator;
import org.springframework.stereotype.Component;

import stibrik.springapp.service.ProductService;

/**
 * The Class DwrProductHandler.
 * 
 * @author Ondrej Stibrik
 */
@Component("dwrProduct")
@RemoteProxy(
	creator = SpringCreator.class,
	creatorParams = {
		@Param(name="beanName", value="dwrProduct"),
		@Param(name="javascript", value="DwrProduct")
		
	},
	name = "dwrProduct"
)
public class DwrProductHandler {

	// -------------------------------- ATTRS ----------------------------------
	/** The logger. */
	private static final Log logger = LogFactory.getLog(DwrProductHandler.class);
	
	/** The product manager service. */
	@Resource
	private ProductService productService;
		
	// ----------------------------- CONSTRUCTORS ------------------------------

	// -------------------------------- METHODS --------------------------------
	/**
	 * Delete product according given parameter. If id is null, zero or negative integer, method does nothing.
	 * 
	 * @param id the id
	 */
	@RemoteMethod
	public void deleteProduct(Integer id) {
		logger.debug("deleteProduct(): Has just been started. Product Id = " + id);
		
		if (id != null && id > 0) {
			productService.deleteProduct(id);
		}
	}
}
