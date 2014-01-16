package stibrik.springapp.web.validation;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
import stibrik.springapp.bean.Product;

/**
 * The Class ProductValidator.
 * 
 * @author Ondrej Stibrik
 */
@Component
public class ProductValidator implements Validator {

// -------------------------------- ATTRS ----------------------------------
/** The logger. */
private static final Log logger = LogFactory.getLog(ProductValidator.class);

// ----------------------------- CONSTRUCTORS ------------------------------

// -------------------------------- METHODS --------------------------------
/* (non-Javadoc)
 * @see org.springframework.validation.Validator#supports(java.lang.Class)
 */
@SuppressWarnings("unchecked")
public boolean supports(final Class clazz) {
return Product.class.isAssignableFrom(clazz);
}

/* (non-Javadoc)
 * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
 */
public void validate(final Object obj,final  Errors errors) {
logger.debug("validate(): Method has just been started.");

Product product = (Product) obj;

ValidationUtils.rejectIfEmpty(errors, "description", "Product.description[not.blank]");
if (product.getDescription() != null && product.getDescription().length()>50) {
errors.rejectValue("description", "Product.description[max.length]", new Object[] { 50 }, "Please enter no more than 50 characters");
}

ValidationUtils.rejectIfEmpty(errors, "price", "Product.price[not.null]");

logger.debug("validate(): Method has finished, errors is " + errors);
}
}
