package lab1;

import java.util.ArrayList;
import java.util.List;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class MyHandler extends DefaultHandler {

    private List<Employee> empList = null;
    private Employee emp = null;
    private StringBuilder data = null;

    public List<Employee> getEmpList() {
        return empList;
    }

    boolean bFirstName = false;
    boolean bLastName = false;
    boolean bLocation = false;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {

        if (qName.equalsIgnoreCase("Employee")) {
            // create a new Employee and put it in Map
            String id = attributes.getValue("id");
            // initialize Employee object and set id attribute
            emp = new Employee();
            emp.setId(Integer.parseInt(id));
            // initialize list
            if (empList == null)
                empList = new ArrayList<>();
        } else if (qName.equalsIgnoreCase("FirstName")) {
            // set boolean values for fields, will be used in setting Employee variables
            bFirstName = true;
        } else if (qName.equalsIgnoreCase("LastName")) {
            bLastName = true;
        } else if (qName.equalsIgnoreCase("Location")) {
            bLocation = true;
        }
        // create the data container
        data = new StringBuilder();
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (bFirstName) {
            // age element, set Employee age
            emp.setFirstName(data.toString());
            bFirstName = false;
        } else if (bLastName) {
            emp.setLastName(data.toString());
            bLastName = false;
        } else if (bLocation) {
            emp.setLocation(data.toString());
            bLocation = false;
        }

        if (qName.equalsIgnoreCase("Employee")) {
            // add Employee object to list
            empList.add(emp);
        }
    }

    @Override
    public void characters(char ch[], int start, int length) throws SAXException {
        data.append(new String(ch, start, length));
    }

}
