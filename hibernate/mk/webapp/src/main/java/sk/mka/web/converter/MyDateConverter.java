package sk.mka.web.converter;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

/**
 *
 * @author katrami
 */
public class MyDateConverter implements Converter {

    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        String pattern = "dd/MM/yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date nDate;
        try {
            nDate = sdf.parse(value);
        } catch (ParseException ex) {
            FacesMessage message = new FacesMessage();
            message.setDetail("Date is missing or not valid");
            message.setSummary("Date is missing or not valid");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(message);
        }
        if (nDate.getTime() > new Date().getTime()) {
            FacesMessage message = new FacesMessage();
            message.setDetail("Date is bigger than current date");
            message.setSummary("Date is bigger than current date");
            message.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ConverterException(message);
        }
        return nDate;

    }

    public String getAsString(FacesContext context, UIComponent component, Object value) {
        return value.toString();

    }
}
