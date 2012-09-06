package sk.novotnyr.jaxb;

import java.io.StringReader;
import java.util.Date;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

/**
 *
 * @author bracek
 */
class SimpleException extends Exception {
}

public class Marshaling {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws PropertyException, JAXBException {

        // vytvoríme kontext JAXB
        JAXBContext jaxbContext = null;
        Marshaller marshaller = null;

        jaxbContext = JAXBContext.newInstance(Person.class);

        marshaller = jaxbContext.createMarshaller();

        Person person = new Person();
        person.setFirstName("Janko");
        person.setLastName("Ždiebik");
        person.setDateOfBirth(new Date(1972, 3, 2));
        person.setWeight(95);


        JAXBElement<Person> personElement = new JAXBElement<Person>(new QName("person"),
                Person.class,
                person);

        marshaller.setProperty(Marshaller.JAXB_ENCODING, "windows-1250");
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(personElement, System.out);


        // deserializacia

        jaxbContext = JAXBContext.newInstance(Person.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();

//        String xmlPerson =
//                "<?xml version=\"1.0\" encoding=\"windows-1250\" ?>\n" +
//                "<person>\n" +
//                "    <dateOfBirth>3872-04-02T00:00:00+02:00</dateOfBirth>\n" +
//                "    <firstName>Janko</firstName>\n" +
//                "    <lastName>Ždiebik</lastName>\n" +
//                "    <weight>95.0</weight>\n" +
//                "</person>";
//        Object o = unmarshaller.unmarshal(new StringReader(xmlPerson));
//        Source source = new StreamSource(new StringReader(xmlPerson));
//        personElement = unmarshaller.unmarshal(source, Person.class);
//
//        person = personElement.getValue();
//        System.out.println(person);

    }
}
