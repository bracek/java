package sk.novotnyr.jaxb;

import java.io.Reader;
import java.io.StringReader;
import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author bracek
 */
//class SimpleException extends Exception {
//}
public class MarshalingAnnotation {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PropertyException, JAXBException {

        JAXBContext jaxbContext = JAXBContext.newInstance(Person.class);
        Marshaller marshaller = jaxbContext.createMarshaller();

        Person person = new Person();
        person.setFirstName("Janko");
        person.setLastName("Ždiebik");
        person.setDateOfBirth(new Date(1972, 3, 2));
        person.setWeight(95);

        marshaller.marshal(person, System.out);

        jaxbContext = JAXBContext.newInstance(Person.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();


        String xmlPerson =
                "<?xml version=\"1.0\" encoding=\"windows-1250\" ?>\n" +
                "<person>\n" +
                "    <dateOfBirth>3872-04-02T00:00:00+02:00</dateOfBirth>\n" +
                "    <firstName>Janko</firstName>\n" +
                "    <lastName>Ždiebik</lastName>\n" +
                "    <weight>95.0</weight>\n" +
                "</person>";

        Reader reader = new StringReader(xmlPerson);
        person = (Person) unmarshaller.unmarshal(reader);

        System.out.println(person);
    }
}
