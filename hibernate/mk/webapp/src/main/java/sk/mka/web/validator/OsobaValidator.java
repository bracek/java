package sk.mka.web.validator;

import com.ixonos.skillnet.logic.bean.Osoba;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;
/**
 *
 * @author katrami
 */
@Component
public class OsobaValidator implements Validator {

    /** Logger for this class and subclasses */
    protected final Log logger = LogFactory.getLog(getClass());

    public boolean supports(final Class clazz) {
        return Osoba.class.equals(clazz);
    }

    public void validate(final Object obj,
final  Errors e) {
        ValidationUtils.rejectIfEmpty(e, "firstName", "firstName.empty");

//        Person person = (Person) obj;
//        if (!person.getPassword().equals(person.getPassword_repeat())) {
//            e.rejectValue("password", "password and repeated password has to be equal");
//        }
    }
}
